package network;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerBank {
	private ServerSocket serverSocket;

	public ServerBank() {
		try {
			this.serverSocket = new ServerSocket(5456);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void start() throws IOException {
		Socket clientSocket = serverSocket.accept(); 
		//PrintStream ps = ;
	}
}
