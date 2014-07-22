package com.lemsun.form.repository;

import com.lemsun.core.*;
import com.lemsun.core.repository.AbstractLocalFsRepository;
import com.lemsun.core.repository.AbstractRepositorySupport;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.form.WebPageParam;
import com.lemsun.form.WebPageResource;
import com.mongodb.BasicDBList;
import com.mongodb.DBObject;
import com.mongodb.MongoException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.DocumentCallbackHandler;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

/**
 * web页面数据库操作层
 * User: dpyang
 * Date: 13-3-21
 * Time: 上午11:23
 */
@Repository
public class WebPageResourceRepository extends AbstractRepositorySupport<WebPageResource> {

    private static final Logger log = LoggerFactory.getLogger(WebPageResourceRepository.class);

    @Autowired
    protected WebPageResourceRepository(MongoTemplate template,
                                        GridFsOperations gridFsOperations,
                                        ResourceRepository resourceRepository) {
        super(template, gridFsOperations, resourceRepository);
    }

    /**
     * 获取模板组件
     */
    public List<LemsunResource> getTempleResource(String pid) {

        //返回是模板的组件列表
        return getTemplate().find(query(where("template").is(true).and("pid").ne(pid)), LemsunResource.class);
    }

    /**
     * 分页获取模板组件
     */
    public Page<LemsunResource> getTempleResource(IPageQuery request, Collection<String> categorys, String name) {
        if (StringUtils.isEmpty(name)) name = ".*";

        Criteria w = where("name").regex(name).and("template").is(true);

        if (!CollectionUtils.isEmpty(categorys)) {
            w.and("category").in(categorys);
        }
        Query query = query(w).limit(request.getPageSize()).skip(request.getOffset());

        org.springframework.data.mongodb.core.query.Sort sort = query.sort();

        if (request.getSort() != null)
            for (Sort.Order o : request.getSort()) {
                sort.on(o.getProperty(), o.isAscending() ? org.springframework.data.mongodb.core.query.Order.ASCENDING
                        : org.springframework.data.mongodb.core.query.Order.DESCENDING);
            }
        long total = getTemplate().count(query, ResourceRepository.ResourceCollectionName);

        List<LemsunResource> data = getTemplate().find(query, LemsunResource.class, ResourceRepository.ResourceCollectionName);

        Page<LemsunResource> page = new PageImpl<LemsunResource>(data, request, total);

        return page;
    }

    /**
     * 获取一个给定的组件对象的页面启动参数. 并且递归查找父组件的启动参数. 如果有需要覆盖的会进行覆盖. <br/>
     * 例如子组件的启动参数, 重复定义了父组件的启动参数. 子组件将会覆盖父组件的启动参数
     *
     * @param pid
     * @return
     */
    public List<WebPageParam> getStartParams(String pid) {
        final HashMap<String, WebPageParam> params = new HashMap<>();

        Query query = new Query(where("pid").is(pid));
        query.fields().include("startParam").include("parentPid");
        final MongoConverter converter = getTemplate().getConverter();

        this.getTemplate().executeQuery(query, ResourceRepository.ResourceCollectionName, new DocumentCallbackHandler() {
            @Override
            public void processDocument(DBObject dbObject) throws MongoException, DataAccessException {
                String parentPid = (String) dbObject.get("parentPid");

                BasicDBList param = (BasicDBList) dbObject.get("startParam");

                if (param != null) {

                    for (Object p : param) {
                        WebPageParam temp = converter.read(WebPageParam.class, (DBObject) p);
                        params.put(temp.getName(), temp);
                    }

                }

                if (StringUtils.isNotEmpty(parentPid)) {
                    List<WebPageParam> temp = getStartParams(parentPid);
                    for (WebPageParam p : temp) {
                        if (!params.containsKey(p.getName())) {
                            params.put(p.getName(), p);
                        }
                    }
                }
            }
        });

        return new ArrayList<>(params.values());
    }

}
