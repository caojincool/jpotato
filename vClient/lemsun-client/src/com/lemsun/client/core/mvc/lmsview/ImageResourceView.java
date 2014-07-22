package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.Host;
import com.lemsun.client.core.model.ImageResource;
import com.lemsun.client.core.service.IImageResourceService;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Map;

/**
 * 图片视图模型渲染
 * User: xudong
 * Date: 13-12-12
 * Time: 下午6:30
 *
 */
public class ImageResourceView implements View {

    private ImageResource resource;

    public ImageResourceView(ImageResource resource) {
        this.resource = resource;
    }

    @Override
    public String getContentType() {
        return "image/png";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        IImageResourceService service = Host.getInstance().getContext().getBean(IImageResourceService.class);

        response.setContentType(getContentType());
        byte[] data = service.getImage(resource);
        OutputStream stream = response.getOutputStream();
        stream.write(data);
        stream.flush();
    }
}
