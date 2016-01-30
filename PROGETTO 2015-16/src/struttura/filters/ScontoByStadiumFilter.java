package struttura.filters;

import java.io.Serializable;
import java.util.ArrayList;

import struttura.Partita;
import struttura.Sconto;
import struttura.Stadio;

/**
 * Classe di call-back per l'interfaccia ScontoFilter per gli Sconti in base
 * allo {@link Stadio}.
 * 
 * @author Gaetano Antonucci
 */
public class ScontoByStadiumFilter implements ScontoFilter, Serializable {

	/**
	 * Costruisce un filtro sugli sconti
	 * 
	 * @param sconti
	 *            - l'ArrayList degli Sconti
	 */
	public ScontoByStadiumFilter(ArrayList<Sconto> sconti) {
		this.sconti = sconti;
	}

	@Override
	public void updateCurrentSconto(int i) {
		this.sconto = this.sconti.get(i);
	}

	@Override
	public boolean accept(Partita partitaDiCalcio) {
		Stadio stadio = this.sconto.getStadio();
		boolean result = false;

		if (stadio != null) {
			if (partitaDiCalcio.getStadio().equals(stadio)
					&& !(partitaDiCalcio.getData().before(sconto.getInizioValidita()))
					&& !(partitaDiCalcio.getData().after(sconto.getFineValidita()))) {
				result = true;
			}
		}
		return result;
	}

	private Sconto sconto;
	private ArrayList<Sconto> sconti;

	private static final long serialVersionUID = -9028085844361626218L;

}
