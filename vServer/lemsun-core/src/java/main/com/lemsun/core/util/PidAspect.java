package com.lemsun.core.util;

import com.lemsun.core.Host;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.ResourceException;
import com.lemsun.core.service.IResourceService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 执行对 PID 的检查
 */
@Aspect
@Component
public class PidAspect {


    private static final Logger log = LoggerFactory.getLogger(PidAspect.class);
    private IResourceService resourceService;


    public PidAspect() {
    }


    @Around(value = "execution(public * com.lemsun..*.*(..,String,..)) && @annotation(atr) && args(pid)")
    public Object beforePidAdvice(ProceedingJoinPoint point, Pid atr, String pid) throws Throwable {

        if(log.isDebugEnabled())
            log.debug("拦截到一个方法将要执行 组件的获取工作. 类名称: {}, 方法名称: {}", point.getStaticPart().toLongString(), point.getSignature().getName());

        try
        {

            Object[] args = point.getArgs();

            if(!ResourceUtil.isRecourcePid(pid)) {

                if(resourceService == null) resourceService = Host.getInstance().getContext().getBean(IResourceService.class);

                LemsunResource baseResource = resourceService.getByBusinessCode(pid);
                if(baseResource == null)
                    throw ResourceException.ResourcePidisNull;

                for(int i=0; i<args.length; i++) {
                    if(pid == args[i]) {
                        args[i] = baseResource.getPid();
                    }
                }

            }

            Object result = point.proceed(args);

            return result;
        }
        catch (Throwable tr) {
            throw tr;
        }


    }

}
