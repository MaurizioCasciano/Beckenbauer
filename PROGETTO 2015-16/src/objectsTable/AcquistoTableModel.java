package objectsTable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.JTable;

import struttura.Acquisto;
import struttura.Partita;
import struttura.Posto;
import struttura.Settore;
import struttura.Stadio;
import user.Cliente;

/**
 * Classe che estende {@link RowObjectTableModel}, usata per gestire una lista
 * di {@link Acquisto} all'interno di una {@link JTable}.
 * 
 * @author Maurizio
 */
public class AcquistoTableModel extends RowObjectTableModel<Acquisto> implements Serializable {

	private static final long serialVersionUID = 1934337865637284431L;
	private static final String[] COLUMN_NAMES = { "Cliente", "Partita", "Data Partita", "Stadio", "Settore", "Posto",
			"Data Acquisto", "Prezzo" };

	protected AcquistoTableModel() {
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

	protected AcquistoTableModel(ArrayList<Acquisto> acquisti) {
		super(acquisti, COLUMN_NAMES);

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
		case 7:
			return acquisto.getBiglietto().getPrezzo();
		default:
			return null;
		}
	}

	@Override
	public void replaceData(ArrayList<Acquisto> modelData) {
		super.replaceData(modelData);
	}
}
