package com.lemsun.client.core.mvc.view;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

/**
 * 将模板内的标记内容的语言解析为JS的执行语句
 * <br/>
 * @{ } 为输出脚本
 * "< !--: -->" 为执行片段
 * User: 宗旭东
 * Date: 13-3-11
 * Time: 下午9:41
 */
public class JsTemplate {

    protected String placeholderStart = "@{";
    protected String placeholderStartIndex = "{";
    protected String placeholderEnd = "}";
    protected String startStatement = "<!--:";
    protected String endStatement = "-->";

    //组件内容
    private char[] cs;
    //解析位置
    private int index = 0;
    private int maxLen = 0;
    private Status status = Status.Text;
    private boolean script;
    private String lineSeparator = System.getProperty("line.separator");
    private StringBuilder builder;
    //转义符号
    static char ESCAPE = '\\';

    /**
     * 使用需要解析的内容构造引擎模板
     * @param context 内容
     */
    public JsTemplate(String context) {
        this(context, "@{", "}", "<!--:", "-->");
    }

    /**
     * 使用需要解析的内容构造引擎模板
     * @param context 内容
     * @param placeholderStart 自定义的输出开始标签
     * @param placeholderEnd 自定义的结束开始标签
     * @param startStatement 自定义的片段输出
     * @param endStatement 自定义的片段结束
     */
    public JsTemplate(String context, String placeholderStart, String placeholderEnd, String startStatement, String endStatement) {
        this.placeholderStart = placeholderStart;
        this.placeholderEnd = placeholderEnd;
        this.startStatement = startStatement;
        this.endStatement = endStatement;
        cs = context.toCharArray();
        maxLen = cs.length;
    }


    /**
     * 获取输出开始标记
     */
    public String getPlaceholderStart() {
        return placeholderStart;
    }

    /**
     * 获取输出结束标记
     */
    public String getPlaceholderEnd() {
        return placeholderEnd;
    }

    /**
     * 获取开始标记
     */
    public String getStartStatement() {
        return startStatement;
    }

    /**
     * 获取结束标记
     */
    public String getEndStatement() {
        return endStatement;
    }

    /**
     * 获取模板前的内容
     */
    public String getContext() {
        return new String(cs);
    }

    /**
     * 获取内容是否包含脚步
     */
    public boolean isScript() {
        return script;
    }

    /**
     * 将内容转化为模板语言
     * @return
     */
    public String parser() {

        builder = new StringBuilder(maxLen);
        script = false;
        findCR();
        if (this.endStatement == null)
        {
            this.endStatement = this.lineSeparator;
        }

        while (index < maxLen) {
            if(status == Status.Text) {
                readCommonString();
            }
            else if(status == Status.Statement) {
                readStatementString();
            }
            else if(status == Status.Output) {
                readOutputString();
            } else {
                break;
            }
        }

        return builder.toString();
    }

    /**
     * 读取正常输出文本
     */
    private void readCommonString() {
        ArrayList<String> lines = new ArrayList<>();
        StringBuilder commonStr = new StringBuilder();
        while (index < maxLen) {
            char c = cs[index];
            if(match(placeholderStart)) {
                index += placeholderStart.length();
                status = Status.Output;
                break;
            }
            else if(match(startStatement)) {
                index += startStatement.length();
                status = Status.Statement;
                break;
            }
            else if(status != Status.End){

                if(c == '\'') {
                    commonStr.append("\\'");
                } else if(c == '\r') {

                } else if(c == '\n') {

                    //lines.add(commonStr.length() == 0 ? "" : commonStr.toString().replaceAll("\\\\", "\\\\\\\\"));
                    lines.add(commonStr.toString());
                    commonStr = new StringBuilder();
                } else {
                    commonStr.append(c);
                }

            }
            index++;
        }

        if(commonStr.length() > 0) {
            //lines.add(commonStr.toString().replaceAll("\\\\", "\\\\\\\\"));
            lines.add(commonStr.toString());
        }


        for(int i=0; i<lines.size(); i++) {

            if(i > 0)
                builder.append("writer.newLine()\n");
            String l = lines.get(i);
            if(StringUtils.isNotEmpty(l))
                builder.append("writer.append('").append(lines.get(i)).append("')\n");
        }
    }

    /**
     * 读取描述文本
     */
    private void readStatementString() {
        while (index < maxLen) {
            if(match(endStatement)) {
                index += endStatement.length();
                status = Status.Text;
                break;
            }
            else if(match(placeholderStart)) {
                index += placeholderStart.length();
                status = Status.Output;
                break;
            }
            else if(status != Status.End){
                script = true;
                builder.append(cs[index]);
                index++;
            }
        }
    }

    /**
     * 读取输出文本
     */
    private void readOutputString() {
        StringBuilder output = new StringBuilder();

        int startCount = 1;

        while (index < maxLen) {
            char c = cs[index];

            if(match(placeholderStartIndex)) {
                startCount++;
            }
            else if(match(placeholderEnd)) {
                startCount--;
            }

            if(startCount == 0 || c == '\n') {
                index += placeholderEnd.length();
                status = Status.Text;
                break;
            }
            else if(status != Status.End) {
                output.append(c);
                index++;
            }



        }

        if(output.length() > 0) {
            script = true;
            builder.append("writer.append(").append(output.toString()).append(")\n");
        }

    }

    public boolean match(String expectedStr)
    {
        if(index >= maxLen) {
            status = Status.End;
        }

        if(maxLen < expectedStr.length() + index) {
            return false;
        }

        int i = 0;

        while (i < expectedStr.length())
        {
            if (cs[index + i] != expectedStr.charAt(i))
            {
                return false;
            }
            i++;
        }

        //如果匹配，但往前看有转义符，则还是算不匹配
        return !isEscape();

    }


    /**
     * 在match情况下，判断前面一符号是否是转义符号，如果是，则删除转义符号
     * 2012-2-5
     * 李bear
     */
    public boolean isEscape()
    {
        if (index != 0 && cs[index - 1] == ESCAPE)
        {
            if (index >= 2 && cs[index - 2] == ESCAPE)
            {
                //俩个转义符号，删除一个
                //removeEscape();
                return false;
            }
            else
            {
                //将已经添加的转义符号删除
                removeEscape();
                return true;
            }
        }
        else
        {
            return false;
        }
    }

    //删除一个转义字符
    private void removeEscape() {
        if (builder.length() != 0)
            builder.setLength(builder.length() - 1);
    }

    /**
     * 寻找换行标记
     */
    private void findCR()
    {
        // 是回车换行,
        StringBuilder cr = new StringBuilder(2);
        for (int i = 0; i < cs.length; i++)
        {
            if (cs[i] == '\n' || cs[i] == '\r')
            {
                cr.append(cs[i]);
                if (cs.length != (i + 1))
                {
                    char next = cs[i] == '\r' ? '\n' : '\r';
                    if (cs[i + 1] == next)
                    {
                        cr.append(next);
                    }
                }
                lineSeparator = cr.toString();
                return;
            }
        }
    }


    /**
     * 解析文本的状态
     */
    enum Status {
        /**
         * 当前在文本处
         */
        Text,
        /**
         * 在执行片段中
         */
        Statement,
        /**
         * 在输出片段中
         */
        Output,
        /**
         * 结束
         */
        End
    }
}
