package struttura.filters;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

import struttura.Partita;

/**
 * Classe che modella un filto su una partita in base alla settimana in cui si gioca.
 * 
 * @author Maurizio
 */
public class MatchByWeekFilter implements Filter, Serializable {

	/**
	 * Costruisce un Filter con la data(settimana) passata in input.
	 * 
	 * @param data
	 *            La data in cui si vuole che la partita si giochi.
	 */
	public MatchByWeekFilter(GregorianCalendar data) {
		this.data = data;
	}

	/**
	 * Filtra una PartitaDiCalcio in base alla data (settimana) in cui si gioca.
	 * 
	 * @param partitaDiCalcio
	 *            La partita di calcio da filtrare.
	 * @return True Se la partita si svolge nella data(settimana) indicata,
	 *         False altrimenti.
	 */
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		if (partitaDiCalcio.getData().get(Calendar.YEAR) == this.data.get(Calendar.YEAR)) {
			return partitaDiCalcio.getData().get(Calendar.WEEK_OF_YEAR) == data.get(Calendar.WEEK_OF_YEAR);
		} else {
			return false;
		}
	}

	private static final long serialVersionUID = -9100419677952099595L;
	private GregorianCalendar data;
}
