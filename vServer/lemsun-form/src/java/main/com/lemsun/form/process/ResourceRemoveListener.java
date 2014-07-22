package com.lemsun.form.process;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.events.ResourceEvent;

import com.lemsun.core.service.IResourceService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.IDbService;
import com.lemsun.data.service.IFileResourceService;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.form.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * 网页和程序组件可能有子组件 本类功能就是做递归删除子组件
 * User: 刘晓宝
 * Date: 13-10-30
 * Time: 上午10:45
 */
@Service
public class ResourceRemoveListener  implements ApplicationListener<ResourceEvent> {

    private static Logger log = LoggerFactory.getLogger(ResourceRemoveListener.class);


    public ResourceRemoveListener() {
    }

    /**
     * 如果删除对象是数据库配置才执行
     * @param resourceEvent
     */
    @Override
    public void onApplicationEvent(ResourceEvent resourceEvent) {
        if (resourceEvent.getResource()==null) throw new RuntimeException("删除的组件不能为空");
        if (!(resourceEvent.getResource() instanceof DbConfigResource)&& resourceEvent.getAction()== ResourceEvent.Action.BefreDelete){
            try {
                IResourceService resourceService= SpringContextUtil.getBean(IResourceService.class);
                IResource resource=resourceEvent.getResource();
                List<LemsunResource>  resources=resourceService.getChildResourceByParentId(resource.getPid());

                for(LemsunResource lemsunResource:resources){

                    if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.WEB_SKIN.getCategory()) ){
                        IWebPageResourceService webPageResourceService= SpringContextUtil.getBean(IWebPageResourceService.class);
                        webPageResourceService.delete(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.WPF_SKIN.getCategory())){
                        IWpfPageResourceService wpfPageResourceService= SpringContextUtil.getBean(IWpfPageResourceService.class);
                        wpfPageResourceService.delete(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.WEB_SCRIPT.getCategory())){
                        IWebScriptResourceService webScriptResourceService= SpringContextUtil.getBean(IWebScriptResourceService.class);
                        webScriptResourceService.delete(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.WPF_SCRIPT.getCategory())){
                        IWpfScriptResourceService wpfScriptResourceService= SpringContextUtil.getBean(IWpfScriptResourceService.class);
                        wpfScriptResourceService.delete(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.RESOURCE.getCategory())){
                        IFileResourceService fileService= SpringContextUtil.getBean(IFileResourceService.class);
                        fileService.delete(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.IMAGE.getCategory())){
                        IFileResourceService fileService= SpringContextUtil.getBean(IFileResourceService.class);
                        fileService.delete(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.SCRIPT.getCategory())){
                        IScriptResourceService scriptResourceService= SpringContextUtil.getBean(IScriptResourceService.class);
                        scriptResourceService.delete(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.DBTABEL_GROUP_5.getCategory())){
                        ILmsTableService lmsTableService= SpringContextUtil.getBean(ILmsTableService.class);
                        lmsTableService.deleteTableGroup(lemsunResource.getPid());
                    }else if(StringUtils.equalsIgnoreCase(lemsunResource.getCategory(), BaseCategory.DB.getCategory())){
                        IDbService dbService= SpringContextUtil.getBean(IDbService.class);
                        dbService.deleteResource(lemsunResource.getPid());
                    }
                }
            } catch (Exception e) {
                if(log.isErrorEnabled())

                    log.error("删除数据库配置时执行删除表组时出错:"+e.getMessage());
            }
        }

    }
}
