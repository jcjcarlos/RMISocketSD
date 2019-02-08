package network;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import business.Bank;

public class ServerBank {
	private ServerSocket serverSocket;

	public ServerBank() {
		try {
			this.serverSocket = new ServerSocket(5456);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() throws IOException {
		Bank bank = Bank.getInstance();
		while (true) {
			new ClientBank(serverSocket.accept(),bank).start();
		}
	}
}
