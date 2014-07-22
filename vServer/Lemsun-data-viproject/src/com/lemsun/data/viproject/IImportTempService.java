package com.lemsun.data.viproject;

import com.lemsun.data.viproject.importmodel.ImportIndexModel;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Lucklim
 * Date: 13-2-4
 * Time: 上午10:44
 * To change this template use File | Settings | File Templates.
 */
public interface IImportTempService {

    /**
     * 得到导入树型目录表结构
     * @return
     */
    List<ImportIndexModel> getModels();

    /**
     * 初始LIST
     */
    void Init();

    /**
     * 向目录树添加目录与表组结构
     * @param jdbcTemplate
     * @param indexid
     * @param name
     * @param pid
     */
    void addFourTableGroup(JdbcTemplate jdbcTemplate ,String indexid,String name,String pid);
}
