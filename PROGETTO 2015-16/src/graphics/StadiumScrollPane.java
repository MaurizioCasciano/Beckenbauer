package graphics;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

public class StadiumScrollPane extends JScrollPane {

	public StadiumScrollPane() {
		super();
		this.stadiumPanel = new StadiumPanel();
		this.setViewportView(this.stadiumPanel);
	}

	public StadiumScrollPane(int capienza) {
		super();
		this.stadiumPanel = new StadiumPanel(capienza);
		this.setViewportView(this.stadiumPanel);
	}

	public StadiumPanel getStadiumPanel() {
		return this.stadiumPanel;
	}

	private static final long serialVersionUID = 5759489801369687927L;
	private StadiumPanel stadiumPanel;

	/***************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame("StadiumScrollPane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new StadiumScrollPane());
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}