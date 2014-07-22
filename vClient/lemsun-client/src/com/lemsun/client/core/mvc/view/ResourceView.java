package com.lemsun.client.core.mvc.view;

import com.lemsun.client.core.IAccount;
import com.lemsun.client.core.model.LemsunResource;
import com.lemsun.client.core.service.IResourceService;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 组件视图显示
 * User: 宗旭东
 * Date: 13-3-11
 * Time: 上午11:52
 */
public class ResourceView implements View {

    private LemsunResource resource;
    private IAccount account;
    private IResourceService resourceService;

    public ResourceView(LemsunResource resource, IAccount account, IResourceService resourceService) {
        this.resource = resource;
        this.account = account;
        this.resourceService = resourceService;
    }



    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(Map<String, ?> stringMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String context = resourceService.getResourceContext(resource);
        response.setContentType(getContentType());
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(context);
        writer.flush();
    }
}
