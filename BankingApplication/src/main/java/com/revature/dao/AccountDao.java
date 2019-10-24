package com.revature.dao;

import java.util.List;
import java.util.Map;

import com.revature.model.Account;
import com.revature.model.User;

public interface AccountDao {
	
	AccountDao currentImplementation = new AccountDaoSQL();
	
	//add account
	int add(Account a);
	
	//remove account
	int remove(int id);
	
	//view this account
	Account viewOne(int id);
	
	//view all accounts from this user
	List<Account> viewOwned();
	
	//deposit
	void deposit(double amount);
	
	//withdraw
	void withdraw(double amount);
		
	//ADMIN ONLY!!!!!

	//view all accounts
	List<Account> viewAll();
	
	//initialize list so it doesnt break at login
	List<Account> initializeList();
}
