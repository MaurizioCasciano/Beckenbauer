package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import login.IdentificationPanel;
import struttura.StrutturaSportiva;
import user.Cliente;
import user.Gestore;
import user.Utente;

public class Window extends JFrame {

	public Window(String title, StrutturaSportiva strutturaSportiva) {
		super(title);
		this.setSize(Window.WIDTH, Window.HEIGHT);
		this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));

		Assets.init();
		this.mainPanel = new BackgroundImagePanel(Assets.greenFiel);
		this.mainPanel.setLayout(new BorderLayout());

		this.identificationPanel = new IdentificationPanel(Assets.cubes, strutturaSportiva);
		this.mainPanel.add(this.identificationPanel, BorderLayout.EAST);

		this.add(mainPanel, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		JMenu fileMenu = new JMenu("File");
		JMenuItem newItem = new JMenuItem("New");
		fileMenu.add(newItem);
		menuBar.add(fileMenu);

		JMenu showMenu = new JMenu("Show");
		JMenuItem partiteItem = new JMenuItem("Partite");
		showMenu.add(partiteItem);
		menuBar.add(showMenu);
		this.setJMenuBar(menuBar);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Imposta l'utente attualmente loggato.
	 * 
	 * @param utente
	 *            L'utente loggato.
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;

		if (this.utente instanceof Cliente) {
			this.mainPanel.remove(this.identificationPanel);
			this.mainPanel.repaint();
			// TODO aggiungere i componenti grafici opportuni alla modalità
		} else if (this.utente instanceof Gestore) {
			this.mainPanel.remove(this.identificationPanel);
			this.mainPanel.repaint();
			// TODO aggiungere i componenti grafici opportuni alla modalità
		}

	}

	private static final long serialVersionUID = 5196150741171238114L;
	public static final int WIDTH = 1000, HEIGHT = 600;
	private JPanel mainPanel;
	private IdentificationPanel identificationPanel;

	private Utente utente;
}
