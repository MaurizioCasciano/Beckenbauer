package struttura.filters;

import java.io.Serializable;

import struttura.Acquisto;
import struttura.Stadio;

public class PurchasesByStadium implements PurchasesFilter, Serializable{

	public PurchasesByStadium(Stadio std){
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
