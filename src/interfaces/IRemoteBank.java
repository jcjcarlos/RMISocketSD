package interfaces;

import java.io.IOException;
import java.rmi.Remote;

import business.Account;

public interface IRemoteBank extends Remote {

	 boolean save(int id);

	 String findAccountById(int id) throws IOException;

	 boolean changeAccount(int id, double value);

	 boolean removeAccount(int id);
	
	 void close();

}
