package com.lemsun.web.model;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.IResource;
import com.lemsun.core.LemsunResource;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.lmstable.Table5GroupResource;
import com.lemsun.data.FileResource;
import com.lemsun.data.ImageResource;
import com.lemsun.form.*;
import com.lemsun.reporter.ReporterResource;
import com.lemsun.reporter.ReporterScriptResource;
import com.lemsun.task.TaskResource;

/**
 * User: 刘晓宝
 * Date: 13-10-22
 * Time: 下午1:28
 */
public class ResourceHelder {
    /**
     * 根据类型创建组件对象
     */
    public static IResource createResource(LemsunResource lemsunResource) throws Exception{
        String cate=lemsunResource.getCategory();


        if(cate.equalsIgnoreCase(BaseCategory.DB.getCategory())){
            return new DbConfigResource(lemsunResource);

        }else if(cate.equalsIgnoreCase(BaseCategory.WPF_SKIN.getCategory())){
            return new WpfPageResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.WEB_SKIN.getCategory())){
            return new WebPageResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.WPF_SCRIPT.getCategory())){
            return new WpfScriptResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.WEB_SCRIPT.getCategory())){
            return new WebScriptResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.IMAGE.getCategory())){
            return new ImageResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.SCRIPT.getCategory())){
            return new ScriptResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.DBTABEL_GROUP_5.getCategory())){
            return new Table5GroupResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.RESOURCE.getCategory())){
            return new FileResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.TASK.getCategory())){
            return new TaskResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.TASK_SCRIPT.getCategory())){
            return new TaskScriptResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.REPORTER.getCategory())){
            return new ReporterResource(lemsunResource);
        }else if(cate.equalsIgnoreCase(BaseCategory.REPORTER_SCRIPT.getCategory())){
            return new ReporterScriptResource(lemsunResource);
        }else{
            throw new Exception("系统组件类型不包含该组件类型!");
        }

    }
}
