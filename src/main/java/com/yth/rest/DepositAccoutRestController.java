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

import com.yth.entity.AccountDetail;
import com.yth.entity.DepositAccount;
import com.yth.service.DepositAccountService;

/**
 * DepositAccoutController
 * 
 * @author yth
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/bank/deposit")
public class DepositAccoutRestController {

	/**
	 * 日志
	 */
	private static final Logger logger = LoggerFactory.getLogger(DepositAccoutRestController.class);

	/**
	 * The service.
	 */
	@Autowired
	private DepositAccountService service;

	/**
	 * List string.
	 *
	 * @return the string
	 */
	@RequestMapping(value = "/alllist", method = RequestMethod.GET)
	public List<DepositAccount> list() {
		List<DepositAccount> deposit = service.getAllDeposit();

		return deposit;
	}

	/**
	 * queryDeposit
	 * 
	 * @param cardNum
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/queryDeposit", method = RequestMethod.GET)
	public List<DepositAccount> queryDeposit(@RequestParam("cardNum") String cardNum,
			@RequestParam("password") String password) {

		List<DepositAccount> deposit = service.findDepositByCardNum(cardNum, password);

		return deposit;
	}

	/**
	 * add account
	 * 
	 * @param deposit
	 * @return
	 */
	@RequestMapping(value = "/addaccount", method = RequestMethod.POST)
	public int add(@RequestBody DepositAccount deposit) {
		int result = service.createDepositAccount(deposit);
		return result;
	}

	/**
	 * to Deposit
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/toDeposit", method = RequestMethod.GET)
	public DepositAccount toDeposit(@RequestParam("id") int id) {
		DepositAccount deposit = service.findDepositById(id);

		return deposit;
	}

	/**
	 * deposit
	 * 
	 * @param deposit
	 * @return
	 */
	@RequestMapping(value = "/deposit", method = RequestMethod.POST)
	public DepositAccount deposit(@RequestBody DepositAccount deposit) {
		DepositAccount deposit1 = service.deposit(deposit);

		return deposit1;
	}

	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/toDraw", method = RequestMethod.GET)
	public DepositAccount toDraw(@RequestParam("id") int id) {
		DepositAccount deposit = service.findDepositById(id);
		return deposit;
	}

	/**
	 * draw
	 * 
	 * @param deposit
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/draw", method = RequestMethod.POST)
	public DepositAccount draw(@RequestBody DepositAccount deposit) throws Exception {
		DepositAccount deposit1 = service.draw(deposit);

		return deposit1;
	}

	@RequestMapping(value = "/queryDetails", method = RequestMethod.GET)
	public List<AccountDetail> details(@RequestParam("cardNum") String cardNum) {
		List<AccountDetail> details = service.getDetailsByCardNum(cardNum);
		return details;
	}

}