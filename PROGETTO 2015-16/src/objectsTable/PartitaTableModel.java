package objectsTable;

import java.util.ArrayList;
import java.util.GregorianCalendar;

import struttura.Partita;
import struttura.Squadra;
import struttura.Stadio;

public class PartitaTableModel extends RowObjectTableModel<Partita> {

	private static final long serialVersionUID = 6082073225853493069L;
	private static final String[] COLUMN_NAMES = { "Casa", "Trasferta", "Stadio", "Data" };

	public PartitaTableModel() {
		super(COLUMN_NAMES, Partita.class);

		setColumnClass(0, Squadra.class);
		setColumnClass(1, Squadra.class);
		setColumnClass(2, Stadio.class);
		setColumnClass(3, GregorianCalendar.class);
	}

	public PartitaTableModel(ArrayList<Partita> partite) {
		super(partite, COLUMN_NAMES, Partita.class);

		setColumnClass(0, Squadra.class);
		setColumnClass(1, Squadra.class);
		setColumnClass(2, Stadio.class);
		setColumnClass(3, GregorianCalendar.class);
	}

	public void addPartita(Partita p) {
		super.addRow(p);
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
			break;
		case 3:
			partita.setData((GregorianCalendar) value);
			break;
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}
}
