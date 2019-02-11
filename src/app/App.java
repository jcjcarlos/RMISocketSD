package app;

import java.rmi.RemoteException;
import java.util.concurrent.Semaphore;

import business.Bank;
import rmi.RMIServerBank;
import socket.SocketServerBank;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Semaphore semaphore = new Semaphore(0);
		Bank bank = Bank.getInstance();
		/*
		 * SocketServerBank sb = new SocketServerBank(); sb.start();
		 */
		
		try {
			RMIServerBank rsb = new RMIServerBank(bank,semaphore);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
