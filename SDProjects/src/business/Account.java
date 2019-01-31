package business;

import java.util.Random;

public class Account {
	private int id;
	private String owner;
	private double balanceAccount = 0.0;
	private static String[] names = {"Fulano","Cicrano","Beltrano"};
	
	public Account() {
		Random random = new Random();
		this.id = random.nextInt(1000000) + 1;
		this.owner = names[random.nextInt(3)];
		this.balanceAccount = 0.0;
	}
	
	public Account(String name, double value) {
		this.generateId();
		this.owner = name;
		this.balanceAccount = value;
	}
	
	public Account(String name) {
		this.generateId();
		this.owner = name;
	}
	
	private void generateId() {
		Random random = new Random();
		this.id = random.nextInt(1000000);
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getOwner() {
		return this.owner;
	}
}
