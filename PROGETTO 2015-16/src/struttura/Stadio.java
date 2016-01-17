
package struttura;

import java.io.Serializable;

public class Stadio implements Serializable {

	public Stadio(String nome, int capienzaStadio, double prezzoPerPartita) {
		this.nome = nome;
		this.calcolaPostiEffettivi(capienzaStadio);
		this.prezzoPerPartita = prezzoPerPartita;
		ID_Stadio = ++ID_Counter;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		
		if (this == obj)
			return true;
		
		if (obj == null)
			return false;
		
		if (this.getClass() != obj.getClass())
			return false;
		
		Stadio other = (Stadio) obj;
		
		if (this.nome.equalsIgnoreCase(other.nome)) {
			result = true;
		}
		
		return result;
	}

	public int getCapienzaStadio() {
		return this.capienzaStadio;
	}
	
	public void setCapienzaStadio(int nuovaCapienza){
		this.capienzaStadio = nuovaCapienza;
	}

	public int getID() {
		return this.ID_Stadio;
	}
	
	public double getPrezzoPerPartita(){
		return this.prezzoPerPartita;
	}
	
	public void setPrezzoPerPartita(double prezzo){
		this.prezzoPerPartita = prezzo;
	}
	
	
	/**
	 * @return the filePerSettore
	 */
	public int getFilePerSettore() {
		return filePerSettore;
	}

	/**
	 * @return the postiPerFilaSettore
	 */
	public int getPostiPerSettore() {
		return postiPerSettore;
	}

	/**
	 * @return the postiPerFila
	 */
	public int getPostiPerFila() {
		return postiPerFila;
	}

	public void calcolaPostiEffettivi(int postiRichiesti){
		int postiPerSettore = (postiRichiesti / SETTORI);
		this.postiPerSettore = postiPerSettore;
		
		int postiEffettivi = postiPerSettore * SETTORI;
		this.capienzaStadio = postiEffettivi;
		
		int numeroFile = (int) Math.sqrt(postiPerSettore);
		int numeroPosti = postiEffettivi / numeroFile;
		
		this.filePerSettore = numeroFile;
		this.postiPerFila = numeroPosti;
		
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [nome = " + nome + ", capienzaStadio = " + capienzaStadio
				+ ", ID_Stadio = " + ID_Stadio + "Prezzo per Partita: " + prezzoPerPartita + "]";
	}

	private static final long serialVersionUID = -5785492477034953352L;
	private String nome;
	private int capienzaStadio;
	private int filePerSettore;
	private int postiPerSettore;
	private int postiPerFila;
	private int ID_Stadio;
	private double prezzoPerPartita;
	
	private static final int SETTORI = 620;
	
	private static int ID_Counter = 1000;
}
