/**
 * @author 20george
 * @version 11/28/18
 * this is a program that takes in user input of a valid word and computes/returns the Scrabble score for that word
 */

import java.util.*;
import java.io.File;

public class ScrabbleScorer {
	private final String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private final int[] points = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
	private ArrayList<String> dictionary;
	
/**
 * main method for the program	
 * @param args
 */
	public static void main(String[] args) {
		ScrabbleScorer app = new ScrabbleScorer();
		System.out.println("* Welcome to the Scrabble Word Scorer app *");
		Scanner in = new Scanner(System.in);
		String temp;
		while(true) {
			System.out.print("Enter a word to score or 0 to quit: ");
			temp = in.nextLine();
			if (temp.equals("0"))
				break;
			if(!app.isValidWord(temp.toUpperCase())) {
				System.out.println(temp + " is not a valid word in the dictionary.");
			}
			else {
				System.out.println(temp + " = " + app.getWordScore(temp.toUpperCase()) + " points");
			}
		}
				System.out.println("Exiting the program thanks for playing");
	}
/**
 * constructor for the class
 */
	public ScrabbleScorer() {
		dictionary = new ArrayList<String>();
		buildDictionary();
	}
/**
 * sorts all of the valid words listed in the scrabble words file
 */
	public void buildDictionary() {
		Scanner in = null;
		try {
			in = new Scanner(new File("SCRABBLE_WORDS.txt"));
			while(in.hasNext())
				dictionary.add(in.nextLine());
			in.close();
			Collections.sort(dictionary);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
/**
 * tests the validity of a given word
 * @param word
 * @return true or false depending on if the word is valid or not
 */
	public boolean isValidWord(String word) {
		return Collections.binarySearch(dictionary, word) >= 0;
	}
/**
 * computes the score of a given word
 * @param word
 * @return the score as an int
 */
	public int getWordScore(String word) {
		int score = 0;
		for(int i = 0; i < word.length(); i++) {
			int num = alpha.indexOf(word.substring(i, i+1));
			score += points[num];
		}
		return score;
	}
}
