package com.lemsun.core.util;

import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 对使用zip压缩或者解压的帮助类
 * User: Xudong
 * Date: 12-12-4
 * Time: 下午12:10
 */
public class ZipCodeStream {

	private static final Logger log = LoggerFactory.getLogger(ZipCodeStream.class);
	/**
	 * 将输入的字符流进行解压
	 * @param input
	 * @return
	 */
	public static byte[] decompressBytes(byte[] input) throws Exception {

		ZipInputStream zipStream = new ZipInputStream(new ByteArrayInputStream(input), Charsets.UTF_8);
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		ZipEntry entry;
		byte[] buf = new byte[4069];
		int readLen = 0;
		try {
			while ((entry = zipStream.getNextEntry()) != null) {

				while ((readLen = zipStream.read(buf)) != -1) {
					outputStream.write(buf, 0, readLen);
				}
				zipStream.closeEntry();
			}
			//outputStream.flush();

			//return outputStream.toByteArray();
		} catch (IOException e) {
			log.info(e.getMessage());
		}
		finally {
			zipStream.close();
		}

		outputStream.flush();

		return outputStream.toByteArray();
	}


	/**
	 * 压缩一段字节
	 * @param input
	 * @return
	 */
	public static byte[] compressBytes(byte[] input) {
		return null;
	}

}
