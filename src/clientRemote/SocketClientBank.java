package clientRemote;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import business.Account;
import interfaces.IRemoteBank;

public class SocketClientBank implements IRemoteBank {
	// Classe asbtrata para ClientSocket, ClientRMI e ClienteDLL
	// Os atributos refere-se a ClientSocket
	private DataInputStream is;// Leitura de informações do servidor
	private DataOutputStream os;// Escrita de informações para o servidor
	private Socket client;

	public SocketClientBank() {
		// TODO Auto-generated method stub
		try {
			client = new Socket("localhost", 9000);
			is = new DataInputStream(client.getInputStream());
			os = new DataOutputStream(client.getOutputStream());
			// bin = new BufferedReader(new InputStreamReader(client.getInputStream()));

			/*
			 * O menu de opções esta delegado para a classe ClientUI A classe
			 * AccountSocketDAO apenas implementa as operações definidas em IAccountDAO
			 */

			/*
			 * char[] message = new char[1000]; is.read(message); String opt = new
			 * String(message); System.out.println(opt);
			 * 
			 * 
			 * while (!opt.equals("exit")) { os.write(sin.readLine()); opt = is.readLine();
			 * System.out.println("Resposta: " + opt);
			 */

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/*
	 * O método findAccountById foi sobrescrito, com isso é possível encontrar uma
	 * conta no banco solicitada pelo usuário, antes e imediatamente após ser
	 * acreditado/debitado o valor,
	 */

	@Override
	public boolean save(int id) {
		try {
			this.os.writeInt(1);
			this.os.flush();
			this.os.writeInt(id);
			this.os.flush();
			return this.is.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean changeAccount(int id, double value) {
		// TODO Auto-generated method stub

		try {
			this.os.writeInt(2);
			this.os.writeInt(id);
			this.os.writeDouble(value);
			return this.is.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeAccount(int id) {
		// TODO Auto-generated method stub
		try {
			this.os.writeInt(4);
			this.os.writeInt(id);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			return this.is.readBoolean();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String findAccountById(int id) throws IOException {
		this.os.writeInt(5);
		this.os.writeInt(id);
		int length = this.is.readInt();
		System.out.println(length);
		char[] infoAccount = new char[length];
		for (int i = 0; i < length; i++)
			infoAccount[i] = this.is.readChar();
		return new String(infoAccount);

	}

	@Override
	public void close() {
		try {
			this.os.writeInt(0);
			this.is.close();
			this.os.close();
			this.client.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
