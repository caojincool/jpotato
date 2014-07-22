package com.lemsun.data.viproject.impl;

import com.lemsun.data.viproject.FourIndex;
import com.lemsun.data.viproject.FourTableGroup;
import com.lemsun.data.viproject.IImportTempService;
import com.lemsun.data.viproject.importmodel.ImportIndexModel;
import com.lemsun.data.viproject.importmodel.ImportTableGroupModel;
import com.lemsun.data.viproject.repository.FourIndexRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-2-2
 * Time: 上午10:57
 * To change this template use File | Settings | File Templates.
 */
@Service
public class ImportTempServiceImpl implements IImportTempService {



    private List<ImportIndexModel> models;
    private FourIndexRepository fourIndexRepository;


    @Autowired
    public ImportTempServiceImpl(FourIndexRepository fourIndexRepository)
    {
        models=new ArrayList<>();
        this.fourIndexRepository=fourIndexRepository;
    }

    /**
     * 获取导入临时模型
     * @return
     */
    public List<ImportIndexModel> getModels()
    {
        return models;
    }

    /**
     * 初始化
     */
    public void Init()
    {
        models=new ArrayList<>();
    }


    /**
     * 是否包含目录
     * @param code
     * @return
     */
    private ImportIndexModel haveCode(String code)
    {
        return selectHaveCode(models,code);
    }
    private ImportIndexModel selectHaveCode(List<ImportIndexModel> importTempModels,String code)
    {
        if(importTempModels==null)return null;
        for (ImportIndexModel im:importTempModels){
            if(im.getCode().equals(code)){
                return im;
            }
            ImportIndexModel semodel= selectHaveCode(im.getChildren(),code);
            if(semodel!=null)return semodel;
        }
        return null;
    }

    /**
     * 添加4代表组
     * @param jdbcTemplate
     * @param code
     * @param name
     * @param pid
     */
    public void addFourTableGroup(JdbcTemplate jdbcTemplate ,String code,String name,String pid)
    {
        FourIndex fourIndex=fourIndexRepository.getFourIndexParentByA1(jdbcTemplate,code);
        if(fourIndex==null)return;
        ImportIndexModel importTempModel=haveCode(fourIndex.getCode());
        if(importTempModel==null)
        {
            importTempModel=new ImportIndexModel();
            importTempModel.setCode(fourIndex.getCode());
            importTempModel.setName(fourIndex.getName());

        }
        addImportIndexModel(jdbcTemplate,fourIndex.getCode(),importTempModel);


        ImportTableGroupModel tableModel=new ImportTableGroupModel();
        tableModel.setPid(pid);
        tableModel.setName(name);
        tableModel.setCode(code);
        importTempModel.addTableGroupModel(tableModel);
    }

    private void addImportIndexModel(JdbcTemplate jdbcTemplate, String code,ImportIndexModel indexModel)
    {
        FourIndex fourIndex=fourIndexRepository.getFourIndexParentByA1(jdbcTemplate,code);//查找数据库
        if(fourIndex==null){
            models.add(indexModel);
            return;
        }
        indexModel.setParentCode(fourIndex.getCode());
        ImportIndexModel importTempModel=haveCode(fourIndex.getCode())  ;    //是否已经存在目录
        if(importTempModel==null){
            importTempModel=new ImportIndexModel();
            importTempModel.setCode(fourIndex.getCode());
            importTempModel.setName(fourIndex.getName());

            addImportIndexModel(jdbcTemplate,fourIndex.getCode(),importTempModel); //继续判断上级
        }
        importTempModel.addIndexModelChild(indexModel);
    }
}
