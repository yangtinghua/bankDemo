package com.yth.service;

import java.util.List;

import com.yth.entity.LoanAccount;

/**
 * 存款业务服务接口
 * 
 * @author yth
 *
 */
public interface LoanAccountService {

	/**
	 * 
	 * @param identityNum
	 * @param cardNum
	 * @return
	 */
	public List<LoanAccount> findLoanByIdentityNumOrCardNum(LoanAccount loanAccount);

	/**
	 *  
	 * @param identityNum
	 * @return
	 */
	public LoanAccount queryCredit(String identityNum);

	/**
	 * 
	 * @param loan
	 * @return
	 */
	public List<LoanAccount> loan(LoanAccount loan) throws Exception ;
}
