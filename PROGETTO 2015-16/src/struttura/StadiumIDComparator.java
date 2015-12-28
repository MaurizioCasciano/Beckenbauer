package struttura;

import java.util.Comparator;

/**
 * Una classe di smistamento (Call-Back), che impone un ordinamento totale su
 * una collezione di PartitaDiCalcio, basandosi sull'ordine crescente rispetto
 * all'identificativo dello stadio in cui si giocano le due PartitaDiCalcio.
 */
public class StadiumIDComparator implements Comparator<PartitaDiCalcio> {
	@Override
	public int compare(PartitaDiCalcio p1, PartitaDiCalcio p2) {
		if (p1.getStadio().getID() < p2.getStadio().getID()) {
			return -1;
		} else if (p1.getStadio().getID() > p2.getStadio().getID()) {
			return 1;
		} else {
			return 0;
		}
	}
}
