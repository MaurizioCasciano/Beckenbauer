package graphics;

import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import struttura.Partita;
import struttura.StrutturaSportiva;
import user.Cliente;

public class StadiumScrollPane extends JScrollPane implements Serializable {

	public StadiumScrollPane(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita,
			StadiumMode stadiumMode) {
		super();
		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;
		this.stadiumMode = stadiumMode;
		this.stadiumPanel = new StadiumPanel(this.strutturaSportiva, this.cliente, this.partita, this.stadiumMode);
		this.setViewportView(this.stadiumPanel);
	}

	public StadiumPanel getStadiumPanel() {
		return this.stadiumPanel;
	}

	private static final long serialVersionUID = 5759489801369687927L;
	private StrutturaSportiva strutturaSportiva;
	private Cliente cliente;
	private Partita partita;
	private StadiumPanel stadiumPanel;
	private StadiumMode stadiumMode;

	public static void main(String[] args) {
		JFrame frame = new JFrame("StadiumScrollPane");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(new StadiumScrollPane(new StrutturaSportiva(""), new Cliente("Maurizio", "", "", "P@ssw0rd"),
				new Partita(), StadiumMode.PRENOTAZIONE));
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}