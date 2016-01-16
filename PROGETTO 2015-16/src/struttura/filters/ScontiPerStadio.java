package struttura.filters;

import java.io.Serializable;
import java.util.ArrayList;

import struttura.Partita;
import struttura.Sconti;
import struttura.Stadio;

public class ScontiPerStadio implements ScontiFilter, Serializable{

	

	/*public ScontiPerStadio(Sconti sconto){
		this.sconto = sconto;
	}*/
	
	public ScontiPerStadio(ArrayList<Sconti> sconti){
		this.sconti = sconti;
	}
	
	@Override
	public void updateCurrentSconto(int i) {
		this.sconto = this.sconti.get(i)	;
	}
	
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		Stadio stadio = this.sconto.getStadio();
		boolean result = false;
		
		if(stadio != null){
			if(partitaDiCalcio.getStadio().equals(stadio) && !(partitaDiCalcio.getData().before(sconto.getInizioValidità())) 
					&& !(partitaDiCalcio.getData().after(sconto.getFineValidità()))){
				result = true;	
			}
		}
		return result;
	}
	
	private Sconti sconto;
	private ArrayList<Sconti> sconti;
	
	private static final long serialVersionUID = -9028085844361626218L;

	

}
