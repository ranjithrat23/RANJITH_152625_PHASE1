package com.cg.paymentwallet.service;

import java.time.LocalDateTime;

import com.cg.paymentwallet.Exception.IWalletException;
import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dao.IWalletDao;
import com.cg.paymentwallet.dao.WalletDaoImpl;
import com.cg.paymentwallet.dto.Wallet;

public class WalletServiceImpl implements IWalletService {

	IWalletDao dao = new WalletDaoImpl();

	public void createAccount(Wallet wallet) {
		dao.createAccount(wallet);
	}

	public double showBalance(String userId) {

		return dao.showBalance(userId);
	}

	public boolean deposit(String userId, double amount) {
				
		boolean result = false;
		if (amount > 0) {
			Wallet wallet=dao.findCustomer(userId);
			wallet.setBalance(wallet.getBalance()+amount);
			LocalDateTime date = LocalDateTime.now();
			wallet.setTransaction("Rupees " + amount + " Deposited at " + date);
			result = true;
		}

		return result;
	}

	public boolean withdraw(String userId, double amount) throws WalletException {
		boolean result = false;
		if (dao.showBalance(userId) >= amount) {
			Wallet wallet=dao.findCustomer(userId);
			wallet.setBalance(wallet.getBalance()-amount);
			LocalDateTime date = LocalDateTime.now();
			wallet.setTransaction("Rupees " + amount + " Withdrawn at " + date);
			result = true;
		}else throw new WalletException(IWalletException.ERROR6);
		return result;
	}

	public boolean fundTransfer(String userIdSender, String userIdReceiver, double amount) throws WalletException {
		boolean result = false;
		if (dao.showBalance(userIdSender) >= amount) {
			LocalDateTime date = LocalDateTime.now();
			Wallet wallet1=dao.findCustomer(userIdSender);
			wallet1.setBalance(wallet1.getBalance()-amount);
			wallet1.setTransaction("Rupees " + amount + " Transfered to " + userIdReceiver + " at " + date);
			
			Wallet wallet=dao.findCustomer(userIdReceiver);
			wallet.setBalance(wallet.getBalance()+amount);
			wallet.setTransaction("Rupees " + amount + " Received from " + userIdSender + " at " + date);
			result = true;
		}else throw new WalletException(IWalletException.ERROR6);
		return result;
	}

	public boolean validateDetails(Wallet wallet) throws WalletException {
		boolean result = false;
		String regex = "[A-Z]{1}[a-z]+";
		String regex2 = "[0-9]{10}";
		String regex3 = "[a-z0-9_.]{1,}@[a-z]{1,10}.com";
		String regex4 = "[A-Za-z0-9]{4,}";
		if (wallet.getName().matches(regex)) {
			
			if (wallet.getPhNumber().matches(regex2)) {
				
				if (wallet.getEmailId().matches(regex3)) {
					
					if (!(wallet.getUserId().equals(wallet.getPassword()))) {
						
						if (wallet.getUserId().matches(regex4)) {
							
							if (wallet.getPassword().length()>=8) {
								
								result = true;
								
							}else 
								throw new WalletException(IWalletException.ERROR9);
							
						} else
							throw new WalletException(IWalletException.ERROR8);

					} else
						throw new WalletException(IWalletException.ERROR7);

				} else
					throw new WalletException(IWalletException.ERROR3);
				
			} else
				throw new WalletException(IWalletException.ERROR2);
		
		} else
			throw new WalletException(IWalletException.ERROR1);
		return result;

	}

	public Wallet login(String id, String password) throws WalletException {

		return dao.login(id, password);
	}

}
