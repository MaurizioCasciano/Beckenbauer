package struttura.filters;

import java.io.Serializable;

import struttura.Acquisto;
import user.Cliente;

/**
 * Classe di call-back per l'interfaccia PurchaseFilter per gli Acquisti in base
 * al {@link Cliente}
 *  
 * @author Gaetano Antonucci
 */
public class PurchaseByCustomerFilter implements PurchaseFilter, Serializable{

	/**
	 * Costruisce un filtro sugli Acquisti in base al cliente passato come parametro
	 * 
	 * @param clt - il {@link Cliente} sul quale si vogliono filtrare i dati
	 */
	public PurchaseByCustomerFilter(Cliente clt){
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
