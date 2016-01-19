package struttura.filters;

import java.io.Serializable;

import struttura.Prenotazione;
import user.Cliente;

public class PrenotationsByCustomer implements PrenotationFilter, Serializable {
	
	public PrenotationsByCustomer(Cliente clt){
		this.cliente = clt;
	}

	@Override
	public boolean accept(Prenotazione prenotazione) {
		boolean result = false;
		
		if(prenotazione.getBigliettoPrenotato().getCliente().equals(this.cliente)){
			result = true;
		}
		return result;
	}
	
	private Cliente cliente;
	
	private static final long serialVersionUID = 5824418745962263670L;
}
