package struttura;

import java.text.DateFormat;
import java.util.GregorianCalendar;

public class PrenotazioneV2 {
	
	public PrenotazioneV2(int iDPrenotazione, GregorianCalendar dataPrenotazione, Biglietto bigliettoPrenotato) {
		IDPrenotazione = iDPrenotazione;
		this.dataPrenotazione = dataPrenotazione;
		this.dataPrenotazioneString = DateFormat.getDateInstance(DateFormat.LONG).format(dataPrenotazione.getTime());
		this.bigliettoPrenotato = bigliettoPrenotato;
		
		this.bigliettoPrenotato.setPrenotato(IS_PRENOTATO);
	}
	
	
	
	

	private int IDPrenotazione;
	private GregorianCalendar dataPrenotazione;
	private String dataPrenotazioneString;
	private Biglietto bigliettoPrenotato;
	
	private static final boolean IS_PRENOTATO = true;
	
	//Iteratore
	private static int IDCounter = 1;
	

}
