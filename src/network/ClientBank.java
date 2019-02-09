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
				case 0:
					this.createAccount();
					break;
				}
				break;
			}
		} catch (IOException e) {
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

	private void createAccount() throws IOException {
		this.os.println("Digite o nome da conta:");
		this.os.flush();
		String name = this.is.readLine();
		System.out.println(name);
		this.os.println("exit");
		this.os.flush();
	}

}
