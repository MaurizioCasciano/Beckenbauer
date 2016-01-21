package struttura.comparators;

import java.io.Serializable;
import java.util.Comparator;

import struttura.Stadio;

public class StadiumNameComparator implements Comparator<Stadio>, Serializable{

	@Override
	public int compare(Stadio stadio1, Stadio stadio2) {
		String nome1 = stadio1.getNome();
		String nome2 = stadio2.getNome();
		
		return nome1.compareToIgnoreCase(nome2);
	}
	
	
	
	private static final long serialVersionUID = -8136137962133300059L;

	

}
