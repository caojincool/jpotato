package com.lemsun.form.repository;


import com.lemsun.core.AbstractLemsunResource;
import com.lemsun.core.repository.AbstractLocalFsRepository;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.core.util.ResourceUtil;
import com.lemsun.form.FormResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.stereotype.Repository;


/**
 * 单据资源操作
 * User: Xudong
 * Date: 12-10-24
 * Time: 下午6:40
 */
@Repository
public class FormResourceRepository extends AbstractLocalFsRepository {


    private ResourceRepository resourceRepository;

    private static final Logger log = LoggerFactory.getLogger(FormResourceRepository.class);


    @Autowired
    public FormResourceRepository(ResourceRepository resourceRepository,
                                  GridFsOperations gridFsOperations,
                                  MongoTemplate template) {
        super(template, gridFsOperations);
        this.resourceRepository = resourceRepository;
    }

    /**
     * 根据组件编码以及组件类型获取一个单据的子类 例如web页面信息
     *
     * @param pid  单据ID
     * @param type 单据的子类例如
     * @param <T>  WebPageResource
     * @return 单据子类的详细信息
     */
    public <T extends AbstractLemsunResource> T get(String pid, Class<T> type) {
        return resourceRepository.get(pid, type);
    }

    /**
     * 创建一个单据组件
     *
     * @param form 单据组件
     * @throws Exception
     */
    public void create(FormResource form) throws Exception {
        resourceRepository.create(form);
    }

    /**
     * 更新一个表单组件信息
     *
     * @param formResource 表单组件信息
     * @throws Exception 没有组件的OID将抛出异常
     */
    public void update(AbstractLemsunResource formResource) throws Exception {
        resourceRepository.update(formResource);
    }

    /**
     * 更新一个表单组件的内容信息
     *
     * @param formResource 表单组件
     * @param context      表单组件的内容信息
     */
    public void update(FormResource formResource, String context) {
        resourceRepository.getAttachFileRepository().updateContent(formResource.getPid(), context);
    }

    /**
     * 移除一个表单组件
     * 和与之相关的组件附件文件
     *
     * @param formResource 表单组件
     */
    public void remove(FormResource formResource) {
        resourceRepository.remove(formResource.getPid());
    }

    /**
     * 获取表单组件内容
     * @param formResource 表单组件
     * @return 表单组件内容 如果表单组件的pid不符合ResourceUtil的isRecourcePid方法返回null
     */
    public String getContext(AbstractLemsunResource formResource) {
        if (!ResourceUtil.isRecourcePid(formResource.getPid()))
            return null;

        return resourceRepository.getAttachFileRepository().getContent(formResource.getPid());
    }
}
