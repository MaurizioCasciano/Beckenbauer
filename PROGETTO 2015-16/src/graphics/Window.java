package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.table.TableRowSorter;
import calendar.Week;
import graphics.login.IdentificationPanel;
import objectsTable.PartitaTable;
import objectsTable.PartitaTableModel;
import objectsTable.filter.PartitaRowFilter;
import password.WeakPasswordException;
import struttura.Mode;
import struttura.Partita;
import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.filters.MatchByStadiumFilter;
import struttura.filters.MatchByWeekFilter;
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

		this.partitaTable = new PartitaTable(this.mode, this.strutturaSportiva.getPartiteProgrammate(),
				this.strutturaSportiva);
		// this.partitaTable.setComponentPopupMenu(new MyPopupMenu());

		this.partitaTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					/*
					 * Aggiungere i controlli per gestire gli item da
					 * disabilitare e da creare in base alla partita selezionata
					 * e alle prenotazioni (acquisti) del cliente.
					 */
					new MyPopupMenu().show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					/*
					 * Aggiungere i controlli per gestire gli item da
					 * disabilitare e da creare in base alla partita selezionata
					 * e alle prenotazioni (acquisti) del cliente.
					 */
					new MyPopupMenu().show(e.getComponent(), e.getX(), e.getY());
				}
			}

		});

		this.partitaTableScrollPane = new JScrollPane(partitaTable);
		partitaTableScrollPane.getViewport().setBackground(Color.LIGHT_GRAY);

		this.partitePanel = new JPanel(new BorderLayout());
		this.partitePanel.add(this.partitaTableScrollPane);

		JPanel filterPanel = new JPanel();

		/*
		 * RadioButtons per selezionare il tipo di filtro.
		 */
		JRadioButton weekFilterRadioButton = new JRadioButton("Settimana", true);
		JRadioButton stadiumFilterRadioButton = new JRadioButton("Stadio");

		JPanel comboBoxButtonsPanel = new JPanel();

		weekFilterRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxButtonsPanel.removeAll();

				JComboBox<Week> weeks = new JComboBox<Week>(Week.getNextYearWeeks().toArray(new Week[53]));

				JButton filtraButton = new JButton("Filtra");

				filtraButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("unchecked")
						TableRowSorter<PartitaTableModel> sorter = (TableRowSorter<PartitaTableModel>) partitaTable
								.getRowSorter();
						sorter.setRowFilter(new PartitaRowFilter(
								new MatchByWeekFilter(((Week) weeks.getSelectedItem()).getStart())));
					}
				});

				JButton resetButton = new JButton("Reset");

				resetButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("unchecked")
						TableRowSorter<PartitaTableModel> sorter = (TableRowSorter<PartitaTableModel>) partitaTable
								.getRowSorter();
						sorter.setRowFilter(null);
					}
				});

				comboBoxButtonsPanel.add(new JLabel("Settimana: "));
				comboBoxButtonsPanel.add(weeks);

				comboBoxButtonsPanel.add(filtraButton);
				comboBoxButtonsPanel.add(resetButton);
				comboBoxButtonsPanel.revalidate();
			}
		});
		weekFilterRadioButton.doClick();
		weekFilterRadioButton.setSelected(true);

		stadiumFilterRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxButtonsPanel.removeAll();

				JComboBox<Stadio> stadiums = new JComboBox<Stadio>(
						Window.this.strutturaSportiva.getStadi().toArray(new Stadio[1]));

				JButton filtraButton = new JButton("Filtra");

				filtraButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("unchecked")
						TableRowSorter<PartitaTableModel> sorter = (TableRowSorter<PartitaTableModel>) partitaTable
								.getRowSorter();
						sorter.setRowFilter(
								new PartitaRowFilter(new MatchByStadiumFilter((Stadio) stadiums.getSelectedItem())));
					}
				});

				JButton resetButton = new JButton("Reset");

				resetButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("unchecked")
						TableRowSorter<PartitaTableModel> sorter = (TableRowSorter<PartitaTableModel>) partitaTable
								.getRowSorter();
						sorter.setRowFilter(null);
					}
				});

				comboBoxButtonsPanel.add(new JLabel("Stadio: "));
				comboBoxButtonsPanel.add(stadiums);

				comboBoxButtonsPanel.add(filtraButton);
				comboBoxButtonsPanel.add(resetButton);
				comboBoxButtonsPanel.revalidate();
			}
		});

		ButtonGroup filterGroup = new ButtonGroup();
		JPanel filterRadioButtonsPanel = new JPanel();
		filterRadioButtonsPanel.setBorder(new LineBorder(Color.GRAY));
		filterRadioButtonsPanel.add(weekFilterRadioButton);
		filterRadioButtonsPanel.add(stadiumFilterRadioButton);

		filterGroup.add(weekFilterRadioButton);
		filterGroup.add(stadiumFilterRadioButton);

		/*
		 * Pannello contenente
		 */
		filterPanel.add(new JLabel("Filtro: "));
		filterPanel.add(filterRadioButtonsPanel);
		filterPanel.add(comboBoxButtonsPanel);

		/*
		 * filterPanel.add(new JLabel("Settimana: "));
		 * 
		 * JComboBox<Week> weeks = new
		 * JComboBox<Week>(Week.getNextYearWeeks().toArray(new Week[53]));
		 * 
		 * filterPanel.add(weeks);
		 */

		/*
		 * JButton filtraButton = new JButton("Filtra");
		 * 
		 * filtraButton.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * @SuppressWarnings("unchecked") TableRowSorter<PartitaTableModel>
		 * sorter = (TableRowSorter<PartitaTableModel>) partitaTable
		 * .getRowSorter(); sorter.setRowFilter( new PartitaRowFilter(new
		 * MatchByWeekFilter(((Week) weeks.getSelectedItem()).getStart()))); }
		 * });
		 * 
		 * 
		 * 
		 * JButton resetButton = new JButton("Reset");
		 * 
		 * resetButton.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) {
		 * 
		 * @SuppressWarnings("unchecked") TableRowSorter<PartitaTableModel>
		 * sorter = (TableRowSorter<PartitaTableModel>) partitaTable
		 * .getRowSorter(); sorter.setRowFilter(null); } });
		 * 
		 * 
		 * filterPanel.add(filtraButton); filterPanel.add(resetButton);
		 */

		this.partitePanel.add(filterPanel, BorderLayout.NORTH);

		this.tabbedPane = new ClosableTabbedPane();
		this.tabbedPane.addTab("Partite", this.partitePanel);

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
				((PartitaTableModel) Window.this.partitaTable.getModel()).addPartita(new Partita());
			}
		});

		partiteMenu.add(newPartitaItem);
		menuBar.add(partiteMenu);

		JMenu stadioMenu = new JMenu("Stadio");
		menuBar.add(stadioMenu);

		JMenuItem newStadioMenuItem = new JMenuItem("Aggiungi Stadio");
		stadioMenu.add(newStadioMenuItem);

		newStadioMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JPanel newStadium = new JPanel();

				JLabel nameLabel = new JLabel("Nome: ");
				JTextField nameField = new JTextField(10);

				JLabel capienzaLabel = new JLabel("Capienza: ");
				JSpinner capienzaSpinner = new JSpinner(new SpinnerNumberModel(30000, 30000, 100000, 1));

				JLabel prezzoLabel = new JLabel("Prezzo");
				JSpinner prezzoSpinner = new JSpinner(new SpinnerNumberModel(5.0, 5.0, 100.0, 0.5));

				newStadium.setLayout(new GridLayout(3, 2));
				newStadium.add(nameLabel);
				newStadium.add(nameField);
				newStadium.add(capienzaLabel);
				newStadium.add(capienzaSpinner);
				newStadium.add(prezzoLabel);
				newStadium.add(prezzoSpinner);

				int returnValue = JOptionPane.showOptionDialog(Window.this, newStadium, "Aggiungi Stadio",
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null,
						new String[] { "Aggiungi", "Cancella" }, 0);

				if (returnValue == 0) {
					strutturaSportiva.addStadio(new Stadio(nameField.getText(), (int) capienzaSpinner.getValue(),
							(double) prezzoSpinner.getValue()));
					System.out.println(strutturaSportiva.getStadi().size());
				}
			}
		});

		this.partitaTable = new PartitaTable(Mode.GESTORE, this.strutturaSportiva.getPartiteProgrammate(),
				this.strutturaSportiva);
		// this.partitaTable.setComponentPopupMenu(new MyPopupMenu());

		this.partitaTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					/*
					 * Aggiungere i controlli per gestire gli item da
					 * disabilitare e da creare in base alla partita
					 * selezionata.
					 */
					new MyPopupMenu().show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					/*
					 * Aggiungere i controlli per gestire gli item da
					 * disabilitare e da creare in base alla partita
					 * selezionata.
					 */
					new MyPopupMenu().show(e.getComponent(), e.getX(), e.getY());
				}
			}

		});

		this.partitaTableScrollPane = new JScrollPane(partitaTable);
		this.mainPanel.add(this.partitaTableScrollPane, BorderLayout.CENTER);

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

				// effetturae controllo prenotazione partita selezionata
				// this.prenota.setEnabled(false);
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
								System.out.println(SwingUtilities.isEventDispatchThread());
								Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
								StadiumScrollPane stadiumScrollPane = new StadiumScrollPane(
										Window.this.strutturaSportiva, (Cliente) Window.this.utente,
										Window.this.partitaTable.getSelectedPartita());
								return stadiumScrollPane;
							}

							@Override
							protected void done() {
								System.out.println(SwingUtilities.isEventDispatchThread());
								try {
									Window.this.tabbedPane.addTab("Stadio", get());
									Window.this.tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
									Window.this.tabbedPane.revalidate();
								} catch (InterruptedException e) {
									e.printStackTrace();
								} catch (ExecutionException e) {
									e.printStackTrace();
								} finally {
									Window.this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
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

						if (viewIndex != -1) {
							int modelIndex = partitaTable.convertRowIndexToModel(viewIndex);
							((PartitaTableModel) partitaTable.getModel()).removePartita(modelIndex);
						}
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
	public static final int WIDTH = 1000, HEIGHT = 700;
	private JPanel mainPanel;
	private IdentificationPanel identificationPanel;
	private Utente utente;
	private Mode mode;
	private PartitaTable partitaTable;
	private JScrollPane partitaTableScrollPane;
	private JPanel partitePanel;
	private ClosableTabbedPane tabbedPane;
	/************************************************/
	private String strutturaSportivaName;
	private StrutturaSportiva strutturaSportiva;
	private File strutturaSportiva_DB_File;
}
