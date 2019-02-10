package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import business.Bank;

public class ServerSocketBank {
	private ServerSocket serverSocket;
	private Semaphore semaphore;

	public ServerSocketBank() {
		try {
			this.serverSocket = new ServerSocket(5456);
			this.semaphore = new Semaphore();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {
		Bank bank = Bank.getInstance();

		while (true) {
			try {
				new ClientSocketThread(serverSocket.accept(), bank).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;

			}
		}
	}
}
