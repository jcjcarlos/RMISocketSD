package business;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private static Bank bank = null;
	private String name;
	private List<Account> accounts;

	private Bank() {
		this.name = "Central";
		this.accounts = new ArrayList<Account>();
	}
	
	public static Bank getInstance() {
		if(bank == null){
			bank = new Bank();
		}
		return bank;
	}

	public void createAccount(Account account) {
		this.accounts.add(account);
	}

	public boolean deleteAccount(Account account) {
		return this.accounts.remove(account);
	}

	public List<Account> findAccountByName(String name) {
		List<Account> accounts = new ArrayList<Account>();
		for (Account count : this.accounts)
			if (count.getOwner() == name)
				accounts.add(count);
		return accounts;
	}
	
	public List<Account> findAccountById(int id){
		List<Account> accounts = new ArrayList<Account>();
		for(Account account: accounts)
			if (account.getId() == id)
				accounts.add(account);
		return accounts;
	}
}
