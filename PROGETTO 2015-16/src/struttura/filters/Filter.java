package struttura.filters;

import struttura.Partita;

/**
 * Interfaccia che modella un filtro su una {@link Partita}
 * 
 * @author Maurizio Casciano
 */
public interface Filter {
	/**
	 * Metodo che verifica se la {@link Partita} passata come parametro soddisfa
	 * il filtro.
	 * 
	 * @param partitaDiCalcio
	 *            la {@link Partita} su cui s'imposta il filtro
	 * @return {@code true} se la partita soddisfa il filtro, {@code false}
	 *         altrimenti
	 * @author Maurizio Casciano
	 */
	public boolean accept(Partita partitaDiCalcio);
}
