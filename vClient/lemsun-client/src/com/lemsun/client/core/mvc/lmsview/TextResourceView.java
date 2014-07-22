package com.lemsun.client.core.mvc.lmsview;

import com.lemsun.client.core.IWebViewResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 当一个组件没有脚本内容的时候内容将进行固定的渲染
 * User: xudong
 * Date: 13-12-11
 * Time: 下午4:13
 */
public class TextResourceView extends AbstractLmsView {

    private String context;
    private static final Logger log = LoggerFactory.getLogger(TextResourceView.class);
    public TextResourceView(IWebViewResource resource, String context) {
        super(resource);
        this.context = context;
    }


    @Override
    public boolean isCache() {
        return true;
    }

    @Override
    public void executeContext(ViewScope scope) {
        scope.getWriter().append(getContext());
        if(log.isDebugEnabled())
            log.debug("已经执行显示内容:\n" +scope.getEngine().getContext());
    }
}
