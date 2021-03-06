package com.lemsun.websocket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * 默认的用户请求命令创建对象. 主要识别用户请求头部信息, 来创建对象.
 * 在对象的寻找机制中. 先在Spring容器中寻找对应的命令对象. 如果没有找到. 就去脚本引擎中查找对应的脚本. 查找完成后抛出 MessageEvent 对象
 * User: 宗旭东
 * Date: 13-2-26
 * Time: 上午9:03
 */
public class DefaultCommandFactory implements ICommandFactory, ApplicationContextAware {

    private ApplicationContext context;

    private String[] resources;

    public DefaultCommandFactory() {

    }

    public String[] getResources() {
        return resources;
    }

    public void setResources(String[] resources) {
        this.resources = resources;
    }

    @Override
    public ISocketCommand createSocketCommand(String command) {


        ISocketCommand excute = context.getBean(command.toLowerCase(), ISocketCommand.class);

        if(excute == null) {

        }

        return excute;
    }

    /**
     * 加载命令容器的子节点
     * @param applicationContext 全局对象容器
     * @throws org.springframework.beans.BeansException 加载异常
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        String[] rs;

        if(resources == null) {
            rs = new String[1];
        }
        else
        {
            rs = new String[resources.length + 1];
            System.arraycopy(resources, 0, rs, 1, resources.length);
        }

        rs[0] = "websocket-commands.xml";

        GenericXmlApplicationContext temp = new GenericXmlApplicationContext(rs);
        temp.setParent(applicationContext);

        context = temp;
    }
}
