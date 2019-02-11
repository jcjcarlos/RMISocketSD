package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import business.Bank;

public class SocketServerBank {
	private ServerSocket serverSocket;
	private Semaphore semaphore;
	private Bank bank;

	public SocketServerBank(Bank bank, Semaphore semaphore) {
		try {
			this.serverSocket = new ServerSocket(9000);
			this.semaphore = semaphore;
			this.bank = bank;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void start() {

		while (true) {
			try {
				new ClientThreadBank(serverSocket.accept(), this.bank, this.semaphore).start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				break;

			}
		}
	}
}
