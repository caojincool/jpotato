package com.lemsun.websocket;

import com.fasterxml.jackson.core.JsonParseException;
import com.lemsun.core.IResponseSenter;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.jackson.JsonObjectMapper;

import java.io.IOException;
import java.nio.Buffer;
import java.nio.CharBuffer;
import java.util.Map;

/**
 * 实现命令对象基本的工作,
 * 命令对象必须以命令的名称注册到私有的容器中. 这样命令工厂才能创建命令对象. 命令对象执行后如果有返回的命令.
 * 那么就需要发送执行完成的命令对象
 * User: 宗旭东
 * Date: 13-2-26
 * Time: 上午9:33
 */
public abstract class AbstractSocketCommand implements ISocketCommand {

    private String context;
    private String id;
    private String command;
    private String contextType;
    private Map<String, String> header;
    private IResponseSenter responseSenter;


    private Object paramObject;

    @Override
    public void setContext(String context) {
        this.context = context;
    }

    @Override
    public void setHeader(Map<String, String> header) throws WebsocketException {

        if(header == null || header.size() == 0)
            throw new WebsocketException("表头不能为空, 或者没有表头信息", WebsocketException.HeaderException);

        if(!header.containsKey(ISocketCommand.ID))
            throw new WebsocketException("头部信息的ID 不能为空", WebsocketException.HeaderException);

        if(!header.containsKey(ISocketCommand.COMMAND))
            throw new WebsocketException("头部的命令信息不能为空", WebsocketException.HeaderException);

        id = header.get(ISocketCommand.ID);

        contextType = header.containsKey(ISocketCommand.CONTEXTTYPE) ? header.get(ISocketCommand.CONTEXTTYPE) : "";

        command = header.get(ISocketCommand.COMMAND);

        this.header = header;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public String getContextType() {
        return contextType;
    }

    @Override
    public String getContext() {
        return context;
    }

    @Override
    public IResponseSenter getResponseSenter() {
        return responseSenter;
    }

    @Override
    public void setResponseSenter(IResponseSenter responseSenter) {
        this.responseSenter = responseSenter;
    }

    public Map<String, String> getHeader() {
        return header;
    }


    public <T> T getJsonParam(Class<T> paramType) throws IOException {

        if(paramObject != null)
            return (T) paramObject;

        JsonObjectMapper mapper = SpringContextUtil.getBean(JsonObjectMapper.class);


       paramObject = mapper.readValue(getContext(), paramType);

       return (T) paramObject;

    }
}
