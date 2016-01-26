
package struttura;

import java.io.Serializable;
import java.util.ArrayList;

import graphics.DivisibleIntoSectors;

public class Stadio implements Serializable, DivisibleIntoSectors {

	/**
	 * Crea un nuovo {@link Stadio} con la capienza e il prezzo passati in
	 * input.
	 * 
	 * @param nome
	 *            Il nome dello Stadio.
	 * @param capienzaDesiderataStadio
	 *            La capienza desiderata dello Stadio, essa deve essere compresa
	 *            tra {@link Stadio#CAPIENZA_MINIMA} e
	 *            {@link Stadio#CAPIENZA_MASSIMA}.
	 * @param prezzoPerPartita
	 *            Il prezzo da applicare ad ogni biglietto per le partite
	 *            giocate in questo stadio.
	 * @throws IllegalArgumentException
	 *             Se la capienza desiderata non e' compresa (estremi inclusi)
	 *             tra {@link Stadio#CAPIENZA_MINIMA} e
	 *             {@link Stadio#CAPIENZA_MASSIMA}.
	 * 
	 *             Oppure se il prezzoPerPartia non e' compreso tra PREZZO_MINIMO e PREZZO_MASSIMO.
	 *
	 * @author Maurizio Casciano
	 */
	public Stadio(String nome, int capienzaDesiderataStadio, double prezzoPerPartita) throws IllegalArgumentException {
		this.nome = nome;

		if (capienzaDesiderataStadio < CAPIENZA_MINIMA || capienzaDesiderataStadio > CAPIENZA_MASSIMA) {
			throw new IllegalArgumentException("La capienza desiderata non e' consentita");
		}

		if (prezzoPerPartita < PREZZO_MINIMO || prezzoPerPartita > PREZZO_MASSIMO) {
			throw new IllegalArgumentException("Il prezzo indicato non e' consentito");
		}

		this.capienzaDesiderataStadio = capienzaDesiderataStadio;
		this.prezzoPerPartita = prezzoPerPartita;
		this.init();
	}

	/**
	 * Effettua i calcoli necessari per permettere la rappresentazione grafica
	 * dei settori dello stadio.
	 * 
	 * @author Maurizio Casciano
	 */
	private void init() {

		/*
		 * Necessita' di re-inizializzare i due caratteri, altrimenti alla
		 * chiamata del metodo setCapienza() che richiama init() i settori dello
		 * stadio non partiranno da AA.
		 */
		firstChar = 'A';
		secondChar = 'A';

		this.postiPerSettore = this.capienzaDesiderataStadio / DivisibleIntoSectors.NUMERO_SETTORI;
		this.filePerSettore = (int) Math.sqrt(this.postiPerSettore);
		this.capienzaEffettiva = this.postiPerSettore * DivisibleIntoSectors.NUMERO_SETTORI;
		/*
		 * Arrotonda il numero di posti per fila al piu' piccolo intero maggiore
		 * dell'argomento, o uguale all'argomento se quest'ultimo rappresenta
		 * gia' un intero.
		 * 
		 * Fondamentale per ottenere l'esatto numero di colonne utilizzate in
		 * seguito dal GridLayout, il quale tiene conto soltanto del numero di
		 * righe e calcola il numero di colonne in base al numero di elementi
		 * effettivamente contenuti, ed ottenere quindi il corretto numero di
		 * riga per ogni posto.
		 */
		this.postiPerFila = (int) Math.ceil((double) this.postiPerSettore / (double) this.filePerSettore);

		// System.out.println("PostiPerSettore = " + this.postiPerSettore);
		// System.out.println("FilePerSettore = " + this.filePerSettore);
		// System.out.println("CapienzaDesiderata = " +
		// this.capienzaDesiderataStadio);
		// System.out.println("CapienzaEffettiva = " + this.capienzaEffettiva);
		// System.out.println("PostiPerFila = " + this.postiPerFila);
		/*********************************************************/
		// System.out.println("PostiPerFilaDouble = " + (double)
		// this.postiPerSettore / (double) this.filePerSettore);
		// System.out.println("PostiPerFila arrotondati = " + Math.ceil((double)
		// this.postiPerSettore / (double) this.filePerSettore));

		this.settori = new ArrayList<>(DivisibleIntoSectors.NUMERO_SETTORI);

		/*
		 * Crea i settori dello stadio.
		 */
		for (int numeroSettore = 0; numeroSettore < DivisibleIntoSectors.NUMERO_SETTORI; numeroSettore++) {
			/*
			 * Crea il settore corrente.
			 */

			String nomeSettore = this.getNextNomeSettore();
			// System.out.println(nomeSettore);

			Settore settore = new Settore(Stadio.this, nomeSettore, this.postiPerSettore, this.filePerSettore);

			/*
			 * Crea i posti per il settore.
			 */
			for (int numeroFila = 0, numeroPosto = 1; numeroPosto <= this.postiPerSettore; numeroPosto++) {

				if (numeroPosto % this.postiPerFila == 1) {
					numeroFila++;
				}

				/*
				 * Crea il posto corrente.
				 */
				Posto posto = new Posto(this, settore, numeroFila, numeroPosto);
				/*
				 * Aggiunge il posto corrente al settore corrente.
				 */
				settore.addPosto(posto);

				// System.out.println(posto);
			}

			/*
			 * Aggiunge il settore corrente all'ArrayList di setttori.
			 */
			this.settori.add(settore);
		}
	}

	/**
	 * Restituisce una copia dei settori dello stadio.
	 * 
	 * @return
	 * @author Maurizio
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<Settore> getSettoriClone() {
		return (ArrayList<Settore>) this.settori.clone();
	}

	@Override
	public int getPostiPerSettore() {
		return this.postiPerSettore;
	}

	@Override
	public int getNumeroFilePerSettore() {
		return this.filePerSettore;
	}

	@Override
	public int getPostiPerFila() {
		return this.postiPerFila;
	}

	@Override
	public int getCapienzaEffettiva() {
		return this.capienzaEffettiva;
	}

	/**
	 * Restituisce il nome dello stadio
	 * @return nome
	 * 
	 * @author Gaetano Antonucci
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Imposta il nome dello stadio
	 * @param nome - il nome da assegnare
	 * 
	 * @author Maurizio Casciano
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * Verifica se l'oggetto corrente e' uguale all'oggetto passato come parametro.
	 * 
	 * @param obj - l'oggetto su cui effettuare la verifica.
	 * @return {@code true} se quest'oggetto e' uguale all'oggetto passato come parametro, {@code false} altrimenti
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (this.getClass() != obj.getClass())
			return false;

		Stadio other = (Stadio) obj;

		if (this.nome.equalsIgnoreCase(other.nome)) {
			result = true;
		}

		return result;
	}

	/**
	 * Restituisce la capienza desiderata, ossia quella impostata dall'utente al momento della creazione.
	 * 
	 * @return la capienza desiderata
	 * @author Maurizio Casciano
	 */
	public int getCapienzaDesiderataStadio() {
		return this.capienzaDesiderataStadio;
	}

	/**
	 * Imposta una nuova capienza per lo stadio 
	 * @param nuovaCapienza - la nuova capienza desiderata
	 * @author Maurizio Casciano
	 */
	public void setCapienzaStadio(int nuovaCapienza) {
		this.capienzaDesiderataStadio = nuovaCapienza;
		this.init();
	}

	/**
	 * Restituisce il prezzo per ogni partita giocata nello stadio.
	 * 
	 * @return il prezzo
	 * @author Maurizio Casciano
	 */ 
	public double getPrezzoPerPartita() {
		return this.prezzoPerPartita;
	}

	/**
	 * Imposta il prezzo per tutte le partite giocate nello stadio.
	 * 
	 * @param prezzo - il nuovo prezzo da impostare
	 * @author Maurizio Casciano
	 */
	public void setPrezzoPerPartita(double prezzo) {
		this.prezzoPerPartita = prezzo;
	}

	public void calcolaPostiEffettivi(int postiRichiesti) {
		int postiPerSettore = (postiRichiesti / NUMERO_SETTORI);
		this.postiPerSettore = postiPerSettore;

		int postiEffettivi = postiPerSettore * NUMERO_SETTORI;
		this.capienzaDesiderataStadio = postiEffettivi;

		int numeroFile = (int) Math.sqrt(postiPerSettore);
		int numeroPosti = postiPerSettore / numeroFile;

		this.filePerSettore = numeroFile;
		this.postiPerFila = numeroPosti;
	}

	/**
	 * Versione modificata del toString per facilitare la visualizzazione degli Stadi nelle comboBox.
	 */
	@Override
	public String toString() {
		return this.nome;
	}

	/**
	 * Genera il nome del prossimo Settore da creare.
	 * 
	 * @return il nome del prossimo settore
	 * @author Maurizio Casciano
	 */
	public String getNextNomeSettore() {

		String nomeSettore = "Settore ";

		if (this.secondChar < 'Z') {
			nomeSettore += this.firstChar;
			nomeSettore += this.secondChar++;
		} else if (this.secondChar == 'Z') {

			nomeSettore += this.firstChar++;
			nomeSettore += this.secondChar;
			this.secondChar = 'A';
		}

		return nomeSettore;
	}

	private char firstChar = 'A', secondChar = 'A';
	private static final long serialVersionUID = -5785492477034953352L;
	private String nome;
	private int capienzaDesiderataStadio;
	private int capienzaEffettiva;
	private int filePerSettore;
	private int postiPerSettore;
	private int postiPerFila;
	private double prezzoPerPartita;
	private ArrayList<Settore> settori;
	public static final int CAPIENZA_MINIMA = 20000, CAPIENZA_MASSIMA = 200000;
	public static final double PREZZO_MINIMO = 5.0, PREZZO_MASSIMO = 500.0;

	public static void main(String[] args) {
		new Stadio("", 80000, 10);
	}
}
