package objectsTable;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;

public class PartitaTableModel extends RowObjectTableModel<Partita> {

	private static final long serialVersionUID = 6082073225853493069L;
	private static final String[] COLUMN_NAMES = { "Home", "Away", "Stadium", "Date" };

	public PartitaTableModel() {
		super(new ArrayList<>(Arrays.asList(COLUMN_NAMES)), Partita.class);

		setColumnClass(0, String.class);
		setColumnClass(1, String.class);
		setColumnClass(2, String.class);
		setColumnClass(3, GregorianCalendar.class);
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
			return partita.getDate();
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
			partita.setDate((GregorianCalendar) value);
			break;
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public static void main(String[] args) {
		PartitaTableModel model = new PartitaTableModel();

		JTable table = new JTable(model) {
			private static final long serialVersionUID = 2906779662014543416L;
			JTable myTable = this;

			@Override
			public Component prepareEditor(TableCellEditor editor, int row, int column) {
				Component c = super.prepareEditor(editor, row, column);

				/*
				 * Imposta le proprietà comuni ai componenti dei vari editor. In
				 * questo modo tutti i componenti avranno lo stesso aspetto
				 * grafico.
				 * 
				 * In particolare, durante l'editing, avranno lo stesso colore
				 * di background e lo stesso bordo.
				 */
				c.setBackground(myTable.getBackground());
				((JComponent) c).setBorder(new LineBorder(Color.BLACK));

				return c;
			}

		};

		model.addRow(new Partita("Milan", "Inter", "San Siro", new GregorianCalendar()));
		model.addRow(new Partita("Juventus", "Torino", "Juventus Stadium", new GregorianCalendar(2016, 0, 24)));
		model.addRow(new Partita("Lazio", "Napoli", "Olimpico Roma", new GregorianCalendar(2016, 2, 20)));

		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setColumnSelectionAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		table.setSelectionBackground(Color.GREEN);

		//table.setAutoCreateRowSorter(true);
		table.setDefaultRenderer(GregorianCalendar.class, new GregorianCalendarCellRenderer());

		table.setDefaultEditor(GregorianCalendar.class, new GregorianCalendarCellEditor());

		table.setRowSorter(new TableRowSorter<PartitaTableModel>(model));

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					System.out.println(model.getRow(table.getSelectedRow()));
				}
			}
		});

		table.getTableHeader().addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int col = table.columnAtPoint(e.getPoint());
				String name = table.getColumnName(col);
				System.out.println("Column index selected " + col + " " + name);
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
