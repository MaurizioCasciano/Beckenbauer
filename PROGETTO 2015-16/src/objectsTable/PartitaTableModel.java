package objectsTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import struttura.Mode;
import struttura.Partita;
import struttura.Squadra;
import struttura.Stadio;

public class PartitaTableModel extends RowObjectTableModel<Partita>implements Serializable {

	private static final long serialVersionUID = 6082073225853493069L;
	private static final String[] COLUMN_NAMES = { "Casa", "Trasferta", "Stadio", "Capienza Stadio", "Data" };
	private Mode mode;

	public PartitaTableModel(Mode mode) {
		super(COLUMN_NAMES, Partita.class);

		this.mode = mode;
		setColumnClass(0, Squadra.class);
		setColumnClass(1, Squadra.class);
		setColumnClass(2, Stadio.class);
		setColumnClass(3, Integer.class);
		setColumnClass(4, GregorianCalendar.class);
	}

	public PartitaTableModel(Mode mode, ArrayList<Partita> partite) {
		super(partite, COLUMN_NAMES, Partita.class);

		this.mode = mode;
		setColumnClass(0, Squadra.class);
		setColumnClass(1, Squadra.class);
		setColumnClass(2, Stadio.class);
		setColumnClass(3, Integer.class);
		setColumnClass(4, GregorianCalendar.class);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		int CAPIENZA_STADIO_COLUMN = 3;

		if (this.mode == Mode.CLIENTE) {
			return false;
		} else if (this.mode == Mode.GESTORE && column != CAPIENZA_STADIO_COLUMN) {
			return true;
		}

		return false;
	}

	public void addPartita(Partita p) {
		super.addRow(p);
	}

	/**
	 * Rimuove la riga (Partita), corrispondente all'indice dato in input, dal
	 * modello di questa tabella.
	 * 
	 * @param rowsIndex
	 *            L'indice della riga (Partita) da rimuovere dlla tabella.
	 */
	public void removePartita(int rowIndex) {
		super.removeRows(rowIndex);
	}

	/**
	 * Rimuove le righe (Partite) corrispondenti agli indici dati in input. Le
	 * ricghe comprese tra indice iniziale e finale (estremi inclusi) saranno
	 * rimosse.
	 * 
	 * @param start
	 *            L'indice della prima riga (Partita) da rimuovere.
	 * @param end
	 *            L'indice dell'ultima riga (Partita) da rimuovere.
	 */
	public void removePartiteRange(int start, int end) {
		super.removeRowRange(start, end);
	}

	public Partita getPartita(int row) {
		return super.getRow(row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Partita partita = getRow(rowIndex);

		switch (columnIndex) {
		case 0:
			return partita.getSquadraInCasa();
		case 1:
			return partita.getSquadraInTrasferta();
		case 2:
			return partita.getStadio();
		case 3:
			return partita.getStadio().getCapienzaEffettiva();
		case 4:
			return partita.getData();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Partita partita = getRow(rowIndex);

		switch (columnIndex) {
		case 0:
			partita.setSquadraInCasa((Squadra) value);
			break;
		case 1:
			partita.setSquadraInTrasferta((Squadra) value);
			break;
		case 2:
			partita.setStadio((Stadio) value);
			setValueAt(((Stadio) value).getCapienzaEffettiva(), rowIndex, columnIndex + 1);
			break;
		case 3:
			partita.getStadio().setCapienzaStadio((int) value);
			break;
		case 4:
			partita.setData((GregorianCalendar) value);
			break;
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
