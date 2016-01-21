/**
 * 
 */
package struttura;

import java.awt.Color;

/**
 * Enum per gestire lo stato dei {@link Posto} di una {@link Partita}.
 * 
 * @author Maurizio
 */
public enum SeatStatus {
	/**
	 * Indica che il posto � disponibile alla prenotazione o all'acquisto. Il
	 * posto libero � caratterizzato dal colore {@link Color#GREEN}.
	 */
	LIBERO(Color.GREEN),

	/**
	 * Indica che il posto � stato prenotato. Il posto prenotato �
	 * caratterizzato dal colore {@link Color#CYAN}.
	 */
	PRENOTATO(Color.CYAN),

	/**
	 * Indica che il posto � stato venduto. Il posto venduto � caratterizzato
	 * dal colore {@link Color#GRAY}.
	 */
	VENDUTO(Color.GRAY);

	private SeatStatus(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return this.color;
	}

	private final Color color;
}
