package app;

import ui.ClientUI;

import java.io.IOException;

import dao.*;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			new ClientUI(new AccountSocketDAO()).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};

	}

}
