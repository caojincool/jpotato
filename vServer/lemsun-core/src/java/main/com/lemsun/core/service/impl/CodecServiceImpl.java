package com.lemsun.core.service.impl;

import com.lemsun.core.service.ICodecService;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 * Created by IntelliJ IDEA.
 * User: Xudong
 * Date: 12-11-14
 * Time: 下午2:40
 */
public class CodecServiceImpl implements ICodecService {
	//算法名称
	public static final String KEY_ALGORITHM = "DES";
	//算法名称/加密模式/填充方式
	//DES共有四种工作模式-->>ECB：电子密码本模式、CBC：加密分组链接模式、CFB：加密反馈模式、OFB：输出反馈模式
	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";
	private SecretKey key;

	public CodecServiceImpl(String key) throws Exception {
		initkey(key);
	}


	/**
	 * 生成密钥
	 */
	public void initkey(String key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key.getBytes());                                      //实例化Des密钥
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM); //实例化密钥工厂
		this.key = keyFactory.generateSecret(dks);                      //生成密钥
	}


	@Override
	public String encode(String context) {                         //还原密钥

		try {
			if(StringUtils.isEmpty(context))
				return context;
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);              //实例化Cipher对象，它用于完成实际的加密操作
			cipher.init(Cipher.ENCRYPT_MODE, key);                               //初始化Cipher对象，设置为加密模式
			return Base64.encodeBase64String(cipher.doFinal(context.getBytes())); //执行加密操作。加密后的结果通常都会用Base64编码进行传输
		} catch (Exception e) {
			return context;
		}

	}

	@Override
	public String decode(String encode) {
		try {
			if(StringUtils.isEmpty(encode))
				return encode;
			Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
			cipher.init(Cipher.DECRYPT_MODE, key);
			return new String(cipher.doFinal(Base64.decodeBase64(encode)));
		} catch (Exception e) {
			return encode;
		}
	}
}
