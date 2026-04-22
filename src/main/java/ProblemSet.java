import java.util.Random;
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

		System.out.println("What Range would you like to play between (#-#)?\n");
		String range = input.nextLine();

		int score = 0;
		
		int even = (rangeStart + rangeEnd)/2;
		int lowStart; 
		int highStart;

		if (even%2==0) {
			lowStart = even;
		} else {
			lowStart = even-1;
		}
		highStart = even+1;

		for (int i = 0; i < rounds; i++) {
			round(i, a, b);
		}
		
	}

	public static boolean validRange(String range) {
		seperator = range.substring(1, range.length()-1).indexOf(range)

		return true;
	}


	public static String round(int roundNum) {
		
		System.out.println("Round " + roundNum + ":\n");

		System.out.println("Please select High, Low or Even:\n1. High (" + highStart + " to " + rangeEnd + ")\n1. High (" + lowStart + " to " + rangeStart + ")");


	}

}
