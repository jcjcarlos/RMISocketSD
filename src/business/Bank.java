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
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public boolean createAccount(Account account) {
		if (this.accounts.size() <= 10) {
			account.setId(random.nextInt(10) + 1);
			this.accounts.add(account);
			return true;
		}
		return false;
		
	}

	public boolean changeAccountBalance(Account account, double value) {
		Account accountFound = this.findAccountById(account);
		if (accountFound != null)
			return accountFound.changeBalance(value);
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

	public Account findAccountById(Account account) {
		for (Account foundAccount : accounts)
			if (foundAccount.getId() == account.getId())
				return account;
		return null;
	}
}
