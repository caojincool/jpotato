package com.lemsun.websocket;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.lemsun.core.Host;
import com.lemsun.core.jackson.JsonObjectMapper;

import java.io.IOException;

/**
 * User: 宗旭东
 * Date: 13-2-28
 * Time: 上午9:59
 */
public abstract class AbstractSocketResponseCommand implements ISocketResponseCommand {

    private String targetId;
    private String command;
    private String contextType;
    private String context;

    protected AbstractSocketResponseCommand(String targetId, String command) {
        this.targetId = targetId;
        this.command = command;
        contextType = "json/text";
    }

    @Override
    public void setTargetId(String id) {
        targetId = id;
    }

    @Override
    public void setContextType(String contextType) {
        this.contextType = contextType;
    }

    @Override
    public String getTargetId() {
        return targetId;
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
    public void setContext(String context) {
        this.context = context;
    }


    @Override
    public void execute() {
        StringBuilder builder = new StringBuilder(getHeader());
        builder.append(executeContext());
        setContext(builder.toString());
    }

    /**
     * 执行获取内容
     */
    public abstract String executeContext();


    @Override
    public String getHeader() {
        StringBuilder builder = new StringBuilder(ISocketResponseCommand.ID).append(":").append(getTargetId()).append("\n");
        builder.append(ISocketResponseCommand.COMMAND).append(":").append(getCommand()).append("\n");
        builder.append(ISocketResponseCommand.CONTEXTTYPE).append(":").append(getContextType()).append("\n");
        builder.append(ISocketResponseCommand.BODY).append(":").append("\n");
        return builder.toString();
    }


    /**
     * 将传入的对象转换成JSON字符串
     * @param obj 传入的对象
     * @return 返回 JSON 字符串
     * @throws IOException
     */
    protected String toJson(Object obj) throws IOException {

        return Host.getInstance().getBean(JsonObjectMapper.class).writeValueAsString(obj);

    }

}
