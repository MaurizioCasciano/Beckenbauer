package struttura.filters;

import java.io.Serializable;
import java.util.GregorianCalendar;

import struttura.Partita;

/**
 * Classe che modella un filto su una partita in base al fatto che debba ancora
 * giocarsi.
 * 
 * @author Maurizio
 */
public class MatchNotYetStartedFilter implements Filter, Serializable {

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

	private static final long serialVersionUID = -3531701089734504572L;
}
