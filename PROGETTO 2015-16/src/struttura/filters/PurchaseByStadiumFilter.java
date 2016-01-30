package struttura.filters;

import java.io.Serializable;

import struttura.Acquisto;
import struttura.Stadio;
/**
 * Classe di call-back per l'interfaccia PurchaseFilter per gli Acquisti in base allo
 * {@link Stadio}
 * 
 * @author Gaetano Antonucci
 *
 */
public class PurchaseByStadiumFilter implements PurchaseFilter, Serializable{
	
	/**
	 * Costruisce un filtro sugli Acquisti in base allo {@link Stadio} passato come
	 * parametro
	 * 
	 * @param std - lo stadio in base al quale si vogliono filtrare i dati
	 */
	public PurchaseByStadiumFilter(Stadio std){
		this.stadio = std;
	}

	@Override
	public boolean accept(Acquisto acquisto) {
		boolean result = false;
		
		if(acquisto.getBiglietto().getPartita().getStadio().equals(this.stadio)){
			result = true;
		}
		
		return result;
	}
	
	private Stadio stadio;
	
	private static final long serialVersionUID = -1659887821611524157L;

}
