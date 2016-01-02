package objectsTable;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PartitaTableModel extends RowObjectTableModel<Partita> {

	private static final long serialVersionUID = 6082073225853493069L;
	private static final String[] COLUMN_NAMES = { "Home", "Away", "Stadium", "Date" };

	public PartitaTableModel() {
		super(new ArrayList<>(Arrays.asList(COLUMN_NAMES)), Partita.class);

		setColumnClass(0, String.class);
		setColumnClass(1, String.class);
		setColumnClass(2, String.class);
		setColumnClass(3, String.class);
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Partita partita = getRow(rowIndex);

		switch (columnIndex) {
		case 0:
			return partita.getHome();
		case 1:
			return partita.getAway();
		case 2:
			return partita.getStadium();
		case 3:
			return partita.getFormattedDate();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		Partita partita = getRow(rowIndex);

		switch (columnIndex) {
		case 0:
			partita.setHome((String) value);
			break;
		case 1:
			partita.setAway((String) value);
			break;
		case 2:
			partita.setStadium((String) value);
			break;
		case 3:
			partita.setFormattedDate((String) value);
			break;
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public static void main(String[] args) {
		PartitaTableModel model = new PartitaTableModel();

		model.addRow(new Partita("Milan", "Inter", "San Siro", new GregorianCalendar()));
		model.addRow(new Partita("Juventus", "Torino", "Juventus Stadium", new GregorianCalendar(2016, 0, 24)));

		JTable table = new JTable(model);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionBackground(Color.GREEN);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					System.out.println(model.getRow(table.getSelectedRow()));
				}
			}
		});

		JScrollPane scrollPane = new JScrollPane(table);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(scrollPane);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
