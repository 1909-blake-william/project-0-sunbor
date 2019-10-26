package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;

public class CloseAccountPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private AccountDao accountDao = AccountDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//TODO maybe show owned accounts
		//get id of account to close
		//make sure user input is integer
		//call close account
		System.out.println("enter id of account to close");
		int id = 0;
		if(scan.hasNextInt()) {
			id = scan.nextInt();
			scan.hasNextLine();
		}
		else {
			//if invalid, go back to main menu
			System.out.println("enter a valid id");
			return new MainMenuPrompt();
		}
		int numUpdated = accountDao.remove(id);
		if(numUpdated != 0) {
			System.out.println(accountDao.viewOne(id).getAccountName() + " closed");
		}
		else {
			System.out.println("account failed to close");
		}
		
		return new MainMenuPrompt();
	}

}
