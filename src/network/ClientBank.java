package network;

import java.net.Socket;

import business.Bank;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;

public class ClientBank extends Thread {

	private Socket client;
	private BufferedReader is;
	private PrintWriter os;
	private Bank bank;

	public ClientBank(Socket client, Bank bank) {
		this.client = client;
		this.bank = bank;
	}

	public void run() {
		try {
			this.is = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
			this.os = new PrintWriter(this.client.getOutputStream(), true);
			while (true) {
				this.showMenu();
				int opt = Integer.parseInt(this.is.readLine());
				switch (opt) {
				case 1:
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void showMenu() throws IOException {
		this.os.println("Bem-vindo ao banco Central!\nSelecione uma das opcoes:");
		this.os.flush();
		this.os.println("1 - Criar Conta:");
		this.os.flush();
		this.os.println("2 - Depositar");
		this.os.flush();
		this.os.println("3 - Sacar");
		this.os.flush();
		this.os.println("4 - Ver Saldo");
		this.os.flush();
	}

	private void createAccount() throws IOException {
		this.os.println("Digite o nome da conta:");
		String name = this.is.readLine();
		System.out.println(name);
	}

}
