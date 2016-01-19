package struttura.filters;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

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
		
		//Si effettua una verifica diversa rispetto agli altri sconti in quanto
		// quello su partita è valido un solo giorno
		int annoInizioValidita = sconto.getInizioValidita().get(Calendar.YEAR);
		int meseInizioValidita = sconto.getInizioValidita().get(Calendar.MONTH);
		int giornoInizioValidita = sconto.getInizioValidita().get(Calendar.DAY_OF_MONTH);
		
		int annoFineValidita = sconto.getFineValidita().get(Calendar.YEAR);
		int meseFineValidita = sconto.getFineValidita().get(Calendar.MONTH);
		int giornoFineValidita = sconto.getFineValidita().get(Calendar.DAY_OF_MONTH);
		
		int annoPartita = partitaDiCalcio.getData().get(Calendar.YEAR);
		int mesePartita = partitaDiCalcio.getData().get(Calendar.MONTH);
		int giornoPartita = partitaDiCalcio.getData().get(Calendar.DAY_OF_MONTH);
		
		if(partitaSconto != null){
			if((partitaDiCalcio.equals(sconto.getPartita())) && (annoPartita == annoInizioValidita) && (mesePartita == meseInizioValidita)
					&& (giornoPartita == giornoInizioValidita) && (annoPartita == annoFineValidita) && (mesePartita == meseFineValidita)
					&& (giornoPartita == giornoFineValidita)){
				result = true;
			}
		}
		
		
		return result;
	}

	private Sconti sconto;
	private ArrayList<Sconti> sconti;
	
	private static final long serialVersionUID = 5163413202426097316L;
}
