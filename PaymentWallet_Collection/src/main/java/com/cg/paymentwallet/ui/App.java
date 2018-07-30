package com.cg.paymentwallet.ui;

import java.util.ArrayList;
import java.util.Scanner;

import com.cg.paymentwallet.Exception.WalletException;
import com.cg.paymentwallet.dto.Wallet;
import com.cg.paymentwallet.service.IWalletService;
import com.cg.paymentwallet.service.WalletServiceImpl;

public class App {
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		int choice1 = 0;

		do {
			System.out.println(" ");
			System.out.println("________________________Payment Wallet_____________________________");
			System.out.println("Enter 1 to Create Account");
			System.out.println("Enter 2 to Login");
			System.out.println("Enter 3 to Exit App");
			System.out.println("____________________________________________________________________");
			choice1 = scanner.nextInt();
			IWalletService service = new WalletServiceImpl();
			switch (choice1) {
			case 1:
				System.out.println("Enter your Phone Number");
				String phNum = scanner.next();
				System.out.println("Enter your Name");
				String name = scanner.next();
				System.out.println("Enter your Email ID");
				String emailId = scanner.next();
				System.out.println("Enter the userId to be created");
				String userId = scanner.next();
				System.out.println("Set your Password");
				String pass = scanner.next();
				System.out.println("Enter the Initial Amount to be Deposited");
				double balance = scanner.nextDouble();

				Wallet wallet = new Wallet();
				wallet.setPhNumber(phNum);
				wallet.setName(name);
				wallet.setEmailId(emailId);
				wallet.setUserId(userId);
				wallet.setPassword(pass);
				wallet.setBalance(balance);

				boolean result = false;
				try {
					result = service.validateDetails(wallet);
					service.createAccount(wallet);
					System.out.println("Hello " + wallet.getName());
					System.out.println("Your Account is Created Succesfully");
				} catch (WalletException e) {

					e.getMessage();
				}
				if (!result) {
					System.out.println("Cannot create account. Try Again");
				}
				break;
			case 2:
				System.out.println("Enter your UserID");
				String id = scanner.next();
				System.out.println("Enter your Password");
				String password = scanner.next();
				Wallet loginWallet;
				try {
					loginWallet = service.login(id, password);
					login(loginWallet);
				} catch (WalletException e) {
					e.getMessage();
				}
				break;
			case 3:
				System.out.println("Thank You for using Payment Wallet");
				break;
			default:
				System.out.println("Wrong choice! Try Again");
				break;
			}
		} while (choice1 != 3);
	}

	private static void login(Wallet wallet) {
		int choice2 = 0;
		System.out.println("Welcome " + wallet.getName());
		do {
			System.out.println(" ");
			System.out.println("________________________Payment Wallet_____________________________");
			System.out.println(" 1 - Show Balance");
			System.out.println(" 2 - Withdraw Money from wallet");
			System.out.println(" 3 - Add Money to wallet");
			System.out.println(" 4 - Transfer Fund");
			System.out.println(" 5 - Print Transaction history");
			System.out.println(" 6 - LogOut");
			System.out.println("____________________________________________________________________");
			choice2 = scanner.nextInt();
			IWalletService service = new WalletServiceImpl();
			String userId = wallet.getUserId();

			switch (choice2) {
			case 1:
				double balance = service.showBalance(userId);
				System.out.println("Your Account Balance is " + balance);
				break;
			case 2:
				System.out.println("Enter the Amount to be Withdrawn");
				double withdrawAmount = scanner.nextDouble();
				try {
					if (service.withdraw(userId, withdrawAmount)) {
						System.out.println("Rupees " + withdrawAmount + " Withdrawn from your Wallet");
						System.out.println("Your Updates Account Balance is Rupees " + service.showBalance(userId));
					}
				} catch (WalletException e1) {
					e1.getMessage();
				}

				break;
			case 3:
				System.out.println("Enter the Amount to be Deposited");
				double depositAmount = scanner.nextDouble();
				if (service.deposit(userId, depositAmount)) {
					System.out.println("Rupees " + depositAmount + " Deposited to your Wallet");
					System.out.println("Your Updated Balance is Rupees " + service.showBalance(userId));
				} else
					System.out.println("Unable to deposit");
				break;
			case 4:
				System.out.println("Enter the UserId of Whom the fund is to be Transfered");
				String receiverUserId = scanner.next();
				System.out.println("Enter the amount to be Transfered");
				double transferAmount = scanner.nextDouble();
				try {
					if (service.fundTransfer(userId, receiverUserId, transferAmount)) {
						System.out.println("Rupees " + transferAmount + " Succesfully Transfered to " + receiverUserId);
						System.out.println("Your Updated Balance is Rupees " + service.showBalance(userId));
					}
				} catch (WalletException e) {
					System.out.println(e.getMessage());
				}
				break;
			case 5:

				ArrayList<String> list = wallet.getTransaction();
				System.out.println("_______________________Transactions Details_________________________");
				for (String string : list) {
					System.out.println(string);
				}
				System.out.println("____________________________________________________________________");
				break;
			case 6:
				System.out.println("__________________________Logged out________________________________");
				break;

			default:
				System.out.println("Wrong Choice! try Again");
				break;
			}
		} while (choice2 != 6);

	}
}
