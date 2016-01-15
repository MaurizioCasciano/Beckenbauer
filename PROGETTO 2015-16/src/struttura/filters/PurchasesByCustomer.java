package struttura.filters;

import java.io.Serializable;

import struttura.Acquisto;
import user.Cliente;

public class PurchasesByCustomer implements PurchasesFilter, Serializable{

	public PurchasesByCustomer(Cliente clt){
		this.cliente = clt;
	}

	@Override
	public boolean accept(Acquisto acquisto) {
		boolean result = false;
		
		if(acquisto.getBiglietto().getCliente().equals(this.cliente)){
			result = true;
		}
		
		return result;
	}
	
	private Cliente cliente;
	
	private static final long serialVersionUID = -2916057589041551270L;

}
