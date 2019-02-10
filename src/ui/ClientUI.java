package ui;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import business.Account;
import interfaces.IAccountDAO;

public class ClientUI {// Classe base para as implementações do usuário (lado client)

	private Scanner sin;
	private IAccountDAO accountDAO; // Classe abstrata para as operações remotas de Account

	public ClientUI(IAccountDAO accountDAO) {
		this.sin = new Scanner(System.in);
		this.accountDAO = accountDAO;
	}

	public void execute() throws IOException {
		int opt;
		int idAccount;

		do {
			this.showMenu();
			opt = sin.nextInt();
			if (opt == 0) {
				System.out.println("Saindo da aplicação");
				break;
			}
			boolean resultOperation = false;
			idAccount = this.getIdFromUser();
			switch (opt) {
			case 0:
				break;
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
				System.out.println("\n" + this.accountDAO.findAccountById(idAccount) + "\n");
			} else {
				System.out.println("Não foi possivel realizar a operação");
			}
		} while (opt != 0);
	}

	private double getValueFromUser() throws IOException {
		System.out.println("Digite o valor:");
		return this.sin.nextDouble();
	}

	private int getIdFromUser() throws IOException {
		System.out.println("Digite o Id da conta");
		return this.sin.nextInt();

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
		String message = "\n\nSelecione uma das opcoes:\n" + "1 - Criar Conta\n2 - Depositar\n"
				+ "3 - Sacar\n4 - Cancelar Conta\n0 - Sair";
		System.out.println(message);
	}
}
