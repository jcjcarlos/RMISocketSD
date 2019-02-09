package app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import business.Account;

public class Client {

	static BufferedReader is;
	static BufferedReader sin;
	static PrintWriter os;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket client = new Socket("localhost", 5456);
			is = new BufferedReader(new InputStreamReader(client.getInputStream()));
			sin = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintWriter(client.getOutputStream(), true);

			char[] message = new char[1000];
			is.read(message);
			String opt = new String(message);
			System.out.println(opt);

			while (!opt.equals("exit")) {
				os.println(sin.readLine());
				opt = is.readLine();
				System.out.println("Resposta: " + opt);
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private Account createAccount() throws IOException {
		System.out.println("Digite o nome da conta:");
		String name = sin.readLine();
		System.out.println("Digite o valor da conta:");
		double value = Double.parseDouble(sin.readLine());
		System.out.println("Conta criada com sucesso!");
		return new Account(name,value);
	}

}
