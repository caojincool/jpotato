package com.lemsun.web.manager.controller.iservice;

import com.lemsun.data.service.IDbService;
import com.lemsun.ldbc.TableData;
import com.lemsun.web.model.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;

/**
 * User: 宗旭东
 * Date: 13-6-19
 * Time: 下午4:30
 */
@Controller("interfaceSqlRunnerController")
@RequestMapping("/interface/{plateform}/sql")
public class SqlRunnerController {

    @Autowired
    private IDbService dbService;


    @RequestMapping(value = "select", method = RequestMethod.GET)
    public ResponseEntity<TableData> select(HttpServletRequest request) throws Exception {
        String pid = request.getParameter("pid");
        String sql = request.getParameter("sql");
        TableData data = dbService.select(pid, sql);
        return new ResponseEntity<>(data);
    }

    @RequestMapping("execute")
    public ResponseEntity<String> execute(String pid, String sql) throws Exception {
        String temp = URLDecoder.decode(sql, "UTF-8");
        dbService.execute(pid, sql);
        return new ResponseEntity<>("OK");
    }
}
