package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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
import java.util.concurrent.ExecutionException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;

import graphics.login.IdentificationPanel;
import objectsTable.PartitaTable;
import objectsTable.PartitaTableModel;
import objectsTable.RowObjectTableModel;
import password.WeakPasswordException;
import struttura.Mode;
import struttura.Partita;
import struttura.StrutturaSportiva;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import user.Gestore;
import user.UserNotFoundException;
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
		} catch (UserNotFoundException e) {
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
			this.mode = Mode.CLIENTE;
		} else if (this.utente instanceof Gestore) {
			this.mode = Mode.GESTORE;
		}

		this.initUI();
	}

	private void initUI() {
		switch (this.mode) {
		case CLIENTE:
			this.initUIClienteMode();
			break;
		case GESTORE:
			this.initUIGestoreMode();
			break;
		default:
			break;
		}
	}

	private void initUIClienteMode() {
		JOptionPane.showMessageDialog(this.mainPanel, "\nBenvenuto " + utente.getNome(), "Benvenuto",
				JOptionPane.INFORMATION_MESSAGE, Assets.getCustomerIcon());

		this.partitaTable = new PartitaTable(this.mode, this.strutturaSportiva.getPartiteProgrammate());
		this.partitaTable.setComponentPopupMenu(new MyPopupMenu());
		this.partitaTableScrollPane = new JScrollPane(partitaTable);
		// scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);

		this.tabbedPane = new JTabbedPane();
		this.tabbedPane.addTab("Partite", this.partitaTableScrollPane);

		// this.mainPanel.add(partitaTableScrollPane, BorderLayout.CENTER);
		this.mainPanel.add(this.tabbedPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		JMenu partiteMenu = new JMenu("Partite");
		JMenuItem newPartitaItem = new JMenuItem("New");

		partiteMenu.add(newPartitaItem);
		menuBar.add(partiteMenu);
		// this.setJMenuBar(menuBar);
		this.revalidate();
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

			}
		});

		partiteMenu.add(newPartitaItem);
		menuBar.add(partiteMenu);

		this.partitaTable = new PartitaTable(Mode.GESTORE, this.strutturaSportiva.getPartiteProgrammate());
		this.partitaTable.setComponentPopupMenu(new MyPopupMenu());
		this.partitaTableScrollPane = new JScrollPane(partitaTable);
		// scrollPane.getViewport().setBackground(Color.LIGHT_GRAY);

		this.mainPanel.add(this.partitaTableScrollPane, BorderLayout.CENTER);
		// TODO aggiungere i componenti grafici opportuni alla modalità

		// this.setJMenuBar(menuBar);
		this.revalidate();
	}

	/**
	 * Rimuove il pannello di identificazione.
	 */
	private void removeIdentificationPanel() {
		this.mainPanel.remove(this.identificationPanel);
		this.mainPanel.repaint();
	}

	/************************************************************************************************/
	class MyWindowAdapter extends WindowAdapter {

		@Override
		public void windowClosing(WindowEvent paramWindowEvent) {
			Window.this.storeStrutturaSportiva();
		}
	}

	class MyPopupMenu extends JPopupMenu implements Serializable {

		public MyPopupMenu() {
			super();

			switch (Window.this.mode) {
			case CLIENTE:
				this.dettaggli = new JMenuItem("Dettagli");
				this.prenota = new JMenuItem("Prenota");
				this.completaPrenotazione = new JMenuItem("Completa prenotazione");
				this.acquista = new JMenuItem("Acquista");
				/*****************************************************************/
				this.prenota.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						/*
						 * Esegue la creazione dello Stadio su un thread in
						 * background in modo da non bloccare l'interfaccia
						 * grafica.
						 */
						new SwingWorker<StadiumScrollPane, Void>() {

							@Override
							protected StadiumScrollPane doInBackground() throws Exception {
								// System.out.println(SwingUtilities.isEventDispatchThread());
								Window.this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
								StadiumScrollPane stadiumScrollPane = new StadiumScrollPane(
										(Cliente) Window.this.utente);
								return stadiumScrollPane;
							}

							@Override
							protected void done() {
								// System.out.println(SwingUtilities.isEventDispatchThread());
								try {
									Window.this.tabbedPane.addTab("Stadio", get());
									Window.this.tabbedPane.setSelectedIndex(1);
									Window.this.tabbedPane.revalidate();
								} catch (InterruptedException e) {
									e.printStackTrace();
								} catch (ExecutionException e) {
									e.printStackTrace();
								} finally {
									Window.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
								}
							}

						}.execute();
					}
				});
				/*****************************************************************/
				this.add(dettaggli);
				this.addSeparator();
				this.add(prenota);
				this.add(completaPrenotazione);
				this.add(acquista);

				break;
			case GESTORE:
				this.addPartita = new JMenuItem("Aggiungi Partita");
				this.removePartita = new JMenuItem("Rimuovi Partita");
				/*************************************************************************/
				this.addPartita.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						((PartitaTableModel) Window.this.partitaTable.getModel()).addPartita(new Partita());
					}
				});

				this.removePartita.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						int viewIndex = partitaTable.getSelectedRow();
						int modelIndex = partitaTable.convertRowIndexToModel(viewIndex);

						if (partitaTable.getModel() instanceof RowObjectTableModel<?>)
							((PartitaTableModel) partitaTable.getModel()).removePartita(modelIndex);
					}
				});
				/***********************************************************************************************/
				this.add(addPartita);
				this.add(removePartita);
				break;
			default:
				break;
			}
		}

		private static final long serialVersionUID = 6108564986742304925L;
		private JMenuItem dettaggli, prenota, completaPrenotazione, acquista;
		/********************************************************************/
		private JMenuItem addPartita, removePartita;
	}

	private static final long serialVersionUID = 5196150741171238114L;
	public static final int WIDTH = 1000, HEIGHT = 600;
	private JPanel mainPanel;
	private IdentificationPanel identificationPanel;
	private Utente utente;
	private Mode mode;
	private PartitaTable partitaTable;
	private JScrollPane partitaTableScrollPane;
	private JTabbedPane tabbedPane;
	/************************************************/
	private String strutturaSportivaName;
	private StrutturaSportiva strutturaSportiva;
	private File strutturaSportiva_DB_File;
}
