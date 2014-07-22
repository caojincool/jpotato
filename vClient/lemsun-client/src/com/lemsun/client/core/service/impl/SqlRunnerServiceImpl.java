package com.lemsun.client.core.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.lemsun.client.core.Host;
import com.lemsun.client.core.IResponseEntity;
import com.lemsun.client.core.LemsunException;
import com.lemsun.client.core.data.ArrayData;
import com.lemsun.client.core.service.IRemoteService;
import com.lemsun.client.core.service.ISqlRunnerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;

/**
 *
 * User: dp
 * Date: 13-6-20
 * Time: 上午10:56
 */
@Service
public class SqlRunnerServiceImpl implements ISqlRunnerService {

    private IRemoteService remoteService;
    private Host host;

    private static final String SelectAddress="sql/select";
    private static final String ExcuteAddress="sql/execute";

    private Logger logger= LoggerFactory.getLogger(SqlRunnerServiceImpl.class);

    @Autowired
    public SqlRunnerServiceImpl(IRemoteService remoteService,
                                @Qualifier("host") Host host) {
        this.remoteService = remoteService;
        this.host=host;
    }

    /**
     * 通过数据源组件编码,执行sql查询语句
     * 注意只能执行查询语句
     *
     * @param pid 数据源组件编码
     * @param sql sql 查询语句例如select top 5 * from employee
     * @return 查询语句结果
     */
    @Override
    public ArrayData select(String pid, String sql) {

        try
        {
            String url = SelectAddress
                    + String.format("?pid=%s&sql=%s", pid, URLEncoder.encode(sql,"UTF-8"));

            IResponseEntity<ArrayData> entity =
                    remoteService.getAddressContext(
                            url,
                            new TypeReference<IResponseEntity<ArrayData>>() {}
                    );

            if(!entity.isSuccess()) {
                throw new LemsunException(entity.getMessage());
            }

            return entity.getEntity();
        }
        catch (Exception ex) {
            throw new LemsunException(ex);
        }

    }

    /**
     * 通过数据源组件编码,执行sql语句
     *
     * @param pid 数据源组件编码
     * @param sql sql 执行语句,
     * @return 影响行数
     */
    @Override
    public String excute(String pid, String sql) {
        try
        {
            String url = ExcuteAddress
                    + String.format("?pid=%s&sql=%s", pid, URLEncoder.encode(sql,"UTF-8"));

            IResponseEntity<String> entity =
                    remoteService.getAddressContext(
                            url,
                            new TypeReference<IResponseEntity<String>>() {}
                    );

            if(!entity.isSuccess()) {
                throw new IOException(entity.getMessage());
            }

            return entity.getEntity();
        }
        catch (Exception ex) {
            throw new LemsunException(ex);
        }

    }
}
