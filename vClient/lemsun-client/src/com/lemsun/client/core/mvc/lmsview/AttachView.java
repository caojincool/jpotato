package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.service.IWebPageResourceService;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * 获取组件附件的视图
 * User: xudong
 * Date: 13-12-12
 * Time: 下午5:17
 *
 */
public class AttachView implements View {

    private String pid;
    private String filename;

    public AttachView(String pid, String filename) {
        this.pid = pid;
        this.filename = filename;
    }

    @Override
    public String getContentType() {
        return null;
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        IWebPageResourceService service = Host.getInstance().getContext().getBean(IWebPageResourceService.class);

        byte[] data=service.getWebPageResourceAttachContext(pid, filename);
        response.setCharacterEncoding("UTF-8");
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();


    }
}
