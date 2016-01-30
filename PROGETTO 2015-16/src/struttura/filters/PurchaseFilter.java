package struttura.filters;

import struttura.Acquisto;

/**
 * Interfaccia che modella un filtro su un {@link Acquisto}
 * @author Gaetano Antonucci
 *
 */
public interface PurchasesFilter {
	
	/**
	 * Metodo che verifica se l'acquisto passato come parametro soddisfa il filtro
	 * 
	 * @param acquisto - l'{@link Acquisto} su cui s'imposta il filtro
	 * @return {@code true} se l'acquisto soddisfa il filtro, {@code false} altrimenti
	 */
	public boolean accept(Acquisto acquisto);

}
