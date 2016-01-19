package struttura;

import java.io.Serializable;
import java.text.DateFormat;
import java.util.GregorianCalendar;

public class Prenotazione implements Serializable {

	public Prenotazione(GregorianCalendar dataPrenotazione, Biglietto bigliettoPrenotato) {
		this.IDPrenotazione = ++IDCounter;
		this.dataPrenotazione = dataPrenotazione;
		this.dataPrenotazioneString = DateFormat.getDateInstance(DateFormat.LONG).format(dataPrenotazione.getTime());
		this.bigliettoPrenotato = bigliettoPrenotato;
		
		this.bigliettoPrenotato.setPrenotato(IS_PRENOTATO);
	}
	
	/**
	 * @return the iDPrenotazione
	 */
	public int getIDPrenotazione() {
		return IDPrenotazione;
	}

	/**
	 * @return the dataPrenotazione
	 */
	public GregorianCalendar getDataPrenotazione() {
		return dataPrenotazione;
	}

	/**
	 * @return the dataPrenotazioneString
	 */
	public String getDataPrenotazioneString() {
		return dataPrenotazioneString;
	}

	/**
	 * @return the bigliettoPrenotato
	 */
	public Biglietto getBigliettoPrenotato() {
		return bigliettoPrenotato;
	}

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
		
		if(this.bigliettoPrenotato.equals(other.bigliettoPrenotato)){
			result = true;
		}
		
		return result;
		
	}
	

	@Override
	public String toString() {
		return ("IDPrenotazione: " + this.IDPrenotazione + " Data Prenotazione: " + this.dataPrenotazioneString +
				"\n IDBiblietto: " + this.bigliettoPrenotato.getIDBiglietto() + " Cliente: " + this.bigliettoPrenotato.getCliente().getCognome() 
				+ " " + this.bigliettoPrenotato.getCliente().getNome());
	}

	private int IDPrenotazione;
	private GregorianCalendar dataPrenotazione;
	private String dataPrenotazioneString;
	private Biglietto bigliettoPrenotato;
	
	private static final boolean IS_PRENOTATO = true;
	
	//Iteratore
	private static int IDCounter = 0;
	
	private static final long serialVersionUID = -5821307391776070857L;
	
}
