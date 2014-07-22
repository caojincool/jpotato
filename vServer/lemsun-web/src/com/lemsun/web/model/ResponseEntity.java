package com.lemsun.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.jackson.JsonObjectMapper;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;

/**
 * 返回对象模型
 * User: Xudong
 * Date: 12-11-2
 * Time: 下午4:30
 */
public class ResponseEntity<T> implements View {

	private boolean success;
	private String code;
	private String message;
	private long totalCount;
    private long   iTotalRecords;
    private long   iTotalDisplayRecords;

	private T entity;

    public long getiTotalRecords() {
        return totalCount;
    }

    public long getiTotalDisplayRecords() {
        return totalCount;
    }

    public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}

	public ResponseEntity(T entity)
	{
		success = true;
		setEntity(entity);
	}

	public ResponseEntity()
	{

	}

    public ResponseEntity(boolean state)
    {
        this.setSuccess(state);
    }

    public ResponseEntity(boolean  state,String message)
    {
        this.setSuccess(state);
        this.setMessage(message);
    }

    /**
     * 获取执行是否成功
     */
	public boolean isSuccess() {
		return success;
	}

    /**
     * 设置执行是否成功
     */
	public void setSuccess(boolean success) {
		this.success = success;
	}

    /**
     * 获取执行代码
     */
	public String getCode() {
		return code;
	}

    /**
     * 设置执行代码
     */
	public void setCode(String code) {
		this.code = code;
	}

    /**
     * 获取返回信息
     */
	public String getMessage() {
		return message;
	}

    /**
     * 设置返回信息
     */
	public void setMessage(String message) {
		this.message = message;
	}

    /**
     * 获取携带参数
     */
	public T getEntity() {
		return entity;
	}

    /**
     * 设置携带参数
     */
	public void setEntity(T entity) {
		this.entity = entity;
	}

    /**
     * 获取HTTP内容类型
     */
    @Override
    @JsonIgnore
    public String getContentType() {
        return "text/json";
    }

    @Override
    public void render(Map<String, ?> stringMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        JsonObjectMapper mapper = SpringContextUtil.getBean(JsonObjectMapper.class);
        httpServletResponse.setContentType(getContentType());
        httpServletResponse.setCharacterEncoding("UTF-8");
        Writer writer = httpServletResponse.getWriter();
        mapper.writeValue(writer, this);
        writer.flush();
    }
}
