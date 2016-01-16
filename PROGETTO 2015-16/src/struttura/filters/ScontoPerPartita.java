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
	public void updateCurrentSconto(int i){
		this.sconto =  this.sconti.get(i);
	}
	
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		Partita partitaSconto = this.sconto.getPartita();
		boolean result = false;
		
		if(partitaSconto != null){
			if((partitaDiCalcio.equals(sconto.getPartita())) && !(partitaDiCalcio.getData().before(sconto.getInizioValidità())) 
					&& !(partitaDiCalcio.getData().after(sconto.getFineValidità()))){
				result = true;
			}
		}
		
		
		return result;
	}

	private Sconti sconto;
	private ArrayList<Sconti> sconti;
	
	private static final long serialVersionUID = 5163413202426097316L;
}
