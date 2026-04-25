
/**
	* File: Problem Set Unit 4: High-Low guessing Game
	* Author: Owais Ali Khan
	* Date Created: April 20, 2026
	* Date Last Modified: April 27, 2026.
	*/

import java.util.Random;
import java.util.Scanner;

public class ProblemSet {

	// resuable varaibles defined in functions
	static Scanner input = new Scanner(System.in);
	static int rangeStart;
	static int rangeEnd;
	static int rounds;
	static int option;

	public static void main(String args[]) {
		Random random = new Random();

		System.out.print("Welcome to the High Low Guessing Game.\n"
				+ "\nInput a number of rounds to play: ");

		// Keep asking for rounds until it is atleast 1.
		while (!validRounds(input)) {
			System.out.print("Invalid Input!\nInput a number of rounds to play: ");
		}

		// Keep asking for range until it is valid.
		while (!parseRange()) {
			System.out.println("Invalid Input!");
		}

		double even = (rangeStart + rangeEnd) / 2.0;
		int size = rangeEnd - rangeStart + 1; // Add 1 to make it inclusive
		int lowEnd;
		int highStart;
		String evenPrompt;

		lowEnd = (int) even - 1; // The lower half should end at 1 less than even

		// Determine the beginning of the upper half depending on whether there are 1 or
		// 2 middle numbers.
		if (size % 2 == 0) {
			// 2 middle numbers
			highStart = (int) even + 2;
			evenPrompt = "3. Even (" + ((int) Math.floor(even)) + " and " + ((int) Math.ceil(even)) + ")";
		} else {
			// 1 middle number
			highStart = (int) even + 1;
			evenPrompt = "3. Even (" + ((int) even) + ")";
		}

		int score = 0;

		for (int i = 1; i <= rounds; i++) {

			// Menu.
			System.out.println("Round " + i + ":\n");
			System.out.println("Please select High, Low or Even:\n"
					+ "1. High (" + highStart + " to " + rangeEnd + ")\n"
					+ "2. Low (" + rangeStart + " to " + lowEnd + ")\n"
					+ evenPrompt + "\n");

			// Keep asking for an option untill it is between 1-3.
			while (!validOption(input)) {
				System.out.print("Invalid Input!\n"
						+ "Please select High, Low or Even:\n"
						+ "1. High (" + highStart + " to " + rangeEnd + ")\n"
						+ "2. Low (" + rangeStart + " to " + lowEnd + ")\n"
						+ evenPrompt + "\n");
			}
			int numberToGuess = random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;

			boolean highCorrect = option == 1 && numberToGuess > Math.ceil(even);
			boolean lowCorrect = option == 2 && numberToGuess < Math.floor(even);
			boolean evenCorrect = option == 3 && numberToGuess == Math.ceil(even)
					|| numberToGuess == Math.floor(even);

			if (highCorrect || lowCorrect || evenCorrect) {
				score++;
				System.out.println(
						"\nThe number was " + numberToGuess + ".  You were correct.\n"
								+ "Current Score: " + score + "\n");
			} else {
				System.out.println(
						"\nThe number was " + numberToGuess + ".  You were incorrect.\n"
								+ "Current Score: " + score + "\n");
			}
		}

		// Final message after all rounds are done.
		System.out.println("Total score: " + score);
		if (score >= rounds / 2.0) {
			System.out.println("Congratulations you got " + score + " out of " + rounds + " rounds right!");
		} else {
			System.out.println("You got " + score + " out of " + rounds + " correct.  Better Luck next time.");
		}
	}

	public static boolean parseRange() {
		String min;
		String max;

		System.out.println("What Range would you like to play between (#-#)?");
		String range = input.nextLine();

		// Check if the range is atleast 3 characters (#-#) and if there is a "-"
		if (!(range.length() >= 3 && range.substring(1).contains("-"))) {
			return false;
		} else {

			// Parse the minimum and maximum using the index of "-"
			int seperator = range.substring(1).indexOf("-") + 1;
			min = range.substring(0, seperator);
			max = range.substring(seperator + 1);
		}

		// Check if the min and max are numbers.
		if (isInteger(max) && isInteger(min)) {
			rangeStart = Integer.parseInt(min);
			rangeEnd = Integer.parseInt(max);
			return rangeEnd > rangeStart + 1; // Handle small ranges like 1-2.
		}
		return false;
	}

	public static boolean validRounds(Scanner input) {
		if (!input.hasNextInt()) {
			input.next(); // Clear scanner
			return false;
		}

		rounds = input.nextInt();
		input.nextLine();

		if (!(rounds >= 1)) {
			return false;
		}
		return true;
	}

	public static boolean validOption(Scanner input) {
		if (!input.hasNextInt()) {
			input.next();
			return false;
		}

		option = input.nextInt();
		input.nextLine();

		if (!(option >= 1 && option <= 3)) {
			return false;
		}
		return true;
	}

	public static boolean isInteger(String num) {

		// Account for negative numbers
		if (num.startsWith("-")) {
			if (num.length() == 1)
				return false;
			num = num.substring(1);
		}

		// Check if each character is a number
		for (int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}

		return true;
	}

}
