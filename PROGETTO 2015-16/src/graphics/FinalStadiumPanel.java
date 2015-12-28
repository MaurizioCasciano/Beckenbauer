package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.RoundRectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class FinalStadiumPanel extends JPanel {

	public FinalStadiumPanel() {
		super(new GridLayout(3, 1));
		this.numeroPosti = 0;

		this.northPanel = new JPanel(new GridLayout(8, 30));
		this.northPanel.setOpaque(false);

		for (int i = 0; i < 8 * 30; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setOpaque(false);

			if ((i >= 3 && i <= 26) || (i >= 31 && i <= 58) || (i >= 61 && i <= 88) || (i >= 90 && i <= 239)) {
				StadiumSeatButton posto = new StadiumSeatButton(++numeroPosti, Color.LIGHT_GRAY);
				panel.add(posto);
			}

			this.northPanel.add(panel);
		}

		/***********************************************************************/

		this.centrePanel = new JPanel(new GridLayout(1, 3));
		this.centrePanel.setOpaque(false);

		this.centreLeftPanel = new JPanel(new GridLayout(8, 10));
		this.centreLeftPanel.setOpaque(false);

		for (int i = 0; i < 8 * 10; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setOpaque(false);

			StadiumSeatButton posto = new StadiumSeatButton(++numeroPosti, Color.LIGHT_GRAY);
			panel.add(posto);

			this.centreLeftPanel.add(panel);
		}

		this.centreCentrePanel = new BackgroundImagePanel(ImageLoader.loadImage("footballpitch.png"));
		this.centreCentrePanel.setLayout(new BorderLayout());
		this.centreCentrePanel.setOpaque(false);

		this.centreRightPanel = new JPanel(new GridLayout(8, 10));
		this.centreRightPanel.setOpaque(false);

		for (int i = 0; i < 8 * 10; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setOpaque(false);

			StadiumSeatButton posto = new StadiumSeatButton(++numeroPosti, Color.LIGHT_GRAY);
			panel.add(posto);

			this.centreRightPanel.add(panel);
		}

		this.centrePanel.add(this.centreLeftPanel);
		this.centrePanel.add(this.centreCentrePanel);
		this.centrePanel.add(this.centreRightPanel);

		this.southPanel = new JPanel(new GridLayout(8, 30));
		this.southPanel.setOpaque(false);

		for (int i = 0; i < 8 * 30; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setOpaque(false);

			if ((i <= 149) || (i >= 151 && i <= 178) || (i >= 181 && i <= 208) || (i >= 213 && i <= 236)) {
				StadiumSeatButton posto = new StadiumSeatButton(++numeroPosti, Color.LIGHT_GRAY);
				panel.add(posto);
			}

			this.southPanel.add(panel);
		}

		this.add(northPanel);
		this.add(centrePanel);
		this.add(southPanel);

	}

	@Override
	protected void paintComponent(Graphics g) {
		//stampa il background
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.LIGHT_GRAY);

		RoundRectangle2D.Double roundRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 250, 250);

		g2.fill(roundRectangle);
	}

	private JPanel northPanel, centrePanel, southPanel;

	private JPanel centreLeftPanel, centreCentrePanel, centreRightPanel;

	private int numeroPosti;

	/***********************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new FinalStadiumPanel());
		frame.setSize(1000, 700);
		frame.setVisible(true);
	}
}
