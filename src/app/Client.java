package app;

import ui.ClientUI;

import java.io.IOException;

import clientRemote.*;
import interfaces.IRemoteBank;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			IRemoteBank remoteBank = new RMIClientBank(); 
			new ClientUI(remoteBank).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

	}

}
