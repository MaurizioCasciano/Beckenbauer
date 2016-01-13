package struttura.filters;

import java.io.Serializable;

import struttura.Partita;
import struttura.Sconti;
import struttura.Stadio;

public class ScontiPerStadio implements Filter, Serializable{

	

	public ScontiPerStadio(Sconti sconto){
		this.sconto = sconto;
	}
	
	@Override
	public boolean accept(Partita partitaDiCalcio) {
		Stadio stadio = partitaDiCalcio.getStadio();
		boolean result = false;
		
		if(stadio != null){
			if((partitaDiCalcio.getData().after(sconto.getInizioValidità())) 
					&& (partitaDiCalcio.getData().before(sconto.getFineValidità()))){
				result = true;	
			}
		}
		return result;
	}
	
	private Sconti sconto;
	
	private static final long serialVersionUID = -9028085844361626218L;

}
