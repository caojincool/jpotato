package com.lemsun.form.web;

import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 视图代码输出对象
 * User: Xudong
 * Date: 12-10-30
 * Time: 下午3:34
 */
public class ViewWriter {

	private Writer writer;

	public ViewWriter() {
		writer = new CharArrayWriter(100);

	}


	public void write(String context)
	{
		try {
			writer.write(context);
			//writer.flush();
		} catch (IOException e) {

		}
	}


	public void append(Object charSequence)
	{
		try {

			if(charSequence == null) return;

			if(charSequence instanceof CharSequence) {
				writer.append((CharSequence)charSequence);
			}
			else
			{
				writer.append(charSequence.toString());
			}
		} catch (IOException e) {

		}
	}


	public void newLine()
	{
		try {
			writer.write('\n');
		} catch (IOException e) {

		}
	}


	public void appendLine(Object charSequence)
	{
		try {
			if(charSequence == null) return;

			if(charSequence instanceof CharSequence)
			{
				writer.append((CharSequence)charSequence).append("\n");
			}
			else
			{
				writer.append(charSequence.toString()).append("\n");
			}

		} catch (IOException e) {

		}
	}

	public void flush()
	{
		try {
			writer.flush();
		} catch (IOException e) {

		}
	}


	public String toString()
	{
		flush();
		return writer.toString();
	}

}
