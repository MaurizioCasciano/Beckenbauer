package password;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password implements Serializable{

	/**
	 * Controlla che la password passata in input rispecchi i requisiti minimi
	 * di sicurezza. I requisiti minimi di sicurezza impongono che una password
	 * sicura sia composta da almeno un carattere numerico, un carattere
	 * maiuscolo, un carattere minuscolo ed un carattere speciale (@#$%?£€^&+=).
	 * E' inoltra necessario che la lunghezza della password non sia inferiore
	 * ad 8 caratteri.
	 * 
	 * @param password
	 *            La password da controllare.
	 * @return true se la password passata in input rispecchia i requisiti
	 *         minimi di sicurezza, false altrimenti.
	 */

	public static boolean check(String password) {
		Matcher myMatcher = MY_PATTERN.matcher(password);
		return myMatcher.find();
	}

	/*
	 * ^ # Indicates the start-of-the-string.
	 */

	/*
	 * (?=.*[0-9]) # a digit must occur at least once
	 */

	/*
	 * (?=.*[a-z]) # a lower case letter must occur at least once
	 */

	/*
	 * (?=.*[A-Z])# an upper case letter must occur at least once
	 */

	/*
	 * (?=.*[@#$%^&+=]) # a special character must occur at least once
	 */

	/*
	 * (?=\S+$) # no whitespace allowed in the entire string
	 */

	/*
	 * .{8,} # anything, at least eight places though $ # end-of-string
	 */

	/*
	 * Lookahead and lookbehind, collectively called "lookaround", are
	 * zero-length assertions just like the start and end of line, and start and
	 * end of word anchors explained earlier in this tutorial. The difference is
	 * that lookaround actually matches characters, but then gives up the match,
	 * returning only the result: match or no match. That is why they are called
	 * "assertions". They do not consume characters in the string, but only
	 * assert whether a match is possible or not.
	 * 
	 * Positive lookahead works just the same. q(?=u) matches a q that is
	 * followed by a u, without making the u part of the match. The positive
	 * lookahead construct is a pair of parentheses, with the opening
	 * parenthesis followed by a question mark and an equals sign.
	 * 
	 * 
	 * Lookaround | Name | What it Does (?=foo) | Lookahead | Asserts that what
	 * immediately follows the current position in the string is foo.
	 */

	private static final long serialVersionUID = -1936279973334043609L;
	private static final String MY_REGULAR_EXPRESSION = "^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=\\S+$).{8,})$";
	private static final Pattern MY_PATTERN = Pattern.compile(MY_REGULAR_EXPRESSION);
	public static final int MINIMUM_LENGTH = 8;

	public static void main(String[] args) {

		String password;

		do {
			System.out.print("Inserisci una password: ");
			Scanner in = new Scanner(System.in);
			password = in.nextLine();

			if (!Password.check(password)) {
				System.out.println("Password non conforme, riprovare!!!");
			} else {
				System.out.println("Password correttamente salvata!!!");
				in.close();
			}
		} while (!Password.check(password));
	}
}
