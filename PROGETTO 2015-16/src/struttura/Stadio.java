
package struttura;

import java.io.Serializable;
import java.util.ArrayList;

import graphics.DivisibleIntoSectors;

public class Stadio implements Serializable, DivisibleIntoSectors {

	public Stadio(String nome, int capienzaDesiderataStadio, double prezzoPerPartita) {
		this.nome = nome;
		this.capienzaDesiderataStadio = capienzaDesiderataStadio;
		this.prezzoPerPartita = prezzoPerPartita;
		this.ID_Stadio = ++ID_Counter;
		this.init();
	}

	/**
	 * Effettua i calcoli necessari per permettere la rappresentazione gafica
	 * dei settori dello stadio.
	 * 
	 * @author Maurizio
	 */
	private void init() {

		/*
		 * Necessità di re-inizializzare i due caratteri, altrimenti, con lo
		 * stesso stadio, alla visualizzazione della seconda partita i settori
		 * dello stadio non partono da AA (Da testare ulteriormente, per capirne
		 * il reale motivo).
		 */
		firstChar = 'A';
		secondChar = 'A';

		this.postiPerSettore = this.capienzaDesiderataStadio / DivisibleIntoSectors.NUMERO_SETTORI;
		this.filePerSettore = (int) Math.sqrt(this.postiPerSettore);
		this.capienzaEffettiva = this.postiPerSettore * DivisibleIntoSectors.NUMERO_SETTORI;
		/*
		 * Arrotonda il numero di posti per fila al più piccolo intero maggiore
		 * dell'argomento, o uguale all'argomento se quest'ultimo rappresenta
		 * già un intero.
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
			Settore settore = new Settore(Stadio.this, this.getNextNomeSettore(), this.postiPerSettore,
					this.filePerSettore);

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

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

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

	public int getCapienzaDesiderataStadio() {
		return this.capienzaDesiderataStadio;
	}

	public void setCapienzaStadio(int nuovaCapienza) {
		this.capienzaDesiderataStadio = nuovaCapienza;
		this.init();
	}

	public int getID() {
		return this.ID_Stadio;
	}

	public double getPrezzoPerPartita() {
		return this.prezzoPerPartita;
	}

	public void setPrezzoPerPartita(double prezzo) {
		this.prezzoPerPartita = prezzo;
	}

	public void calcolaPostiEffettivi(int postiRichiesti) {
		int postiPerSettore = (postiRichiesti / SETTORI);
		this.postiPerSettore = postiPerSettore;

		int postiEffettivi = postiPerSettore * SETTORI;
		this.capienzaDesiderataStadio = postiEffettivi;

		int numeroFile = (int) Math.sqrt(postiPerSettore);

		/*
		 * Controllare il valore di numeroFile.
		 * 
		 * Possibile rischio: "java.lang.ArithmeticException: / by zero" Causato
		 * dalla creazione di una nuova partita in cui lo stadio ha capienza 0.
		 * Nuova capienza di default (per nuova partita) stadio aggiornata a
		 * 30000 (Minimo valore selezionabile dallo spinner alla creazione di
		 * uno stadio).
		 */
		int numeroPosti = postiPerSettore / numeroFile;

		this.filePerSettore = numeroFile;
		this.postiPerFila = numeroPosti;

	}

	@Override
	public String toString() {
		return this.nome;
	}

	/**
	 * Genera il nome del prossimo Settore da creare
	 * 
	 * @return
	 * @author Maurizio
	 */
	public String getNextNomeSettore() {

		String nomeSettore = "Settore ";

		if (this.secondChar <= 'Z') {
			nomeSettore += this.firstChar;
			nomeSettore += this.secondChar++;
		} else if (this.secondChar == 'Z' + 1) {
			this.secondChar = 'A';
			this.firstChar++;

			nomeSettore += this.firstChar;
			nomeSettore += this.secondChar;
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
	private int ID_Stadio;
	private double prezzoPerPartita;
	private ArrayList<Settore> settori;
	private static final int SETTORI = 620;
	private static int ID_Counter = 1000;

	public static void main(String[] args) {
		new Stadio("", 80000, 10);
	}
}
