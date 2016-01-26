package struttura.filters;

/**
 * Interfaccia che modella un filtro sugli {@link Sconti}
 * 
 * @author Gaetano Antonucci
 *
 */
public interface ScontiFilter extends Filter{
	
	/**
	 * Aggiorna lo sconto da analizzare con quello corrente
	 * 
	 * @param i - l'indice dell'ArrayList degli Sconti
	 * @author Gaetano Antonucci
	 */
	public void updateCurrentSconto(int i);
}
