package struttura;

import java.io.Serializable;

public class Stadio implements Serializable {

	public Stadio(String nome, int capienzaStadio) {
		this.nome = nome;
		this.capienzaStadio = capienzaStadio;
		ID_Stadio = ++ID_Counter;
	}

	public Stadio(String nome, int capienzaStadio, Sconti sconto) {
		this.nome = nome;
		this.capienzaStadio = capienzaStadio;
		ID_Stadio = ++ID_Counter;
		this.scontoStadio = sconto;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stadio other = (Stadio) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	public int getCapienzaStadio() {
		return this.capienzaStadio;
	}

	public int getID() {
		return this.ID_Stadio;
	}

	public Sconti getScontoStadio() {
		return scontoStadio;
	}

	

	@Override
	public String toString() {
		return this.nome;
	}



	private static final long serialVersionUID = -5785492477034953352L;
	private String nome;
	private int capienzaStadio;
	private int ID_Stadio;

	private Sconti scontoStadio; // (GA) lo stadio può avere una particolare
									// politica di sconto
	// che va estesa a tutte le partite in esso giocate

	private static int ID_Counter = 1000;
}
