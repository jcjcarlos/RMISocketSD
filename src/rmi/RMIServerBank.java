package rmi;

import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.Semaphore;

import business.Bank;
import interfaces.IRMIServerBank;

public class RMIServerBank extends UnicastRemoteObject implements IRMIServerBank {

	private Registry registry;
	private Bank bank;
	private Semaphore semaphore;

	public RMIServerBank(Bank bank, Semaphore semaphore) throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
		this.registry = LocateRegistry.createRegistry(9001);
		this.registry.rebind("rmiServer", this);
		this.bank = bank;
		this.semaphore = semaphore;

	}

	@Override
	public boolean createAccount(int id) {
		// TODO Auto-generated method stub
		return this.bank.addAccount(id);
	}

	@Override
	public String findAccountById(int id) {
		// TODO Auto-generated method stub
		return String.valueOf(this.bank.findAccountById(id));
	}

	@Override
	public boolean changeAccountBalance(int id, double value) {
		// TODO Auto-generated method stub
		return this.bank.changeAccountBalance(id, value);
	}

	@Override
	public boolean deleteAccount(int id) {
		// TODO Auto-generated method stub
		return this.bank.removeAccount(id);
	}

}
