package com.lemsun.form.web;

import com.lemsun.form.WebPageResource;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 将模板转换成代码
 * User: Xudong
 * Date: 12-10-30
 * Time: 下午2:56
 */
public class JsTemplate implements ILemsunTemplate {

	private String context;
	private WebPageResource resource;
	private static Pattern codePattern = Pattern.compile("<%.*?%>", Pattern.DOTALL | Pattern.MULTILINE);
	private static final Logger log = LoggerFactory.getLogger(JsTemplate.class);

	public JsTemplate(WebPageResource resource, String context)
	{
		this.resource = resource;
		this.context = context;
	}


	@Override
	public String toCode() {
		return anaylce();
	}


	protected String anaylce()
	{

		//StringBuilder builder = new StringBuilder("function page_" + resource.getPid() + "(scope, request, response, writer) { \n");
		StringBuilder builder = new StringBuilder("function page_" + resource.getPid() + "() { \n");


		Matcher matcher =codePattern.matcher(context);


		int start = 0;
		int end = context.length();

		while (matcher.find())
		{
			String text = context.substring(start, matcher.start());
			addContextText(builder, text);

			String script = matcher.group();

			addScriptText(builder, script);

			start = matcher.end();
		}

		addContextText(builder, context.substring(start, end));

		builder.append("}\n");
		return builder.toString();
	}


	protected void addContextText(StringBuilder builder, String text)
	{
		String[] rows = text.split("\n");

		for(String r : rows)
		{
			String temp = r.replaceAll("'", "\\'")
					.replaceAll("\r", "")
					.replaceAll("\\\\", "\\\\\\\\");


			if(StringUtils.isEmpty(temp))
			{
				builder.append("writer.newLine()\r\n");
			}
			else
			{
				builder.append("writer.appendLine('" + temp + "')\r\n");
			}

			//builder.append("\t writer.appendLine('").append(temp).append("'); \n");
		}
	}


	public void addScriptText(StringBuilder builder, String text)
	{

		int end = text.length() - 2;
		if(text.startsWith("<%--")) {
			//忽略备注信息
		}
		else if(text.startsWith("<%="))
		{
			String temp = text.substring(3, end).trim();
			//builder.append(text.substring(3, end).trim());
			builder.append("writer.append(" + temp + ")\n");
		}
		else if(text.startsWith("<%"))
		{
			builder.append(text.substring(2, end).trim()).append('\n');
		}
	}

}
