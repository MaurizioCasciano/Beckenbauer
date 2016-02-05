package graphics;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import struttura.Acquisto;
import struttura.AlreadyExistsObjectException;
import struttura.Biglietto;
import struttura.Partita;
import struttura.Posto;
import struttura.PostoIndisponibileException;
import struttura.PrenotationNotAllowed;
import struttura.Prenotazione;
import struttura.SeatStatus;
import struttura.Settore;
import struttura.StrutturaSportiva;
import user.Cliente;

/**
 * Classe che estende {@link JButton}, utile per rappresentare graficamente il
 * posto di uno Stadio.
 * 
 * @author Maurizio
 */
public class StadiumSeatButton extends JButton implements Serializable {

	/**
	 * Costruisce un nuovo StadiumSeatButton.
	 * 
	 * @param strutturaSportiva
	 *            La StrutturaSportiva che gestisce acquisti/prenotazioni.
	 * @param cliente
	 *            Il cliente che accede alla visualizzazione dello stadio.
	 * @param partita
	 *            La partita da prenotare/acquistare.
	 * @param posto
	 *            Il posto da rappresentare graficamente.
	 * @param stadiumMode
	 *            La modalità di visualizzazione dello stadio in cui si trova il
	 *            posto.
	 * @author Maurizio
	 */
	public StadiumSeatButton(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita, Posto posto,
			StadiumMode stadiumMode) {
		super();
		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;
		this.posto = posto;
		this.settore = this.posto.getSettore();
		this.numeroFila = this.posto.getNumeroFila();
		this.numeroPosto = this.posto.getNumeroPosto();
		this.stadiumMode = stadiumMode;

		this.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseEntered(MouseEvent e) {
				setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

		});

		this.posto.addPropertyChangeListener(new PropertyChangeListener() {

			/*
			 * Aggiorna il colore del posto sulla sagoma grafica al cambio dello
			 * stato del posto corrispondente.
			 */
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				StadiumSeatButton.this.setBackground(posto.getStato().getColor());
				StadiumSeatButton.this.setToolTipText(
						"Fila: " + numeroFila + "  " + "Posto: " + numeroPosto + " " + "Stato: " + posto.getStato());
				StadiumSeatButton.this.repaint();
			}
		});

		this.setToolTipText(
				"Fila: " + numeroFila + "  " + "Posto: " + numeroPosto + " " + "Stato: " + this.posto.getStato());
		this.setBorder(null);
		this.setOpaque(true);
		this.setBackground(this.posto.getStato().getColor());
		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				switch (stadiumMode) {
				case PRENOTAZIONE:
					try {
						if (isIndisponibile()) {
							throw new PostoIndisponibileException();
						}

						if (strutturaSportiva.verificaAcquisto(cliente, partita)) {
							throw new PrenotationNotAllowed();
						}

						strutturaSportiva.addPrenotazione(new Prenotazione(new GregorianCalendar(),
								new Biglietto(strutturaSportiva, cliente, partita, settore, numeroFila, numeroPosto)));

						posto.setStato(SeatStatus.PRENOTATO);
						JOptionPane.showMessageDialog(null,
								"Complimenti, prenotazione aggiunta correttamente.\nN.B.: Si ricorda che la prenotazione scade 12 ore prima dell'inizio della partita.\nIn assenza di un acquisto ad essa collegato, la prenotazione, verra' cancellata automaticamente.",
								"Prenotazione effettuata.", JOptionPane.INFORMATION_MESSAGE);

					} catch (AlreadyExistsObjectException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "Prenotazione già presente",
								JOptionPane.ERROR_MESSAGE);
					} catch (PostoIndisponibileException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), e1.getClass().getSimpleName(),
								JOptionPane.ERROR_MESSAGE);
					} catch (PrenotationNotAllowed e3) {
						JOptionPane.showMessageDialog(null, e3.getMessage(), e3.getClass().getSimpleName(),
								JOptionPane.ERROR_MESSAGE);
					}
					break;

				case ACQUISTO:
					try {
						if (isIndisponibile()) {
							throw new PostoIndisponibileException();
						}

						if (!strutturaSportiva.verificaPrenotazione(cliente, partita)) {
							strutturaSportiva.addAcquisto(new Acquisto(cliente, partita, settore, numeroFila,
									numeroPosto, strutturaSportiva));

							posto.setStato(SeatStatus.VENDUTO);
							// setBackground(posto.getStato().getColor());
							// StadiumSeatButton.this.setToolTipText("Fila: " +
							// numeroFila + " " + "Posto: " + numeroPosto
							// + " " + "Stato: " + posto.getStato());
							// repaint();

							JOptionPane.showMessageDialog(null, "Complimenti, acquisto aggiunto correttamente.",
									"Prenotazione presente", JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(StadiumSeatButton.this,
									"Spiacenti, è presente una sua prenotazione per questa partita nel sistema. Completarla.",
									"Prenotazione già presente", JOptionPane.ERROR_MESSAGE);
						}
					} catch (AlreadyExistsObjectException e2) {
						JOptionPane.showMessageDialog(null, e2.getMessage(), "Acquisto già presente Exception.",
								JOptionPane.ERROR_MESSAGE);
					} catch (PostoIndisponibileException e1) {
						JOptionPane.showMessageDialog(null, e1.getMessage(), "Posto Indisponibile Exception",
								JOptionPane.ERROR_MESSAGE);
					}
					break;
				default:
					break;
				}
			}
		});
	}

	/**
	 * Controlla se il posto è indisponibile o meno.
	 * 
	 * @return true se il posto è indisponibile, false altrimenti.
	 * @author Maurizio
	 */
	public boolean isIndisponibile() {
		if (this.posto.getStato() != SeatStatus.LIBERO) {
			return true;
		}
		return false;
	}

	/**
	 * Restituisce il cliente che deve prenotare/acquistare un biglietto.
	 * 
	 * @return Il cliente che deve prenotare/acquistare un biglietto.
	 * @author Maurizio
	 */
	public Cliente getCliente() {
		return this.cliente;
	}

	/**
	 * Restituisce la partita da prenotare/acquistare.
	 * 
	 * @return La partita da prenotare/acquistare.
	 * @author Maurizio
	 */
	public Partita getPartita() {
		return this.partita;
	}

	/**
	 * Restituisce il numero di fila di questo Posto.
	 * 
	 * @return il numero di fila di questo Posto.
	 */
	public int getNumeroFila() {
		return numeroFila;
	}

	/**
	 * Il numero di questo Posto.
	 * 
	 * @return Il numero di questo Posto.
	 */
	public int getNumeroPosto() {
		return numeroPosto;
	}

	/**
	 * Restituisce la strutturaSportiva che gestisce le prenotazioni e gli
	 * acquisti.
	 * 
	 * @return La strutturaSportiva che gestisce le prenotazioni e gli acquisti.
	 * @author Maurizio
	 */
	public StrutturaSportiva getStrutturaSportiva() {
		return this.strutturaSportiva;
	}

	/**
	 * Restituisce la modalità di accesso con cui si chiede di visualizzare il
	 * posto.
	 * 
	 * @return La modalità di accesso con cui si chiede di visualizzare il
	 *         posto.
	 * @author Maurizio
	 */
	public StadiumMode getStadiumMode() {
		return stadiumMode;
	}

	private static final long serialVersionUID = 5537467864342318585L;
	private StrutturaSportiva strutturaSportiva;
	private Cliente cliente;
	private Partita partita;
	private Posto posto;
	private Settore settore;
	private int numeroFila, numeroPosto;
	private StadiumMode stadiumMode;
}
