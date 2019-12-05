package com.yth.bean;

/**
 * 交易类型
 * @author yth
 *
 */
public enum TradeType {

	Deposit("deposit"), Draw("draw"), Loan("loan");

	private String name;

	TradeType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
