package network;

import java.net.Socket;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintStream;


public class ClientBank extends Thread {
	
	private Socket client;
	private DataInputStream is;
	
	public ClientBank (Socket client) {
		this.client = client;
	}
	
	public void run() {
		try {
			DataInputStream is = new DataInputStream(this.client.getInputStream());
			PrintStream os = new PrintStream(this.client.getOutputStream());
			while (true) {
				os.println("Bem-vindo ao banco Central");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
