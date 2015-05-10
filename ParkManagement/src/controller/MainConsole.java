package controller;

import java.util.Scanner;

import models.JobController;
import models.User;
import models.UserController;

public class MainConsole {

	static UserController userController;
	static JobController jobController;
	static User currentUser;

	public static void main(String[] args) {

		// Pre-load the User persistent data:
		userController = new UserController();
		userController.readUserFile("/testFile.csv");

		// Pre-load the Jobs persistent data:
		jobController = new JobController();

		// Display copyright info
		displayDoc();

		// Display login prompt
		displayLoginPrompt();

		// Retrieve and verify email:
		boolean emailIsVerified = false;
		while (!emailIsVerified) {
			emailIsVerified = verifyEmail(retrieveEmail());
		}

		// Display appropriate user options:
		displayRoleOptions(currentUser);

	}

	private static void displayDoc() {
		System.out
				.println("*******************HuSCII Parks Management System****************");
		System.out
				.println("* Software version 1.0                                          *");
		System.out.print("* Authors: Jingzhu Guo, Duy Huynh, ");
		System.out.println("Ian McPeek, Putthida Samrith *");
		System.out
				.println("* Copyright (c) 2015 HuSCII, TCSS 360 Spring '15 Project Group. *");
		System.out
				.println("*                                                               *");
		System.out
				.println("*****************************************************************");
	}

	private static void displayLoginPrompt() {
		System.out.println("Welcome to the HuSCII Parks Management v1.0");
		System.out.println("Please login with your email address:");
		System.out.print("-->");
	}

	private static String retrieveEmail() {

		Scanner input = new Scanner(System.in);
		return input.nextLine();
	}

	private static boolean verifyEmail(String email) {

		for (User u : userController.getUserList()) {
			if (email.equals(u.getEmail())) {
				System.out.println("Logging in ... SUCCESS!");
				System.out.println("You are registered as: " + u.getRole());
				currentUser = u;
				return true;
			}
		}
		System.out.println("Logging in ... FAIL!");
		System.out.println("ERROR: Email not found, please try again:");
		System.out.print("-->");
		return false;
	}

	private static void displayRoleOptions(User user) {

		switch (user.getRole().toLowerCase()) {
		case "volunteer":
			// Call volunteer console controller class:
			System.out.println("Volunteer console class here");
			break;
		case "park manager":
			// Call park manager console controller class:
			ParkManagerController parkManager = new ParkManagerController();
			parkManager.menuScreen();
			break;
		case "administrator":
			// Call administrator console controller class:
			System.out.println("Administrator console class here");
			break;

		default:
			break;
		}
	}

}