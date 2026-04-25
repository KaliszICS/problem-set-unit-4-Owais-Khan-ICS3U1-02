import java.util.Random;
import java.util.Scanner;

public class ProblemSet {
	static Scanner input = new Scanner(System.in);
	static int rangeStart;
	static int rangeEnd;
	static int rounds;
	static int option;

	public static void main(String args[]) {
		Random random = new Random();

		System.out.print("Welcome to the High Low Guessing Game.\n\nInput a number of rounds to play: ");
		
		while (!validRounds(input)) {
			System.out.print("Invalid Input!\nInput a number of rounds to play: ");
		}

		while (!parseRange()) {
			System.out.println("Invalid Input!");
		}

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
			while (!validOption(input)) {
				System.out.print("Invalid Input!\nPlease select High, Low or Even:\n1. High (" + highStart + " to " + rangeEnd + ")\n2. Low (" + rangeStart + " to " + lowEnd + ")\n" + evenPrompt + "\n");
			}
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

	public static boolean parseRange() {
		String min;
		String max;

		System.out.println("What Range would you like to play between (#-#)?");
		String range = input.nextLine();

		if (!(range.length() >= 3 && range.substring(1).contains("-"))) {
			return false;
		} else {
			int seperator = range.substring(1).indexOf("-")+1;
			min = range.substring(0, seperator);
			max = range.substring(seperator+1);
		}
		if (isInteger(max) && isInteger(min)) {
			rangeStart = Integer.parseInt(min);
			rangeEnd = Integer.parseInt(max);
			return rangeEnd > rangeStart+1;
		}
		return false;
	} 

	public static boolean validRounds(Scanner input) {
		if (!input.hasNextInt()) {
			input.next();
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
