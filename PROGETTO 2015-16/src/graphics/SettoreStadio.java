package graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettoreStadio extends JPanel {

	public SettoreStadio(int posti) {
		super(new CardLayout());

		this.posti = posti;
		this.labelPanelCard = new JPanel(new BorderLayout());

		this.nameLabel = new JLabel("Settore", JLabel.CENTER);
		this.labelPanelCard.add(this.nameLabel, BorderLayout.CENTER);

		int sqrtPosti = (int) Math.floor(Math.sqrt(posti));

		this.seatsPanelCard = new JPanel(new GridLayout(sqrtPosti, sqrtPosti, 3, 3));

		for (int i = 0; i < posti; i++) {
			this.seatsPanelCard.add(new StadiumSeatButton(i, Color.GREEN));
		}

		this.add(this.labelPanelCard);
		this.add(this.seatsPanelCard);
	}

	public void showNextCard() {
		((CardLayout) getLayout()).next(this);
	}

	public int getSeats() {
		return posti;
	}

	private static final long serialVersionUID = 4920370237665489993L;
	private int posti;
	private JPanel labelPanelCard, seatsPanelCard;
	private JLabel nameLabel;
}
