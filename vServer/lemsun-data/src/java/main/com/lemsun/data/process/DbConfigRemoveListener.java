package com.lemsun.data.process;

import com.lemsun.core.SpringContextUtil;
import com.lemsun.core.events.ResourceEvent;
import com.lemsun.core.service.IResourceService;
import com.lemsun.data.connection.DbConfigResource;
import com.lemsun.data.service.ILmsTableService;
import com.lemsun.ldbc.DbCategory;
import com.lemsun.ldbc.service.ITableOperaterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * User: 刘晓宝
 * Date: 13-10-30
 * Time: 上午9:05
 */
@Service
public class DbConfigRemoveListener implements ApplicationListener<ResourceEvent> {

    private static Logger log = LoggerFactory.getLogger(DbConfigRemoveListener.class);

    private Map<DbCategory, ITableOperaterService> tableServiceMap = new HashMap<>();
        @Autowired
        public DbConfigRemoveListener(Collection<ITableOperaterService> tableServices) {
            for (ITableOperaterService service : tableServices) {
                tableServiceMap.put(service.getSupportCategory(), service);
            }
        }

        /**
         * 如果删除对象是数据库配置才执行
         * @param resourceEvent
         */
        @Override
        public void onApplicationEvent(ResourceEvent resourceEvent) {
            if (resourceEvent.getResource()==null) throw new RuntimeException("删除的组件不能为空");

            if (resourceEvent.getResource() instanceof DbConfigResource
                    &&resourceEvent.getAction()== ResourceEvent.Action.BefreDelete){
                DbConfigResource dbCategory=(DbConfigResource)resourceEvent.getResource();
                try {
                    ITableOperaterService operaterService = tableServiceMap.get(dbCategory.getDbCategory());
                    operaterService.removeDbConfig(dbCategory);
                } catch (Exception e) {
                    if(log.isErrorEnabled())
                    log.error("删除数据库配置时执行删除表组时出错:"+e.getMessage());
                }
            }
    }
}
