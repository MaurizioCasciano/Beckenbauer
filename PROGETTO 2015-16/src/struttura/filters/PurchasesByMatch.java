package struttura.filters;

import java.io.Serializable;

import struttura.Acquisto;
import struttura.Partita;

public class PurchasesByMatch implements PurchasesFilter, Serializable{

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
