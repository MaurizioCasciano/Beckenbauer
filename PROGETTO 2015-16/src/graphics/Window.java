package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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

		this.mainPanel = new BackgroundImagePanel(Assets.getGreenField());
		this.mainPanel.setLayout(new BorderLayout());

		this.identificationPanel = new IdentificationPanel(Assets.getCubes(), strutturaSportiva);
		this.mainPanel.add(this.identificationPanel, BorderLayout.EAST);

		this.add(mainPanel, BorderLayout.CENTER);

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Imposta l'utente attualmente loggato ed aggiorna l'interfaccia grafica di
	 * conseguenza.
	 * 
	 * @param utente
	 *            L'utente loggato.
	 */
	public void setUtente(Utente utente) {
		this.utente = utente;

		if (this.utente instanceof Cliente) {
			this.mainPanel.remove(this.identificationPanel);
			this.mainPanel.repaint();

			this.modalita = MODE.Cliente;

			JOptionPane.showMessageDialog(this.mainPanel,
					"Modalità " + this.modalita + "\nBenvenuto " + utente.getNome(), "Benvenuto",
					JOptionPane.INFORMATION_MESSAGE, Assets.getCustomerIcon());

			// TODO aggiungere i componenti grafici opportuni alla modalità
		} else if (this.utente instanceof Gestore) {
			this.mainPanel.remove(this.identificationPanel);
			this.mainPanel.repaint();

			this.modalita = MODE.Gestore;

			JOptionPane.showMessageDialog(this.mainPanel,
					"Modalità " + this.modalita + "\nBenvenuto " + utente.getNome(), "Login",
					JOptionPane.INFORMATION_MESSAGE, Assets.getManagerIcon());

			JMenuBar menuBar = new JMenuBar();
			menuBar.setBackground(Color.LIGHT_GRAY);
			JMenu partiteMenu = new JMenu("Partite");
			JMenuItem newPartitaItem = new JMenuItem("New");

			newPartitaItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Aggiungere componenti grafici scelta squadre e
					// pulsante aggiungi
				}
			});

			partiteMenu.add(newPartitaItem);
			menuBar.add(partiteMenu);

			// TODO aggiungere i componenti grafici opportuni alla modalità

			this.setJMenuBar(menuBar);
			this.revalidate();
		}

	}

	private static final long serialVersionUID = 5196150741171238114L;
	public static final int WIDTH = 1000, HEIGHT = 600;
	private JPanel mainPanel;
	private IdentificationPanel identificationPanel;

	private Utente utente;
	private MODE modalita;
}
