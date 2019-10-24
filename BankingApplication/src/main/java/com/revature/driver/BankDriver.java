package com.revature.driver;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.prompt.LoginPrompt;
import com.revature.prompt.Prompt;

public class BankDriver {
	
	//private static UserDao userDao = UserDao.currentImplementation;
	//private static AccountDao accountDao = AccountDao.currentImplementation;
	
	public static void main(String[] args) {
		
	//	userDao.initializeList();
	//	accountDao.initializeList();
		
		Prompt p = new LoginPrompt();
		
		while(true) {
			p = p.run();
		}
	}

}
