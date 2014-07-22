package com.lemsun.client.core.mvc.lmsview;

/**
 * 视图内容输出
 * User: xudong
 * Date: 13-12-11
 * Time: 下午1:39
 *
 */
public class ViewWriterImpl implements IViewWriter {

    private StringBuilder builder = new StringBuilder();


    @Override
    public void flush() {

    }

    @Override
    public void append(Object context) {
        if(context != null)
        {
            builder.append(context);
        }
    }

    public void newLine() {
        builder.append('\n');
    }


    public void write(String context) {
        builder.append(context);
    }

    @Override
    public void appendLine(Object context) {
        builder.append(context).append('\n');
    }

    @Override
    public String toString()
    {
        return builder.toString();
    }
}
