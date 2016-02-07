package struttura;

import java.io.Serializable;
//import java.text.DateFormat;
import java.util.GregorianCalendar;

import user.Cliente;

/**
 * Classe che modella una prenotazione per una partita di calcio
 * 
 * @author Gaetano Antonucci
 * @author Maurizio Casciano
 */
public class Prenotazione implements Serializable {

	/**
	 * Costruisce un oggetto prenotazione in base ai parametri impostati
	 * 
	 * @param dataPrenotazione
	 *            - la data ({@link GregorianCalendar}) in cui viene effetuata
	 *            la prenotazione
	 * @param stru
	 *            - la {@link StrutturaSportiva} sulla quale si sta operando
	 * @param cliente
	 *            - il {@link Cliente} che effettua la prenotazione
	 * @param partita
	 *            - la {@link Partita} per cui si sta prenotando un biglietto
	 * @param settore
	 *            - il {@link Settore} dello {@link Stadio} dove e' ubicato il
	 *            posto
	 * @param fila
	 *            - la fila del {@link Settore} dove e' ubicato il posto
	 * @param posto
	 *            - il posto prenotato
	 * 
	 * @author Gaetano Antonucci
	 */
	public Prenotazione(StrutturaSportiva stru, Cliente cliente, Partita partita, Settore settore, int fila,
			int posto) {
		this.bigliettoPrenotato = new Biglietto(stru, cliente, partita, settore, fila, posto);
		this.bigliettoPrenotato.setPrenotato();
	}

	/**
	 * Restituisce il {@link Cliente} che ha prenotato il {@link Biglietto}
	 * 
	 * @return Cliente - il cliente che ha prenotato
	 * @author Maurizio Casciano
	 */
	public Cliente getCliente() {
		return this.bigliettoPrenotato.getCliente();
	}

	/**
	 * Restituisce la {@link Partita} per cui si è prenotato un biglietto
	 * 
	 * @return la Partita
	 * @author Maurizio Casciano
	 */
	public Partita getPartita() {
		return this.bigliettoPrenotato.getPartita();
	}

	/**
	 * Restituisce il {@link Settore} dello {@link Stadio} dove e' ubicato il
	 * posto.
	 * 
	 * @return il Settore del posto della prenotazione.
	 * @author Maurizio Casciano
	 */
	public Settore getSettore() {
		return this.bigliettoPrenotato.getSettore();
	}

	/**
	 * Restituisce lo {@link Stadio} dove sara'/e' stata giocata la partita.
	 * 
	 * @return lo Stadio lo stadio in cui si gioca la partita.
	 * @author Maurizio Casciano
	 */
	public Stadio getStadio() {
		return this.getSettore().getStadio();
	}

	/**
	 * Restituisce il {@link Posto} prenotato
	 * 
	 * @return il Posto il posto prenotato.
	 * @author Maurizio Casciano
	 */
	public Posto getPosto() {
		return this.bigliettoPrenotato.getPosto();
	}

	/**
	 * Restituisce la fila del {@link Settore} dove e' situato il posto
	 * 
	 * @return la fila in cui e' situato il posto prenotato.
	 * @author Maurizio Casciano
	 */
	public int getFila() {
		return this.bigliettoPrenotato.getFila();
	}

	/**
	 * Restituisce il codice identificativo (ID) della prenotazione
	 * 
	 * @return l'IDPrenotazione
	 * @author Gaetano Antonucci
	 */
	/*
	 * public int getIDPrenotazione() { return IDPrenotazione; }
	 */

	/**
	 * Restituisce la data ({@link GregorianCalendar}) in cui e' stata
	 * effettuata la prenotazione.
	 * 
	 * @return dataPrenotazione ({@link GregorianCalendar}) la data della
	 *         prenotazione.
	 * @author Gaetano Antonucci
	 */
	public GregorianCalendar getDataPrenotazione() {
		return this.bigliettoPrenotato.getDataBiglietto();
	}

	/**
	 * Restituisce la data in cui e' stata effettuata la prenotazione come
	 * {@link String} formattata.
	 * 
	 * @return dataPrenotazioneString la data della prenotazione.
	 * @author Gaetano Antonucci
	 */
	/*
	 * public String getDataPrenotazioneString() { return
	 * dataPrenotazioneString; }
	 */

	/**
	 * Restituisce il {@link Biglietto} oggetto della prenotazione
	 * 
	 * @return bigliettoPrenotato il biglietto prenotato.
	 * @author Gaetano Antonucci
	 */
	public Biglietto getBigliettoPrenotato() {
		return bigliettoPrenotato;
	}

	/**
	 * Verifica se l'oggetto corrente e' uguale all'oggetto passato come
	 * parametro
	 * 
	 * @param obj
	 *            - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto e' uguale all'oggetto passato come
	 *         parametro, {@code false} altrimenti
	 * @author Gaetano Antonucci
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (this == obj)
			return true;

		if (obj == null)
			return false;

		if (getClass() != obj.getClass())
			return false;

		Prenotazione other = (Prenotazione) obj;

		if (this.bigliettoPrenotato.equals(other.bigliettoPrenotato)) {
			result = true;
		}

		return result;

	}

	/**
	 * Restituisce le informazioni dell'oggetto corrente come {@link String}
	 * 
	 * @author Gaetano Antonucci
	 */
	@Override
	public String toString() {
		return ("IDBiblietto: " + this.bigliettoPrenotato.getIDBiglietto() + " Cliente: "
				+ this.bigliettoPrenotato.getCliente().getCognome() + " "
				+ this.bigliettoPrenotato.getCliente().getNome());
	}

	private Biglietto bigliettoPrenotato;
	private static final long serialVersionUID = -5821307391776070857L;

}
