package com.revature.driver;

import java.util.List;

import org.apache.log4j.Logger;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.AuthUtil;


public class TestDriver {
	private static Logger log = Logger.getRootLogger();
	//private AuthUtil auth = AuthUtil.instance;
	
	public static void main(String[] args) {

		
		UserDao userDao = UserDao.currentImplementation;
		AccountDao accountDao = AccountDao.currentImplementation;
		
		log.info("testing userList");
		List<User> userList = userDao.findAll();
		userList.forEach(user -> {
			System.out.println(user);
		});
		
		log.info("testing findByUserName");
		System.out.println(userDao.findByUsername("joe"));
		
		log.info("testing findByUsernameAndPassword");
		System.out.println(userDao.findByUsernameAndPassword("john", "uncool"));
		
//		log.info("testing save");
//		User testU = new User(5, "jake", "chill", "customer");
//		System.out.println(userDao.save(testU));
//		userList = userDao.findAll();
//		userList.forEach(user -> {
//			System.out.println(user);
//		});
		
		log.info("testing findByUserId");
		System.out.println(userDao.findById(63));
		

		log.info("testing viewAll accounts");
		List<Account> accountList = accountDao.viewAll();
		accountList.forEach(account -> {
			System.out.println(account);
		});
		
//		log.info("testing add account");
//		Account testA = new Account(1, "hello", userDao.findById(2), 70, true);
//		System.out.println(accountDao.add(testA));
//		accountList = accountDao.viewAll();
//		accountList.forEach(account -> {
//			System.out.println(account);
//		});
		
		log.info("testing close account");
		System.out.println(accountDao.remove(8));
		accountList = accountDao.viewAll();
		accountList.forEach(account -> {
			System.out.println(account);
		});
		
		log.info("testing viewOne account");
		System.out.println(accountDao.viewOne(1));
		
		log.info("logging in");
		AuthUtil auth = AuthUtil.instance;
		auth.login("john", "uncool");
		System.out.println(auth.getCurrentUser());
		
		log.info("testing viewOwned accounts");
		List<Account> ownedAccounts = accountDao.viewOwned();
		ownedAccounts.forEach(account -> {
			System.out.println(account);
		});
	}
}
