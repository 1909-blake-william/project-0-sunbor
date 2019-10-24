package com.revature.prompt;

import java.util.Scanner;

import com.revature.dao.UserDao;
import com.revature.model.User;
import com.revature.util.Constants;

public class RegisterPrompt implements Prompt { 

	private Scanner scan = new Scanner(System.in);
	private UserDao userDao = UserDao.currentImplementation;
	
	@Override
	public Prompt run() {
		//get username from user
		System.out.println("Input new username");
		String newName = scan.nextLine();
		//check to see that size is not over limit
		if(newName.length() > Constants.maxTextLength) {
			System.out.println("get a nickname");
			return new LoginPrompt();
		}
		
		//check if username is already taken
		if(userDao.findByUsername(newName) != null) {
			//berate user and go back to menu
			System.out.println("be more creative");
			return new LoginPrompt();
		}
		
		//get password
		System.out.println("input new password"); 
		String newPass = scan.nextLine();
		if(newPass.length() > Constants.maxTextLength) {
			System.out.println("no need to be so paranoid");
			return new LoginPrompt();
		}
		
		//ask if admin
		System.out.println("are you an admin? (y/n) dont lie!!!!");
		String rankInput = scan.nextLine();
		String newRank;
		switch (rankInput) {
		case "y":
			newRank = "admin";
			break;
		case "n":
			newRank = "customer";
			break;
		default:
			System.out.println("dont mess with me");
			return new LoginPrompt();
		}
		
		//make new user object
		User newUser = new User(1, newName, newPass, newRank);
		//save user to dao
		userDao.save(newUser);
		
		// TODO Auto-generated method stub
		return new LoginPrompt();
	}

}
