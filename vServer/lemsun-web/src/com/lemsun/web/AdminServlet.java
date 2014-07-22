package com.lemsun.web;



import org.apache.catalina.core.StandardContext;
import org.apache.catalina.mbeans.MBeanUtils;
import org.apache.tomcat.util.modeler.ManagedBean;
import org.apache.tomcat.util.modeler.Registry;

import javax.management.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * User: 刘晓宝
 * Date: 14-1-9
 * Time: 上午9:13
 */
public class AdminServlet  extends HttpServlet{
    private Registry register;
    private MBeanServer mBeanServer;
    @Override
    public void init() throws ServletException {
       getServletContext().getAttribute("");
        register= MBeanUtils.createRegistry();
        mBeanServer=MBeanUtils.createServer();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        ManagedBean bean=register.findManagedBean("StandardContext");
        try {
           ObjectName objectName= new ObjectName("Catalina:j2eeType=WebModule,name=//localhost/index,J2EEApplication=none,J2EEServer=none");
            mBeanServer.invoke(objectName,"reload",null,null);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
