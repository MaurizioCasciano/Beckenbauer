
package struttura;

import java.io.Serializable;

public class Stadio implements Serializable {

	public Stadio(String nome, int capienzaStadio, double prezzoPerPartita) {
		this.nome = nome;
		this.capienzaStadio = capienzaStadio;
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

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [nome = " + nome + ", capienzaStadio = " + capienzaStadio
				+ ", ID_Stadio = " + ID_Stadio + "Prezzo per Partita: " + prezzoPerPartita + "]";
	}

	private static final long serialVersionUID = -5785492477034953352L;
	private String nome;
	private int capienzaStadio;
	private int ID_Stadio;
	private double prezzoPerPartita;
	
	private static int ID_Counter = 1000;
}
