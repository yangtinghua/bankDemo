package com.yth.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yth.entity.LoanAccount;
import com.yth.service.LoanAccountService;

/**
 * LoanAccoutController
 * 
 * @author yth
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/bank/loan")
public class LoanAccoutRestController {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoanAccoutRestController.class);

	/**
	 * The service.
	 */
	@Autowired
	private LoanAccountService service;



	/**
	 * queryDeposit
	 * @param loan
	 * @return
	 */
	@RequestMapping(value = "/queryLoan", method = RequestMethod.POST)
	public List<LoanAccount> queryDeposit(@RequestBody LoanAccount loan) {

		List<LoanAccount> list = service.findLoanByIdentityNumOrCardNum(loan);

		return list;
	}



	/**
	 * toDeposit
	 * @param identityNum
	 * @return
	 */
	@RequestMapping(value = "/toquerycredit", method = RequestMethod.GET)
	public LoanAccount toDeposit(@RequestParam("identityNum") String identityNum) {
		LoanAccount loan = service.queryCredit(identityNum);

		return loan;
	}

	/**
	 * loan
	 * @param loan
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/loan", method = RequestMethod.POST)
	public List<LoanAccount> loan(@RequestBody LoanAccount loan) throws Exception {
		List<LoanAccount> loanlist = service.loan(loan);
		return loanlist;
	}

}