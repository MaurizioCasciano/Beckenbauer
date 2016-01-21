/**
 * Program: Partita.java
 * Purpose: classe che modella una partita di calcio 
 * @author Maurizio Casciano & Gaetano Antonucci
 * Last Modified: 11/01/2016 (GA)
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

		/********************************************/
		/* ArrayList per salvare lo stato dei posti */
		/********************************************/
		this.settori = this.stadio.getSettoriClone();
	}

	/**
	 * Crea una nuova partita con valori di default, usata per inserire una
	 * nuova riga nella JTable delle partite, e poi permettere la modifica
	 * all'utente.
	 */
	public Partita() {
		this.squadraInCasa = new Squadra("-");
		this.squadraInTrasferta = new Squadra("-");
		this.stadio = new Stadio("-", 50000, 0.00);
		this.data = new GregorianCalendar();
		this.settori = this.stadio.getSettoriClone();
	}

	public ArrayList<Settore> getSettori() {
		return this.settori;
	}

	public void resetSeatStatus(Prenotazione prenotazione) {
		Posto posto = prenotazione.getPosto();
		Settore settore = posto.getSettore();

		for (Settore s : this.settori) {
			if (s.equals(settore)) {
				for (Posto p : s.getPosti()) {
					if (p.equals(posto)) {
						p.setStato(SeatStatus.LIBERO);
						break;
					}
				}
				break;
			}
		}
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
	 * Restituisce lo stadio in cui sara' disputata la partita.
	 * 
	 * @return Lo stadio in cui sara' disputata la partita.
	 */
	public Stadio getStadio() {
		return this.stadio;
	}

	/**
	 * Imposta lo stadio in cui sara' disputata la partita.
	 * 
	 * ATTENZIONE: le prenotazioni e gli acquisti presenti andranno perse.
	 * 
	 * @param stadio
	 *            Lo stadio in cui sara' disputata la partita.
	 */
	public void setStadio(Stadio stadio) {
		this.stadio = stadio;
		this.settori = this.stadio.getSettoriClone();
	}

	/**
	 * Restituisce la data in cui sara' disputata la partita.
	 * 
	 * @return La data in cui sara' disputata la partita.
	 */
	public GregorianCalendar getData() {
		return this.data;
	}

	/**
	 * Imposta la data in cui sara' disputata la partita.
	 * 
	 * @param data
	 *            La data in cui sara' disputata la partita.
	 */
	public void setData(GregorianCalendar data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + ": " + squadraInCasa.getNome() + " - " + squadraInTrasferta.getNome()
				+ " Stadio: " + stadio.getNome() + " " + DATE_FORMAT.format(data.getTime());
	}

	public ArrayList<Posto> getPosti() {
		return posti;
	}

	@Override
	public boolean equals(Object obj) { // (GA)
		boolean result = false;

		if (this == obj) {
			result = true;
		}

		if (obj == null) {
			result = false;
		}

		if (getClass() != obj.getClass())
			result = false;

		Partita other = (Partita) obj;

		if ((this.data.equals(other.data)) && (this.squadraInCasa.equals(other.squadraInCasa))
				&& (this.squadraInTrasferta.equals(other.squadraInTrasferta))) {
			result = true;
		}

		return result;
	}

	private Squadra squadraInCasa, squadraInTrasferta;
	private Stadio stadio;
	private GregorianCalendar data;

	/*
	 * Arraylist per tenere traccia dei posti prenotati, acquistati.
	 */
	private ArrayList<Posto> posti;
	private ArrayList<Settore> settori;

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E  dd/MM/yyyy  HH:mm");

	private static final long serialVersionUID = 1548727127680681004L;

}
