package app;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

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
			
			while(opt != "exit") {
				os.println(sin.readLine());
				opt = is.readLine();
				System.out.println("Resposta: "+opt);
				System.out.println(opt.length());
			}
			
			System.out.println("String null detectada");
			

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
