package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import javax.swing.AbstractAction;
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
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.table.TableRowSorter;
import calendar.Week;
import combo.renderers.StadioComboRenderer;
import combo.renderers.WeekComboRenderer;
import graphics.incasso.VisualizzaIncassoFrame;
import graphics.login.IdentificationPanel;
import graphics.sconti.AggiungiStadioFrame;
import graphics.sconti.ModificaStadioFrame;
import graphics.sconti.ScontoGiornoFrame;
import graphics.sconti.ScontoPartitaFrame;
import graphics.sconti.ScontoStadioFrame;
import graphics.stadium.StadiumMode;
import graphics.stadium.StadiumScrollPane;
import objectsTable.AcquistoTable;
import objectsTable.AcquistoTableModel;
import objectsTable.PartitaTable;
import objectsTable.PartitaTableModel;
import objectsTable.PrenotazioneTable;
import objectsTable.PrenotazioneTableModel;
import objectsTable.filter.PartitaRowFilter;
import password.WeakPasswordException;
import struttura.Acquisto;
import struttura.Mode;
import struttura.Partita;
import struttura.Prenotazione;
import struttura.SeatStatus;
import struttura.Stadio;
import struttura.StrutturaSportiva;
import struttura.filters.MatchByStadiumFilter;
import struttura.filters.MatchByWeekFilter;
import struttura.filters.MatchNotYetStartedFilter;
import struttura.filters.PrenotationByCustomerFilter;
import struttura.filters.PurchaseByCustomerFilter;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import user.Gestore;
import user.UserNotFoundException;
import user.Utente;

/**
 * Classe che estende {@link JFrame}, usata per la visualizzazione
 * dell'interfaccia grafica della StrutturaSportiva.
 * 
 * @author Maurizio
 */
public class Window extends JFrame implements Serializable {

	/**
	 * Crea un nuovo frame per la gestione di una {@link StrutturaSportiva} e lo
	 * rende visibile.
	 * 
	 * @param nomeStruttura
	 *            il nome della StrutturaSportiva.
	 * @author Maurizio
	 */
	public Window(String nomeStruttura) {
		super(nomeStruttura);
		this.setIconImage(Assets.getFrameBallIcon());
		this.setSize(Window.WIDTH, Window.HEIGHT);
		this.setMinimumSize(new Dimension(Window.WIDTH, Window.HEIGHT));

		this.mainPanel = new BackgroundImagePanel(Assets.getGreenField());
		this.mainPanel.setLayout(new BorderLayout());

		this.strutturaSportivaName = nomeStruttura;
		this.strutturaSportiva_DB_File = new File(this.strutturaSportivaName + ".ser");
		this.strutturaSportiva = this.loadStrutturaSportiva(this.strutturaSportiva_DB_File);

		/*
		 * In assenza dell'account del gestore, viene creato.
		 */
		try {
			this.strutturaSportiva.getUtente("admin");
		} catch (UserNotFoundException e) {
			try {
				this.strutturaSportiva.addUtente(new Gestore("Gestore", "Gestore", "admin", "P@ssw0rd"));
			} catch (WeakPasswordException e1) {
				e1.printStackTrace();
			} catch (AlreadyRegisteredUserException e1) {
				e1.printStackTrace();
			}
		}

		this.identificationPanel = new IdentificationPanel(Window.this, Assets.getCubes(), this.strutturaSportiva);
		this.mainPanel.add(this.identificationPanel, BorderLayout.EAST);

		this.add(mainPanel, BorderLayout.CENTER);

		/*
		 * Necessario per avere il controllo totale sulla chiusura del frame e
		 * quindi sul salvataggio del file.
		 */
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new MyWindowAdapter());
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	/**
	 * Carica un oggetto {@link StrutturaSportiva} da file. Se il file non
	 * esiste viene creato un nuovo oggetto StrutturaSportiva.
	 * 
	 * @param DB_File
	 *            Il file contenente l'oggetto StrutturaSportiva da caricare.
	 * @return L'oggetto StrutturaSportiva presente nel file.
	 */
	protected StrutturaSportiva loadStrutturaSportiva(File DB_File) {

		StrutturaSportiva strutturaSportiva = null;

		if (DB_File.exists()) {

			try {
				/*
				 * Se i costruttori lanciano un'eccezione, il blocco finally NON
				 * sarà eseguito.
				 */
				FileInputStream fileInputStrem = new FileInputStream(DB_File);
				ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStrem);

				try {
					strutturaSportiva = (StrutturaSportiva) objectInputStream.readObject();
				} finally {
					/*
					 * Chiude i flussi di input.
					 */
					objectInputStream.close();
					fileInputStrem.close();
				}
			} catch (FileNotFoundException e) {
				/*
				 * NON SUCCEDE MAI perchè viene fatto il controllo
				 * sull'esistenza del file.
				 */
				JOptionPane.showMessageDialog(Window.this, e.getMessage(), e.getClass().getSimpleName(),
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(-1);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(Window.this, e.getMessage(), e.getClass().getSimpleName(),
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(-2);
			} catch (ClassNotFoundException e) {
				JOptionPane.showMessageDialog(Window.this, e.getMessage(), e.getClass().getSimpleName(),
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				System.exit(-3);
			}
		} else if (!DB_File.exists()) {
			/*
			 * Nel caso in cui il file non esiste viene creata una nuova istanza
			 * di StrutturaSportiva.
			 */
			strutturaSportiva = new StrutturaSportiva(this.strutturaSportivaName);
		}

		return strutturaSportiva;
	}

	/**
	 * Salva la {@link StrutturaSportiva} su file. Se il file non esiste ne
	 * viene creato uno nuovo.
	 * 
	 * @author Maurizio
	 */
	public void storeStrutturaSportiva() {
		try {
			/*
			 * Crea il file se non esiste.
			 */
			this.strutturaSportiva_DB_File.createNewFile();

			/*
			 * Se i costruttori lanciano un'eccezione, il blocco finally NON
			 * sarà eseguito.
			 */
			FileOutputStream fileOutputStream = new FileOutputStream(this.strutturaSportiva_DB_File);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

			try {
				objectOutputStream.writeObject(this.strutturaSportiva);
			} finally {
				/*
				 * Chiude i flussi di output.
				 */
				objectOutputStream.close();
				fileOutputStream.close();
			}

		} catch (FileNotFoundException e) {
			/*
			 * NON SUCCEDE MAI perchè il file viene creato se non esiste.
			 */
			JOptionPane.showMessageDialog(Window.this, e.getMessage(), e.getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-1);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(Window.this, e.getMessage(), e.getClass().getSimpleName(),
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
			System.exit(-2);
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

		this.partitaTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					new PartitePopupMenu().show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					new PartitePopupMenu().show(e.getComponent(), e.getX(), e.getY());
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
		JRadioButton weekFilterRadioButton = new JRadioButton("Settimana");
		JRadioButton stadiumFilterRadioButton = new JRadioButton("Stadio");
		JRadioButton notYetStartedRadioButton = new JRadioButton("Da giocarsi");

		JPanel comboBoxButtonsPanel = new JPanel();

		weekFilterRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxButtonsPanel.removeAll();

				ArrayList<Week> nextYearWeeks = Week.getNextYearWeeks();

				JComboBox<Week> weeks = new JComboBox<Week>(nextYearWeeks.toArray(new Week[nextYearWeeks.size()]));
				weeks.setRenderer(new WeekComboRenderer());

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
		/*
		 * Genera un actionEvent e selezione il radioButton.
		 */
		weekFilterRadioButton.doClick();

		stadiumFilterRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxButtonsPanel.removeAll();
				ArrayList<Stadio> stadi = Window.this.strutturaSportiva.getStadi();

				JComboBox<Stadio> stadiums = new JComboBox<Stadio>(stadi.toArray(new Stadio[stadi.size()]));
				stadiums.setRenderer(new StadioComboRenderer());

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

		notYetStartedRadioButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				comboBoxButtonsPanel.removeAll();

				JButton filtraButton = new JButton("Filtra");

				filtraButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("unchecked")
						TableRowSorter<PartitaTableModel> sorter = (TableRowSorter<PartitaTableModel>) partitaTable
								.getRowSorter();
						sorter.setRowFilter(new PartitaRowFilter(new MatchNotYetStartedFilter()));
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
		filterRadioButtonsPanel.add(notYetStartedRadioButton);

		filterGroup.add(weekFilterRadioButton);
		filterGroup.add(stadiumFilterRadioButton);
		filterGroup.add(notYetStartedRadioButton);

		/*
		 * Pannello contenente
		 */
		filterPanel.add(new JLabel("Filtro: "));
		filterPanel.add(filterRadioButtonsPanel);
		filterPanel.add(comboBoxButtonsPanel);

		this.partitePanel.add(filterPanel, BorderLayout.NORTH);

		this.tabbedPane = new ClosableTabbedPane();
		this.tabbedPane.addTab("Partite", this.partitePanel);

		this.mainPanel.add(this.tabbedPane, BorderLayout.CENTER);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);

		JMenu fileMenu = new JMenu("File");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setMnemonic('S');
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Window.this.storeStrutturaSportiva();
						return null;
					}

					@Override
					protected void done() {
						Window.this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				}.execute();
			}
		});
		fileMenu.add(saveMenuItem);

		JMenuItem exitMenuItem = new JMenuItem();
		exitMenuItem.setAction(new LogoutAction());

		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		JMenu showMenu = new JMenu("Visualizza");
		JMenuItem showPrenotazioni = new JMenuItem("Prenotazioni");

		showPrenotazioni.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SwingWorker<ArrayList<Prenotazione>, Void>() {

					@Override
					protected ArrayList<Prenotazione> doInBackground() throws Exception {
						Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						ArrayList<Prenotazione> prenotazioniCliente = Window.this.strutturaSportiva
								.getPrenotazioniFiltrate(new PrenotationByCustomerFilter((Cliente) Window.this.utente));
						return prenotazioniCliente;
					}

					@Override
					protected void done() {
						try {
							try {
								Window.this.prenotazioniTable = new PrenotazioneTable(get());
								Window.this.prenotazioniTable.setComponentPopupMenu(new PrenotazioniPopupMenu());

								JPanel prenotazioniPanel = new JPanel(new BorderLayout());
								JPanel buttonNorthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
								prenotazioniPanel.add(buttonNorthPanel, BorderLayout.NORTH);

								JButton updateButton = new JButton("Update");
								buttonNorthPanel.add(updateButton);
								updateButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										new SwingWorker<ArrayList<Prenotazione>, Void>() {

											@Override
											protected ArrayList<Prenotazione> doInBackground() throws Exception {
												Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
												ArrayList<Prenotazione> prenotazioniCliente = Window.this.strutturaSportiva
														.getPrenotazioniFiltrate(new PrenotationByCustomerFilter(
																(Cliente) Window.this.utente));
												return prenotazioniCliente;
											}

											@Override
											protected void done() {
												try {
													try {
														((PrenotazioneTableModel) (Window.this.prenotazioniTable
																.getModel())).replaceData(get());
													} finally {
														Window.this.tabbedPane
																.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
													}
												} catch (InterruptedException e) {
													e.printStackTrace();
												} catch (ExecutionException e) {
													e.printStackTrace();
												}
											}
										}.execute();
									}
								});

								JScrollPane prenotazioniScrollPane = new JScrollPane(Window.this.prenotazioniTable);
								prenotazioniPanel.add(prenotazioniScrollPane, BorderLayout.CENTER);

								Window.this.tabbedPane.addTab("Prenotazioni", prenotazioniPanel);
								Window.this.tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
								Window.this.tabbedPane.revalidate();
							} finally {
								Window.this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					}
				}.execute();
			}
		});

		JMenuItem showAcquisti = new JMenuItem("Acquisti");
		showAcquisti.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent actionevent) {
				new SwingWorker<ArrayList<Acquisto>, Void>() {

					@Override
					protected ArrayList<Acquisto> doInBackground() throws Exception {
						Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						ArrayList<Acquisto> acquistiCliente = Window.this.strutturaSportiva
								.getAcquistiFiltrati(new PurchaseByCustomerFilter((Cliente) Window.this.utente));
						return acquistiCliente;
					}

					@Override
					protected void done() {

						try {
							try {
								Window.this.acquistiTabel = new AcquistoTable(get());

								JPanel acquistiPanel = new JPanel(new BorderLayout());
								JPanel buttonNorthPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
								acquistiPanel.add(buttonNorthPanel, BorderLayout.NORTH);

								JButton updateButton = new JButton("Update");
								buttonNorthPanel.add(updateButton);

								updateButton.addActionListener(new ActionListener() {

									@Override
									public void actionPerformed(ActionEvent e) {
										new SwingWorker<ArrayList<Acquisto>, Void>() {

											@Override
											protected ArrayList<Acquisto> doInBackground() throws Exception {
												Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
												ArrayList<Acquisto> acquistiCliente = Window.this.strutturaSportiva
														.getAcquistiFiltrati(new PurchaseByCustomerFilter(
																(Cliente) Window.this.utente));
												return acquistiCliente;
											}

											@Override
											protected void done() {
												try {
													try {
														((AcquistoTableModel) (Window.this.acquistiTabel.getModel()))
																.replaceData(get());
													} finally {
														Window.this.tabbedPane
																.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
													}
												} catch (InterruptedException e) {
													e.printStackTrace();
												} catch (ExecutionException e) {
													e.printStackTrace();
												}
											}
										}.execute();

									}
								});

								JScrollPane acquistiScrollPane = new JScrollPane(Window.this.acquistiTabel);
								acquistiPanel.add(acquistiScrollPane, BorderLayout.CENTER);

								Window.this.tabbedPane.addTab("Acquisti", acquistiPanel);
								Window.this.tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
								Window.this.tabbedPane.revalidate();
							} finally {
								Window.this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							}
						} catch (InterruptedException e) {
							e.printStackTrace();
						} catch (ExecutionException e) {
							e.printStackTrace();
						}
					}
				}.execute();
			}
		});

		showMenu.add(showPrenotazioni);
		showMenu.add(showAcquisti);
		menuBar.add(showMenu);

		this.setJMenuBar(menuBar);
		this.revalidate();
	}

	/**
	 * Inizializza l'interfaccia grafica con i componenti necessari per la
	 * MODALITA' GESTORE.
	 * 
	 * @author Maurizio
	 */
	private void initUIGestoreMode() {
		JOptionPane.showMessageDialog(this.mainPanel, "\nBenvenuto " + utente.getNome(), "Login",
				JOptionPane.INFORMATION_MESSAGE, Assets.getManagerIcon());

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(Color.LIGHT_GRAY);
		JMenu fileMenu = new JMenu("File");
		JMenuItem saveMenuItem = new JMenuItem("Save");
		saveMenuItem.setMnemonic('S');
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				new SwingWorker<Void, Void>() {

					@Override
					protected Void doInBackground() throws Exception {
						Window.this.partitaTableScrollPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
						Window.this.storeStrutturaSportiva();
						return null;
					}

					@Override
					protected void done() {
						Window.this.partitaTableScrollPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}
				}.execute();
			}
		});

		fileMenu.add(saveMenuItem);

		JMenuItem exitMenuItem = new JMenuItem();
		exitMenuItem.setAction(new LogoutAction());

		fileMenu.add(exitMenuItem);
		menuBar.add(fileMenu);

		JMenu partiteMenu = new JMenu("Partita");
		JMenuItem newPartitaItem = new JMenuItem("Aggiungi");

		newPartitaItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				((PartitaTableModel) Window.this.partitaTable.getModel()).addPartita(new Partita());
			}
		});

		partiteMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				if (strutturaSportiva.getStadi().size() == 0) {
					newPartitaItem.setEnabled(false);
				} else {
					newPartitaItem.setEnabled(true);
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
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

				AggiungiStadioFrame aggiungiStadioFrame = new AggiungiStadioFrame(strutturaSportiva);
				aggiungiStadioFrame.setLocationRelativeTo(Window.this);
				aggiungiStadioFrame.setVisible(true);
			}
		});

		JMenuItem modificaStadioItem = new JMenuItem("Modifica Stadio");
		stadioMenu.add(modificaStadioItem);

		modificaStadioItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Stadio> stadi = strutturaSportiva.getStadi();

				try {
					ModificaStadioFrame modificaStadioFrame = new ModificaStadioFrame(stadi);
					modificaStadioFrame.setLocationRelativeTo(Window.this);
					modificaStadioFrame.setVisible(true);
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});

		stadioMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				if (strutturaSportiva.getStadi().size() == 0) {
					modificaStadioItem.setEnabled(false);
				} else {
					modificaStadioItem.setEnabled(true);
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});

		JMenu scontiMenu = new JMenu("Sconti");
		JMenuItem scontiStadio = new JMenuItem("Stadio");
		scontiStadio.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Stadio> stadi = strutturaSportiva.getStadi();

				try {
					ScontoStadioFrame scontoStadioFrame = new ScontoStadioFrame(strutturaSportiva, stadi);
					scontoStadioFrame.setLocationRelativeTo(Window.this);
					scontoStadioFrame.setVisible(true);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Window.this, ex.getMessage(), "Nessuno stadio presente.",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JMenuItem scontiPartita = new JMenuItem("Partita");
		scontiPartita.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<Partita> partite = strutturaSportiva.getPartiteProgrammate();

				try {
					ScontoPartitaFrame scontoPartitaFrame = new ScontoPartitaFrame(strutturaSportiva, partite);
					scontoPartitaFrame.setLocationRelativeTo(Window.this);
					scontoPartitaFrame.setVisible(true);
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(Window.this, ex.getMessage(), "Nessuno partita presente.",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		JMenuItem scontiPerGiornoSettimana = new JMenuItem("Giorno");

		scontiPerGiornoSettimana.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScontoGiornoFrame scontoGiornoFrame = new ScontoGiornoFrame(strutturaSportiva);
				scontoGiornoFrame.setLocationRelativeTo(Window.this);
				scontoGiornoFrame.setVisible(true);
			}
		});

		scontiMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				if (strutturaSportiva.getStadi().size() == 0) {
					scontiStadio.setEnabled(false);
					scontiPartita.setEnabled(false);
					scontiPerGiornoSettimana.setEnabled(false);
				} else {
					scontiStadio.setEnabled(true);
					scontiPartita.setEnabled(true);
					scontiPerGiornoSettimana.setEnabled(true);
				}

				if (strutturaSportiva.getPartiteProgrammate().size() == 0) {
					scontiPartita.setEnabled(false);
					scontiPerGiornoSettimana.setEnabled(false);
				} else {
					scontiPartita.setEnabled(true);
					scontiPerGiornoSettimana.setEnabled(true);
				}

			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});

		scontiMenu.add(scontiStadio);
		scontiMenu.add(scontiPartita);
		scontiMenu.add(scontiPerGiornoSettimana);
		menuBar.add(scontiMenu);

		JMenu incassoMenu = new JMenu("Incasso");
		JMenuItem incassoMenuItem = new JMenuItem("Visualizza incasso");

		incassoMenu.addMenuListener(new MenuListener() {

			@Override
			public void menuSelected(MenuEvent e) {
				if (strutturaSportiva.getStadi().size() == 0) {
					incassoMenuItem.setEnabled(false);
				} else {
					incassoMenuItem.setEnabled(true);
				}
			}

			@Override
			public void menuDeselected(MenuEvent e) {
			}

			@Override
			public void menuCanceled(MenuEvent e) {
			}
		});

		incassoMenuItem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					VisualizzaIncassoFrame incassoFrame = new VisualizzaIncassoFrame(strutturaSportiva);
					incassoFrame.setLocationRelativeTo(Window.this);
					incassoFrame.setVisible(true);
				} catch (NullPointerException e2) {
					// NON SUCCEDE MAI
					e2.printStackTrace();
					JOptionPane.showMessageDialog(Window.this, e2.getMessage(), "StrutturaSportiva null.",
							JOptionPane.ERROR_MESSAGE);
				} catch (IllegalArgumentException e3) {
					// NON SUCCEDE MAI perchè il menuItem viene disabilitato
					// quando non ci sono stadi
					e3.printStackTrace();
					JOptionPane.showMessageDialog(Window.this, e3.getMessage(), "Nessuno stadio presente.",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});

		incassoMenu.add(incassoMenuItem);
		menuBar.add(incassoMenu);

		this.partitaTable = new PartitaTable(Mode.GESTORE, this.strutturaSportiva.getPartiteProgrammate(),
				this.strutturaSportiva);

		this.partitaTable.addMouseListener(new MouseAdapter() {

			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					new PartitePopupMenu().show(e.getComponent(), e.getX(), e.getY());
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					new PartitePopupMenu().show(e.getComponent(), e.getX(), e.getY());
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

	/**
	 * Classe per gestire le operazioni da eseguire all'apertura e alla chiusura
	 * della finestra.
	 * 
	 * @author Maurizio
	 */
	class MyWindowAdapter extends WindowAdapter {

		/**
		 * All'apertura della finestra viene eseguito il controllo per eliminare
		 * le prenotazioni scadute, che sarà ripetuto ad intervalli regolari.
		 */
		@Override
		public void windowOpened(WindowEvent windowevent) {
			System.out.println("OPENED " + new GregorianCalendar().getTime());

			new SwingWorker<Void, Void>() {
				@Override
				protected Void doInBackground() throws Exception {

					final TimerTask timerTask = new TimerTask() {
						@Override
						public void run() {
							Window.this.strutturaSportiva.cancellaPrenotazioniScadute();
							System.out.println("Controllate prenotazioni scadute " + new GregorianCalendar().getTime());
						}
					};

					final Timer timer = new Timer();
					final int MINUTI = 1;
					timer.schedule(timerTask, 0, 1000 * 60 * MINUTI);
					return null;
				}
			}.execute();
		}

		/**
		 * Alla chiusura della finestra viene salvata la
		 * {@link StrutturaSportiva} newl file.
		 */
		@Override
		public void windowClosing(WindowEvent paramWindowEvent) {
			System.out.println("CLOSING");

			new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					Boolean done = false;

					Window.this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Window.this.storeStrutturaSportiva();

					done = true;
					return done;
				}

				@Override
				protected void done() {
					try {
						if (get() == true) {
							Window.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							System.out.println("DONE");
							System.exit(0);
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}.execute();
		}
	}

	/**
	 * PopupMenu per prenotare, acquistare, aggiungere, rimuovere partite.
	 * 
	 * @author Maurizio
	 */
	class PartitePopupMenu extends JPopupMenu implements Serializable {

		public PartitePopupMenu() {
			super();

			switch (Window.this.mode) {
			case CLIENTE:

				this.add(new JLabel("  Dettagli"));
				this.prenota = new JMenuItem("Prenota");

				Partita partita = Window.this.partitaTable.getSelectedPartita();
				GregorianCalendar dataPartita = partita.getData();
				GregorianCalendar dataAttualePiu12Ore = new GregorianCalendar();
				dataAttualePiu12Ore.add(Calendar.HOUR_OF_DAY, 12);

				/*
				 * Disabilita le prenotazioni a partire da 12 ore prima della
				 * partita.
				 */
				if (dataAttualePiu12Ore.after(dataPartita)) {
					this.prenota.setEnabled(false);
				}

				if (strutturaSportiva.verificaPrenotazione((Cliente) Window.this.utente, partita)
						|| strutturaSportiva.verificaAcquisto((Cliente) Window.this.utente, partita)) {
					this.prenota.setEnabled(false);
				}

				this.completaPrenotazione = new JMenuItem("Completa prenotazione");

				if (!strutturaSportiva.verificaPrenotazione((Cliente) Window.this.utente,
						Window.this.partitaTable.getSelectedPartita())) {
					this.completaPrenotazione.setEnabled(false);
				} else {
					Prenotazione prenotazione = strutturaSportiva.getPrenotazioneCliente((Cliente) Window.this.utente,
							Window.this.partitaTable.getSelectedPartita());

					this.completaPrenotazione.addActionListener(new ActionListener() {

						@Override
						public void actionPerformed(ActionEvent e) {
							Partita partitaPrenotata = prenotazione.getPartita();
							partitaPrenotata.resetSeatStatus(prenotazione, SeatStatus.VENDUTO);

							strutturaSportiva.addAcquisto(new Acquisto(prenotazione, strutturaSportiva));
							prenotazione.getPosto().setStato(SeatStatus.VENDUTO);

							JOptionPane.showMessageDialog(Window.this.partitaTable,
									"Complimenti, la prenotazione per questa partita e' stata completata con successo.",
									"Prenotazione completata.", JOptionPane.INFORMATION_MESSAGE);
						}
					});
				}

				this.acquista = new JMenuItem("Acquista");

				/*
				 * Disabilita gli acquisti se la partita e' gia' iniziata.
				 */
				if (new GregorianCalendar().after(partita.getData())) {
					this.acquista.setEnabled(false);
				}

				if (strutturaSportiva.verificaPrenotazione((Cliente) Window.this.utente,
						Window.this.partitaTable.getSelectedPartita())
						|| strutturaSportiva.verificaAcquisto((Cliente) Window.this.utente,
								Window.this.partitaTable.getSelectedPartita())) {
					this.acquista.setEnabled(false);
				}

				this.acquista.addActionListener(new ActionListener() {
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
								Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));

								StadiumScrollPane stadiumScrollPane = new StadiumScrollPane(
										Window.this.strutturaSportiva, (Cliente) Window.this.utente,
										Window.this.partitaTable.getSelectedPartita(), StadiumMode.ACQUISTO);

								return stadiumScrollPane;
							}

							@Override
							protected void done() {
								// System.out.println(SwingUtilities.isEventDispatchThread());
								try {
									Window.this.tabbedPane.addTab("Stadio", get());
									Window.this.tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
									Window.this.tabbedPane.revalidate();
								} catch (InterruptedException e) {
									e.printStackTrace();
								} catch (ExecutionException e) {
									e.printStackTrace();
								}

								Window.this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							}

						}.execute();
					}
				});

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
								Window.this.tabbedPane.setCursor(new Cursor(Cursor.WAIT_CURSOR));
								StadiumScrollPane stadiumScrollPane = new StadiumScrollPane(
										Window.this.strutturaSportiva, (Cliente) Window.this.utente,
										Window.this.partitaTable.getSelectedPartita(), StadiumMode.PRENOTAZIONE);
								return stadiumScrollPane;
							}

							@Override
							protected void done() {
								// System.out.println(SwingUtilities.isEventDispatchThread());
								try {
									Window.this.tabbedPane.addTab("Stadio", get());
									Window.this.tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
									Window.this.tabbedPane.revalidate();
								} catch (InterruptedException e) {
									e.printStackTrace();
								} catch (ExecutionException e) {
									e.printStackTrace();
								}

								Window.this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
							}

						}.execute();
					}
				});

				this.addSeparator();
				this.add(prenota);
				this.add(completaPrenotazione);
				this.add(acquista);

				break;
			case GESTORE:
				this.addPartita = new JMenuItem("Aggiungi Partita");
				this.removePartita = new JMenuItem("Rimuovi Partita");

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
							Partita partita = partitaTable.getSelectedPartita();
							Window.this.strutturaSportiva.cancellaPrenotazioniAcquistiScontiPerPartita(partita);

							int modelIndex = partitaTable.convertRowIndexToModel(viewIndex);
							((PartitaTableModel) partitaTable.getModel()).removePartita(modelIndex);
						}
					}
				});

				this.add(addPartita);
				this.add(removePartita);
				break;
			default:
				break;
			}
		}

		private static final long serialVersionUID = 6108564986742304925L;
		private JMenuItem prenota, completaPrenotazione, acquista;
		private JMenuItem addPartita, removePartita;
	}

	/**
	 * PopupMenu per consentire il completamento e la cancellazione delle
	 * prenotazioni.
	 * 
	 * @author Maurizio
	 */

	class PrenotazioniPopupMenu extends JPopupMenu implements Serializable {

		public PrenotazioniPopupMenu() {
			super();

			this.completaPrenotazione = new JMenuItem("Completa Prenotazione");
			this.add(completaPrenotazione);
			this.completaPrenotazione.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int viewIndex = prenotazioniTable.getSelectedRow();

					if (viewIndex != -1) {
						Prenotazione prenotazione = prenotazioniTable.getSelectedPrenotazione();
						Partita partitaPrenotata = prenotazione.getPartita();
						partitaPrenotata.resetSeatStatus(prenotazione, SeatStatus.VENDUTO);

						int modelIndex = partitaTable.convertRowIndexToModel(viewIndex);
						((PrenotazioneTableModel) prenotazioniTable.getModel()).removePrenotazione(modelIndex);

						Window.this.strutturaSportiva.addAcquisto(new Acquisto(prenotazione, strutturaSportiva));
						prenotazione.getPosto().setStato(SeatStatus.VENDUTO);

						JOptionPane.showMessageDialog(Window.this.partitaTable,
								"Complimenti, la prenotazione per questa partita e' stata completata con successo.",
								"Prenotazione completata.", JOptionPane.INFORMATION_MESSAGE);
					}
				}
			});

			this.deletePrenotazioneItem = new JMenuItem("Canella Prenotazione");
			this.add(deletePrenotazioneItem);

			this.deletePrenotazioneItem.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent actionevent) {
					int viewIndex = prenotazioniTable.getSelectedRow();

					if (viewIndex != -1) {
						Prenotazione prenotazione = prenotazioniTable.getSelectedPrenotazione();
						Partita partitaPrenotata = prenotazione.getPartita();
						partitaPrenotata.resetSeatStatus(prenotazione, SeatStatus.LIBERO);

						int modelIndex = partitaTable.convertRowIndexToModel(viewIndex);
						((PrenotazioneTableModel) prenotazioniTable.getModel()).removePrenotazione(modelIndex);
						Window.this.strutturaSportiva.cancellaPrenotazione(prenotazione);
					}
				}
			});

		}

		private JMenuItem completaPrenotazione, deletePrenotazioneItem;
		private static final long serialVersionUID = -8528048458887315464L;
	}

	/**
	 * Classe contenente l'azione da eseguire per effettuare il logout.
	 * 
	 * @author Maurizio
	 */

	class LogoutAction extends AbstractAction implements Serializable {

		public LogoutAction() {
			super("Exit");
			putValue(MNEMONIC_KEY, KeyEvent.VK_E);
			putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
		}

		@Override
		public void actionPerformed(ActionEvent arg0) {
			new SwingWorker<Boolean, Void>() {

				@Override
				protected Boolean doInBackground() throws Exception {
					Boolean done = false;

					Window.this.setCursor(new Cursor(Cursor.WAIT_CURSOR));
					Window.this.storeStrutturaSportiva();

					done = true;
					return done;
				}

				@Override
				protected void done() {
					try {
						try {
							if (get() == true) {
								/*
								 * Rimuove la JMenuBar.
								 */
								Window.this.setJMenuBar(null);
								/*
								 * Rimuove tutti i componenti presenti.
								 */
								Window.this.mainPanel.removeAll();
								/*
								 * Aggiunge nuovamente l'IdentificationPanel.
								 */
								Window.this.mainPanel.add(Window.this.identificationPanel, BorderLayout.EAST);
								Window.this.revalidate();
							}
						} finally {
							Window.this.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch (ExecutionException e) {
						e.printStackTrace();
					}
				}
			}.execute();

		}

		private static final long serialVersionUID = 8289365721136717863L;
	}

	private static final long serialVersionUID = 5196150741171238114L;
	public static final int WIDTH = 1000, HEIGHT = 700;
	private JPanel mainPanel;
	private IdentificationPanel identificationPanel;
	private Utente utente;
	private Mode mode;
	private PrenotazioneTable prenotazioniTable;
	private AcquistoTable acquistiTabel;
	private PartitaTable partitaTable;
	private JScrollPane partitaTableScrollPane;
	private JPanel partitePanel;
	private ClosableTabbedPane tabbedPane;
	private String strutturaSportivaName;
	private StrutturaSportiva strutturaSportiva;
	private File strutturaSportiva_DB_File;
}
