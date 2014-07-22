package com.lemsun.data.service.impl;

import com.lemsun.TestSupport;
import com.lemsun.core.jackson.JsonObjectMapper;
import com.lemsun.data.connection.DbManager;
import com.lemsun.data.service.IDbService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-16
 * Time: 下午2:24
 */
public class TableServiceImplTest extends TestSupport {



    @Autowired
    private IDbService dbService;

    @Autowired
    private DbManager dbManager;

    @Autowired
    private JsonObjectMapper jsonObjectMapper;

    public String getResourceContext(String fileName) throws IOException {

        File file = ResourceUtils.getFile("classpath:com/lemsun/init/web/" + fileName);
        FileReader reader = new FileReader(file);
        BufferedReader br = new BufferedReader(reader);
        StringBuffer sb = new StringBuffer();

        while (br.ready()) {
            sb.append(br.readLine() + "\r\n");
        }
        return new String(sb);
    }

    /**
     * 测试创建 表格
     *
     * @throws Exception
     */
    @Test
    public void testCreateTable() throws Exception {

//        String data = getContent("json1.js");
//
//
//        CreateTableParam param = jsonObjectMapper.readValue(data, CreateTableParam.class);
//
       // Table5GroupResource groupResource = param.createGroupResource();
//
       // SpringContextUtil.getBean(ILmsTableService.class).create(groupResource);



//        String db = getDbManager().getDefaultName();
//
//        String pid = getDbManager().getDataSource(db).getPid();
//
//        TableResource resource = new TableResource("测试表格", pid, null);
//
//        resource.setVersion(5);
//
//        List<TableColumn> cols = new ArrayList<>();
//
//        TableColumn col = null;
//        col = new TableColumn("A1", ColumnCategory.TEXT);
//        col.setLength(50);
//        cols.add(col);
//
//        col = new TableColumn("A2", ColumnCategory.BOOL);
//        cols.add(col);
//
//        col = new TableColumn("A3", ColumnCategory.DATA);
//        cols.add(col);
//
//        col = new TableColumn("A4", ColumnCategory.STREAM);
//        cols.add(col);
//
//        col = new TableColumn("A5", ColumnCategory.TIME);
//        cols.add(col);
//
//
//        resource.setColumns(cols);
//
//        FaceSetting setting = new FaceSetting();
//        setting.setRange("A1");
//        setting.setName(1);
//        setting.setValue(20);
//
//        resource.getFace().getSettings().add(setting);
//        resource.getFace().getSettings().add(setting);
//
//        resource.setCreateUser(getAuth().getPrincipal().getAccount());
//
//        service.createTable(resource, getAccount());

    }

}
