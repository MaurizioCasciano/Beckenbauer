package struttura;

public class Squadra {

	public Squadra(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nuovoNome) {
		this.nome = nuovoNome;
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " [nome = " + nome + "]";
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = false;

		Squadra other = (Squadra) obj;

		if (this.nome.equals(other.getNome())) {
			result = true;
		}

		return result;
	}

	private String nome;
}
