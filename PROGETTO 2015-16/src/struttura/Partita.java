package struttura;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * Classe che modella una partita di calcio
 * 
 * @author Maurizio Casciano
 * @author Gaetano Antonucci
 */
public class Partita implements Serializable {

	/**
	 * Costruisce un oggetto Partita in base ai parametri impostati
	 * 
	 * @param squadraInCasa
	 *            - la {@link Squadra} che gioca in Casa
	 * @param squadraInTrasferta
	 *            - la {@link Squadra} che gioca in Trasferta
	 * @param stadio
	 *            - lo {@link Stadio} in cui sara' giocata la partita
	 * @param data
	 *            - la data ({@link GregorianCalendar}) in cui sara' disputata
	 *            la partita
	 * 
	 * @author Maurizio Casciano
	 */
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
	 * 
	 * @author Maurizio Casciano
	 */
	public Partita() {
		this.squadraInCasa = new Squadra("-");
		this.squadraInTrasferta = new Squadra("-");
		this.stadio = new Stadio("-", Stadio.CAPIENZA_MINIMA, Stadio.PREZZO_MINIMO);
		this.data = new GregorianCalendar();
		this.settori = this.stadio.getSettoriClone();
	}

	/**
	 * Restituisce i settori dello stadio per questa partita.
	 * 
	 * @return l'ArrayList contenente i settori dello stadio per questa partita
	 */
	public ArrayList<Settore> getSettori() {
		return this.settori;
	}

	/**
	 * Imposta lo stato del posto della prenotazione passata in input al nuovo
	 * stato a scelta tra {@link SeatStatus#LIBERO},
	 * {@link SeatStatus#PRENOTATO}, {@link SeatStatus#VENDUTO}.
	 * 
	 * @param prenotazione
	 *            La prenotazione eliminata o scaduta.
	 * @param newSeatStaus
	 *            Il nuovo stato da impostare per il posto della prenotazione.
	 * @author Maurizio Casciano
	 */
	public void resetSeatStatus(Prenotazione prenotazione, SeatStatus newSeatStaus) {
		Posto posto = prenotazione.getPosto();
		Settore settore = posto.getSettore();

		for (Settore s : this.settori) {
			if (s.equals(settore)) {
				for (Posto p : s.getPosti()) {
					if (p.equals(posto)) {
						p.setStato(newSeatStaus);
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
	 * @author Maurizio Casciano
	 */
	public Squadra getSquadraInCasa() {
		return this.squadraInCasa;
	}

	/**
	 * Imposta la squadra che gioca in casa.
	 * 
	 * @param squadraInCasa
	 *            La squadra che gioca in casa.
	 * @author Maurizio Casciano
	 */
	public void setSquadraInCasa(Squadra squadraInCasa) {
		this.squadraInCasa = squadraInCasa;
	}

	/**
	 * Restituisce la squadra che gioca in trasferta.
	 * 
	 * @return La squadra che gioca intrasferta.
	 * @author Maurizio Casciano
	 */
	public Squadra getSquadraInTrasferta() {
		return this.squadraInTrasferta;
	}

	/**
	 * Imposta la squadra che gioca in trasferta.
	 * 
	 * @param squadraInTrasferta
	 *            La squadra che gioca in trasferta.
	 * @author Maurizio Casciano
	 */
	public void setSquadraInTrasferta(Squadra squadraInTrasferta) {
		this.squadraInTrasferta = squadraInTrasferta;
	}

	/**
	 * Restituisce lo stadio in cui sara' disputata la partita.
	 * 
	 * @return Lo stadio in cui sara' disputata la partita.
	 * @author Maurizio Casciano
	 */
	public Stadio getStadio() {
		return this.stadio;
	}

	/**
	 * Imposta lo stadio in cui sara' disputata la partita.
	 * 
	 * ATTENZIONE: le prenotazioni e gli acquisti presenti andranno persi.
	 * 
	 * @param stadio
	 *            - Lo stadio in cui sara' disputata la partita.
	 * @author Maurizio Casciano
	 */
	public void setStadio(Stadio stadio) {
		if (stadio != null) {
			this.stadio = stadio;
			this.settori = this.stadio.getSettoriClone();
		}
	}

	/**
	 * Restituisce la data ({@link GregorianCalendar}) in cui sara' disputata la
	 * partita.
	 * 
	 * @return La data ({@link GregorianCalendar}) in cui sara' disputata la
	 *         partita.
	 * @author Maurizio Casciano
	 */
	public GregorianCalendar getData() {
		return this.data;
	}

	/**
	 * Imposta la data ({@link GregorianCalendar}) in cui sara' disputata la
	 * partita.
	 * 
	 * @param data
	 *            - La data ({@link GregorianCalendar}) in cui sara' disputata
	 *            la partita.
	 * @author Maurizio Casciano
	 */
	public void setData(GregorianCalendar data) {
		this.data = data;
	}

	/**
	 * Restituisce le informazioni della partita
	 * 
	 * @author Maurizio Casciano
	 */
	@Override
	public String toString() {
		return this.getClass().getName() + " [squadraInCasa=" + squadraInCasa + ", squadraInTrasferta="
				+ squadraInTrasferta + ", stadio=" + stadio + ", data=" + data.getTime() + ", settori=" + settori + "]";
	}

	/**
	 * Verifica se l'oggetto corrente e' uguale all'oggetto passato come
	 * parametro
	 * 
	 * @param obj
	 *            - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto è uguale all'oggetto passato come
	 *         parametro, {@code false} altrimenti
	 * @author Gaetano Antonucci
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass())
			return false;

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
	private ArrayList<Settore> settori;

	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("E  dd/MM/yyyy  HH:mm");

	private static final long serialVersionUID = 1548727127680681004L;

}
