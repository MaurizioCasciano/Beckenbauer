package struttura;

import java.util.Comparator;

/**
 * Una classe di smistamento (Call-Back), che impone un ordinamento totale su
 * una collezione di PartitaDiCalcio, basandosi sull'ordine cronologico rispetto
 * alle date delle due PartitaDiCalcio.
 */
public class DateComparator implements Comparator<PartitaDiCalcio> {

	@Override
	public int compare(PartitaDiCalcio p1, PartitaDiCalcio p2) {
		if (p1.getData().before(p2.getData())) {
			return -1;
		} else if (p1.getData().after(p2.getData())) {
			return 1;
		} else {
			return 0;
		}
	}

}
