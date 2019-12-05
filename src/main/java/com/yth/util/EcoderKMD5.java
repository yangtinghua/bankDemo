package com.yth.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sun.misc.BASE64Encoder;

/**
 * MD5加密
 * 
 * @author yth
 *
 */
public class EcoderKMD5 {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(EcoderKMD5.class);

	private static final String POLICY = "MD5";

	/**
	 * 进行MD5 加密
	 * 
	 * @param password
	 * @return
	 */
	public static String getMD5Password(String password) {
		String newPwd = null;

		try {
			MessageDigest md5 = MessageDigest.getInstance(POLICY);
			BASE64Encoder base64en = new BASE64Encoder();
			newPwd = base64en.encode(md5.digest(password.getBytes("utf-8")));

		} catch (Exception e) {
			logger.error("Exception:{}", e);
		}
		return newPwd;

	}

}
