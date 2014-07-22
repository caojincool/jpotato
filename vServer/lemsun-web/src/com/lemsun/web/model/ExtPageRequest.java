package com.lemsun.web.model;

import com.lemsun.core.AbstractPageRequest;

/**
 * 对 Ext 的
 * User: zongxudong
 * Date: 12-10-26
 * Time: 上午9:27
 */
public class ExtPageRequest extends AbstractPageRequest{

    public int getLimit() {
		return getPageSize();
	}

	public int getPage(){
		return getPageNumber();
	}

	public void setPage(int page){
        if (page==0)
            setPageNumber(1);
		setPageNumber(page);
	}

	public void setLimit(int limit) {

		setPageSize(limit);
	}

	public int getStart() {
		return getOffset();
	}

	public void setStart(int start) {
		setOffset(start);
	}

}
