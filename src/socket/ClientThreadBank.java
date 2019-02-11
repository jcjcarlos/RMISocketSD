package socket;

import java.net.Socket;
import java.util.concurrent.Semaphore;

import business.Account;
import business.Bank;
import interfaces.IRemoteBank;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ClientThreadBank extends Thread {

	private Socket client;
	private DataInputStream is;
	private DataOutputStream os;
	private Bank bank;

	public ClientThreadBank(Socket client, Bank bank, Semaphore semaphore) throws IOException {
		this.client = client;
		this.bank = bank;
		this.is = new DataInputStream(this.client.getInputStream());
		// this.pos = new PrintWriter(this.client.getOutputStream());
		this.os = new DataOutputStream(this.client.getOutputStream());

	}

	public void run() {
		int opt = 0;
		do {
			try {

				opt = this.is.readInt();
				if (opt == 0)
					break;

				int idAccount = this.is.readInt();
				switch (opt) {
				case 1:
					this.os.writeBoolean(this.bank.addAccount(idAccount));
					this.os.flush();
					break;
				case 2:
				case 3:
					this.os.writeBoolean(this.bank.changeAccountBalance(idAccount, this.is.readDouble()));
					this.os.flush();
					break;
				case 4:
					this.os.writeBoolean(this.bank.removeAccount(idAccount));
					break;
				case 5:
					String infoAccount = String.valueOf(this.bank.findAccountById(idAccount));
					System.out.println(infoAccount);
					this.os.writeInt(infoAccount.length());
					this.os.flush();
					char[] charInfoAccount = new char[infoAccount.length()];
					this.os.writeChars(infoAccount);
					this.os.flush();

				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} while (opt != 0 || !this.client.isClosed());

	}

	private void closeThread() throws IOException {
		this.is.close();
		this.os.close();
		this.client.close();

	}

}
