package struttura.filters;
import struttura.Sconto;

/**
 * Interfaccia che modella un filtro sugllo {@link Sconto}
 * 
 * @author Gaetano Antonucci
 *
 */
public interface ScontoFilter extends Filter{
	
	/**
	 * Aggiorna lo sconto da analizzare con quello corrente
	 * 
	 * @param i - l'indice dell'ArrayList degli Sconti
	 * @author Gaetano Antonucci
	 */
	public void updateCurrentSconto(int i);
}
