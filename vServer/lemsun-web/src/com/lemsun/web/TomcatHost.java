package com.lemsun.web;

import com.lemsun.core.Host;
import org.apache.catalina.Context;
import org.apache.catalina.Wrapper;


/**
 * User: 宗旭东
 * Date: 13-9-23
 * Time: 上午11:27
 */
public class TomcatHost extends Host {

    private Wrapper wrapper;
    private Context context;

    public TomcatHost() {

    }

    /**
     * 设置环境的对象的包装对象
     * @param wrapper
     */
    public void setWrapper(Wrapper wrapper)
    {
        this.wrapper = wrapper;
        context = (Context) this.wrapper.getParent();
    }


    @Override
    public void restart() {
        context.reload();
    }



    @Override
    public void stop() {

    }


}
