package business;

import java.util.ArrayList;
import java.util.List;

public class Bank {
	private String name;
	private List<Account> accounts;

	public Bank() {
		this.name = "Central";
		this.accounts = new ArrayList<Account>();
	}

	public void createAccount(String name) {
		this.accounts.add(new Account(name));
	}

	public boolean deleteAccount(int id) {
		for (Account count : this.accounts)
			if (count.getId() == id) {
				this.accounts.remove(count);
				return true;
			}
		return false;
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
