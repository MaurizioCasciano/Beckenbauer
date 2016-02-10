package struttura.filters;

import java.util.GregorianCalendar;

import struttura.Partita;
import struttura.Sconto;

/**
 * Interfaccia che modella un filtro sugllo {@link Sconto}
 * 
 * @author Gaetano Antonucci
 *
 */
public interface ScontoFilter {

	/**
	 * Aggiorna lo sconto da analizzare con quello corrente
	 * 
	 * @param i
	 *            - l'indice dell'ArrayList degli Sconti
	 * @author Gaetano Antonucci
	 */
	void updateCurrentSconto(int i);
	boolean accept(Partita partitaDaVerificare, GregorianCalendar dataDaVerificare);
}
