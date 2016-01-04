package struttura;

import java.io.IOException;
import java.util.ArrayList;
import database.GenericDB;
import graphics.*;
import struttura.filters.Filter;
import user.AlreadyRegisteredUser;
import user.Cliente;
import user.Gestore;
import user.UserNotFound;
import user.Utente;

public class StrutturaSportiva {
	/**
	 * Costruisce un oggetto StrutturaSportiva.
	 * 
	 * @param nome
	 *            Il nome della StrutturaSportiva.
	 */
	public StrutturaSportiva(String nome) {
		this.nome = nome;
		this.partiteProgrammate = new ArrayList<>();
		this.stadi = new ArrayList<>();
		this.clienti = new GenericDB<>("Clienti");
		this.gestori = new GenericDB<>("Gestori");
		this.initGUI();
	}

	private void initGUI() {
		this.myWindow = new Window(this.nome, this);
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
		this.myWindow.setUtente(this.utente);
	}

	/**
	 * Restituisce il nome della StrutturaSportiva.
	 * 
	 * @return Il nome della StrutturaSportiva.
	 */
	public String getNome() {
		return nome;
	}

	public void addPartita(Partita p) {
		this.partiteProgrammate.add(p);
	}

	public void addStadio(Stadio s) {
		this.stadi.add(s);
	}

	/**
	 * Restituisce tutte le partite programmate.
	 * 
	 * @return Tutte le partite programmate.
	 */
	public ArrayList<Partita> getPartiteProgrammate() {
		return this.partiteProgrammate;
	}

	/**
	 * Restituisce le partite programmate filtrate dal Filtro passato in input.
	 * 
	 * @param myFilter
	 *            Il filtro da applicare.
	 * @return Le partite programmate dopo l'applicazione del filtro.
	 */
	public ArrayList<Partita> getPartiteProgrammate(Filter myFilter) {
		ArrayList<Partita> filteredByWeek = new ArrayList<>();

		for (int i = 0; i < this.partiteProgrammate.size(); i++) {
			if (myFilter.accept(this.partiteProgrammate.get(i))) {
				filteredByWeek.add(this.partiteProgrammate.get(i));
			}
		}
		return filteredByWeek;
	}

	public void addCliente(Cliente cliente) throws ClassNotFoundException, IOException, AlreadyRegisteredUser {
		this.clienti.add(cliente);
	}

	public Cliente getCliente(String username) throws ClassNotFoundException, IOException, UserNotFound {
		return this.clienti.get(username);
	}

	public void addGestore(Gestore gestore) throws ClassNotFoundException, IOException, AlreadyRegisteredUser {
		this.gestori.add(gestore);
	}

	public Gestore getGestore(String username) throws ClassNotFoundException, IOException, UserNotFound {
		return this.gestori.get(username);
	}

	private String nome;
	// una struttura sportiva che comprenda più stadi
	private ArrayList<Stadio> stadi;
	private ArrayList<Partita> partiteProgrammate;
	private Window myWindow;
	private GenericDB<Cliente> clienti;
	private GenericDB<Gestore> gestori;

	private Utente utente;
}
