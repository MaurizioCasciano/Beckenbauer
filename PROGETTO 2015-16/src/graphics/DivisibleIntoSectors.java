/**
 * 
 */
package graphics;

/**
 * @author Maurizio
 */
public interface DivisibleIntoSectors {

	/**
	 * Restituisce il numero di posti per ogni settore.
	 * 
	 * Il numero di posti per ogni settore va calcolato dividendo la capienza
	 * desiderata dello Stadio per NUMERO_SETTORI.
	 * 
	 * (Il valore deve essere arrotondato al più grande intero minore o uguale
	 * del valore precedentemente ottenuto).
	 * 
	 * @return Il numero di posti per ogni settore.
	 * @author Maurizio
	 */
	public int getPostiPerSettore();

	/**
	 * Restituisce il numero di file per ogni settore.
	 * 
	 * Il numero di file per ogni settore deve essere calcolato come la radice
	 * quadrata dei postiPerSettore. (Il valore deve essere arrotondato al più
	 * grande intero minore o uguale del valore precedentemente ottenuto).
	 * 
	 * @return Il numero di file di ogni settore.
	 * @author Maurizio
	 */
	public int getNumeroFilePerSettore();

	/**
	 * Restituisce il numero di posti per ogni fila del settore.
	 * 
	 * Il numero di posti per ogni fila deve essere calcolato come il rapporto
	 * tra il numero di posti per settore ed il numero di file per settore. (Il
	 * valore deve essere arrotondato al più grande intero minore o uguale del
	 * valore precedentemente ottenuto).
	 * 
	 * @return Il numero di posti per ogni fila del settore.
	 * @author Maurizio
	 */
	public int getPostiPerFila();

	/**
	 * Restituisce la capienza effettiva dello Stadio.
	 * 
	 * La capienza effettiva dello Stadio deve essere calcolata come il prodotto
	 * tra i PostiPerSettore e NUMERO_SETTORI.
	 * 
	 * @return La capienza effettiva dello Stadio.
	 * @author Maurizio
	 */
	public int getCapienzaEffettiva();

	/**
	 * Il numero di settori dello Stadio.
	 */
	public static final int NUMERO_SETTORI = 620;
}
