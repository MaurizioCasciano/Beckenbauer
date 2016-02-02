package objectsTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import struttura.Partita;
import struttura.Posto;
import struttura.Prenotazione;
import struttura.Settore;
import struttura.Stadio;
import user.Cliente;

/**
 * Classe che estende {@link RowObjectTableModel}, usata per permettere la
 * gestione di una lista di {@link Prenotazione} in una {@link JTable}.
 * 
 * @author Maurizio
 */
public class PrenotazioneTableModel extends RowObjectTableModel<Prenotazione> implements Serializable {

	private static final long serialVersionUID = 2064556786482215828L;
	private static final String[] COLUMN_NAMES = { "Cliente", "Partita", "Data Partita", "Stadio", "Settore", "Posto",
			"Data Prenotazione", "Prezzo" };

	protected PrenotazioneTableModel() {
		super(COLUMN_NAMES);

		setColumnClass(0, Cliente.class);
		setColumnClass(1, Partita.class);
		setColumnClass(2, GregorianCalendar.class);
		setColumnClass(3, Stadio.class);
		setColumnClass(4, Settore.class);
		setColumnClass(5, Posto.class);
		setColumnClass(6, GregorianCalendar.class);
		setColumnClass(7, Double.class);
	}

	protected PrenotazioneTableModel(ArrayList<Prenotazione> prenotazioni) {
		super(prenotazioni, COLUMN_NAMES);

		setColumnClass(0, Cliente.class);
		setColumnClass(1, Partita.class);
		setColumnClass(2, GregorianCalendar.class);
		setColumnClass(3, Stadio.class);
		setColumnClass(4, Settore.class);
		setColumnClass(5, Posto.class);
		setColumnClass(6, GregorianCalendar.class);
		setColumnClass(7, Double.class);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void addPrenotazione(Prenotazione p) {
		super.addRow(p);
	}

	public void removePrenotazione(int rowIndex) {
		super.removeRows(rowIndex);
	}

	public void removePrenotazioniRange(int start, int end) {
		super.removeRowRange(start, end);
	}

	public Prenotazione getAcquisto(int row) {
		return super.getRow(row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Prenotazione prenotazione = getRow(rowIndex);

		switch (columnIndex) {
		case 0:
			return prenotazione.getCliente();
		case 1:
			return prenotazione.getPartita();
		case 2:
			return prenotazione.getPartita().getData();
		case 3:
			return prenotazione.getStadio();
		case 4:
			return prenotazione.getSettore();
		case 5:
			return prenotazione.getPosto();
		case 6:
			return prenotazione.getDataPrenotazione();
		case 7:
			return prenotazione.getBigliettoPrenotato().getPrezzo();
		default:
			return null;
		}
	}

	@Override
	public void replaceData(ArrayList<Prenotazione> modelData) {
		super.replaceData(modelData);
	}

}
