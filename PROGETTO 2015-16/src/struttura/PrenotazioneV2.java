package struttura;

import java.text.DateFormat;
import java.util.GregorianCalendar;

public class PrenotazioneV2 {
	
	public PrenotazioneV2(GregorianCalendar dataPrenotazione, Biglietto bigliettoPrenotato) {
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PrenotazioneV2 other = (PrenotazioneV2) obj;
		if (bigliettoPrenotato == null) {
			if (other.bigliettoPrenotato != null)
				return false;
		} else if (!bigliettoPrenotato.equals(other.bigliettoPrenotato))
			return false;
		return true;
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
	private static int IDCounter = 1;
	

}