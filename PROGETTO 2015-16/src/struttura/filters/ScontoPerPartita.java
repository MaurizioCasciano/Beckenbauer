package struttura.filters;

import java.io.Serializable;

import struttura.Partita;
import struttura.Sconti;

public class ScontoPerPartita implements Filter, Serializable {

	public ScontoPerPartita(Sconti sconto){
		this.sconto = sconto;
	}
	
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		boolean result = false;
		
		if(partitaDiCalcio.equals(sconto.getPartita())){
			result = true;
		}
		
		return result;
	}

	private Sconti sconto;
	
	private static final long serialVersionUID = 5163413202426097316L;
}
