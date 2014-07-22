package com.lemsun.client.core;

import com.lemsun.client.TestSupport;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 工具包测试类
 * User: dpyang
 * Date: 13-5-8
 * Time: 下午2:23
 */
public class UtilsTest extends TestSupport{

    private static final Logger loger= LoggerFactory.getLogger(UtilsTest.class);

    @Test
    public void testIsResourceId(){
        loger.debug("组件编码规则:C或者S开头后面跟9个整数编码");
        loger.debug("开始测试组件编码是否合法");
        loger.debug("用例1:C0002");
        String pid1="C0002";
        loger.debug(String.valueOf(Utils.isResourceId(pid1)));

        loger.debug("用例2:C100223023");
        String pid2="C100223023";
        loger.debug(String.valueOf(Utils.isResourceId(pid2)));

        loger.debug("用例3:S100223023");
        String pid3="S100223023";
        loger.debug(String.valueOf(Utils.isResourceId(pid3)));

        loger.debug("用例4:c100223023");
        String pid4="c100223023";
        loger.debug(String.valueOf(Utils.isResourceId(pid4)));
    }

    //解析组件编码
    @Test
    public void testParsePid(){
        loger.debug("解析组件编码是通过一个组件编码获得完成完整的组件编码,忽略大小写,末尾编码,和指定是否是一个系统组件");
        loger.debug("开始测试解析组件编码");
        loger.debug("用例1:C000025231");
        String pid1="C000025231";
        String resourt=Utils.parsePid(pid1,false);
        loger.debug("返回:"+resourt);

        loger.debug("用例2:C000025231");
        String pid2="C000025231";
        String resourt2=Utils.parsePid(pid2,true);
        loger.debug("返回:"+resourt2);

        loger.debug("用例3:025231");
        String pid3="025231";
        String resourt3=Utils.parsePid(pid3,false);
        loger.debug("返回:"+resourt3);

        loger.debug("用例4:-1");
        String pid4="-1";
        String resourt4=Utils.parsePid(pid4,true);
        loger.debug("返回:"+resourt4);

        loger.debug("用例4:-0.1");
        String pid5="-0.1";
        String resourt5=Utils.parsePid(pid5,true);
        loger.debug("返回:"+resourt5);
    }

    // 获取基本的组件
    @Test
    public void testGetLemsunResource(){
        loger.debug("通过组件编码和指定该组件是否是系统组件来获取基本组件");
        loger.debug("开始测试");

        loger.debug("用例1->已经存在的自定义组件:C000001068");
        String pid="C000001068";
        loger.debug(String.valueOf(Utils.getLemsunResource(pid)));

        loger.debug("用例2->不存在的自定义组件:C100001068");
        String pid2="C100001068";
        loger.debug(String.valueOf(Utils.getLemsunResource(pid2)));
    }
}
