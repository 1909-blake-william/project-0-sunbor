package com.revature.driver;

import com.revature.prompt.LoginPrompt;
import com.revature.prompt.Prompt;

public class BankDriver {
		
	public static void main(String[] args) {		
		Prompt p = new LoginPrompt();
		
		while(true) {
			p = p.run();
		}
	}

}
