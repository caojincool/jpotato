package com.lemsun.channel.command;

import com.lemsun.channel.AbstractExcuteCommand;
import com.lemsun.channel.ChannelException;
import com.lemsun.channel.ChannelExcuteException;
import com.lemsun.channel.command.table.TableData;
import com.lemsun.channel.command.table.TableSaveModel;
import com.lemsun.data.connection.JdbcTemplate;
import com.lemsun.data.connection.TableSet;
import com.lemsun.data.connection.TransactionManager;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.data.service.ITableService;
import com.lemsun.data.tables.TableResource;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.ApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created with IntelliJ IDEA.
 * User: Xudong
 * Date: 12-12-24
 * Time: 下午1:18
 * 执行表格组件资源的保存命令
 */
public class TableSaveCommand extends AbstractExcuteCommand {

    @Override
    public void excute() throws ChannelException
    {
        TableSaveModel model = getContext().getObject(TableSaveModel.class);

        try {

            ILmsTableService tableService = getApplicationContext().getBean(ILmsTableService.class);

            ITableService service = getApplicationContext().getBean(ITableService.class);

            TableResource resource = service.getSimple(model.getPid());

            TransactionManager manager  = getSession().getTransactionManager();

            Connection connection = manager.getConnection(resource.getDbConfig().getName());

            TableData table = model.getEntity();

            table.execute(connection, resource);

            getResponse().getHeader().setActionCommand("table.save");
            getResponse().setResponse(true);
        } catch (Exception e) {
            throw new ChannelExcuteException("执行更新出错: " + e.getMessage());
        }
    }
}
