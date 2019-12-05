package com.yth.util;

import java.security.MessageDigest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yth.entity.AccountDetail;
import com.yth.entity.DepositAccount;
import com.yth.entity.LoanAccount;

import sun.misc.BASE64Encoder;

/**
 * MD5加密
 * 
 * @author yth
 *
 */
public class ServiceUtil {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(ServiceUtil.class);



	/**
	 * 对象转换
	 * 
	 * @param password
	 * @return
	 */
	public static LoanAccount convertDeposit(DepositAccount deposit) {
	
		LoanAccount loan = new LoanAccount();

		loan.setName(deposit.getName());
		loan.setCardNum(deposit.getCardNum());
		loan.setIdentityNum(deposit.getIdentityNum());
		
		return loan;

	}
	
	
}
