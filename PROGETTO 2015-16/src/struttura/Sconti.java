/**
 * Program: Sconti.java
 * Purpose: Classe che implementa le politiche di sconto
 * @author Gaetano Antonucci
 * @version 1.0 
 * Last Modified: 29/12/2015
 */
package struttura;

public class Sconti {
	
	public Sconti(String tipoSconto, double percetualeSconto) {
		this.denominazioneSconto = tipoSconto;
		this.percetualeSconto = percetualeSconto;
		this.IDSconto = ++IDCounter;
	}
	
	public int getIDSconto() {
		return IDSconto;
	}

	public String getDenominazioneSconto() {
		return denominazioneSconto;
	}

	public double getPercetualeSconto() {
		return percetualeSconto;
	}

	private int IDSconto;
	private String denominazioneSconto;
	private double percetualeSconto;
	
	private static int IDCounter = 1;

}
