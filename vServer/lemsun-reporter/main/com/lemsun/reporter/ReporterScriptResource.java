package com.lemsun.reporter;

import com.lemsun.core.BaseCategory;
import com.lemsun.core.FunctionParam;
import com.lemsun.core.LemsunResource;
import com.lemsun.core.repository.ResourceRepository;
import com.lemsun.form.ScriptResource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Set;

/**
 * 填报的执行脚本组件
 * User: 宗旭东
 * Date: 14-5-20
 * Time: 下午2:20
 */
public class ReporterScriptResource extends ScriptResource {

    public ReporterScriptResource(){
        super(null, BaseCategory.REPORTER_SCRIPT.getCategory());
    }

    public ReporterScriptResource(String name){
        super(name,BaseCategory.REPORTER_SCRIPT.getCategory());
    }

    public ReporterScriptResource(LemsunResource resource){
        super(resource);
        setCategory(BaseCategory.REPORTER_SCRIPT.getCategory());
    }
}
