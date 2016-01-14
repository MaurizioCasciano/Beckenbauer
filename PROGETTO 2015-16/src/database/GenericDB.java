package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import user.UserNotFoundException;
import user.Utente;

/**
 * DataBase generico per gestire
 *
 * @param <T>
 * @deprecated Classe non più utilizzata in quanto non serializzabile pur implementando Serializable.
 * 
 * java.security.ProtectionDomain$JavaSecurityAccessImpl.doIntersectionPrivilege(Unknown Source).
 */
public class GenericDB<T extends Utente> implements Serializable {

	public GenericDB(String dbName) {
		this.dbName = dbName;
		this.fileDB = new File(this.dbName + ".db");
	}

	@SuppressWarnings("unchecked")
	public void add(T user) throws IOException, ClassNotFoundException, AlreadyRegisteredUserException {

		if (!this.fileDB.exists()) {
			this.fileDB.createNewFile();

			this.fileOutputStream = new FileOutputStream(this.fileDB);
			this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);

			this.collection = new ArrayList<>();
			this.collection.add(user);

			this.objectOutputStream.writeObject(this.collection);
			this.objectOutputStream.close();
			this.fileOutputStream.close();
		} else if (this.fileDB.exists()) {

			this.fileInputStrem = new FileInputStream(this.fileDB);
			this.objectInputStream = new ObjectInputStream(this.fileInputStrem);
			this.collection = (ArrayList<T>) this.objectInputStream.readObject();
			this.objectInputStream.close();
			this.fileInputStrem.close();

			for (T u : this.collection) {
				if (u.equals(user)) {
					throw new AlreadyRegisteredUserException(
							user.getClass().getSimpleName() + " già presente nel database");
				}
			}

			this.collection.add(user);
			this.fileOutputStream = new FileOutputStream(this.fileDB);
			this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);
			this.objectOutputStream.writeObject(this.collection);
			this.objectOutputStream.close();
			this.fileOutputStream.close();
		}
	}

	@SuppressWarnings("unchecked")
	public T get(String username) throws IOException, ClassNotFoundException, UserNotFoundException {

		if (!this.fileDB.exists()) {
			throw new UserNotFoundException();
		} else {
			this.fileInputStrem = new FileInputStream(this.fileDB);
			this.objectInputStream = new ObjectInputStream(this.fileInputStrem);
			this.collection = (ArrayList<T>) this.objectInputStream.readObject();

			for (T element : this.collection) {
				if (element.getUsername().equalsIgnoreCase(username)) {
					return element;
				}
			}

			throw new UserNotFoundException();
		}
	}

	private static final long serialVersionUID = 7602419843753158840L;
	private String dbName;
	private File fileDB;
	private ArrayList<T> collection;

	private FileInputStream fileInputStrem;
	private FileOutputStream fileOutputStream;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;

	/**
	 * @throws AlreadyRegisteredUserException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws UserNotFoundException
	 ***************************************************************/
	public static void main(String[] args)
			throws ClassNotFoundException, IOException, AlreadyRegisteredUserException, UserNotFoundException {
		GenericDB<Cliente> clienti = new GenericDB<>("clienti");

		@SuppressWarnings("unused")
		Cliente c = new Cliente("Maurizio", "Casciano", "1z10", "P@ssw0rd");
		// clienti.add(c);

		System.out.println(clienti.get("1z10"));
	}
}
