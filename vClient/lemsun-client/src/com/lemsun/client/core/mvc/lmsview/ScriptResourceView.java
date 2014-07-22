package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.IWebViewResource;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Script;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 渲染组件中带有脚本信息的视图
 * User: xudong
 * Date: 13-12-11
 * Time: 下午4:18
 */
public class ScriptResourceView extends AbstractLmsView {

    private Script contextScript;
    private static final Logger log = LoggerFactory.getLogger(ScriptResourceView.class);

    public ScriptResourceView(IWebViewResource resource, String contextScript) {
        super(resource);

        initContext(contextScript);
    }

    protected void initContext(String contextfScript) {

        Context.enter();

        Context cx = getViewEngine().getContext();


        try
        {
            contextScript = cx.compileString(contextfScript, getResource().getName() + "组件内容", 1, null);
        }
        catch (Exception ex) {
            getErrors().add("脚本组件内容编译异常: \n" + ex.getMessage());
        }

        Context.exit();

    }

    @Override
    public void executeContext(ViewScope scope) {

        if(contextScript != null)
        {
            Context.enter();

            try
            {
                contextScript.exec(scope.getEngine().getContext(), scope.getLocalScope());
                if(log.isDebugEnabled())
                    log.debug("已经执行显示脚本内容:\n" +scope.getEngine().getContext());
            }
            catch (Exception ex) {
                getErrors().add("组件内容运行异常:\n" + ex.getMessage());
                if(log.isDebugEnabled())
                    log.debug("组件内容运行异常:\n" + ex.getMessage());
            }

            Context.exit();
        }


    }
}
