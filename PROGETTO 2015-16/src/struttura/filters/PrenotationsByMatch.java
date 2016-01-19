package struttura.filters;

import java.io.Serializable;

import struttura.Partita;
import struttura.Prenotazione;

public class PrenotationsByMatch implements PrenotationFilter, Serializable{
	
	public PrenotationsByMatch(Partita partita){
		this.partita = partita;
	}
	
	@Override
	public boolean accept(Prenotazione prenotazione) {
		boolean result = false;
		
		if(prenotazione.getBigliettoPrenotato().getPartita().equals(this.partita)){
			result = true;
		}
		
		return result;
	}
	
	private Partita partita;
	
	private static final long serialVersionUID = 2154947496110081855L;
	
}
