package struttura;

import java.io.Serializable;
import java.util.GregorianCalendar;

import user.Cliente;

/**
 * Classe che modella l'acquisto di un biglietto sia direttamente sia da una
 * prenotazione
 * 
 * @author Gaetano Antonucci
 * @author Maurizio Casciano
 *
 */
public class Acquisto implements Serializable {
	/**
	 * Costruisce un acquisto diretto, cioe' senza una prenotazione in base ai
	 * parametri impostati
	 * 
	 * @param c
	 *            - il cliente che acquista il biglietto
	 * @param partita
	 *            - la partita su cui si effettua l'acquisto
	 * @param settore
	 *            - il settore dello stadio
	 * @param fila
	 *            - la fila del settore
	 * @param posto
	 *            - il posto scelto dal cliente
	 * @param stru
	 *            - la struttura sportiva sui cui si sta operando
	 * 
	 * @author Gaetano Antonucci
	 */
	public Acquisto(Cliente c, Partita partita, Settore settore, int fila, int posto, StrutturaSportiva stru) {
		//this.IDAcquisto = ++IDCounter;
		//this.dataAcquisto = new GregorianCalendar();
		this.biglietto = new Biglietto(stru, c, partita, settore, fila, posto);
		this.biglietto.setVenduto();
	}

	/**
	 * Costruisce un acquisto basato su una prenotazione, dopodiche' elimina la
	 * {@link Prenotazione}
	 * 
	 * @param prenotazione
	 *            - la prenotazione da cui si ricavano le informazioni
	 * @param struct
	 *            - la struttura sportiva sulla quale si sta operando
	 * 
	 * @author Gaetano Antonucci
	 */
	public Acquisto(Prenotazione prenotazione, StrutturaSportiva struct) {

		//this.IDAcquisto = ++IDCounter;
		//this.dataAcquisto = new GregorianCalendar();

		this.biglietto = prenotazione.getBigliettoPrenotato();

		this.biglietto.setVenduto();

		/* Cancella la prenotazione in quanto e' stato effettuato l'acquisto */
		struct.cancellaPrenotazioneCliente(this.biglietto.getCliente(), this.biglietto.getPartita());

	}

	/**
	 * Reistituisce il cliente che ha effetuato l'acquisto recuperandolo dal
	 * {@link Biglietto}
	 * 
	 * @return il cliente che ha acquistato
	 * @author Maurizio Casciano
	 */
	public Cliente getCliente() {
		return this.biglietto.getCliente();
	}

	/**
	 * Restituisce la partita su cui e' stato effetuato l'acquisto,
	 * recuperandola dal {@link Biglietto}
	 * 
	 * @return la partita su cui e' stato effettuato l'acquisto.
	 * @author Maurizio Casciano
	 */
	public Partita getPartita() {
		return this.biglietto.getPartita();
	}

	/**
	 * Restituisce il {@link Settore} dello {@link Stadio} scelto dal
	 * {@link Cliente}
	 * 
	 * @return il settore relativo all'acquisto.
	 * @author Maurizio Casciano
	 */
	public Settore getSettore() {
		return this.biglietto.getSettore();
	}

	/**
	 * Restituisce lo {@link Stadio} dove sara'/e' stata giocata la
	 * {@link Partita}
	 * 
	 * @return lo stadio.
	 * @author Maurizio Casciano
	 */
	public Stadio getStadio() {
		return this.getSettore().getStadio();
	}

	/**
	 * Restituisce la fila del {@link Settore} scelto dal {@link Cliente}
	 * 
	 * @return la fila relativa all'acquisto.
	 * @author Maurizio Cascaino
	 */
	public int getFila() {
		return this.biglietto.getFila();
	}

	/**
	 * Restituisce il {@link Posto} scelto dal {@link Cliente}
	 * 
	 * @return il posto relativo all'acquisto.
	 * @author Maurizio Casciano
	 */
	public Posto getPosto() {
		return this.biglietto.getPosto();
	}

	/**
	 * Restituisce il codice identificativo (ID) dell'acquisto
	 * 
	 * @return l'IDAcquisto
	 * @author Gaetano Antonucci
	 */
	/*public int getIDAcquisto() {
		return IDAcquisto;
	}*/

	/**
	 * Restituisce la data ({@link GregorianCalendar}) in cui viene effettuato
	 * l'acquisto.
	 * 
	 * @return la dataAcquisto
	 * @author Gaetano Antonucci
	 */
	public GregorianCalendar getDataAcquisto() {
		return this.biglietto.getDataBiglietto();
	}

	/**
	 * Restituisce il {@link Biglietto} acquistato
	 *
	 * @return il biglietto
	 * @author Gaetano Antonucci
	 */
	public Biglietto getBiglietto() {
		return this.biglietto;
	}

	@Override
	public String toString() {
		return this.getClass().getName() + /*" [IDAcquisto=" + IDAcquisto + */"[dataAcquisto=" + biglietto.getDataBiglietto().getTime()
				+ ", biglietto=" + biglietto + "]";
	}

	/**
	 * Verifica se l'oggetto corrente e' uguale all'oggetto passato come
	 * parametro
	 * 
	 * @param obj
	 *            - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto e' uguale all'oggetto passato come
	 *         parametro, {@code false} altrimenti
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj == null)
			return false;

		if (this == obj)
			return true;

		if (getClass() != obj.getClass())
			return false;

		Acquisto other = (Acquisto) obj;

		if (this.biglietto.equals(other.biglietto))
			result = true;

		return result;

	}
	
	//private int IDAcquisto;
	//private GregorianCalendar dataAcquisto;
	private Biglietto biglietto;
	// Iteratore
	//private static int IDCounter = 1;

	private static final long serialVersionUID = -7156904400269928829L;

	
}
