package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.model.ScriptResource;
import com.lemsun.client.core.service.*;
import org.apache.commons.lang3.StringUtils;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.ScriptableObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import sun.org.mozilla.javascript.internal.Scriptable;

import java.util.List;

/**
 * 视图组件执行引擎
 * User: xudong
 * Date: 13-12-12
 * Time: 下午5:31
 */
@Service
public class LmsViewEngine implements IViewEngine, DisposableBean, InitializingBean {


    //是否使用动态作用域
    static boolean useDynamicScope;
    private static final Logger log = LoggerFactory.getLogger(LmsViewEngine.class);

    /**
     * 自定义工厂
     */
    static class MyFactory extends ContextFactory {
        @Override
        protected boolean hasFeature(Context cx, int featureIndex)
        {
            if (featureIndex == Context.FEATURE_DYNAMIC_SCOPE) {
                return useDynamicScope;
            }
            return super.hasFeature(cx, featureIndex);
        }
    }

    static {
        ContextFactory.initGlobal(new MyFactory());
    }

    private Context context;
    private ScriptableObject scope;
    private ApplicationContext applicationContext;
    private IResourceService resourceService;
    private IScriptService scriptService;

    /**
     * 初始化参数
     */
    @Autowired
    public LmsViewEngine(ApplicationContext context,
                        IResourceService resourceService,
                        IScriptService scriptService
    )
    {
        this.applicationContext = context;
        useDynamicScope = true;
        this.resourceService = resourceService;
        this.scriptService=scriptService;
    }




    /**
     * 初始化全部的脚本类型
     */
    public void init(){
        log.debug("加载全局脚本和网页脚本");

        context = Context.enter();
        scope = context.initStandardObjects(null, true);

        //初始化共享参数
        Host host = Host.getInstance();
        ScriptableObject.putProperty(scope, "host", host);
        ScriptableObject.putProperty(scope, "plateform", host.getPlateform());
        ScriptableObject.putProperty(scope, "context", applicationContext);
        ScriptableObject.putProperty(scope, "resourceService", applicationContext.getBean(IResourceService.class));
        ScriptableObject.putProperty(scope, "accountService", applicationContext.getBean(IAccountService.class));
        ScriptableObject.putProperty(scope, "sqlRunnerService", applicationContext.getBean(ISqlRunnerService.class));
        ScriptableObject.putProperty(scope, "lmsViewService", applicationContext.getBean(ILmsViewService.class));
        ScriptableObject.putProperty(scope, "remoteService", applicationContext.getBean(IRemoteService.class));
        ScriptableObject.putProperty(scope, "formulaService", applicationContext.getBean(IFormulaService.class));

        try
        {
            //获取所有的脚本组件
            List<ScriptResource> res = scriptService.getGlobelScriptResources();
            if(!CollectionUtils.isEmpty(res)) {
                for(ScriptResource r : res) {
                    runGlobelScript(r);
                }
            }

        }
        catch (Exception ex) {
            log.error(ex.getMessage());
        }


    }



    private void runGlobelScript(ScriptResource resource) {
        try
        {
            String script = resource.toScript();

            if(StringUtils.isEmpty(script))
                return;
            if(log.isDebugEnabled())
                log.debug("开始初始化脚本, 组件:{}, 名称:{}", resource.getPid(), resource.getName());
                log.debug(script);
            context.evaluateString(scope, script, resource.getName(), 1, null);
            if(log.isDebugEnabled())

                log.debug("----->  初始化完成");
        }
        catch (Exception ex) {
            log.error("在全局脚本组件 ({}), 初始化脚本异常: \n {}", resource.getPid(), ex.getMessage());
        }
    }

    /**
     * @return 返回共享环境的内容
     */
    @Override
    public Context getContext() {
        return context;
    }


    @Override
    public ScriptableObject getGlobelScriptable() {
        return scope;
    }

    @Override
    public void destroy() throws Exception {
        try
        {
            Context.exit();
        }
        catch (Exception ex)
        {

        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
