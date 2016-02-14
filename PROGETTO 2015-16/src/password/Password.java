package password;

import java.io.Serializable;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Password implements Serializable {

	/**
	 * Controlla che la password passata in input rispecchi i requisiti minimi
	 * di sicurezza. I requisiti minimi di sicurezza impongono che una password
	 * sicura sia composta da almeno un carattere numerico, un carattere
	 * maiuscolo, un carattere minuscolo ed un carattere speciale
	 * {@literal (&@#$%?£€^+=)}. E' inoltra necessario che la lunghezza della
	 * password non sia inferiore ad 8 caratteri.
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

	/**
	 * {@code ^} Beginning. Indica l'inizio della Stringa.<br>
	 * <br>
	 * 
	 * {@code (?=regex)} Positive lookahead. Abbina un gruppo con l'espressione
	 * principale, senza includerlo nel risultato.<br>
	 * <br>
	 * 
	 * {@code .} Dot. Abbina qualsiasi carattere, eccetto il carattere di new
	 * line.<br>
	 * <br>
	 * 
	 * {@code *} Star. Abbina 0 o piu' occorrenze del simbolo precedente.<br>
	 * <br>
	 * 
	 * {@code [0-9]} Character set. Abbina qualsiasi carattere presente
	 * nell'insieme. Abbina un carattere compreso tra "0" a "9".<br>
	 * <br>
	 * 
	 * {@code [a-z]} Character set. Abbina qualsiasi carattere presente
	 * nell'insieme. Abbina un carattere compreso tra "a" e "z".<br>
	 * <br>
	 * 
	 * {@code [A-Z]} Character set. Abbina qualsiasi carattere presente
	 * nell'insieme. Abbina un carattere compreso tra "A" e "Z".<br>
	 * <br>
	 * 
	 * {@code [@#$%?£€^&+=]} Character set. Abbina qualsiasi carattere presente
	 * nell'insieme. Abbina un carattere tra quelli presenti nell'insieme<br>
	 * <br>
	 * 
	 * {@code (\S)} Not whitespace. Abbina qualsiasi carattere che non sia uno
	 * spazio (spazio, tab, new line).<br>
	 * <br>
	 * 
	 * {@code +} Plus. Abbina 1 o piu' occorrenze del simbolo precedente.<br>
	 * <br>
	 * 
	 * <code> {8,}</code> Quantifier. Abbina 8 o piu' occorrenze del simbolo
	 * precedente.<br>
	 * <br>
	 * 
	 * {@code $} End. Indica la fine della stringa.<br>
	 * <br>
	 * 
	 * @author Maurizio
	 */
	private static final String MY_REGULAR_EXPRESSION = "^((?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%?£€^&+=])(?=^\\S+$).{8,})$";
	private static final Pattern MY_PATTERN = Pattern.compile(MY_REGULAR_EXPRESSION);
	public static final int MINIMUM_LENGTH = 8;
	private static final long serialVersionUID = -1936279973334043609L;

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
