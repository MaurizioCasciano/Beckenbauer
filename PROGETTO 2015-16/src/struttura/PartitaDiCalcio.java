package struttura;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import user.Cliente;

public class PartitaDiCalcio {

	public PartitaDiCalcio(Squadra squadraCasa, Squadra squadraTrasferta, Stadio stadio, GregorianCalendar data) {
		this.squadraCasa = squadraCasa;
		this.squadraTrasferta = squadraTrasferta;
		this.stadio = stadio;
		this.data = data;
		this.dataString = DateFormat.getDateInstance(DateFormat.LONG).format(data.getTime());
		
		this.postiDisponibili = stadio.getCapienzaStadio();
	}
	
	public PartitaDiCalcio(Squadra squadraCasa, Squadra squadraTrasferta, Stadio stadio, GregorianCalendar data, Sconti sconto) {
		this.squadraCasa = squadraCasa;
		this.squadraTrasferta = squadraTrasferta;
		this.stadio = stadio;
		this.data = data;
		this.dataString = DateFormat.getDateInstance(DateFormat.LONG).format(data.getTime());
		
		this.postiDisponibili = stadio.getCapienzaStadio();
		
		this.sconto = sconto;
	}
	

	/**
	 * Restituisce la data in cui sar� disputata la partita.
	 * @return La data in cui sar� disputata la partita.
	 */
	public GregorianCalendar getData() {
		return data;
	}

	/**
	 * Restituisce lo stadio in cui sar� disputata la partita.
	 * @return Lo stadio in cui sar� disputata la partita.
	 */
	public Stadio getStadio() {
		return this.stadio;
	}

	/**
	 * Restituisce la squadra che gioca in casa.
	 * @return La squadra che gioca in casa.
	 */
	public Squadra getSquadraCasa() {
		return squadraCasa;
	}

	/**
	 * Restituisce la squadra che gioca in trasferta.
	 * @return La squadra che gioca intrasferta.
	 */
	public Squadra getSquadraTrasferta() {
		return squadraTrasferta;
	}
	
	/**
	 * Retistituisce i posti ancora disponbili per la partita
	 * @return posti disponibili
	 */
	public int getPostiDisponibili(){
		return this.postiDisponibili;
	}
	
	/**
	 * Restituisce la politica di sconto della partita
	 * @return politica di sconto se è presente
	 * @return null se la partita non ha una politica di sconto
	 */
	public Sconti getSconto(){
		return this.sconto;
	}
	
	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}
	
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [data = " + dataString + ", stadio = " + stadio + ", squadraCasa = " + squadraCasa
				+ ", squadraTrasferta = " + squadraTrasferta + "]";
	}


	public void prenotaBiglietto(Cliente c){
		this.prenotazioni.add(new Prenotazione(c, this));
	}
	
	
	public void acquistaBigliettoPrenotato(Cliente c){
		for(int i = 0; i < this.prenotazioni.size(); i++){
			if(this.prenotazioni.get(i).getCliente().equals(c)){
				
			}
		}
	}
	
	private GregorianCalendar data;
	private String dataString;
	private Stadio stadio;
	private Squadra squadraCasa, squadraTrasferta;
	
	private int postiDisponibili;	// (GA) di default è il numero di posti disponibili dello stadio, 
									// poi viene aggiornato il base alle vendite e alle prenotazioni
	
	private Sconti sconto;			// (GA) di default può essere null se la partita non ha nessuna politica
									// di sconto associata
	
	private ArrayList<Prenotazione> prenotazioni;
}
