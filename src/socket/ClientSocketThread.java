package socket;

import java.net.Socket;

import business.Account;
import business.Bank;
import interfaces.IAccountDAO;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ClientSocketThread extends Thread {

	private Socket client;
	private BufferedReader is;
	private PrintWriter os;
	private Bank bank;

	public ClientSocketThread(Socket client, Bank bank) throws IOException {
		this.client = client;
		this.bank = bank;
		this.is = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		this.os = new PrintWriter(this.client.getOutputStream(), true);
	}

	public void run() {

		while (true) {

			try {
				/*
				 * 1 - Identificar opção
				 */
				int opt;
				opt = this.is.read();
				int idAccount = this.is.read();

				switch (opt) {
				case 1:
					this.os.println(this.bank.addAccount(idAccount));
					break;
				case 2:
					this.os.println(
							this.bank.changeAccountBalance(idAccount, Double.parseDouble(this.is.readLine())));
					break;
				case 3:
					this.os.println(
							this.bank.changeAccountBalance(idAccount, -Double.parseDouble(this.is.readLine())));
					break;
				case 4:
					this.os.println(this.bank.removeAccount(idAccount));
					break;
				case 5:
					this.os.println(this.bank.findAccountById(idAccount));
				}
			} catch (NumberFormatException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

}
