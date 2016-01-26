package struttura.comparators;

import java.io.Serializable;
import java.util.Comparator;

import struttura.Partita;

/**
 * Una classe di smistamento (Call-Back), che impone un ordinamento totale su
 * una collezione di PartitaDiCalcio, basandosi sull'ordine crescente rispetto
 * all'identificativo dello stadio in cui si giocano le due PartitaDiCalcio.
 */
public class StadiumIDComparator implements Comparator<Partita>, Serializable {

	@Override
	public int compare(Partita p1, Partita p2) {
		return p1.getStadio().getNome().compareTo(p2.getStadio().getNome());
	}

	private static final long serialVersionUID = -5964931509835566728L;
}
