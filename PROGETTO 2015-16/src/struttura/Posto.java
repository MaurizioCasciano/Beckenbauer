package struttura;

public class Posto {
	
	public Posto(Stadio stadio, Settore settore, int fila, int posto) {
		this.stadio = stadio;
		this.settore = settore;
		this.fila = fila;
		this.posto = posto;
	}
	
	public Posto(Stadio stadio, Settore settore, int fila, int posto, boolean prenotato, boolean venduto) {
		this.stadio = stadio;
		this.settore = settore;
		this.fila = fila;
		this.posto = posto;
		this.prenotato = prenotato;
		this.venduto = venduto;
	}

	/**
	 * @return the prenotato
	 */
	public boolean isPrenotato() {
		return prenotato;
	}

	/**
	 * @param prenotato the prenotato to set
	 */
	public void setPrenotato(boolean prenotato) {
		this.prenotato = prenotato;
	}

	/**
	 * @return the venduto
	 */
	public boolean isVenduto() {
		return venduto;
	}

	/**
	 * @param venduto the venduto to set
	 */
	public void setVenduto(boolean venduto) {
		this.venduto = venduto;
	}

	/**
	 * @return the stadio
	 */
	public Stadio getStadio() {
		return stadio;
	}

	/**
	 * @return the settore
	 */
	public Settore getSettore() {
		return settore;
	}

	/**
	 * @return the fila
	 */
	public int getFila() {
		return this.fila;
	}

	/**
	 * @return the posto
	 */
	public int getPosto() {
		return this.posto;
	}

	@Override
	public String toString() {
		return "Posto [stadio=" + stadio + ", settore=" + settore + ", fila=" + fila + ", posto=" + posto
				+ ", prenotato=" + prenotato + ", venduto=" + venduto + "]";
	}


	private Stadio stadio;
	private Settore settore;
	private int fila;
	private int posto;
	private boolean prenotato;
	private boolean venduto;

}
