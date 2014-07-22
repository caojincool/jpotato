package com.lemsun.websocket;

import com.lemsun.core.Host;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;
import java.util.HashMap;
import java.util.Map;

/**
 * 接收命令编码, 将接收到的文字信息. 转换成命令对象
 * User: xudong
 * Date: 13-11-29
 * Time: 下午2:51
 */
public class CommandDecoder implements Decoder.Text<ISocketCommand> {

    private static final Logger log = LoggerFactory.getLogger(CommandDecoder.class);
    private ICommandFactory commandFactory;

    @Override
    public ISocketCommand decode(String s) throws DecodeException {


        StringBuilder key = new StringBuilder();
        StringBuilder value = new StringBuilder();
        boolean isKey = true;
        HashMap<String, String> headers = new HashMap<>();

        char[] chars = s.toCharArray();
        for(int i=0; i<chars.length; i++)
        {
            char c = chars[i];


            if(c == ':') {
                isKey = false;
            } else if(c == '\n') {

                String name = key.toString().trim();

                if(StringUtils.equalsIgnoreCase(name, ISocketCommand.BODY)) {

                    String context = new String(ArrayUtils.subarray(chars, i+1, chars.length));

                    try
                    {
                        ISocketCommand command = createSocketCommand(headers, context);
                        return command;
                    }
                    catch (WebsocketException ex)
                    {

                    }


                }

                String v = value.toString();

                setCommandProperty(headers, name, v);

                isKey = true;
                key = new StringBuilder();
                value = new StringBuilder();
            } else {
                if(isKey) {
                    key.append(c);
                }
                else {
                    value.append(c);
                }
            }

        }


        return null;
    }

    @Override
    public boolean willDecode(String s) {
        return s.startsWith("Id:");
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
        commandFactory = Host.getInstance().getContext().getBean(ICommandFactory.class);
    }

    @Override
    public void destroy() {

    }


    private static void setCommandProperty(Map<String, String> headers, String key, String value) throws WebsocketException {

        if(StringUtils.isEmpty(key))
            throw WebsocketException.DecodingException;

        String name = key.trim().toUpperCase();
        String v = StringUtils.isEmpty(value) ? null : value.trim();

        if(headers.containsKey(name)) headers.remove(name);
        headers.put(name, v);

    }

    private ISocketCommand createSocketCommand(Map<String, String> headers, String context) throws WebsocketException {
        if(!headers.containsKey(ISocketCommand.COMMAND)) {
            throw new WebsocketException("协议头中必须标记 Command 命令内容", WebsocketException.HeaderException);
        }

        String command = headers.get(ISocketCommand.COMMAND);

        ISocketCommand c = commandFactory.createSocketCommand(command);

        if(c == null) {
            throw new WebsocketException("没有找到对应的命令对象", WebsocketException.HeaderException);
        }

        c.setHeader(headers);
        c.setContext(context);
        return c;
    }

}
