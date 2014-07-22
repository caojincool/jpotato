package com.lemsun.core;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Order;
import org.springframework.data.mongodb.core.query.Query;

/**
 * User: Xudong
 * Date: 12-12-10
 * Time: 下午1:48
 * 抽象的封装分页请求对象. 如果页面数据 PageSize 大于 0 , 那么就进行分页. 否则按一页的方式加载
 */
public abstract class AbstractPageRequest implements IPageQuery {
    private int pageNumber;
    private int pageSize;
    private int offset;
    private Sort soft;
    private boolean dataCount;


    public boolean getDataCount() {
        return dataCount;
    }

    public void setDataCount(boolean dataCount) {
        this.dataCount = dataCount;
    }

    /**
     * 创建使用与Spring MongoDB 分页的查询对象
     *
     * @return 查询对象
     */
    public Query createQuery() {
        Query query = new Query();

        if (getPageSize() > 0)
            query.limit(getPageSize()).skip(getOffset());

        if (soft != null) {
            for (Sort.Order o : soft) {
                query.sort().on(
                        o.getProperty(),
                        o.getDirection() == Sort.Direction.ASC ? Order.ASCENDING : Order.DESCENDING
                );
            }
        }

        return query;
    }

    /**
     * 设置的第几页的数据
     *
     * @param pageNumber 数量
     */
    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    /**
     * 设置页面的显示数据条数
     *
     * @param pageSize 显示数量
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 设置页面的移动数
     *
     * @param offset 移动数量
     */
    public void setOffset(int offset) {
        this.offset = offset;
    }

    /**
     * 设置页面的排序
     *
     * @param soft 排序对象
     */
    public void setSoft(Sort soft) {
        this.soft = soft;
    }

    @Override
    public int getPageNumber() {
        return pageNumber;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getOffset() {
        return ((pageNumber > 0 ? pageNumber : 1) - 1) * pageSize;
    }

    @Override
    public Sort getSort() {
        return soft;
    }

}
