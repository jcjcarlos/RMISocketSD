package business;

import java.util.Random;

public class Account {
	private int id;
	private String owner;
	private double balanceAccount = 0.0;
	private static String[] names = { "Fulano", "Cicrano", "Beltrano" };

	public Account() {
		this.owner = names[new Random().nextInt(3)];
		this.balanceAccount = 0.0;
	}

	public Account(String name) {
		this.owner = name;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return this.id;
	}

	public String getOwner() {
		return this.owner;
	}

	public boolean creditAccount(double value) {
		this.balanceAccount += value;
		return true;
	}

	public boolean debitAccount(double value) {
		if (this.balanceAccount >= value) {
			this.balanceAccount -= value;
			return true;
		}
		return false;
	}
}
