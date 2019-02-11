package interfaces;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRMIServerBank extends Remote {
	boolean createAccount(int id) throws RemoteException;

	String findAccountById(int id) throws RemoteException;

	boolean changeAccountBalance(int id, double value) throws RemoteException;

	boolean deleteAccount(int id) throws RemoteException;
}
