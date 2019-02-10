package app;

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

public class Client {
	// Classe asbtrata para ClientSocket, ClientRMI e ClienteDLL
	static BufferedReader is;// Leitura de informações do servidor
	static BufferedReader sin;// Leitura do teclado
	static PrintWriter os;// Escrita de informações para o servidor
	static ObjectOutputStream oos;// Escrita de uma conta criada
	static ObjectInputStream ois;// Leitura de contas alteradas/deletadas

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket client = new Socket("localhost", 5456);
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			sin = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintWriter(client.getOutputStream(), true);
			oos = new ObjectOutputStream(client.getOutputStream());

			showMenu();
			switch (Integer.parseInt(sin.readLine())) {
			case 1:
				oos.writeObject(createAccount());
				break;
			case 2:
				break;
			}

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
	 * Os métodos create/report/delete sempre retornam uma conta, para serem
	 * reaproveitados na comunicação via Socket, RMI ou DLL
	 */

	private static Account createAccount() throws IOException {
		System.out.println("Digite o nome da conta:");
		String name = sin.readLine();
		System.out.println("Conta criada com sucesso!");
		return new Account(name);
	}

	private static Account findAccountById() throws IOException {
		System.out.println("Digite o ID da conta");
		return findAccountById(Integer.parseInt(sin.readLine()));
	}
	/*
	 * O método findAccountById foi sobrescrito, com isso é possível encontrar uma
	 * conta no banco solicitada pelo usuário, antes e imediatamente após ser
	 * acreditado/debitado o valor,
	 */

	private static Account findAccountById(int id) throws IOException {
		os.println(id);
		try {
			return (Account) ois.readObject();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	private static Account reportAccountAmount() throws IOException {
		Account findAccount = findAccountById();
		if (findAccount != null) {
			System.out.println("Digite o valor:");
			os.println(Double.parseDouble(sin.readLine()));
			return findAccountById(findAccount.getId());
			/*
			 * Consulta o objeto no servidor, após a creditação/debitação, pois o objeto
			 * "findAccount" neste método está desatualizado
			 */
		}
		System.out.println("ID não encontrado");
		return null;
	}

	private static Account deleteAccount() throws IOException {
		return findAccountById();
	}

	private static void showMenu() throws IOException {
		String message = "Bem-vindo ao banco Central!\nSelecione uma das opcoes:\n"
				+ "1 - Criar Conta\n2 - Depositar\n3 - Sacar\n4 - Ver Saldo\n";
		System.out.println(message);
	}

}
