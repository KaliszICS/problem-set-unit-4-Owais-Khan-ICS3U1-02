import java.util.Random;
import java.util.Scanner;

public class ProblemSet {
	static Scanner input = new Scanner(System.in);

	public static void main(String args[]) {
		Random random = new Random();

		System.out.print("Welcome to the High Low Guessing Game.\n\nInput a number of rounds to play: ");
		
		while (!input.hasNextInt()) {
			input.nextLine();
			System.out.print("Invalid Input!\nInput a number of rounds to play: ");
		}
		int rounds = input.nextInt();

		input.nextLine();

		String min = "";
		String max = "";

		String range;
		boolean valid = true;
		do {
			if (!valid) {
				System.out.println("Invalid Input!");
			}

			System.out.println("What Range would you like to play between (#-#)?");
			range = input.nextLine();

			if (!range.substring(1).contains("-") || range.length() < 3) {
				valid = false;
			} else {
				int seperator = range.substring(1).indexOf("-")+1;
				min = range.substring(0, seperator);
				max = range.substring(seperator+1);
				valid = validateNumbers(min, max);
			}

		} while (!valid);

		int rangeStart = Integer.parseInt(min);
		int rangeEnd = Integer.parseInt(max);

		double even = (rangeStart + rangeEnd)/2.0;
		int size = rangeEnd - rangeStart + 1;
		int lowEnd; 
		int highStart;
		String evenPrompt;

		lowEnd = (int)even-1;
		if (size%2==0) {
			highStart = (int)even+2;
			evenPrompt = "3. Even (" + ((int)Math.floor(even)) + " and " + ((int)Math.ceil(even)) + ")";
		} else {
			highStart = (int)even+1;
			evenPrompt = "3. Even (" + ((int)even) +")";
		}

		int score = 0;

		for (int i = 1; i <= rounds; i++) {
			System.out.println("Round " + i + ":\n");
		    System.out.println("Please select High, Low or Even:\n1. High (" + highStart + " to " + rangeEnd + ")\n2. Low (" + rangeStart + " to " + lowEnd + ")\n" + evenPrompt + "\n");
			while (!input.hasNextInt()) {
				input.nextLine();
				System.out.print("Invalid Input!\nPlease select High, Low or Even:\n1. High (" + highStart + " to " + rangeEnd + ")\n2. Low (" + rangeStart + " to " + lowEnd + ")\n" + evenPrompt + "\n");
			}
			int option = input.nextInt(); 
			int numberToGuess = random.nextInt(rangeEnd - rangeStart + 1) + rangeStart;
			
			boolean highCorrect = option == 1 && numberToGuess > Math.ceil(even);
			boolean lowCorrect = option == 2 && numberToGuess < Math.floor(even);
			boolean evenCorrect = option == 3 && numberToGuess == Math.ceil(even) 
										      || numberToGuess == Math.floor(even);

			if (highCorrect || lowCorrect || evenCorrect) {
				score++;
				System.out.println("\nThe number was " + numberToGuess +".  You were correct.\nCurrent Score: " + score + "\n");
			} else {
				System.out.println("\nThe number was " + numberToGuess +".  You were incorrect.\nCurrent Score: " + score + "\n");
			}
		}

		System.out.println("Total score: " + score);
		if (score >= rounds/2.0) {
			System.out.println("Congratulations you got " + score + " out of " + rounds + " rounds right!");
		} else {
			System.out.println("You got " + score + " out of " + rounds + " correct.  Better Luck next time.");
		}

	}

	public static boolean parseRange(String range) {
		System.out.println("What Range would you like to play between (#-#)?");
		range = input.nextLine();

		if (!range.substring(1).contains("-") || range.length() < 3) {
			return false;
		} else {
			int seperator = range.substring(1).indexOf("-")+1;
			min = range.substring(0, seperator);
			max = range.substring(seperator+1);
			return validateNumbers(min, max);
		}
	} 

	public static boolean validateRounds(Scanner input) {
		if (!input.hasNextInt()) {
			return false;
		}

		int num = input.nextInt();
		input.nextLine();

		if (!(num >= 1 && num <= 3)) {
			return false;
		}
		return true;
	}

	public static boolean validateOption(Scanner input, int minVal, int maxVal) {
		if (!input.hasNextInt()) {
			return false;
		}

		int num = input.nextInt();
		input.nextLine();

		if (!(num >= 1 && num <= 3)) {
			return false;
		}
		return true;
	}

	public static boolean validateNumbers(String num1, String num2) {
		return isInteger(num1) & isInteger(num2) 
									& (num2.compareTo(num1) < 2);
	}

	public static boolean isInteger(String num) {
		//if (num == null || num.length() == 0) return false;

		if (num.startsWith("-")) {
			if (num.length() == 1) return false;
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
