/**
 * Program: PartitaDiCalcio.java
 * Purpose: classe che modella una partita di calcio 
 * @author Maurizio Casciano & Gaetano Antonucci
 * Last Modified: 29/12/2015 (GA)
 */

package struttura;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Partita implements Serializable {

	public Partita(Squadra squadraInCasa, Squadra squadraInTrasferta, Stadio stadio, GregorianCalendar data) {
		this.squadraInCasa = squadraInCasa;
		this.squadraInTrasferta = squadraInTrasferta;
		this.stadio = stadio;
		this.data = data;

		this.postiDisponibili = stadio.getCapienzaStadio();
	}

	/**
	 * Crea una nuova partita con valori di default, usata per inserire una
	 * nuova riga nella JTable delle partite, e poi permettere la modifica
	 * all'utente.
	 */
	public Partita() {
		this.squadraInCasa = new Squadra("-");
		this.squadraInTrasferta = new Squadra("-");
		this.stadio = new Stadio("-", 0);
		this.data = new GregorianCalendar();
	}

	public Partita(Squadra squadraCasa, Squadra squadraTrasferta, Stadio stadio, GregorianCalendar data,
			Sconti sconto) {
		this.squadraInCasa = squadraCasa;
		this.squadraInTrasferta = squadraTrasferta;
		this.stadio = stadio;
		this.data = data;

		this.postiDisponibili = stadio.getCapienzaStadio();

		this.sconto = sconto;
	}

	/**
	 * Restituisce la squadra che gioca in casa.
	 * 
	 * @return La squadra che gioca in casa.
	 */
	public Squadra getSquadraInCasa() {
		return this.squadraInCasa;
	}

	/**
	 * Imposta la squadra che gioca in casa.
	 * 
	 * @param squadraInCasa
	 *            La squadra che gioca in casa.
	 */
	public void setSquadraInCasa(Squadra squadraInCasa) {
		this.squadraInCasa = squadraInCasa;
	}

	/**
	 * Restituisce la squadra che gioca in trasferta.
	 * 
	 * @return La squadra che gioca intrasferta.
	 */
	public Squadra getSquadraInTrasferta() {
		return this.squadraInTrasferta;
	}

	/**
	 * Imposta la squadra che gioca in trasferta.
	 * 
	 * @param squadraInCasa
	 *            La squadra che gioca in trasferta.
	 */
	public void setSquadraInTrasferta(Squadra squadraInTrasferta) {
		this.squadraInTrasferta = squadraInTrasferta;
	}

	/**
	 * Restituisce lo stadio in cui sarï¿½ disputata la partita.
	 * 
	 * @return Lo stadio in cui sarï¿½ disputata la partita.
	 */
	public Stadio getStadio() {
		return this.stadio;
	}

	/**
	 * Imposta lo stadio in cui sarà disputata la partita.
	 * 
	 * @param stadio
	 *            Lo stadio in cui sarà disputata la partita.
	 */
	public void setStadio(Stadio stadio) {
		this.stadio = stadio;
	}

	/**
	 * Restituisce la data in cui sarà disputata la partita.
	 * 
	 * @return La data in cui sarà disputata la partita.
	 */
	public GregorianCalendar getData() {
		return this.data;
	}

	/**
	 * Imposta la data in cui sarà disputata la partita.
	 * 
	 * @param data
	 *            La data in cui sarà disputata la partita.
	 */
	public void setData(GregorianCalendar data) {
		this.data = data;
	}

	/**
	 * Retistituisce i posti ancora disponbili per la partita
	 * 
	 * @return posti disponibili
	 */
	public int getPostiDisponibili() {
		return this.postiDisponibili;
	}

	/**
	 * Restituisce la politica di sconto della partita
	 * 
	 * @return politica di sconto se Ã¨ presente
	 * @return null se la partita non ha una politica di sconto
	 */
	public Sconti getSconto() {
		return this.sconto;
	}

	public ArrayList<Prenotazione> getPrenotazioni() {
		return prenotazioni;
	}

	/*
	 * public void prenotaBiglietto(Cliente c){ this.prenotazioni.add(new
	 * Prenotazione(c, this)); }
	 * 
	 * 
	 * public void acquistaBigliettoPrenotato(Cliente c){ for(int i = 0; i <
	 * this.prenotazioni.size(); i++){
	 * if(this.prenotazioni.get(i).getCliente().equals(c)){
	 * 
	 * } } }
	 */

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [squadraInCasa=" + squadraInCasa.getNome() + ", squadraInTrasferta="
				+ squadraInTrasferta.getNome() + ", stadio=" + stadio.getNome() + ", data="
				+ DATE_FORMAT.format(data.getTime()) + "]";
	}

	@Override
	public boolean equals(Object obj) { // (GA)
		boolean result = false;

		Partita other = (Partita) obj;

		if ((this.data.equals(other.data)) && (this.squadraInCasa.equals(other.squadraInCasa))
				&& (this.squadraInTrasferta.equals(other.squadraInTrasferta))) {
			result = true;
		}

		return result;
	}

	private static final long serialVersionUID = 1548727127680681004L;
	private Squadra squadraInCasa, squadraInTrasferta;
	private Stadio stadio;
	private GregorianCalendar data;

	private int postiDisponibili; // (GA) di default Ã¨ il numero di posti
									// disponibili dello stadio,
									// poi viene aggiornato il base alle vendite
									// e alle prenotazioni

	private Sconti sconto; // (GA) di default puÃ² essere null se la partita non
							// ha nessuna politica
							// di sconto associata
	private ArrayList<Prenotazione> prenotazioni;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy HH:mm");
}
