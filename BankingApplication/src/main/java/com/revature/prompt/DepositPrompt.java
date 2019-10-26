package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;

public class DepositPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//TODO possibly display owned accounts
		//get id of account to deposit in
		//get amount to deposit
		//check if id and amount are valid numbers
		//call deposit method

		System.out.println("input id of account to deposit in");
		int id = 0;
		if(scan.hasNextInt()) {
			id = scan.nextInt();
			scan.nextLine();
		}
		else {
			System.out.println("enter a valid id");
			return new MainMenuPrompt();
		}
		
		System.out.println("input amount to deposit");
		double amount = 0;
		if(scan.hasNextDouble()) {
			amount = scan.nextDouble();
			scan.nextLine();
		}
		else {
			System.out.println("enter a valid amount");
			return new MainMenuPrompt();
		}
		
		int numUpdated = accountDao.deposit(id, amount);
		if(numUpdated != 0) {
			System.out.println("deposited " + amount + " into " + accountDao.viewOne(id).getAccountName());
		}
		else {
			System.out.println("deposit failed");
		}

		return new MainMenuPrompt();
	}

}
