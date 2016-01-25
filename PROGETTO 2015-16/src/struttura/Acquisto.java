package struttura;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import user.Cliente;

public class Acquisto implements Serializable {
	public Acquisto(Cliente c, Partita partita, Settore settore, int fila, int posto, StrutturaSportiva stru){
		this.IDAcquisto = ++IDCounter;
		this.dataAcquisto = new GregorianCalendar();
		this.biglietto = new Biglietto(stru, c, partita, settore, fila, posto);

		this.biglietto.setPrenotato(NOT_PRENOTATO);
		this.biglietto.setPagato(IS_PAGATO);

	}

	public Acquisto(Prenotazione prenotazione, StrutturaSportiva struct) {

		this.IDAcquisto = ++IDCounter;
		this.dataAcquisto = new GregorianCalendar();

		this.biglietto = prenotazione.getBigliettoPrenotato();

		this.biglietto.setPagato(IS_PAGATO);

		/* Cancella la prenotazione in quanto è stato effettuato l'acquisto */
		struct.cancellaPrenotazioneCliente(this.biglietto.getCliente(), this.biglietto.getPartita());

	}

	/**
	 * 
	 * @return
	 * @author Maurizio
	 */
	public Cliente getCliente() {
		return this.biglietto.getCliente();
	}

	/**
	 * 
	 * @return
	 * @author Maurizio
	 */
	public Partita getPartita() {
		return this.biglietto.getPartita();
	}

	/**
	 * 
	 * @return
	 * @author Maurizio
	 */
	public Settore getSettore() {
		return this.biglietto.getSettore();
	}

	public Stadio getStadio() {
		return this.getSettore().getStadio();
	}

	/**
	 * @return
	 * @author Maurizio
	 */
	public int getFila() {
		return this.biglietto.getFila();
	}

	public Posto getPosto() {
		return this.biglietto.getPosto();
	}

	/**
	 * @return the iDAcquisto
	 */
	public int getIDAcquisto() {
		return IDAcquisto;
	}

	/**
	 * @return the dataAcquisto
	 */
	public GregorianCalendar getDataAcquisto() {
		return this.dataAcquisto;
	}

	/**
	 * @return the biglietto
	 */
	public Biglietto getBiglietto() {
		return this.biglietto;
	}

	@Override
	public String toString() {
		int giornoAcquisto = this.dataAcquisto.get(Calendar.DAY_OF_MONTH);
		int meseAcquisto = this.dataAcquisto.get(Calendar.MONTH) + 1;
		int annoAcquisto = this.dataAcquisto.get(Calendar.YEAR);
		return ("ID Acquisto: " + this.IDAcquisto + " Data Acquisto: " + giornoAcquisto + "/" + meseAcquisto + "/"
				+ annoAcquisto + " \n" + "Biglietto: " + this.biglietto);
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (obj == null)
			return false;

		if (this == obj)
			return true;

		Acquisto other = (Acquisto) obj;

		if (this.biglietto.equals(other.biglietto))
			result = true;

		return result;

	}

	private int IDAcquisto;
	private GregorianCalendar dataAcquisto;
	private Biglietto biglietto;

	private static final boolean NOT_PRENOTATO = false;
	private static final boolean IS_PAGATO = true;

	// Iteratore
	private static int IDCounter = 1;

	private static final long serialVersionUID = -7156904400269928829L;
}
