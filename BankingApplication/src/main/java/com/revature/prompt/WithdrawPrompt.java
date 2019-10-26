package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.TransactionDao;

public class WithdrawPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	private TransactionDao transactionDao = TransactionDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//TODO possibly display owned accounts
		//get id of account to withdraw from
		//get amount to withdraw
		//check if id and amount are valid numbers
		//call deposit method

		System.out.println("input id of account to withdraw");
		int id = 0;
		if(scan.hasNextInt()) {
			id = scan.nextInt();
			scan.nextLine();
		}
		else {
			System.out.println("enter a valid id");
			return new MainMenuPrompt();
		}
		
		System.out.println("input amount to withdraw");
		double amount = 0;
		if(scan.hasNextDouble()) {
			amount = scan.nextDouble();
			scan.nextLine();
		}
		else {
			System.out.println("enter a valid amount");
			return new MainMenuPrompt();
		}
		
		int numUpdated = accountDao.withdraw(id, amount);
		if(numUpdated != 0) {
			System.out.println("withdrew " + amount + " from " + accountDao.viewOne(id).getAccountName());

			transactionDao.addTransaction(id, -amount);
		}
		else {
			System.out.println("withdraw failed");
		}

		//TODO possibly close account if balance goes below 0
		
		return new MainMenuPrompt();
	}

}
