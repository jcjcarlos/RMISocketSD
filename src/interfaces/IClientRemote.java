package interfaces;

import business.Account;

public interface IClientRemote {

	public abstract void saveAccount(Account account);

	public abstract Account changeAccount(Account account, double value);

	public abstract void removeAccount(Account account);

}
