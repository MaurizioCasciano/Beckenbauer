package struttura;

import java.io.Serializable;

/**
 * Classe che modella una Squdra.
 * 
 * @author Maurizio Casciano
 * @author Gaetano Antonucci
 */
public class Squadra implements Serializable, Comparable<Squadra> {

	/**
	 * Costruisce un'oggetto Squadra impostandone il nome al valore passato in
	 * input.
	 * 
	 * @param nome
	 *            il nome della Squadra.
	 * @author Maurizio Casciano
	 */
	public Squadra(String nome) {
		this.nome = nome;
	}

	/**
	 * Resituisce il nome della Squdra.
	 * 
	 * @return nome il nome della Squadra.
	 * @author Maurizio Casciano
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * Imposta un nuovo nome per la Squdra
	 * 
	 * @param nuovoNome
	 *            il nuovo nome della Squadra.
	 * @author Maurizio Casciano
	 */
	public void setNome(String nuovoNome) {
		this.nome = nuovoNome;
	}

	/**
	 * Confronta due Squadre in base al loro nome.
	 * 
	 * @param s
	 *            La squadra con cui effettuare il confronto lessicografico.
	 * @return Il valore restituito dal metodo {@link String#compareTo(String)}
	 *         applicato ai nomi delle due squadre.
	 */
	@Override
	public int compareTo(Squadra s) {
		return this.getNome().compareTo(s.getNome());
	}

	/**
	 * Resituisce le informazioni relative alla Squdra.
	 * 
	 * @author Maurizio Casciano
	 */
	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [nome = " + nome + "]";
	}

	/**
	 * Verifica se l'oggetto corrente è uguale all'oggetto passato come
	 * parametro
	 * 
	 * @param obj
	 *            - l'oggetto su cui effettuare la verifica
	 * @return {@code true} se quest'oggetto è uguale all'oggetto passato come
	 *         parametro, {@code false} altrimenti
	 * @author Gaetano Antonucci
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		if (this == obj) {
			return true;
		}

		if (obj == null) {
			return false;
		}

		if (getClass() != obj.getClass())
			return false;

		Squadra other = (Squadra) obj;

		if (this.nome.equals(other.getNome())) {
			result = true;
		}

		return result;
	}

	private static final long serialVersionUID = -5406532006369346747L;
	private String nome;
}
