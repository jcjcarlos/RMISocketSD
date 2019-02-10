package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
	private static Bank bank = null;
	private Random random;
	private String name;
	private List<Account> accounts;

	private Bank() {
		this.name = "Central";
		this.accounts = new ArrayList<Account>();
		this.random = new Random();
	}
	
	public static Bank getInstance() {
		if(bank == null){
			bank = new Bank();
		}
		return bank;
	}

	public void createAccount(Account account) {
		account.setId(random.nextInt(1000) + 1);
		this.accounts.add(account);
	}
	
	public boolean depositAccount(int id, double value) {
		Account accountFound = this.findAccountById(id);
		if (accountFound != null)
			return accountFound.creditAccount(value);
		return false;
	}
	
	public boolean takeOutAccount(int id, double value, int password) {
		return false;
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
	
	public Account findAccountById(int id){
		for(Account account: accounts)
			if (account.getId() == id)
				return account;
		return null;
	}
}
