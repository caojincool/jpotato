package com.dp.baobao.model;

import com.dp.baobao.util.JsonObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.servlet.View;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Writer;
import java.util.Map;

/**
 * Created by dpyang on 2014/11/14.
 */
public class ResponseEntity<T> implements View {
    private boolean success;
    private String message;
    private int total;
    private T rows;


    /**
     * 默认构造函数
     */
    public ResponseEntity(){

    }

    /**
     * 消息提示构造函数
     * @param success 处理结果
     * @param message 问题描述
     */
    public ResponseEntity(boolean success,String message){
        this.success=success;
        this.message=message;
    }

    /**
     * 返回结果对象
     * @param success 处理结果
     * @param total 总行数
     * @param rows 每个对象
     */
    public ResponseEntity(boolean success,int total,T rows){
        this(success,"OK");
        this.total=total;
        this.rows=rows;
    }

    /**
     * 处理结果
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * 处理结果
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 结果对象
     */
    public T getRows() {
        return rows;
    }

    /**
     * 结果对象
     */
    public void setRows(T rows) {
        this.rows = rows;
    }

    /**
     * 影响行数
     */
    public int getTotal() {
        return total;
    }

    /**
     * 影响行数
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 提示消息
     */
    public String getMessage() {
        return message;
    }

    /**
     * 提示消息
     */
    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    @JsonIgnore
    public String getContentType() {
        return "text/json";
    }

    @Override
    public void render(Map<String, ?> stringMap, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        JsonObjectMapper mapper=new JsonObjectMapper();
        httpServletResponse.setContentType(getContentType());
        httpServletResponse.setCharacterEncoding("UTF-8");
        Writer writer = httpServletResponse.getWriter();
        mapper.writeValue(writer, this);
        writer.flush();
    }
}
