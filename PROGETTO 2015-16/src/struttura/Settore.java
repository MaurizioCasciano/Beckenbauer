package struttura;

public class Settore {
	
	public Settore(Stadio stadio, String nomeSettore, int numeroPosti, int numeroFile) {
		this.stadio = stadio;
		this.nomeSettore = nomeSettore;
		this.numeroPosti = numeroPosti;
		this.numeroFile = numeroFile;
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

	private Stadio stadio;
	private String nomeSettore;
	private int numeroPosti;
	private int numeroFile;

}
