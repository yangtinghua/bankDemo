package com.yth.entity;

import java.util.Date;

/**
 * Deposit Account
 * @author yth
 *
 */
public class DepositAccount {

	    /**
	     * ID
	     */
	    private int id;
	       
	    
	    /**
	     * 账户名称
	     */
	    private String name;
	    
	      
	    /**
	     * 密码
	     */
	    private String password;
	    
	    
	    /**
	     * 身份证号码
	     */
	    private String identityNum;
	    
	    /**
	     * 卡号
	     */
	    private String cardNum;
	    
	    /**
	     * 电话号码
	     */
	    private String phoneNum;
	    
	    /**
	     * 存款金额
	     */
	    private double balance;
	    
	    
	    /**
	     * 存款利率
	     */
	    private double rate;
		
	    
	    /**
	     * 利息
	     */
	    private double interest;
	    
    
	    /**
	     * 开户时间
	     */
	    private Date createTime;
	    
	    /**
	     * 存款开始时间
	     */
	    private Date beginTime;
	    
	    /**
	     * 存款结束时间
	     */
	    private Date endTime;
	    
	    /**
	     * 存款期限（月）
	     */
	    private int count;
	    
	    /**
	     * 存款类型  1：活期 2：定期
	     */
	    private int type;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getIdentityNum() {
			return identityNum;
		}

		public void setIdentityNum(String identityNum) {
			this.identityNum = identityNum;
		}

		public String getCardNum() {
			return cardNum;
		}

		public void setCardNum(String cardNum) {
			this.cardNum = cardNum;
		}

		public String getPhoneNum() {
			return phoneNum;
		}

		public void setPhoneNum(String phoneNum) {
			this.phoneNum = phoneNum;
		}

		public double getBalance() {
			return balance;
		}

		public void setBalance(double balance) {
			this.balance = balance;
		}

		public double getRate() {
			return rate;
		}

		public void setRate(double rate) {
			this.rate = rate;
		}

		public double getInterest() {
			return interest;
		}

		public void setInterest(double interest) {
			this.interest = interest;
		}

		public Date getCreateTime() {
			return createTime;
		}

		public void setCreateTime(Date createTime) {
			this.createTime = createTime;
		}



		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public Date getBeginTime() {
			return beginTime;
		}

		public void setBeginTime(Date beginTime) {
			this.beginTime = beginTime;
		}

		public Date getEndTime() {
			return endTime;
		}

		public void setEndTime(Date endTime) {
			this.endTime = endTime;
		}
	    
	    
	    
	    
}
