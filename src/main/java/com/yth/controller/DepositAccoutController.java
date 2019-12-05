package com.yth.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yth.entity.AccountDetail;
import com.yth.entity.DepositAccount;
import com.yth.service.DepositAccountService;

/**
 * DepositAccoutController
 * @author yth
 *
 */
@Controller
public class DepositAccoutController {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DepositAccoutController.class);
	
    /**
     * The  service.
     */
    @Autowired
    private DepositAccountService service;


    /**
     * Index string.
     *
     * @return the string
     */
    @RequestMapping("/bank")
    public String index() {
        return "disposit/list";
    }
    /**
     * List string.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping("/dlist")
    public String list(Model model) {
        List<DepositAccount> deposit = service.getAllDeposit();
        model.addAttribute("DepositAccount", deposit);
        return "disposit/list";
    }

    /**
     * queryDeposit
     * @param model
     * @param deposit
     * @return
     */
    @RequestMapping("/queryDeposit")
    public String queryDeposit(Model model, DepositAccount deposit) {
    	
    	List<DepositAccount> deposit1 = service.findDepositByCardNum(deposit.getCardNum(), deposit.getPassword());
    	
    	model.addAttribute("DepositAccount", deposit1);
        return "disposit/list";
    }
    
    
    /**
     * To add string.
     *
     * @return the string
     */
    @RequestMapping("/toAddAccount")
    public String toAdd() {
        return "disposit/accountAdd";
    }
    
    
    /**
     * add account
     *
     * @param deposit the deposit
     * @return the string
     */
    @RequestMapping("/addaccount")
    public String add(DepositAccount deposit) {
    	service.createDepositAccount(deposit);
        return "redirect:/dlist";
    }
    
    /**
     * To Deposit.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @RequestMapping("/toDeposit")
    public String toDeposit(Model model, int id) {
    	DepositAccount  deposit=service.findDepositById(id);
    	model.addAttribute("DepositAccount", deposit);
    	return "disposit/disposit";
    }
    
    /**
     * deposit string.
     *
     * @param user the user
     * @return the string
     */
    @RequestMapping("/deposit")
    public String deposit(Model model,DepositAccount deposit) {
    	DepositAccount deposit1=service.deposit(deposit);

    	model.addAttribute("DepositAccount", deposit1);
        return "disposit/list";
    }
    
    
    /**
     * To draw string.
     *
     * @param model the model
     * @param id    the id
     * @return the string
     */
    @RequestMapping("/toDraw")
    public String toDraw(Model model, int id) {
    	DepositAccount  deposit=service.findDepositById(id);
    	model.addAttribute("DepositAccount", deposit);
    	return "disposit/draw";
    }
    
    /**
     * draw string.
     *
     * @param deposit the deposit
     * @return the string
     * @throws Exception 
     */
    @RequestMapping("/draw")
    public String draw(Model model,DepositAccount deposit) throws Exception {
    	DepositAccount deposit1 = service.draw(deposit);
    	model.addAttribute("DepositAccount", deposit1);
        return "disposit/list";
    }
    
  
    @RequestMapping("/toLoanList")
    public String toLoanList() {
        return "loan/loanlist";
    }
    
    @RequestMapping("/queryDetails")
    public String details(Model model,String cardNum) {
        List<AccountDetail> details = service.getDetailsByCardNum(cardNum);
        model.addAttribute("AccountDetail", details);
        return "disposit/details";
    }
}