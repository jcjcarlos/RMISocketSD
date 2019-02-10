package interfaces;

import java.io.IOException;

import business.Account;

public interface IAccountDAO {

	public abstract boolean save(int id);

	public abstract String findAccountById(int id) throws IOException;

	public abstract boolean changeAccount(int id, double d);

	public abstract boolean removeAccount(int id) ;
	
	public abstract void close();

}
