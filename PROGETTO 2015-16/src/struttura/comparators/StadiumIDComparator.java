package struttura.comparators;

import java.io.Serializable;
import java.util.Comparator;

import struttura.Partita;

/**
 * Una classe di smistamento (Call-Back), che impone un ordinamento totale su
 * una collezione di PartitaDiCalcio, basandosi sull'ordine crescente rispetto
 * all'identificativo dello stadio in cui si giocano le due PartitaDiCalcio.
 * 
 */
public class StadiumIDComparator implements Comparator<Partita>, Serializable { // CLASSE da modificare

	@Override
	public int compare(Partita p1, Partita p2) {
		/*if (p1.getStadio().getID() < p2.getStadio().getID()) {
			return -1;
		} else if (p1.getStadio().getID() > p2.getStadio().getID()) {
			return 1;
		} else {
			return 0;
		}*/
		return 0;	// ERRORE, messo solo per evitare di visualizzare l'errore.
	}

	private static final long serialVersionUID = -5964931509835566728L;
}
