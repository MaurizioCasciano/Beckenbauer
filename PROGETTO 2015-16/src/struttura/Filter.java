package struttura;
import java.util.GregorianCalendar;

/**
 * 
 */
public interface Filter {
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
	public boolean accept(PartitaDiCalcio partitaDiCalcio, Stadio stadio);
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
	public boolean accept(PartitaDiCalcio partitaDiCalcio, GregorianCalendar data);
	/**
	 * Filtra una PartitaDiCalcio che non è ancora iniziata.
	 * 
	 * @param partitaDiCalcio
	 *            La partita di calcio da filtrare.
	 * @return True Se la partita deve ancora iniziare, False altrimenti.
	 */
	public boolean accept(PartitaDiCalcio partitaDiCalcio);
}
