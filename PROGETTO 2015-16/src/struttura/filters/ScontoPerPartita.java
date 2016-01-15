package struttura.filters;

import java.io.Serializable;
import java.util.ArrayList;

import struttura.Partita;
import struttura.Sconti;

public class ScontoPerPartita implements ScontiFilter, Serializable {

	/*public ScontoPerPartita(Sconti sconto){
		this.sconto = sconto;
	}*/
	
	public ScontoPerPartita(ArrayList<Sconti> sconti){
		this.sconti = sconti;
	}
	
	@Override
	public void getSconto(int i){
		this.sconto =  this.sconti.get(i);
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
	private ArrayList<Sconti> sconti;
	
	private static final long serialVersionUID = 5163413202426097316L;
}
