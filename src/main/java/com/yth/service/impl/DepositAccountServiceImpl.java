package com.yth.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yth.bean.TradeType;
import com.yth.entity.AccountDetail;
import com.yth.entity.DepositAccount;
import com.yth.mapper.AccountDetailMapper;
import com.yth.mapper.DepositAccountMapper;
import com.yth.service.DepositAccountService;
import com.yth.util.EcoderKMD5;

/**
 * 存款账户服务类
 * @author yth
 *
 */
@Service
public class DepositAccountServiceImpl implements DepositAccountService {
	
	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DepositAccountServiceImpl.class);
	

	private static int detail_id = 0;
	
	@Autowired
    private DepositAccountMapper depositAccountMapper;
	
	@Autowired
    private AccountDetailMapper accountDetailMapper;
	
	@Override
	public synchronized DepositAccount deposit(DepositAccount deposit) {
		
		DepositAccount oldDeposit= this.findDepositById(deposit.getId());
				
		double balance = oldDeposit.getBalance()+deposit.getBalance();
		
		oldDeposit.setBalance(balance);
		
		//记录交易明细
		this.toLogDetails(deposit.getCardNum(),deposit.getBalance(),TradeType.Deposit,balance);
		
		
		int result = depositAccountMapper.update(oldDeposit);
		
	
		
		return oldDeposit;
	}

	

	@Override
	public synchronized DepositAccount draw(DepositAccount deposit) throws Exception {


		DepositAccount oldDeposit= this.findDepositById(deposit.getId());
		
		double balance = oldDeposit.getBalance()-deposit.getBalance();
		
		if(balance < 0){
			
			throw new Exception("accout balance is not enough !");
		}
		
		oldDeposit.setBalance(balance);
		
		//记录交易明细
	    this.toLogDetails(deposit.getCardNum(),deposit.getBalance(),TradeType.Draw,balance);
				
		
		int result = depositAccountMapper.update(oldDeposit);
		
		
		return oldDeposit;
	}





	@Override
	public List<DepositAccount> getAllDeposit() {
		
		return depositAccountMapper.getAllDeposit();
	}

	@Override
	public List<DepositAccount> findDepositByCardNum(String cardNum, String password) {
		
		//密码加密
		String ecoderPwd=EcoderKMD5.getMD5Password(password);
		
		return depositAccountMapper.findDepositByCardNum(cardNum, ecoderPwd);
	}

	@Override
	public int createDepositAccount(DepositAccount depositAccount) {
		
		//密码加密
		String ecoderPwd=EcoderKMD5.getMD5Password(depositAccount.getPassword());
		depositAccount.setPassword(ecoderPwd);
		
		//账户默认值
		depositAccount.setBalance(0);
		depositAccount.setCreateTime(new Date());
		depositAccount.setRate(0.003);
		return depositAccountMapper.insertDepositAccount(depositAccount);
	}

	@Override
	public DepositAccount findDepositById(int id) {
		List<DepositAccount>  list = depositAccountMapper.findDepositById(id);
		
		if(list==null||list.isEmpty()){
			
			logger.info("findDepositById list is null ");
			
			return null;
		}
		
		return list.get(0);
	}




	/**
	 * 
	 * @param cardNum
	 * @param balance
	 * @param draw
	 */
	public void toLogDetails(String cardNum, double change, TradeType type,double balance) {

	
		AccountDetail detail =new AccountDetail();
		detail.setId(detail_id++);
		detail.setBalance(balance);
		detail.setCardNum(cardNum);
		String changeStr = "";
		if(TradeType.Deposit.equals(type)){
			changeStr ="+ "+change;
		}else if(TradeType.Draw.equals(type)){
			changeStr ="- "+change;
		}else if(TradeType.Loan.equals(type)){
			changeStr ="+ "+change;
		}
		detail.setChange(changeStr);
		detail.setTime(new Date());
		detail.setType(type.name());
		
		accountDetailMapper.insertLoanAccount(detail);
	}



	@Override
	public List<AccountDetail> getDetailsByCardNum(String cardNum) {
		List<AccountDetail> details=	accountDetailMapper.findDetailByCardNum(cardNum);
		return details;
	}
}
