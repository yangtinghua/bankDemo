package com.yth.service;

import java.util.List;

import com.yth.bean.TradeType;
import com.yth.entity.AccountDetail;
import com.yth.entity.DepositAccount;

/**
 * 存款业务服务接口
 * @author yth
 *
 */
public interface DepositAccountService {

	/**
	 * 存款
	 * @param deposit
	 * @return
	 */
	public DepositAccount deposit(DepositAccount deposit);
	

	
	
	
	/**
	 * 取款
	 * @param deposit
	 * @return
	 */
	public DepositAccount draw(DepositAccount deposit) throws Exception ;
	
	/**
	 * getAllDeposit
	 * @return
	 */
	public List<DepositAccount> getAllDeposit();
	
	/**
	 * findDepositByCardNum 
	 * @param cardNum
	 * @param password
	 * @return
	 */
	public List<DepositAccount> findDepositByCardNum(String cardNum,String password);
	
	/**
	 * createDepositAccount
	 * @param depositAccount
	 * @return
	 */
	public int createDepositAccount(DepositAccount depositAccount);
	
	/**
	 * findDepositById
	 * @param id
	 * @return
	 */
	public DepositAccount findDepositById(int id);
	
	/**
	 * getAllAccountDetails
	 * @return
	 */
	public List<AccountDetail> getDetailsByCardNum(String cardNum);
	
	/**
	 * 
	 * @param cardNum
	 * @param balance
	 * @param draw
	 */
	public void toLogDetails(String cardNum, double change, TradeType draw, double balance);
}
