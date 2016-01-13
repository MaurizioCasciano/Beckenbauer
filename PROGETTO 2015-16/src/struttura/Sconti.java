/**
 * Program: Sconti.java
 * Purpose: Classe che implementa le politiche di sconto
 * @author Gaetano Antonucci
 * @version 2.0 
 * Last Modified: 13/01/2016
 */
package struttura;

import java.io.Serializable;

public class Sconti implements Serializable {

	public Sconti(int tipoSconto, double percetualeSconto) {
		this.scontoScelto = tipoSconto;
		this.percetualeSconto = (percetualeSconto / 100);
	}
	
	public int getScontoScelto(){
		return this.scontoScelto;
	}
	
	public String getApplicazioneSconto() {
		return this.applicazioneSconto[this.scontoScelto];
	}
	
	public String getGiornoSettimana(int i){
		return this.giorniSettimana[i];
	}

	public double getPercetualeSconto() {
		return percetualeSconto;
	}

	private static final long serialVersionUID = 8088987206686770452L;
	private int scontoScelto;
	private double percetualeSconto;
	
	private final String[] applicazioneSconto = {"Partita Corrente", "Tutte le partite dello Stadio", "Giorno prestabilito"};
	private final String[] giorniSettimana = {"Lunedì", "Martedì", "Mercoledì", "Giovedì", "Venerdì", "Sabato", "Domenica"};


}
