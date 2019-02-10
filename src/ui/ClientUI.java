package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import business.Account;
import interfaces.IAccountDAO;

public class ClientUI {// Classe base para as implementações do usuário (lado client)

	private BufferedReader sin;
	private IAccountDAO accountDAO; // Classe abstrata para as operações remotas de Account

	public ClientUI(IAccountDAO accountDAO) {
		this.sin = new BufferedReader(new InputStreamReader(System.in));
		this.accountDAO = accountDAO;
	}

	public void execute() throws IOException {
		while (true) {
			this.showMenu();
			int opt = Integer.parseInt(sin.readLine());
			boolean resultOperation = false;
			int idAccount = this.getIdFromUser();
			switch (opt) {
			case 1:
				resultOperation = this.accountDAO.save(idAccount);
				break;
			case 2:
				resultOperation = this.accountDAO.changeAccount(idAccount, this.getValueFromUser());
				break;
			case 3:
				resultOperation = this.accountDAO.changeAccount(idAccount, -this.getValueFromUser());
				break;
			case 4:
				resultOperation = this.accountDAO.removeAccount(idAccount);
				break;
			default:
				System.out.println("Opção inválida");
			}
			if (resultOperation) {
				System.out.println("Operaçao realizada com sucesso");
				System.out.println(this.accountDAO.findAccountById(idAccount));
			}
		}
	}

	private double getValueFromUser() throws IOException {
		System.out.println("Digite o valor:");
		return Double.parseDouble(this.sin.readLine());
	}

	private int getIdFromUser() throws IOException {
		System.out.println("Digite o Id da conta");
		return Integer.parseInt(this.sin.readLine());

	}

	/*
	 * Os métodos createAccount/changeAccount/deleteAccount retornam uma conta,
	 * utilizados pelas implementações de IAccountDAO
	 */

	/*
	 * private Account createAccount() throws IOException {
	 * System.out.println("Digite o nome da conta:"); String name = sin.readLine();
	 * System.out.println("Conta criada com sucesso!"); return new Account(name); }
	 */

	/*
	 * private Account changeAccountBalance() throws IOException { Account
	 * foundAccount = findAccountById(); if (foundAccount != null) {
	 * System.out.println("Digite o valor:");
	 * this.accountDAO.changeAccount(foundAccount,
	 * (Double.parseDouble(sin.readLine()))); return
	 * findAccountById(foundAccount.getId()); /* Consulta o objeto no servidor, após
	 * a creditação/debitação, pois o objeto "findAccount" neste método está
	 * desatualizado
	 *
	 * } System.out.println("ID não encontrado"); return null; }
	 */

	protected void showMenu() {
		String message = "Selecione uma das opcoes:\n" + "1 - Criar Conta\n2 - Depositar\n3 - Sacar\n4 - Ver Saldo\n";
		System.out.println(message);
	}
}
