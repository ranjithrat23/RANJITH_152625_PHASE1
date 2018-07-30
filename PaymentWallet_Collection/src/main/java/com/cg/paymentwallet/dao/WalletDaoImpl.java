package com.cg.paymentwallet.dao;

import java.util.HashMap;

import com.cg.paymentwallet.Exception.IWalletException;
import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dto.Wallet;

public class WalletDaoImpl implements IWalletDao {
	private static HashMap<String, Wallet> map = null;

	static {
		map = new HashMap<String, Wallet>();
	}

	public void createAccount(Wallet wallet) {
		map.put(wallet.getUserId(), wallet);

	}

	public double showBalance(String userId) {
		return map.get(userId).getBalance();
	}

	public Wallet findCustomer(String userId) {
		return map.get(userId);
	}

	public Wallet login(String id, String password) throws WalletException {
		Wallet wall = null;
		if (map.containsKey(id) && map.get(id).getPassword().equals(password)) {
			wall = map.get(id);
		} else
			throw new WalletException(IWalletException.ERROR5);
		return wall;
	}
}
