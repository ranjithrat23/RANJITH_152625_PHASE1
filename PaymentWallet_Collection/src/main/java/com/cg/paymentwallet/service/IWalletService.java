package com.cg.paymentwallet.service;

import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dto.Wallet;

public interface IWalletService {

	public abstract void createAccount(Wallet wallet);

	public abstract double showBalance(String phNumber);

	public abstract boolean deposit(String phNumber, double amount);

	public abstract boolean withdraw(String phNumber, double amount) throws WalletException;

	public abstract boolean fundTransfer(String phSender, String phReceiver, double amount) throws WalletException;

	public abstract boolean validateDetails(Wallet wallet) throws WalletException;

	public abstract Wallet login(String id, String password) throws WalletException;
}
