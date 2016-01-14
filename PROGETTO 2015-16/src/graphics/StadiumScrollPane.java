package graphics;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import user.Cliente;

public class StadiumScrollPane extends JScrollPane implements Serializable{

	public StadiumScrollPane(Cliente cliente) {
		super();
		this.cliente = cliente;
		this.stadiumPanel = new StadiumPanel(this.cliente);
		this.setViewportView(this.stadiumPanel);
	}

	public StadiumScrollPane(int capienza) {
		super();
		this.stadiumPanel = new StadiumPanel(this.cliente, capienza);
		this.setViewportView(this.stadiumPanel);
	}

	public StadiumPanel getStadiumPanel() {
		return this.stadiumPanel;
	}

	private static final long serialVersionUID = 5759489801369687927L;
	private Cliente cliente;
	private StadiumPanel stadiumPanel;

	/***************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame("StadiumScrollPane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new StadiumScrollPane(new Cliente("Maurizio", "", "", "P@ssw0rd")));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}