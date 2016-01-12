package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import graphics.login.IdentificationPanel;
import objectsTable.PartitaTable;
import password.WeakPasswordException;
import struttura.StrutturaSportiva;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import user.Gestore;
import user.UserNotFound;
import user.Utente;

public class Window extends JFrame implements Serializable {

	public Window(String nomeStruttura) {
		super(nomeStruttura);
		this.setSize(Window.WIDTH, Window.HEIGHT);
		this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));

		this.mainPanel = new BackgroundImagePanel(Assets.getGreenField());
		this.mainPanel.setLayout(new BorderLayout());

		this.strutturaSportivaName = nomeStruttura;
		this.strutturaSportiva_DB_File = new File(this.strutturaSportivaName + ".ser");
		this.strutturaSportiva = this.loadStrutturaSportiva(this.strutturaSportiva_DB_File);

		try {
			this.strutturaSportiva.getUtente("gestore");
		} catch (UserNotFound e) {
			try {
				this.strutturaSportiva.addUtente(new Gestore("NomeGestore", "CognomeGestore", "gestore", "P@ssw0rd"));
			} catch (WeakPasswordException e1) {
				e1.printStackTrace();
			} catch (AlreadyRegisteredUserException e1) {
				e1.printStackTrace();
			}
		}

		this.identificationPanel = new IdentificationPanel(Window.this, Assets.getCubes(), this.strutturaSportiva);
		this.mainPanel.add(this.identificationPanel, BorderLayout.EAST);

		this.add(mainPanel, BorderLayout.CENTER);

		this.addWindowListener(new MyWindowAdapter());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Carica un oggetto StrutturaSportiva da file. Se il file non esiste viene
	 * creato un nuovo oggetto StrutturaSportiva.
	 * 
	 * @param DB_File
	 *            Il file contenente l'oggetto StrutturaSportiva da caricare.
	 * @return L'oggetto StrutturaSportiva presente nel file.
	 * @throws Exception
	 */
	private StrutturaSportiva loadStrutturaSportiva(File DB_File) {

		StrutturaSportiva strutturaSportiva = null;

		if (DB_File.exists()) {

			FileInputStream fileInputStrem = null;
			ObjectInputStream objectInputStream = null;
			try {
				fileInputStrem = new FileInputStream(DB_File);
				objectInputStream = new ObjectInputStream(fileInputStrem);
				strutturaSportiva = (StrutturaSportiva) objectInputStream.readObject();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					objectInputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					fileInputStrem.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (!DB_File.exists()) {
			strutturaSportiva = new StrutturaSportiva(this.strutturaSportivaName);
		}

		return strutturaSportiva;
	}

	public void storeStrutturaSportiva() {
		if (!this.strutturaSportiva_DB_File.exists()) {
			try {
				this.strutturaSportiva_DB_File.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
				System.exit(-1);
			}
		}

		FileOutputStream fileOutputStream = null;
		ObjectOutputStream objectOutputStream = null;

		try {
			fileOutputStream = new FileOutputStream(this.strutturaSportiva_DB_File);
			objectOutputStream = new ObjectOutputStream(fileOutputStream);
			objectOutputStream.writeObject(this.strutturaSportiva);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				objectOutputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Restituisce la StrutturaSportiva precedentemente caricata/creata.
	 * 
	 * @return La StrutturaSportiva gestita da questa finestra.
	 */
	public StrutturaSportiva getStrutturaSportiva() {
		return this.strutturaSportiva;
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
		this.removeIdentificationPanel();

		if (this.utente instanceof Cliente) {
			this.initUIClienteMode();
		} else if (this.utente instanceof Gestore) {
			this.initUIGestoreMode();
		}

	}

	private void initUIClienteMode() {
		JOptionPane.showMessageDialog(this.mainPanel, "\nBenvenuto " + utente.getNome(), "Benvenuto",
				JOptionPane.INFORMATION_MESSAGE, Assets.getCustomerIcon());

		PartitaTable partitaTable = new PartitaTable(this.strutturaSportiva.getPartiteProgrammate());
		JScrollPane scrollPane = new JScrollPane(partitaTable);
		// scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);

		this.mainPanel.add(scrollPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		JMenu partiteMenu = new JMenu("Partite");
		JMenuItem newPartitaItem = new JMenuItem("New");

		partiteMenu.add(newPartitaItem);
		menuBar.add(partiteMenu);
		this.setJMenuBar(menuBar);
		this.revalidate();

		// TODO aggiungere i componenti grafici opportuni alla modalità
	}

	private void initUIGestoreMode() {
		JOptionPane.showMessageDialog(this.mainPanel, "\nBenvenuto " + utente.getNome(), "Login",
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

	/**
	 * Rimuove il pannello di identificazione.
	 */
	private void removeIdentificationPanel() {
		this.mainPanel.remove(this.identificationPanel);
		this.mainPanel.repaint();
	}

	class MyWindowAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent paramWindowEvent) {
			super.windowClosing(paramWindowEvent);
			System.out.println("CLOSING");
			Window.this.storeStrutturaSportiva();
		}
	}

	private static final long serialVersionUID = 5196150741171238114L;
	public static final int WIDTH = 1000, HEIGHT = 600;
	private JPanel mainPanel;
	private IdentificationPanel identificationPanel;
	private Utente utente;

	/************************************************/
	private String strutturaSportivaName;
	private StrutturaSportiva strutturaSportiva;
	private File strutturaSportiva_DB_File;
}
