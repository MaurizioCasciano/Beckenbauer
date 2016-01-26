package struttura.filters;

import java.io.Serializable;

import struttura.Acquisto;
import struttura.Partita;
/**
 * Classe di call-back per l'interfaccia PurchasesFilter per gli Acquisti in base
 * alla {@link Partita}
 * 
 * @author Gaetano Antonucci
 */
public class PurchasesByMatch implements PurchasesFilter, Serializable{

	/**
	 * Costruisce un filtro sugli Acquisti in base alla partita passata come parametro
	 * 
	 * @param part - la {@link Partita} sulla quale si vogliono filtrare i dati
	 */
	public PurchasesByMatch(Partita part){
		this.partita = part;
	}

	@Override
	public boolean accept(Acquisto acquisto) {
		boolean result = false;
		
		if(acquisto.getBiglietto().getPartita().equals(this.partita)){
			result = true;
		}
				
				
		return result;
	}
	
	private Partita partita;

	private static final long serialVersionUID = 8796230365851580724L;
}
