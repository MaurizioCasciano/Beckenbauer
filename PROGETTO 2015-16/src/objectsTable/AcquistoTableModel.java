package objectsTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import struttura.Acquisto;
import struttura.Partita;
import struttura.Posto;
import struttura.Settore;
import struttura.Stadio;
import user.Cliente;

/**
 * 
 * @author Maurizio
 */
public class AcquistoTableModel extends RowObjectTableModel<Acquisto>implements Serializable {

	private static final long serialVersionUID = 1934337865637284431L;
	private static final String[] COLUMN_NAMES = {"Cliente", "Partita", "Data Partita", "Stadio", "Settore", "Posto",
			"Data Acquisto" };

	/**
	 * @param columnNames
	 * @param rowClass
	 * @author Maurizio
	 */
	protected AcquistoTableModel() {
		super(COLUMN_NAMES);

		setColumnClass(0, Cliente.class);
		setColumnClass(1, Partita.class);
		setColumnClass(2, GregorianCalendar.class);
		setColumnClass(3, Stadio.class);
		setColumnClass(4, Settore.class);
		setColumnClass(5, Posto.class);
		setColumnClass(6, GregorianCalendar.class);
	}

	protected AcquistoTableModel(ArrayList<Acquisto> acquisti) {
		super(acquisti, COLUMN_NAMES);

		setColumnClass(0, Cliente.class);
		setColumnClass(1, Partita.class);
		setColumnClass(2, GregorianCalendar.class);
		setColumnClass(3, Stadio.class);
		setColumnClass(4, Settore.class);
		setColumnClass(5, Posto.class);
		setColumnClass(6, GregorianCalendar.class);
	}

	@Override
	public boolean isCellEditable(int row, int column) {
		return false;
	}

	public void addAcquisto(Acquisto a) {
		super.addRow(a);
	}

	public void removeAcquisto(int rowIndex) {
		super.removeRows(rowIndex);
	}

	public void removeAcquistiRange(int start, int end) {
		super.removeRowRange(start, end);
	}

	public Acquisto getAcquisto(int row) {
		return super.getRow(row);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Acquisto acquisto = getRow(rowIndex);

		switch (columnIndex) {
		case 0:
			return acquisto.getCliente();
		case 1:
			return acquisto.getPartita();
		case 2:
			return acquisto.getPartita().getData();
		case 3:
			return acquisto.getStadio();
		case 4:
			return acquisto.getSettore();
		case 5:
			return acquisto.getPosto();
		case 6:
			return acquisto.getDataAcquisto();
		default:
			return null;
		}
	}
}
