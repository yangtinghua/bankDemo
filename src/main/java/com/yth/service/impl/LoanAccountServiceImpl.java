package com.yth.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yth.bean.TradeType;
import com.yth.entity.DepositAccount;
import com.yth.entity.LoanAccount;
import com.yth.mapper.DepositAccountMapper;
import com.yth.mapper.LoanAccountMapper;
import com.yth.service.DepositAccountService;
import com.yth.service.LoanAccountService;
import com.yth.util.EcoderKMD5;
import com.yth.util.ServiceUtil;

/**
 * 存款账户服务类
 * @author yth
 *
 */
@Service
public class LoanAccountServiceImpl implements LoanAccountService {
	
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoanAccountServiceImpl.class);
	

	@Autowired
    private LoanAccountMapper loanAccountMapper;


	@Autowired
    private DepositAccountService depositAccountService;

	@Autowired
    private DepositAccountMapper depositAccountMapper;
	
	@Override
	public List<LoanAccount> findLoanByIdentityNumOrCardNum(LoanAccount loanAccount) {

		List<LoanAccount> list =this.findLoanByIdentityNumOrCardNum(loanAccount.getIdentityNum(), loanAccount.getCardNum());
		return list;
	}

	@Override
	public LoanAccount queryCredit(String identityNum) {
		
		List<LoanAccount> list =this.findLoanByIdentityNumOrCardNum(identityNum, "");
		
		LoanAccount loan = list.get(0);
		
		if(loan.getCredit()>70){
			loan.setCreditDesc("good");
		}else if(loan.getCredit()<50){
			loan.setCreditDesc("bad");
		}
			
		return loan;

	}
	
	/**
	 * findLoanByIdentityNumOrCardNum
	 * @param identityNum
	 * @param cardNum
	 * @return
	 */
	private List<LoanAccount> findLoanByIdentityNumOrCardNum(String identityNum,String cardNum){
		
		List<LoanAccount> list =loanAccountMapper.findLoanByIdentityNumOrCardNum(identityNum, cardNum);

		if(list==null||list.isEmpty()){
			list = new ArrayList<LoanAccount>(1);
			
			List<DepositAccount> deposits=depositAccountMapper.findDepositByIdentityNumOrCardNum(identityNum, cardNum);
		
			if(deposits==null||deposits.isEmpty()){
				
				logger.info("please open diposit account first ! ");
				
				return null;
				
			}
			DepositAccount deposit = deposits.get(0);
			
			LoanAccount loan	=ServiceUtil.convertDeposit(deposit);
			loan.setId(deposit.getId());
			loan.setLoanLimit(100000);
			loan.setCredit(80);
			loan.setRate(0.005);
			loanAccountMapper.insertLoanAccount(loan);
			list.add(loan);
		}
		
		return list;
	}

	@Override
	public List<LoanAccount> loan(LoanAccount loan) throws Exception {
		
		//放贷
		this.doLoan(loan);
		
		
		//贷款金额存入用户账户
		this.depositToAccount(loan.getCardNum(),loan.getBalance());
		
	
		List<LoanAccount> newlist=loanAccountMapper.findLoanByIdentityNumOrCardNum(loan.getIdentityNum(), loan.getCardNum());
				
		return newlist;
	}

	/**
	 * 放贷
	 * @param loan
	 * @throws Exception
	 */
	private void doLoan(LoanAccount loan) throws Exception {
		
		List<LoanAccount> list=loanAccountMapper.findLoanByIdentityNumOrCardNum(loan.getIdentityNum(), loan.getCardNum());
		LoanAccount oldloan=list.get(0);
		
		oldloan.setId(oldloan.getId()+1);
		oldloan.setBalance(loan.getBalance());
		if(oldloan.getLoanLimit()-loan.getBalance()<0){
			
			logger.info("LoanLimit  is not enough ");
			
			throw new Exception("LoanLimit  is not enough ");
		}
		
		oldloan.setLoanLimit(oldloan.getLoanLimit()-loan.getBalance());
		oldloan.setBeginTime(new Date());
		oldloan.setEndTime(new Date());
		oldloan.setCount(oldloan.getCount());
		loanAccountMapper.insertLoanAccount(oldloan);
	}

	/**
	 * 存款存入相应账户
	 * @param cardNum
	 * @param balance
	 */
	private synchronized void depositToAccount(String cardNum, double balance) {
		List<DepositAccount> list=depositAccountMapper.findDepositByIdentityNumOrCardNum("", cardNum);
		DepositAccount old=list.get(0);
		
		double temp = old.getBalance()+balance;
		
		old.setBalance(temp);
		
		//交易明细
		depositAccountService.toLogDetails(cardNum, balance, TradeType.Loan,temp);
		
		
		depositAccountMapper.update(old);
		
	}

}
