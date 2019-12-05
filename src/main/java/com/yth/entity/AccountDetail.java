package com.yth.entity;

import java.util.Date;

/**
 * Account Detail
 * 
 * @author yth
 *
 */
public class AccountDetail {

	/**
	 * ID
	 */
	private int id;

	/**
	 * 卡号
	 */
	private String cardNum;

	/**
	 * 交易类型： deposit,draw,loan
	 */
	private String type;

	/**
	 * 变动金额
	 */
	private String change;

	/**
	 * 余额
	 */
	private double balance;

	/**
	 * 开户时间
	 */
	private Date time;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

}
