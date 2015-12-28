package password;

import java.security.SecureRandom;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import javax.crypto.SecretKeyFactory;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * PBKDF2
 * 
 * Stands for Password-based-Key-Derivative-Function, a successor of PBKDF1 and
 * is used to implement a pseudorandom function, such as a cryptographic hash,
 * cipher, or HMAC to the input password or passphrase along with a salt value
 * and repeats the process many times to produce a derived key, which can then
 * be used as a cryptographic key in subsequent operations.
 * 
 * HMAC
 * 
 * Stands for Keyed-Hash Message Authentication Code (HMAC) is a specific
 * construction for calculating a message authentication code (MAC) involving a
 * cryptographic hash function in combination with a secret cryptographic key.
 * Any cryptographic hash function,may be used in the calculation of an HMAC;
 * the resulting MAC algorithm is termed HMAC-MD5 or HMAC-SHA1 accordingly.
 * 
 * SHA512
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
 */
public class PasswordHash {
	public static final String PBKDF2_ALGORITHM = "PBKDF2WithHmacSHA512";

	public static final int SALT_BYTE_SIZE = 64;
	public static final int HASH_BYTE_SIZE = 64;
	public static final int PBKDF2_ITERATIONS = 100000;

	public static final int ITERATION_INDEX = 0;
	public static final int SALT_INDEX = 1;
	public static final int PBKDF2_INDEX = 2;

	/**
	 * Returns a salted PBKDF2 hash of the password.
	 *
	 * @param password
	 *            the password to hash
	 * @return a salted PBKDF2 hash of the password
	 */
	public static String createHash(String password) {
		return createHash(password.toCharArray());
	}

	/**
	 * Returns a salted PBKDF2 hash of the password.
	 *
	 * @param password
	 *            the password to hash
	 * @return a salted PBKDF2 hash of the password
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
	 * Validates a password using a hash.
	 *
	 * @param password
	 *            the password to check
	 * @param correctHash
	 *            the hash of the valid password
	 * @return true if the password is correct, false if not
	 */
	public static boolean validatePassword(String password, String correctHash) {
		return validatePassword(password.toCharArray(), correctHash);
	}

	/**
	 * Validates a password using a hash.
	 *
	 * @param password
	 *            the password to check
	 * @param correctHash
	 *            the hash of the valid password
	 * @return true if the password is correct, false if not
	 */
	public static boolean validatePassword(char[] password, String correctHash) {
		// Decode the hash into its parameters
		String[] params = correctHash.split(":");
		int originalIterations = Integer.parseInt(params[ITERATION_INDEX]);
		byte[] originalSaltBytes = fromHex(params[SALT_INDEX]);
		byte[] originalPasswordHashBytes = fromHex(params[PBKDF2_INDEX]);

		// Compute the hash of the provided password, using the same salt,
		// iteration count, and hash length
		byte[] testHash = pbkdf2(password, originalSaltBytes, originalIterations, originalPasswordHashBytes.length);
		// Compare the hashes in constant time. The password is correct if
		// both hashes match.
		return verySlowEquals(originalPasswordHashBytes, testHash);
	}

	/**
	 * Compares two byte arrays in length-constant time. This comparison method
	 * is used so that password hashes cannot be extracted from an on-line
	 * system using a timing attack and then attacked off-line.
	 * 
	 * @param a
	 *            the first byte array
	 * @param b
	 *            the second byte array
	 * @return true if both byte arrays are the same, false if not
	 */
	private static boolean verySlowEquals(byte[] a, byte[] b) {

		/*
		 * Effettua lo XOR bit a bit tra le lunghezze dei due array.
		 * 
		 * diff = diff = a.length XOR b.length
		 */
		int diff = a.length ^ b.length;

		for (int i = 0; i < a.length && i < b.length; i++) {
			diff |= a[i] ^ b[i]; // OR
		}

		return diff == 0;
	}

	/**
	 * Computes the PBKDF2 hash of a password.
	 *
	 * @param password
	 *            the password to hash.
	 * @param salt
	 *            the salt
	 * @param iterations
	 *            the iteration count (slowness factor)
	 * @param bytes
	 *            the length of the hash to compute in bytes
	 * @return the PBDKF2 hash of the password
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
	 * Converts a string of hexadecimal characters into a byte array.
	 *
	 * @param hex
	 *            the hex string
	 * @return the hex string decoded into a byte array
	 */
	private static byte[] fromHex(String hex) {
		return DatatypeConverter.parseHexBinary(hex);
	}

	/**
	 * Converts a byte array into a hexadecimal string.
	 *
	 * @param array
	 *            the byte array to convert
	 * @return a length*2 character string encoding the byte array
	 */
	private static String toHex(byte[] array) {
		return DatatypeConverter.printHexBinary(array);
	}

	/**
	 * Tests the basic functionality of the PasswordHash class
	 *
	 * @param args
	 *            ignored
	 * @throws UnsupportedEncodingException
	 * @throws InvalidKeySpecException
	 * @throws NoSuchAlgorithmException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String passwordHash = PasswordHash.createHash("Ciao");

		System.out.println(passwordHash);
		System.out.println(PasswordHash.validatePassword("Ciao", passwordHash));
		System.out.println(PasswordHash.validatePassword("ciao", passwordHash));
	}

}
