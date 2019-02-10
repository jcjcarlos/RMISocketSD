package socket;

import java.net.Socket;

import business.Account;
import business.Bank;
import interfaces.IClientRemote;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ClientThread extends Thread implements IClientRemote {

	private Socket client;
	private BufferedReader is;
	private PrintWriter os;
	private ObjectInputStream ois;
	private Bank bank;
	private ObjectOutputStream oos;

	public ClientThread(Socket client, Bank bank) throws IOException {
		this.client = client;
		this.bank = bank;
		this.is = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
		this.os = new PrintWriter(this.client.getOutputStream(), true);
		this.ois = new ObjectInputStream(this.client.getInputStream());
		this.oos = new ObjectOutputStream(this.client.getOutputStream());

	}

	public void run() {

		while (true) {

			try {
				int opt;
				opt = Integer.parseInt(this.is.readLine());
				switch (opt) {
				case 1:
					this.saveAccount((Account) ois.readObject());
					break;
				case 2:
					this.changeAccount((Account) ois.readObject(), Double.parseDouble(is.readLine()));
					break;
				case 3:
					this.changeAccount((Account) ois.readObject(), -Double.parseDouble(is.readLine()));
					break;
				case 4:
					this.removeAccount((Account) ois.readObject());
					break;
				}
			} catch (NumberFormatException | ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void saveAccount(Account account) {
		// TODO Auto-generated method stub
		this.os.println(Boolean.toString(this.bank.createAccount(account)));

	}

	@Override
	public Account changeAccount(Account account, double value) {
		// TODO Auto-generated method stub
		if (this.bank.changeAccountBalance(account, value))
			this.os.println(Boolean.toString(true));
		else
			this.os.println(Boolean.toString(false));
		return this.bank.findAccountById(account);

	}

	@Override
	public void removeAccount(Account account) {
		// TODO Auto-generated method stub
		this.os.println(Boolean.toString(this.bank.deleteAccount(account)));

	}

}
