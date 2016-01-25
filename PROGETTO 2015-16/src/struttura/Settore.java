package struttura;

import java.io.Serializable;
import java.util.ArrayList;

public class Settore implements Serializable{

	private static final long serialVersionUID = -9113714476267820001L;
	public Settore(Stadio stadio, String nomeSettore, int numeroPosti, int numeroFile) {
		this.stadio = stadio;
		this.nomeSettore = nomeSettore;
		this.numeroPosti = numeroPosti;
		this.numeroFile = numeroFile;
		this.posti = new ArrayList<>();
	}

	/**
	 * Aggiunge il Posto passato in input alla lista dei Posti.
	 * 
	 * @param p
	 *            Il Posto da aggiungere alla lista.
	 * @author Maurizio
	 */
	public void addPosto(Posto p) {
		this.posti.add(p);
	}

	public ArrayList<Posto> getPosti() {
		return this.posti;
	}

	/**
	 * Restituisce il posto che ha per numero index + 1.
	 * 
	 * @param index
	 *            L'indice del posto nell'ArrayLyst dei Posti.
	 * @return Il Posto in posizione index.
	 * @author Maurizio
	 */
	public Posto getPosto(int index) {
		return this.posti.get(index);
	}

	/**
	 * @return the stadio
	 */
	public Stadio getStadio() {
		return stadio;
	}

	/**
	 * @return the nomeSettore
	 */
	public String getNomeSettore() {
		return nomeSettore;
	}

	/**
	 * @return the numeroPosti
	 */
	public int getNumeroPosti() {
		return numeroPosti;
	}

	/**
	 * @return the numeroFile
	 */
	public int getNumeroFile() {
		return numeroFile;
	}

	@Override
	public String toString() {
		return "Settore [stadio=" + stadio + ", nomeSettore=" + nomeSettore + ", numeroPosti=" + numeroPosti
				+ ", numeroFile=" + numeroFile + "]";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Settore other = (Settore) obj;
		
		if(this.stadio.equals(other.stadio) && this.nomeSettore.equals(other.nomeSettore) && this.numeroFile == other.numeroFile && this.numeroPosti == other.numeroPosti){
			return true;
		}
		
		return false;
	}



	private Stadio stadio;
	private String nomeSettore;
	private int numeroPosti;
	private int numeroFile;
	private ArrayList<Posto> posti;
}
