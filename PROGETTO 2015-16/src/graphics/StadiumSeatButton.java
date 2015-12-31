package graphics;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class StadiumSeatButton extends JButton {

	public StadiumSeatButton(int numeroPosto, Color backgroundColor) {
		super();
		this.setIcon(Assets.getBlueSeat());
		this.setBorder(null);
		this.setToolTipText(numeroPosto + "");
		this.setOpaque(true);
		this.setBackground(backgroundColor);

		this.setRolloverIcon(Assets.getBlueSeatRollover());
		this.setRolloverEnabled(true);
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("YOU CLICKED SEAT n° " + getToolTipText());
			}
		});
	}

	private static final long serialVersionUID = 5537467864342318585L;
}
