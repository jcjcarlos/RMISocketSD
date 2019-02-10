package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import business.Bank;

public class ServerSocketBank {
	private ServerSocket serverSocket;

	public ServerSocketBank() {
		try {
			this.serverSocket = new ServerSocket(5456);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		Bank bank = Bank.getInstance();
		
		while (true) {
			try {
				new ClientThread(serverSocket.accept(),bank).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
