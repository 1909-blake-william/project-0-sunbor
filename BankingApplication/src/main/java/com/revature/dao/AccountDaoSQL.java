package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.model.Account;
import com.revature.model.User;
import com.revature.util.AuthUtil;
import com.revature.util.ConnectionUtil;

public class AccountDaoSQL implements AccountDao {

	private AuthUtil auth = AuthUtil.instance;
	private UserDao userDao = UserDao.currentImplementation;
	
	public Account accountFromSQL(ResultSet rs) throws SQLException {
		//get fields
		int id = rs.getInt("account_id");
		String accountName = rs.getString("account_name");
		int ownerId = rs.getInt("owner_id");
		User owner = userDao.findById(ownerId);
		double balance = rs.getFloat("balance");
		boolean opened = rs.getInt("opened") == 0 ? false : true;
		
		//TODO add to transaction history
		
		//return new account object
		return new Account(id, accountName, owner, balance, opened);
	}
	
	@Override
	public int add(Account a) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO bank_accounts (account_id, account_name, owner_id, balance, opened) "
					+ "VALUES (BANK_ACCOUNTS_ID_SEQ.nextval, ?, ?, ?, ?)";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setString(1, a.getAccountName());
			ps.setInt(2, a.getOwner().getId());
			ps.setFloat(3, (float) a.getBalance());
			ps.setInt(4, 1);
			
			return ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int remove(int id) {
		//TODO add edge cases (already closed, account not found, doesnt own account)
		//close not delete
		//just change the boolean
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "UPDATE bank_accounts SET opened = 0 WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			return ps.executeUpdate();

		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Account viewOne(int id) {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_accounts WHERE account_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return accountFromSQL(rs);
			}
			else {
				return null;
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Account> viewOwned() {

		//get id of current user
		int currentId = auth.getCurrentUser().getId();
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_accounts WHERE owner_id = ?";
			PreparedStatement ps = c.prepareStatement(sql);
			ps.setInt(1, currentId);
			ResultSet rs = ps.executeQuery();
			
			List<Account> accountList = new ArrayList<Account>();
			while(rs.next()) {
				accountList.add(accountFromSQL(rs));
			}
			return accountList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void deposit(double amount) {
		//prevent user from accessing unowned account
		//prevent user from accessing closed account
		//get original balance
		//modify balance
		//update
		//add to transaction history
		try(Connection c = ConnectionUtil.getConnection()){
			//get account id
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw(double amount) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Account> viewAll() {
		try(Connection c = ConnectionUtil.getConnection()){
			String sql = "SELECT * FROM bank_accounts";
			PreparedStatement ps = c.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			List<Account> accountList = new ArrayList<Account>();
			while(rs.next()) {
				accountList.add(accountFromSQL(rs));
			}
			return accountList;
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Account> initializeList() {
		// TODO Auto-generated method stub
		return null;
	}

}
