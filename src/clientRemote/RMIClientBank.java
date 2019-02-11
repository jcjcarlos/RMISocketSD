package clientRemote;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.IRMIServerBank;
import interfaces.IRemoteBank;

public class RMIClientBank implements IRemoteBank {
	private Registry registry;
	private IRMIServerBank serverBank;

	public RMIClientBank() {
		try {
			this.registry = LocateRegistry.getRegistry("localhost", 9001);
			this.serverBank = (IRMIServerBank) this.registry.lookup("rmiServer");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean save(int id) {
		// TODO Auto-generated method stub
		try {
			return this.serverBank.createAccount(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String findAccountById(int id) throws IOException {
		// TODO Auto-generated method stub
		return this.serverBank.findAccountById(id);
	}

	@Override
	public boolean changeAccount(int id, double value) {
		// TODO Auto-generated method stub
		try {
			return this.serverBank.changeAccountBalance(id, value);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeAccount(int id) {
		// TODO Auto-generated method stub
		try {
			return this.serverBank.deleteAccount(id);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}

}
