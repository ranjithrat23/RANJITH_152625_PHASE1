package com.cg.paymentwallet.dto;

import java.util.ArrayList;

public class Wallet extends Customer {

	private double balance;
	private String userId;
	private String password;
	private ArrayList<String> transaction = new ArrayList<String>();

	public ArrayList<String> getTransaction() {
		return transaction;
	}

	public void setTransaction(String transaction) {
		this.transaction.add(transaction);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Wallet() {

	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

}