package struttura;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.GregorianCalendar;
import database.GenericDB;
import graphics.*;
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
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 * @throws IOException
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

	public void addPartita(PartitaDiCalcio p) {
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
	public ArrayList<PartitaDiCalcio> getPartiteProgrammate() {
		return this.partiteProgrammate;
	}

	/**
	 * Restituisce le partite programmate nella data (settimana) indicata.
	 * 
	 * @param myFilter
	 *            Il filtro da utilizzare.
	 * @param data
	 *            La data(settimana) da utilizzare.
	 * @return Le partite programmate nella data (settimana) indicata.
	 */
	public ArrayList<PartitaDiCalcio> getPartiteProgrammate(Filter myFilter, GregorianCalendar data) {
		ArrayList<PartitaDiCalcio> filteredByWeek = new ArrayList<>();

		for (int i = 0; i < this.partiteProgrammate.size(); i++) {
			if (myFilter.accept(this.partiteProgrammate.get(i), data)) {
				filteredByWeek.add(this.partiteProgrammate.get(i));
			}
		}
		return filteredByWeek;
	}

	/**
	 * Restituisce le partite programmate nello stadio indicato.
	 * 
	 * @param myFilter
	 *            Il filtro da utilizzare.
	 * @param stadio
	 *            Lo stadio da utilizzare.
	 * @return Le partite programmate nello stadio indicato.
	 */
	public ArrayList<PartitaDiCalcio> getPartiteProgrammate(Filter myFilter, Stadio stadio) {
		ArrayList<PartitaDiCalcio> filteredByStadium = new ArrayList<>();

		for (int i = 0; i < this.partiteProgrammate.size(); i++) {
			if (myFilter.accept(this.partiteProgrammate.get(i), stadio)) {
				filteredByStadium.add(this.partiteProgrammate.get(i));
			}
		}
		return filteredByStadium;
	}

	/**
	 * Restituisce le partite programmate non ancora iniziate, ordinate secondo
	 * il comparatore indicato.
	 * 
	 * @param myComparator
	 *            Il comparatore da utilizzare.
	 * @return Le partite programmate non ancora iniziate, ordinate secondo il
	 *         comparatore.
	 */
	public ArrayList<PartitaDiCalcio> getPartiteProgrammate(Comparator<PartitaDiCalcio> myComparator) {
		ArrayList<PartitaDiCalcio> stillToPlay = new ArrayList<>();
		Filter myFilter = new MatchFilter();

		for (int i = 0; i < this.partiteProgrammate.size(); i++) {
			if (myFilter.accept(this.partiteProgrammate.get(i))) {
				stillToPlay.add(this.partiteProgrammate.get(i));
			}
		}

		stillToPlay.sort(myComparator);
		return stillToPlay;
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
	private ArrayList<PartitaDiCalcio> partiteProgrammate;
	private Window myWindow;
	private GenericDB<Cliente> clienti;
	private GenericDB<Gestore> gestori;

	private Utente utente;
}
