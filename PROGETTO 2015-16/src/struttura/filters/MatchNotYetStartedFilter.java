package struttura.filters;

import java.util.GregorianCalendar;

import struttura.Partita;

public class MatchNotYetStartedFilter implements Filter {

	/**
	 * Filtra una partita che non è ancora iniziata.
	 * 
	 * @param partitaDiCalcio
	 *            La partita da filtrare.
	 * @return true se la partita non è ancora iniziata, false altrimenti.
	 */
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		return partitaDiCalcio.getData().after(new GregorianCalendar());
	}
}
