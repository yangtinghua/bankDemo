package com.yth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yth.entity.LoanAccount;
import com.yth.service.LoanAccountService;

/**
 * LoanAccoutController
 * @author yth
 *
 */
@Controller
public class LoanAccoutController {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(LoanAccoutController.class);
	
    /**
     * The  service.
     */
    @Autowired
    private LoanAccountService service;


    
 
    /**
     * queryDeposit
     * @param model
     * @param deposit
     * @return
     */
    @RequestMapping("/queryLoan")
    public String queryDeposit(Model model, LoanAccount loan) {
    	
    	List<LoanAccount> list = service.findLoanByIdentityNumOrCardNum(loan);
    	
    	model.addAttribute("LoanAccount", list);
        return "loan/loanlist";
    }
    
    /**
     * To Deposit.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @RequestMapping("/toquerycredit")
    public String toDeposit(Model model, String  identityNum) {
    	LoanAccount  loan=service.queryCredit(identityNum);
    	model.addAttribute("LoanAccount", loan);
    	return "loan/loan";
    }
   
    @RequestMapping("/loan")
    public String loan(Model model,LoanAccount loan) throws Exception {
    	List<LoanAccount> loanlist=service.loan(loan);

    	model.addAttribute("LoanAccount", loanlist);
        return "loan/loanlist";
    }
    
}