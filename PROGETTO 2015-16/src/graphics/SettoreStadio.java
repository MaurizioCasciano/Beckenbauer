package graphics;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettoreStadio extends JPanel {

	public SettoreStadio() {
		super(new CardLayout());

		this.labelPanelCard = new JPanel(new BorderLayout());

		this.nameLabel = new JLabel("Settore", JLabel.CENTER);
		this.labelPanelCard.add(this.nameLabel, BorderLayout.CENTER);

		this.seatsPanelCard = new JPanel(new GridLayout(3, 3, 3, 3));

		for (int i = 0; i < 9; i++) {
			this.seatsPanelCard.add(new StadiumSeatButton(i, Color.GREEN));
		}

		this.add(this.labelPanelCard);
		this.add(this.seatsPanelCard);
	}

	public void showNextCard() {
		((CardLayout) getLayout()).next(this);
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	private static final long serialVersionUID = 4920370237665489993L;
	private int seats;
	private JPanel labelPanelCard, seatsPanelCard;
	private JLabel nameLabel;
}
