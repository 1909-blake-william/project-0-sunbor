package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.model.Account;
import com.revature.util.AuthUtil;
import com.revature.util.Constants;

public class AddAccountPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private UserDao userDao = UserDao.currentImplementation;
	private AccountDao accountDao = AccountDao.currentImplementation;
	private AuthUtil auth = AuthUtil.instance;
	
	@Override
	public Prompt run() {
		//get account name
		System.out.println("enter account name");
		String accountName = scan.nextLine();
		//check if too long
		if(accountName.length() > Constants.maxTextLength) {
			System.out.println("account name too long");
			return new MainMenuPrompt();
		}
		//get starting balance
		System.out.println("enter starting balance");
		double startingBalance = 0;
		if(scan.hasNextDouble()) {
			startingBalance = scan.nextDouble();
		}
		else {
			System.out.println("enter a number");
			return new MainMenuPrompt();
		}
		//make account object
		Account newAccount = new Account(1, accountName, auth.getCurrentUser(), startingBalance, true);
		//add new account object to dao
		accountDao.add(newAccount);
		// TODO Auto-generated method stub
		return new MainMenuPrompt();
	}

}