package com.revature.prompt;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDao;
import com.revature.dao.UserDao;
import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.AuthUtil;

public class MainMenuPrompt implements Prompt {

	private Scanner scan = new Scanner(System.in);
	private UserDao userDao = UserDao.currentImplementation;
	private AccountDao accountDao = AccountDao.currentImplementation;
	private AuthUtil auth = AuthUtil.instance;
	
	public Prompt run() {
		//get user info
		//see if user is admin
		String userRole = auth.getCurrentUser().getRole();
		
		//1 to add account
		//2 to remove account
		//3 to view accounts
		//4 to deposit
		//5 to withdraw
		//6 to view transaction history
		//7 to logout
		
		//super secret methods!!!!!!
		//8 to view all users
		//9 to view all accounts
		//10 to delete account
		
		System.out.println("Press 1 to add account");
		System.out.println("Press 2 to remove account");
		System.out.println("Press 3 to view accounts");
		System.out.println("Press 4 to make a deposit");
		System.out.println("Press 5 to make a withdrawal");
		System.out.println("Press 6 to view transaction history");
		System.out.println("Press 7 to logout");
		
		if(userRole.equals("admin")) {
			System.out.println("super secret admin privileges");
			System.out.println("Press 8 to view all users");
			System.out.println("Press 9 to view all accounts");
			System.out.println("Press 10 to delete user");
		}
		
		//get user input
		String input = scan.nextLine();
		switch (input) {
		case "1":
			//add account
			return new AddAccountPrompt();
		case "2":
			//remove account
			System.out.println("not implemented");
			break;
		case "3":
			//view accounts
			System.out.println("not implemented");
			break;
		case "4":
			//make deposit
			System.out.println("not implemented");
			break;
		case "5":
			//make withdrawal
			System.out.println("not implemented");
			break;
		case "6":
			//view transaction history
			System.out.println("not implemented");
			break;
		case "7":
			//logout
			System.out.println("not implemented");
			break;
		case "8":
			//view all users
			//only if admin
			if(userRole.equals("admin")) {
				List<User> userList = userDao.findAll();
				for(User u: userList) {
					System.out.println(u);
				}
			}
			else {
				System.out.println("stay out!");
			}
			break;
		case "9":
			//view all accounts	
			//only if admin
			if(userRole.equals("admin")) {
				List<Account> accountList = accountDao.viewAll();
				for(Account a: accountList) {
					System.out.println(a);
				}
			}
			else {
				System.out.println("stay out!");
			}
			break;
		case "10":
			//delete user
			System.out.println("not implemented");
			break;
		default:
			System.out.println("enter a valid option");
			break;
		}
		// TODO Auto-generated method stub
		return new MainMenuPrompt();
	}

}
