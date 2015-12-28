package struttura;

public class Stadio {

	public Stadio(String nome, int capienzaStadio) {
		this.nome = nome;
		this.capienzaStadio = capienzaStadio;
		ID_Stadio = ++ID_Counter;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (this.getClass() != obj.getClass())
			return false;
		Stadio other = (Stadio) obj;
		if (this.ID_Stadio != other.ID_Stadio){
			return false;
		}
		else{
			return true;
		}
	}
	
	public int getCapienzaStadio() {
		return this.capienzaStadio;
	}

	public int getID() {
		return this.ID_Stadio;
	}


	@Override
	public String toString() {
		return "Stadio [nome = " + nome + ", capienzaStadio = " + capienzaStadio + ", ID_Stadio = " + ID_Stadio + "]";
	}










	private String nome;
	private int capienzaStadio;
	private int ID_Stadio;
	private static int ID_Counter = 1000;
}
