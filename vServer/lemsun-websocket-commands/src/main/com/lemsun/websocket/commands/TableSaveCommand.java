package com.lemsun.websocket.commands;

import com.lemsun.data.service.ILmsTableService;
import com.lemsun.websocket.AbstractSocketCommand;
import com.lemsun.websocket.commands.models.TableData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 表格组件的数据保存执行命令
 * User: 宗旭东
 * Date: 13-3-26
 * Time: 下午2:47
 */
public class TableSaveCommand extends AbstractSocketCommand {


    private ILmsTableService tableService;

    private static Logger log = LoggerFactory.getLogger(TableSaveCommand.class);

    @Autowired
    public TableSaveCommand(ILmsTableService tableService) {
        this.tableService = tableService;
    }

    @Override
    public void excute() throws Exception {

        ReTableSaveCommand re = new ReTableSaveCommand(getId());

        try
        {
            TableData table = getJsonParam(TableData.class);
            if(log.isDebugEnabled())
                log.debug("开始执行表格的数据保存, 保存数据条数 {}", table.getRowCount());

            tableService.saveData(table.getTablePid(), table);
        }
        catch (Exception ex) {
            if(log.isErrorEnabled())
                log.error("保存数据表格异常, {}", getId(), ex);
            re.setSuccess(false);
        }

        getResponseSenter().sent(re);
    }

}
