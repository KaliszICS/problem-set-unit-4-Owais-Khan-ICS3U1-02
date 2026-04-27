
/**
	* File: Problem Set Unit 4: High-Low guessing Game
	* Author: Owais Ali Khan
	* Date Created: April 20, 2026
	* Date Last Modified: April 27, 2026.
	*/

import java.util.Random;
import java.util.Scanner;

public class ProblemSet {

	// Shared input scanner and range values (used across functions).
	static Scanner input = new Scanner(System.in);
	static int rangeStart;
	static int rangeEnd;

	public static void main(String args[]) {
		Random random = new Random();

		System.out.print("Welcome to the High Low Guessing Game.\n"
				+ "\nInput a number of rounds to play: ");

		int rounds;
		while (!(input.hasNextInt() && (rounds = input.nextInt()) > 0)) {
			input.nextLine();
			System.out.print("\nInvalid Input!\nInput a number of rounds to play: ");
		}
		input.nextLine();

		while (!(parseRange())) {
			System.out.print("\nInvalid Input!");
		}

		// Calculate midpoint(s) to divide range into low, middle, and high sections.
		double midpoint = (rangeStart + rangeEnd) / 2.0;
		int lowerMid = (int) Math.floor(midpoint);
		int upperMid = (int) Math.ceil(midpoint);
		int rangeSize = rangeEnd - rangeStart + 1; // Inclusive range size
		int lowEnd = lowerMid - 1;
		int highStart = upperMid + 1;
		
		// Determine the even prompt based on whether there are 1 or 2 middle numbers.
		String evenPrompt;
		if (lowerMid != upperMid) {
			evenPrompt = "3. Even (" + (lowerMid) + " and " + (upperMid) + ")";  // 2 middles
		} else {
			evenPrompt = "3. Even (" + (lowerMid) + ")";  // 1 middle
		}

		String highPrompt = "1. High (" + highStart + " to " + rangeEnd + ")\n";
		String lowPrompt = "2. Low (" + rangeStart + " to " + lowEnd + ")\n"; 

		// For very small ranges, avoid showing misleading "ranges" for high/low.
		if (rangeSize <= 4) {
			highPrompt = "1. High (" + rangeEnd + ")\n";
			lowPrompt = "2. Low (" + rangeStart + ")\n"; 
		}

		// Store menu for cleaner printing.
		String menu = "Please select High, Low or Even:\n" + highPrompt + lowPrompt + evenPrompt + "\n";

		int score = 0;

		// Repeat the game for as many rounds they said.
		for (int i = 1; i <= rounds; i++) {

			// Menu.
			System.out.println("\nRound " + i + ":\n");
			System.out.println(menu);

			// Check if the user choice is valid (1-3).
			int userChoice;
			while (!(input.hasNextInt() && (userChoice = input.nextInt()) >= 1 && userChoice <= 3 )) {
				input.nextLine();
				System.out.print("\nInvalid Input!\n" + menu);
			}
			input.nextLine();
			int randomNumber = random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;

			boolean highCorrect = userChoice == 1 && randomNumber > upperMid;
			boolean lowCorrect = userChoice == 2 && randomNumber < lowerMid;
			boolean evenCorrect = userChoice == 3 && (randomNumber == lowerMid
					|| randomNumber == upperMid);

			String result;

			if (highCorrect || lowCorrect || evenCorrect) {
				score++;
				result = "correct";
			} else {
				result = "incorrect";
			}

			// Correct or incorrect message.
			System.out.println("\nThe number was " + randomNumber + ".  You were " + result + ".\n"
							   + "Current Score: " + score);
		}

		// Final message after all rounds are done.
		System.out.println("\nTotal score: " + score);
		if (score >= rounds / 2.0) {
			System.out.println("Congratulations you got " + score + " out of " + rounds + " rounds right!");
		} else {
			System.out.println("You got " + score + " out of " + rounds + " correct.  Better Luck next time.");
		}
	}

	public static boolean parseRange() {
		String min;
		String max;

		System.out.println("\nWhat Range would you like to play between (#-#)?");
		String range = input.nextLine();

		// Check if the range is atleast 3 characters (#-#) and if there is a "-".
		if (!(range.length() >= 3 && range.substring(1).contains("-"))) {
			return false;
		} else {

			// Ignore the first "-" when finding the separator (handles negative numbers)
			int separator = range.substring(1).indexOf("-") + 1;

			// Parse the minimum and maximum using the index of "-".
			min = range.substring(0, separator);
			max = range.substring(separator + 1);
		}

		// Check if the min and max are numbers.
		if (isInteger(max) && isInteger(min)) {
			rangeStart = Integer.parseInt(min);
			rangeEnd = Integer.parseInt(max);
			return rangeEnd > rangeStart + 1; // Handle small ranges like 1-2
		}
		return false;
	}

	public static boolean isInteger(String num) {

		// Account for negative numbers.
		if (num.startsWith("-")) {
			if (num.length() == 1)
				return false;
			num = num.substring(1);
		}

		for (int i = 0; i < num.length(); i++) {
			char c = num.charAt(i);
			if (c < '0' || c > '9') {
				return false;
			}
		}
		return true;
	}

}
