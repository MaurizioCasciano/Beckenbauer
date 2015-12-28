package struttura;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class MatchFilter implements Filter {

	/**
	 * Filtra una PartitaDiCalcio in base alla data (settimana).
	 * 
	 * @param partitaDiCalcio
	 *            La partita di calcio da filtrare.
	 * @param data
	 *            La data(settimana)in cui si vuole che la partita di calcio si
	 *            svolga.
	 * @return True Se la partita si svolge nella data(settimana) indicata,
	 *         False altrimenti.
	 */
	@Override
	public boolean accept(PartitaDiCalcio partitaDiCalcio, GregorianCalendar data) {
		if (partitaDiCalcio.getData().get(Calendar.YEAR) == data.get(Calendar.YEAR)) {
			return partitaDiCalcio.getData().get(Calendar.WEEK_OF_YEAR) == data.get(Calendar.WEEK_OF_YEAR);
		} else {
			return false;
		}
	}

	/**
	 * Filtra una PartitaDiCalcio in base allo Stadio.
	 * 
	 * @param partitaDiCalcio
	 *            La partita di calcio da filtrare.
	 * @param stadio
	 *            Lo stadio in cui si vuole che la partita di calcio si svolga.
	 * @return True Se la partita si svolge nello stadio indicato, False
	 *         altrimenti.
	 */
	@Override
	public boolean accept(PartitaDiCalcio partitaDiCalcio, Stadio stadio) {
		return partitaDiCalcio.getStadio().equals(stadio);
	}

	/**
	 * Filtra una PartitaDiCalcio che non è ancora iniziata.
	 * 
	 * @param partitaDiCalcio
	 *            La partita di calcio da filtrare.
	 * @return True Se la partita deve ancora iniziare, False altrimenti.
	 */
	@Override
	public boolean accept(PartitaDiCalcio partitaDiCalcio) {
		return partitaDiCalcio.getData().after(new GregorianCalendar());
	}
}
