package struttura;

public class Squadra {

	public Squadra(String nome) {
		this.nome = nome;
	}

	
	
	public String getNome() {
		return nome;
	}



	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [nome = " + nome + "]";
	}




	private String nome;
}
