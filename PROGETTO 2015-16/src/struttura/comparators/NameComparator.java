package struttura.comparators;

import java.util.Comparator;

import struttura.Partita;

/**
 * Una classe di smistamento (Call-Back), che impone un ordinamento totale su
 * una collezione di PartitaDiCalcio, basandosi sull'ordine lessicografico
 * crescente rispetto al nome delle squadre che si affrontano.
 */
public class NameComparator implements Comparator<Partita> {

	/**
	 * Confronta due PartitaDiCalcio, in base ai nomi delle squadre che si
	 * affrontano.
	 * 
	 * @param partita1
	 *            La prima PartitaDiCalcio.
	 * @param partita2
	 *            La seconda PartitaDiCalcio.
	 * 
	 * @return Il valore -1 se la squadra in casa della prima partita ha un nome
	 *         che precede lessicograficamente la squadra in casa della seconda
	 *         partita, oppure se hanno lo stesso ordine lessicografico ma la
	 *         squadra in trasferta della prima partita precede
	 *         lessicograficamente la squadra in trasferta della seconda
	 *         partita.
	 * 
	 *         Il valore 1 se la squadra in casa della prima partita ha un nome
	 *         che segue lessicograficamente la squadra in casa della seconda
	 *         partita, oppure se hanno lo stesso ordine lessicografico ma la
	 *         squadra in trasferta della prima partita segue
	 *         lessicograficamente la squadra in trasferta della seconda
	 *         partita.
	 * 
	 *         Il valore 0 se la squadra in casa della prima partita coincide
	 *         con la squadra in casa della seconda partita e la squadra in
	 *         trasferta della prima partita coincide con la squadra in
	 *         trasferta della seconda patita.
	 */
	@Override
	public int compare(Partita partita1, Partita partita2) {
		int result1 = partita1.getSquadraInCasa().getNome().compareTo(partita2.getSquadraInCasa().getNome());
		if (result1 != 0) {
			return result1;
		}

		int result2 = partita1.getSquadraInTrasferta().getNome().compareTo(partita2.getSquadraInTrasferta().getNome());
		if (result2 != 0) {
			return result2;
		}

		return 0;
	}

}
