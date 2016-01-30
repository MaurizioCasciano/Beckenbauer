package password;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.SecretKeyFactory;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Classe che gestisce i codici hash delle password. I codici hash vengono
 * generati utilizzando l'algoritmo PBKDF2WithHmacSHA512.
 * 
 * <P>
 * PBKDF2<BR>
 * 
 * Stands for Password-based-Key-Derivative-Function, a successor of PBKDF1 and
 * is used to implement a pseudorandom function, such as a cryptographic hash,
 * cipher, or HMAC to the input password or passphrase along with a salt value
 * and repeats the process many times to produce a derived key, which can then
 * be used as a cryptographic key in subsequent operations.
 * </P>
 * 
 * <P>
 * HMAC<BR>
 * 
 * Stands for Keyed-Hash Message Authentication Code (HMAC) is a specific
 * construction for calculating a message authentication code (MAC) involving a
 * cryptographic hash function in combination with a secret cryptographic key.
 * Any cryptographic hash function,may be used in the calculation of an HMAC;
 * the resulting MAC algorithm is termed HMAC-MD5 or HMAC-SHA1 accordingly.
 * </P>
 * 
 * <P>
 * SHA512<BR>
 * 
 * stands for Secure Hash Algorithm. Cryptographic hash functions are
 * mathematical operations run on digital data; by comparing the computed "hash"
 * (the output from execution of the algorithm) to a known and expected hash
 * value, a person can determine the data's integrity. For example, computing
 * the hash of a downloaded file and comparing the result to a previously
 * published hash result can show whether the download has been modified or
 * tampered with.[4] A key aspect of cryptographic hash functions is their
 * collision resistance: nobody should be able to find two different input
 * values that result in the same hash output.
 * </P>
 */
public class PasswordHash implements Serializable {
	private static final long serialVersionUID = -6843744174735008039L;

	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA512";

	public static final int SALT_BYTE_SIZE = 64;
	public static final int HASH_BYTE_SIZE = 64;
	public static final int PBKDF2_ITERATIONS = 100000;

	public static final int ITERATION_INDEX = 0;
	public static final int SALT_INDEX = 1;
	public static final int PBKDF2_INDEX = 2;

	/**
	 * Restituisce l'hash PBKDF2 con salt della password.
	 *
	 * @param password
	 *            la password di cui si vuole generare l'hash.
	 * @return l'hash PBKDF2 con salto della password.
	 */
	public static String createHash(String password) {
		return createHash(password.toCharArray());
	}

	/**
	 * Restituisce l'hash PBKDF2 con salt della password.
	 *
	 * @param password
	 *            la password di cui si vuole generare l'hash.
	 * @return l'hash PBKDF2 con salto della password.
	 */
	public static String createHash(char[] password) {
		// Generate a random salt
		SecureRandom secureRandomNumberGenerator = new SecureRandom();
		byte[] salt = new byte[SALT_BYTE_SIZE];
		secureRandomNumberGenerator.nextBytes(salt);

		// Hash the password
		byte[] hash = pbkdf2(password, salt, PBKDF2_ITERATIONS, HASH_BYTE_SIZE);

		// format iterations:salt:hash
		return PBKDF2_ITERATIONS + ":" + toHex(salt) + ":" + toHex(hash);
	}

	/**
	 * Convalida una password utilizzando il suo codice hash.
	 *
	 * @param password
	 *            la password da convalidare.
	 * @param correctHash
	 *            il codice hash della password valida.
	 * @return true se la password è corretta, false altrimenti.
	 */
	public static boolean validatePassword(String password, String correctHash) {
		return validatePassword(password.toCharArray(), correctHash);
	}

	/**
	 * Convalida una password utilizzando il suo codice hash.
	 *
	 * @param password
	 *            la password da convalidare.
	 * @param correctHash
	 *            il codice hash della password valida.
	 * @return true se la password è corretta, false altrimenti.
	 */
	public static boolean validatePassword(char[] password, String correctHash) {
		/*
		 * Divide l'hash corretto nei suoi parametri.
		 */
		String[] params = correctHash.split(":");
		int originalIterations = Integer.parseInt(params[ITERATION_INDEX]);
		byte[] originalSaltBytes = fromHex(params[SALT_INDEX]);
		byte[] originalPasswordHashBytes = fromHex(params[PBKDF2_INDEX]);

		/*
		 * Calcola il codice hash della password passata in input, utilizzando
		 * lo stesso salt, numero di iterazioni e lunghezza dell'hash originale.
		 */
		byte[] testHash = pbkdf2(password, originalSaltBytes, originalIterations, originalPasswordHashBytes.length);
		/*
		 * Confronta i codici hash in tempo costante rispetto alla loro
		 * lunghezza. La password è corretta se i codici hash sono uguali.
		 */
		return verySlowEquals(originalPasswordHashBytes, testHash);
	}

	/**
	 * Confronta due array di byte in tempo costante rispetto alla loro
	 * lunghezza.
	 * 
	 * Questo metodo di confronto è usato per impedire che i codici hash delle
	 * password siano scaricati in locale da un sistema online tramite un
	 * attacco temporizzato per poi essere attaccati off-line.
	 * 
	 * @param firstBytesArray
	 *            il primo array da confrontare.
	 * 
	 * @param secondBytesArray
	 *            il secondo array da confrontare.
	 * 
	 * @return {@code true} se entrambi gli array di bytes sono uguali,
	 *         {@code false} altrimenti.
	 */
	private static boolean verySlowEquals(byte[] firstBytesArray, byte[] secondBytesArray) {

		/*
		 * Effettua lo XOR bit a bit tra le lunghezze dei due array.
		 * 
		 * diff = diff = a.length XOR b.length
		 */
		int diff = firstBytesArray.length ^ secondBytesArray.length;

		for (int i = 0; i < firstBytesArray.length && i < secondBytesArray.length; i++) {
			// OR degli XOR
			diff |= firstBytesArray[i] ^ secondBytesArray[i];
		}

		return diff == 0;
	}

	/**
	 * Calcola l'hash PBKDF2 con salt di una password.
	 *
	 * @param password
	 *            la password di cui si vuole generare l'hash.
	 * @param salt
	 *            il sale (salt in inglese) per rafforzare la sicurezza della
	 *            password e complicare gli attacchi a dizionario.<BR><BR>
	 * 
	 *            Le lookup-tables e le rainbow-tables funzionano soltanto
	 *            perchè ogni password hash è generato nello stesso identico
	 *            modo.
	 * 
	 *            Se due utenti hanno la stessa password, avranno gli stessi
	 *            password hashes.
	 * 
	 *            Possiamo prevenire questi attacchi randomizzando ogni hash,
	 *            per farlo si affianca alla password originale il salt generato
	 *            in maniera random prima di generare l'hash.
	 * 
	 *            In questo modo la stessa password affiancata da salt
	 *            differenti genera hash totalmente diversi.
	 * @param iterations
	 *            il numero di iterazioni (fattore di rallentamento).
	 * @param bytes
	 *            la lunghezza dell'hash da calcolare, espressa in numero di
	 *            bytes.
	 * @return l'hash PBKDF2 con salt di una password.
	 */
	public static byte[] pbkdf2(char[] password, byte[] salt, int iterations, int bytes) {
		PBEKeySpec myPBEKeySpec = new PBEKeySpec(password, salt, iterations, bytes * 8);

		SecretKeyFactory mySecretKeyFactory;
		try {
			mySecretKeyFactory = SecretKeyFactory.getInstance(PBKDF2_ALGORITHM);
			return mySecretKeyFactory.generateSecret(myPBEKeySpec).getEncoded();
		} catch (NoSuchAlgorithmException e) {
			// never happens
			e.printStackTrace();
			return null;
		} catch (InvalidKeySpecException e) {
			// never happens
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Converte una stringa di caratteri esadecimali in un array di bytes.
	 *
	 * @param hex
	 *            la stringa di caratteri esadecimali.
	 * @return la stringa di caratteri esadecimali decodificata in un array di
	 *         bytes.
	 */
	private static byte[] fromHex(String hex) {
		return DatatypeConverter.parseHexBinary(hex);
	}

	/**
	 * Converte un array di bytes in una stringa di caratteri esadecimali.
	 *
	 * @param array
	 *            l'array di bytes da convertire.
	 * @return una stringa del doppio della lunghezza dell'array rappresentante
	 *         la codifica in caratteri esadecimali dell'array di bytes.
	 */
	private static String toHex(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}

	public static void main(String[] args) throws UnsupportedEncodingException {
		String passwordHash = PasswordHash.createHash("Ciao");

		System.out.println(passwordHash);
		System.out.println(PasswordHash.validatePassword("Ciao", passwordHash));
		System.out.println(PasswordHash.validatePassword("ciao", passwordHash));
	}

}
