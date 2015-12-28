package database;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import password.WeakPasswordException;
import user.AlreadyRegisteredUser;
import user.Cliente;
import user.Gestore;
import user.UserNotFound;

public class DataBase {

	/**
	 * Crea un oggetto Database per la gestione degli account di Clienti e
	 * Gestori.
	 */
	public DataBase() {
		this.customersDB = new File("customers.db");
		this.managersDB = new File("managers.db");
	}

	/**
	 * Aggiunge un oggetto Gestore a questo DataBase.
	 * 
	 * @param gestore
	 *            Il gestore da aggiungere a questo DataBase.
	 * @throws IOException
	 *             Qualsiasi eccezione relativa all'IO.
	 * @throws ClassNotFoundException
	 *             La classe di un oggetto serializzato non può essere trovata.
	 * @throws AlreadyRegisteredUser
	 *             Un Gestore con la stessa username è gia presente in questo
	 *             DataBase.
	 */
	@SuppressWarnings("unchecked")
	public void addGestore(Gestore gestore) throws IOException, ClassNotFoundException, AlreadyRegisteredUser {
		if (!this.managersDB.exists()) {
			this.managersDB.createNewFile();

			this.fileOutputStream = new FileOutputStream(this.managersDB);
			this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);

			this.gestori = new ArrayList<>();
			this.gestori.add(gestore);
			this.objectOutputStream.writeObject(this.gestori);
			this.objectOutputStream.close();
			this.fileOutputStream.close();
		} else if (this.managersDB.exists()) {

			this.fileInputStrem = new FileInputStream(this.managersDB);
			this.objectInputStream = new ObjectInputStream(this.fileInputStrem);
			this.gestori = (ArrayList<Gestore>) this.objectInputStream.readObject();

			for (Gestore g : this.gestori) {
				if (g.equals(gestore)) {
					throw new AlreadyRegisteredUser("Gestore già presente nel database");
				}
			}

			this.gestori.add(gestore);
			this.fileOutputStream = new FileOutputStream(this.managersDB);
			this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);
			this.objectOutputStream.writeObject(this.gestori);
			this.objectOutputStream.close();
			this.fileOutputStream.close();
		}
	}

	/**
	 * Restituisce il Gestore con l'username passata come parametro.
	 * 
	 * @param username
	 *            L'username del Gestore da cercare.
	 * @return Il Gestore con l'username specificata.
	 * @throws IOException
	 *             Qualsiasi eccezione relativa all'IO.
	 * @throws ClassNotFoundException
	 *             La classe di un oggetto serializzato non può essere trovata.
	 * @throws UserNotFound
	 */
	@SuppressWarnings("unchecked")
	public Gestore getGestore(String username) throws IOException, ClassNotFoundException, UserNotFound {

		if (!this.managersDB.exists()) {
			throw new UserNotFound();
		} else {
			this.fileInputStrem = new FileInputStream(this.managersDB);
			this.objectInputStream = new ObjectInputStream(this.fileInputStrem);
			this.gestori = (ArrayList<Gestore>) this.objectInputStream.readObject();

			for (Gestore g : this.gestori) {
				if (g.getUsername().equals(username)) {
					return g;
				}
			}

			throw new UserNotFound();
		}
	}

	/**
	 * Aggiunge un oggetto Cliente a questo DataBase.
	 * 
	 * @param cliente
	 *            Il cliente da aggiungere a questo DataBase.
	 * @throws IOException
	 *             Qualsiasi eccezione relativa all'IO.
	 * @throws ClassNotFoundException
	 *             La classe di un oggetto serializzato non può essere trovata.
	 * @throws AlreadyRegisteredUser
	 *             Un Cliente con la stessa username è gia presente in questo
	 *             DataBase.
	 */
	@SuppressWarnings("unchecked")
	public void addCliente(Cliente cliente) throws IOException, ClassNotFoundException, AlreadyRegisteredUser {
		if (!this.customersDB.exists()) {
			this.managersDB.createNewFile();

			this.fileOutputStream = new FileOutputStream(this.customersDB);
			this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);

			this.clienti = new ArrayList<>();
			this.clienti.add(cliente);
			this.objectOutputStream.writeObject(this.clienti);
			this.objectOutputStream.close();
			this.fileOutputStream.close();
		} else if (this.customersDB.exists()) {

			this.fileInputStrem = new FileInputStream(this.customersDB);
			this.objectInputStream = new ObjectInputStream(this.fileInputStrem);
			this.clienti = (ArrayList<Cliente>) this.objectInputStream.readObject();

			for (Cliente c : this.clienti) {
				if (c.equals(cliente)) {
					throw new AlreadyRegisteredUser("Cliente già presente nel database");
				}
			}

			this.clienti.add(cliente);
			this.fileOutputStream = new FileOutputStream(this.customersDB);
			this.objectOutputStream = new ObjectOutputStream(this.fileOutputStream);
			this.objectOutputStream.writeObject(this.clienti);
			this.objectOutputStream.close();
			this.fileOutputStream.close();
		}
	}

	/**
	 * Restituisce il Cliente con l'username passata come parametro.
	 * 
	 * @param username
	 *            L'username del Cliente da cercare.
	 * @return Il Gestore con l'username specificata.
	 * @throws IOException
	 *             Qualsiasi eccezione relativa all'IO.
	 * @throws ClassNotFoundException
	 *             La classe di un oggetto serializzato non può essere trovata.
	 * @throws UserNotFound
	 */
	@SuppressWarnings("unchecked")
	public Cliente getCliente(String username) throws IOException, ClassNotFoundException, UserNotFound {

		if (!this.customersDB.exists()) {
			throw new UserNotFound();
		} else {
			this.fileInputStrem = new FileInputStream(this.customersDB);
			this.objectInputStream = new ObjectInputStream(this.fileInputStrem);
			this.clienti = (ArrayList<Cliente>) this.objectInputStream.readObject();

			for (Cliente c : this.clienti) {
				if (c.getUsername().equals(username)) {
					return c;
				}
			}

			throw new UserNotFound();
		}
	}

	private File customersDB, managersDB;
	private FileInputStream fileInputStrem;
	private FileOutputStream fileOutputStream;
	private ObjectInputStream objectInputStream;
	private ObjectOutputStream objectOutputStream;
	private ArrayList<Cliente> clienti;
	private ArrayList<Gestore> gestori;

	public static void main(String[] args)
			throws WeakPasswordException, IOException, ClassNotFoundException, AlreadyRegisteredUser {
		DataBase db = new DataBase();
		db.addGestore(new Gestore("NomeGestore", "CognomeGestore", "UsernameGestore", "P@ssw0rd"));
		db.addCliente(new Cliente("Maurizio", "Casciano", "1z10", "P@ssw0rd"));

		Gestore gestore = null;
		Cliente cliente = null;

		try {
			gestore = db.getGestore("UsernameGestore");
			cliente = db.getCliente("1z10");

			System.out.println(gestore);
			System.out.println(cliente);

			System.out.println(cliente.matchPassword("P@ssw0rd"));
			System.out.println(gestore.matchPassword("P@ssw0rd"));
		} catch (UserNotFound e) {
			e.printStackTrace();
		}

	}
}
