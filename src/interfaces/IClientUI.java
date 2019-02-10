package interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import business.Account;

public abstract class IClientUI {

	private BufferedReader sin;

	public IClientUI() {
		this.sin = new BufferedReader(new InputStreamReader(System.in));
	}

	protected Account findAccountById() throws IOException {
		System.out.println("Digite o Id da conta");
		return this.findAccountById(Integer.parseInt(sin.readLine()));
	};

	protected abstract Account findAccountById(int id) throws IOException;
}
