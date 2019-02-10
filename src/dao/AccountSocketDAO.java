package dao;

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
import interfaces.IAccountDAO;

public class AccountSocketDAO implements IAccountDAO {
	// Classe asbtrata para ClientSocket, ClientRMI e ClienteDLL
	// Os atributos refere-se a ClientSocket
	private BufferedReader is;// Leitura de informações do servidor
	private PrintWriter os;// Escrita de informações para o servidor
	private ObjectOutputStream oos;// Escrita de uma conta criada
	private ObjectInputStream ois;// Leitura de contas alteradas/deletadas

	public AccountSocketDAO() {
		// TODO Auto-generated method stub
		try {
			Socket client = new Socket("localhost", 5456);
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			os = new PrintWriter(client.getOutputStream(), true);

			/*
			 * O menu de opções esta delegado para a classe ClientUI A classe
			 * AccountSocketDAO apenas implementa as operações definidas em IAccountDAO
			 */

			/*
			 * char[] message = new char[1000]; is.read(message); String opt = new
			 * String(message); System.out.println(opt);
			 * 
			 * 
			 * while (!opt.equals("exit")) { os.println(sin.readLine()); opt =
			 * is.readLine(); System.out.println("Resposta: " + opt);
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
		this.os.println(1);
		try {
			this.os.println(id);
			return Boolean.parseBoolean(this.is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean changeAccount(int id, double value) {
		// TODO Auto-generated method stub
		if (value > 0.0)
			this.os.println(2);
		else
			this.os.println(3);
		try {
			this.os.println(id);
			this.os.println(value);
			return Boolean.parseBoolean(this.is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean removeAccount(int id) {
		// TODO Auto-generated method stub
		this.os.println(4);
		this.os.println(id);
		try {
			return Boolean.parseBoolean(this.is.readLine());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public Account findAccountById(int id) throws IOException {
		this.os.println(5);
		this.os.println(id);
		try {
			return (Account) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
