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
public class StadiumPanel extends JPanel {

	public StadiumPanel() {
		super(new GridLayout(STADIUM_PANEL_ROWS, STADIUM_PANEL_COLUMNS));
		this.init();
	}

	private void init() {
		this.numeroPosti = 0;

		this.initNorthPanel();
		this.initCentrePanel();
		this.initSouthPanel();
		this.add(northPanel);
		this.add(centrePanel);
		this.add(southPanel);
	}

	private void initNorthPanel() {
		final int NORTH_PANEL_ROWS = 8;
		final int NORTH_PANEL_COLUMNS = 30;

		this.northPanel = new JPanel(new GridLayout(NORTH_PANEL_ROWS, NORTH_PANEL_COLUMNS));
		this.northPanel.setOpaque(false);

		for (int i = 0; i < NORTH_PANEL_ROWS * NORTH_PANEL_COLUMNS; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setOpaque(false);

			if ((i >= 3 && i <= 26) || (i >= 31 && i <= 58) || (i >= 61 && i <= 88) || (i >= 90 && i <= 239)) {
				StadiumSeatButton posto = new StadiumSeatButton(++numeroPosti, Color.LIGHT_GRAY);
				panel.add(posto);
			}

			this.northPanel.add(panel);
		}
	}

	private void initCentrePanel() {
		final int CENTRE_PANEL_ROWS = 1;
		final int CENTRE_PANEL_COLUMNS = 3;

		this.initCentreLeftPanel();
		this.initCentreCentrePanel();
		this.initCentreRightPanel();

		this.centrePanel = new JPanel(new GridLayout(CENTRE_PANEL_ROWS, CENTRE_PANEL_COLUMNS));
		this.centrePanel.setOpaque(false);
		this.centrePanel.add(this.centreLeftPanel);
		this.centrePanel.add(this.centreCentrePanel);
		this.centrePanel.add(this.centreRightPanel);
	}

	private void initCentreLeftPanel() {
		final int CENTRE_LEFT_PANEL_ROWS = 8;
		final int CENTRE_LEFT_PANEL_COLUMNS = 10;

		this.centreLeftPanel = new JPanel(new GridLayout(CENTRE_LEFT_PANEL_ROWS, CENTRE_LEFT_PANEL_COLUMNS));
		this.centreLeftPanel.setOpaque(false);

		for (int i = 0; i < CENTRE_LEFT_PANEL_ROWS * CENTRE_LEFT_PANEL_COLUMNS; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setOpaque(false);

			StadiumSeatButton posto = new StadiumSeatButton(++numeroPosti, Color.LIGHT_GRAY);
			panel.add(posto);

			this.centreLeftPanel.add(panel);
		}
	}

	private void initCentreCentrePanel() {
		this.centreCentrePanel = new BackgroundImagePanel(ImageLoader.loadImage("footballpitch.png"));
	}

	private void initCentreRightPanel() {
		this.centreRightPanel = new JPanel(new GridLayout(8, 10));
		this.centreRightPanel.setOpaque(false);

		for (int i = 0; i < 8 * 10; i++) {
			JPanel panel = new JPanel(new BorderLayout());
			panel.setOpaque(false);

			StadiumSeatButton posto = new StadiumSeatButton(++numeroPosti, Color.LIGHT_GRAY);
			panel.add(posto);

			this.centreRightPanel.add(panel);
		}
	}

	private void initSouthPanel() {

		final int SOUTH_PANEL_ROWS = 8;
		final int SOUTH_PANEL_COLUMNS = 30;

		this.southPanel = new JPanel(new GridLayout(SOUTH_PANEL_ROWS, SOUTH_PANEL_COLUMNS));
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
	}

	@Override
	protected void paintComponent(Graphics g) {
		// stampa il background
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.LIGHT_GRAY);

		RoundRectangle2D.Double roundRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 250, 250);

		g2.fill(roundRectangle);
	}

	private JPanel northPanel, centrePanel, southPanel;

	private JPanel centreLeftPanel, centreCentrePanel, centreRightPanel;

	private int numeroPosti;

	private static final int STADIUM_PANEL_ROWS = 3;
	private static final int STADIUM_PANEL_COLUMNS = 1;

	/***********************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(new StadiumPanel());
		frame.setSize(1000, 700);
		frame.setVisible(true);
	}
}
