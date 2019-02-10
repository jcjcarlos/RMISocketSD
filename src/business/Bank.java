package business;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bank {
	private static Bank bank = null;
	private List<Account> accounts;

	private Bank() {
		this.accounts = new ArrayList<Account>();
		this.accounts.add(new Account(1));
		this.accounts.get(0).changeBalance(100);

	}

	public static Bank getInstance() {
		if (bank == null) {
			bank = new Bank();
		}
		return bank;
	}

	public boolean addAccount(int id) {
		if (this.accounts.size() <= 10) {
			return this.accounts.add(new Account(id));
		}
		return false;

	}

	public boolean changeAccountBalance(int id, double value) {
		Account accountFound = this.findAccountById(id);
		if (accountFound != null)
			return accountFound.changeBalance(value);

		return false;
	}

	public boolean removeAccount(int id) {
		Account account = this.findAccountById(id);
		System.out.println("Conta encontrada para remover:\n"+account);
		return this.accounts.remove(account);
		
	}

	public List<Account> findAccountByName(String name) {
		List<Account> accounts = new ArrayList<Account>();
		for (Account count : this.accounts)
			if (count.getOwner() == name)
				accounts.add(count);
		return accounts;
	}

	public Account findAccountById(int id) {
		for (Account foundAccount : accounts)
			if (foundAccount.getId() == id)
				return foundAccount;
		return null;
	}
}
