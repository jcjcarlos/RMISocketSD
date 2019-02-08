package network;

import java.net.Socket;

import business.Bank;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;

public class ClientBank extends Thread {

	private Socket client;
	private DataInputStream is;
	private PrintStream os;
	private Bank bank;

	public ClientBank(Socket client, Bank bank) {
		this.client = client;
		this.bank = bank;
	}

	public void run() {
		try {
			this.is = new DataInputStream(this.client.getInputStream());
			this.os = new PrintStream(this.client.getOutputStream());
			while (true) {
				os.println("Bem-vindo ao banco Central!\nSelecione uma das opcoes:");
				this.showMenu();
				int opt = Integer.parseInt(this.is.readUTF());
				switch (opt) {
				case 1:
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showMenu() {
		this.os.println("1 - Criar Conta:");
		this.os.println("2 - Depositar");
		this.os.println("3 - Sacar");
		this.os.println("4 - Ver Saldo");
		this.os.flush();
	}

	private void createAccount() {
		this.os.println("Digite o nome da conta:");
		String nameAccount = this.is.
	}

}
