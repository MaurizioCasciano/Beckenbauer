package struttura;

import java.io.Serializable;
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
	
	public Acquisto(PrenotazioneV2 prenotazione, StrutturaSportiva struct){
		
		this.IDAcquisto = ++IDCounter;
		this.dataAcquisto = new GregorianCalendar();
		
		this.biglietto = prenotazione.getBigliettoPrenotato();
		
		this.biglietto.setPagato(IS_PAGATO);
		
		/* Cancella la prenotazione in quanto è stato effettuato l'acquisto */
		struct.cancellaPrenotazioneCliente(this.biglietto.getCliente(), this.biglietto.getPartita());
	
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
		return dataAcquisto;
	}

	/**
	 * @return the biglietto
	 */
	public Biglietto getBiglietto() {
		return biglietto;
	}
	
	@Override
	public String toString() {
		return("ID Acquisto" + this.IDAcquisto + " Data Acquisto: " + this.dataAcquisto + " \n" + 
	            "Biglietto: " + this.biglietto);
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if(obj == null)
			return false;
		
		if(this == obj)
			return true;
		
		Acquisto other = (Acquisto) obj;
		
		if(this.dataAcquisto.equals(other.dataAcquisto) && (this.biglietto.equals(other.biglietto)))
			result = true;
		
		return result;
			
	}

	private int IDAcquisto;
	private GregorianCalendar dataAcquisto;
	private Biglietto biglietto;
	
	private static final boolean NOT_PRENOTATO = false;
	private static final boolean IS_PAGATO = true;
	
	//Iteratore
	private static int IDCounter = 1;
	

	private static final long serialVersionUID = -7156904400269928829L;
}
