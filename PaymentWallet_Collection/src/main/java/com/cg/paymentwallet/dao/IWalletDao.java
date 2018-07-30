package com.cg.paymentwallet.dao;

import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dto.Wallet;

public interface IWalletDao {

	public abstract void createAccount(Wallet wallet);

	public abstract double showBalance(String userId);
	
	public abstract Wallet findCustomer(String userId); 

	public abstract Wallet login(String id, String password) throws WalletException;
}
