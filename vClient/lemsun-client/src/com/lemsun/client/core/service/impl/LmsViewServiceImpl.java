package com.lemsun.client.core.service.impl;

import com.lemsun.client.core.Utils;
import com.lemsun.client.core.model.BaseCategory;
import com.lemsun.client.core.model.ImageResource;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.model.WebPageResource;
import com.lemsun.client.core.mvc.lmsview.*;
import com.lemsun.client.core.mvc.view.JsTemplate;
import com.lemsun.client.core.service.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.servlet.View;

import java.util.Date;
import java.util.HashMap;

/**
 * 组件视图实现
 * User: xudong
 * Date: 13-12-12
 * Time: 下午4:41
 */
@Service
public class LmsViewServiceImpl implements ILmsViewService {

    //内部缓存的视图
    private HashMap<String, CacheModel> viewsCache = new HashMap<>();
    private IWebPageResourceService webPageResourceService;
    private IResourceService resourceService;
    private IImageResourceService imageResourceService;
    private IAccountService accountService;

    private final static Logger logger = LoggerFactory.getLogger(LmsViewServiceImpl.class);

    @Autowired
    public LmsViewServiceImpl(IWebPageResourceService webPageResourceService,
                              IResourceService resourceService,
                              IImageResourceService imageResourceService,
                              IAccountService accountService) {
        this.webPageResourceService = webPageResourceService;
        this.resourceService = resourceService;
        this.imageResourceService = imageResourceService;
        this.accountService = accountService;
    }

    @Override
    public View getView(String pid) {

        LemsunResource resource = null;

        if (!Utils.isResourceId(pid)) {
            resource = webPageResourceService.getHomeWebPageResource(accountService.getCurrentAccount());
        } else {
            resource = resourceService.getLemsunResource(null, pid);
        }


        View view = getCachedView(resource);

        if(view != null)
        {
            return view;
        }

        return getView(resource);
    }

    @Override
    public synchronized View getView(WebPageResource resource) {

        View view = getCachedView(resource);

        if(view != null)
        {
            return view;
        }

        String context = resourceService.getResourceContext(resource);

        //获取脚本模板
        JsTemplate template = new JsTemplate(context);

        String js = template.parser();//将内容转化为模板语言
        if(logger.isDebugEnabled()){
            logger.debug("已经将内容转换成了模板语言:{}",js);
        }
        //包含脚本
        if(template.isScript()) {
            view = new ScriptResourceView(resource, js);
        }
        else {
            view = new TextResourceView(resource, context);
        }

        cacheView(resource, view);

        return view;
    }

    @Override
    public synchronized View getView(ImageResource resource) {

        View view = getCachedView(resource);

        if(view != null)
        {
            return view;
        }

        view = new ImageResourceView(resource);

        cacheView(resource, view);

        return view;
    }

    @Override
    public synchronized View getViewAttach(String pid, String fileName) {

        String f = fileName.substring(fileName.lastIndexOf('.') + 1);

        View view = new AttachView(pid, fileName);

        return view;
    }


    /**
     * 返回组件内容视图目前
     * @param resource 组件
     * @return 组件视图
     */
    public View getView(LemsunResource resource){


        if(resource==null)
            throw new ResourceAccessException("组件为空");

        View view=null;

        if(StringUtils.equals(resource.getCategory(), BaseCategory.WEB_SKIN.getCategory())){

            try {
                view=getView(webPageResourceService.getWebPageResource(resource.getPid()));
            } catch (Exception e) {
                logger.error("获取WEB组件失败:"+e.getMessage());
            }

        }else if(StringUtils.equals(resource.getCategory(),BaseCategory.IMAGE.getCategory())){

            try {
                view=getView(imageResourceService.getImageResource(null, resource.getPid()));
            } catch (Exception e) {
                logger.error("获取图片组件事态"+e.getMessage());
            }
        }else{
            throw new ResourceAccessException(resource.getCategory()+"组件不支持在web服务器中解析");
        }
        return view;
    }

    /**
     * 获取缓存的视图
     * @param resource 获取组件
     * @return
     */
    protected View getCachedView(LemsunResource resource) {
        return null;
    }

    /**
     *
     */
   protected void cacheView(LemsunResource resource, View view)
   {
       if(viewsCache.containsKey(resource.getPid()))
       {
           viewsCache.remove(resource.getPid());
       }


       CacheModel model = new CacheModel(new Date(), view);

       viewsCache.put(resource.getPid(), model);
   }


    /**
     * 缓存模型
     */
   private class CacheModel
   {
       private Date createTime;
       private View view;

       private CacheModel(Date createTime, View view) {
           this.createTime = createTime;
           this.view = view;
       }


       private Date getCreateTime() {
           return createTime;
       }

       private View getView() {
           return view;
       }
   }

}
