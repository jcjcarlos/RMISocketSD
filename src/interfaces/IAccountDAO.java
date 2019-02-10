package interfaces;

import java.io.IOException;

import business.Account;

public interface IAccountDAO {

	public abstract boolean save(int id);

	public abstract Account findAccountById(int id) throws IOException;

	public abstract boolean changeAccount(int id, double value);

	public abstract boolean removeAccount(int id) ;

}
