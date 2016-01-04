package struttura.filters;

import struttura.Partita;
import struttura.Stadio;

public class MatchByStadiumFilter implements Filter {

	/**
	 * Costruisce un Filter con lo Stadio passato in input.
	 * 
	 * @param stadio
	 *            Lo Stadio in cui si vuole che la partita si giochi.
	 */
	public MatchByStadiumFilter(Stadio stadio) {
		this.stadio = stadio;
	}

	/**
	 * Filtra una Partita in base allo Stadio in cui si gioca.
	 * 
	 * @param La
	 *            partita da filtrare.
	 * 
	 * @return true se la partita si svolge nello Stadio indicato, false
	 *         altrimenti.
	 */
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		return partitaDiCalcio.getStadio().equals(this.stadio);
	}

	private Stadio stadio;
}
