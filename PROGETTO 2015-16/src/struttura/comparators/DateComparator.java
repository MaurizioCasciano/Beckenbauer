package struttura.comparators;

import java.io.Serializable;
import java.util.Comparator;

import struttura.Partita;

/**
 * Una classe di smistamento (Call-Back), che impone un ordinamento totale su
 * una collezione di PartitaDiCalcio, basandosi sull'ordine cronologico rispetto
 * alle date delle due PartitaDiCalcio.
 */
public class DateComparator implements Comparator<Partita>, Serializable {

	@Override
	public int compare(Partita p1, Partita p2) {
		if (p1.getData().before(p2.getData())) {
			return -1;
		} else if (p1.getData().after(p2.getData())) {
			return 1;
		} else {
			return 0;
		}
	}

	private static final long serialVersionUID = -8200790403457487036L;
}
