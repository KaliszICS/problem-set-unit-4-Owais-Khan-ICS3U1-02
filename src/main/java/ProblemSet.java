import java.util.Scanner;

public class ProblemSet {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		System.out.print("Welcome to the High Low Guessing Game.\n\nInput a number of rounds to play: ");
		
		while (!input.hasNextInt()) {
			input.nextLine();
			System.out.print("Invalid Input!\nInput a number of rounds to play: ");
		}
		int rounds = input.nextInt();

		input.nextLine();

		String min;
		String max;

		String range;
		do {
			System.out.println("What Range would you like to play between (#-#)?\n");
			range = input.nextLine();
			int seperator = range.substring(1).indexOf("-");
			min = range.substring(0, seperator);
			max = range.substring(seperator+1);

		} while (!(validateNumbers(min, max)));

		int score = 0;
		
		int rangeStart = Integer.parseInt(min);
		int rangeEnd = Integer.parseInt(max);

		double even = (rangeStart + rangeEnd)/2.0;
		int lowStart; 
		int highStart;
		String evenPrompt;

		lowStart = (int)even-1;
		if (even%2!=0) {
			highStart = (int)even+2;
			evenPrompt = "3. Even (" + (lowStart+1) + " and " + (lowStart+2) + ")";
		} else {
			highStart = (int)even+1;
			evenPrompt = "3. Even (" + (lowStart+1) +")";
		}

		for (int i = 1; i <= rounds; i++) {
			System.out.println("Round " + i + ":\n");
		    System.out.println("Please select High, Low or Even:\n1. High (" + highStart + " to " + rangeEnd + ")\n1. Low (" + rangeStart + " to " + lowStart + ")\n" + evenPrompt);
		}
		
	}

	public static boolean validateNumbers(String num1, String num2) {
		num1 = num1.substring(1);
		num2 = num2.substring(1);
		return validateNumber(num1) & validateNumber(num2) & (num1.compareTo(num2) < 0);
	}

	public static boolean validateNumber(String num) {

		for (int i = 0; i < num.length(); i++) {
			int asciiVal = (int)num.charAt(i);
			if ((asciiVal < 48 || asciiVal > 57)) {
				return false;
			}
		}

		return true;
	}

}
