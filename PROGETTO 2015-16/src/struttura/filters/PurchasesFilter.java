package struttura.filters;

import struttura.Acquisto;

public interface PurchasesFilter {
	
	public boolean accept(Acquisto acquisto);

}
