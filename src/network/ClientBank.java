package network;

import java.net.Socket;

import business.Account;
import business.Bank;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ClientBank extends Thread {

	private Socket client;
	private BufferedReader is;
	private PrintWriter os;
	private ObjectInputStream ois;
	private ObjectOutputStream oos;
	private Bank bank;

	public ClientBank(Socket client, Bank bank) {
		this.client = client;
		this.bank = bank;
	}

	public void run() {
		try {
			this.is = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			this.os = new PrintWriter(this.client.getOutputStream(), true);
			this.ois = new ObjectInputStream(this.client.getInputStream());
			this.oos = new ObjectOutputStream(this.client.getOutputStream());

			while (true) {
				this.showMenu();
				int opt = Integer.parseInt(this.is.readLine());
				switch (opt) {

				case 1:
					this.bank.createAccount((Account) ois.readObject());
					break;
				case 2:
					break;

				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void showMenu() throws IOException {
		String message = "Bem-vindo ao banco Central!\nSelecione uma das opcoes:\n"
				+ "1 - Criar Conta\n2 - Depositar\n3 - Sacar\n4 - Ver Saldo\n";
		this.os.print(message);
		this.os.flush();
	}

}
