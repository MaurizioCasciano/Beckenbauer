package objectsTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.util.GregorianCalendar;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableRowSorter;

public class PartitaTable extends JTable {

	public PartitaTable() {
		super(new PartitaTableModel());

		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setColumnSelectionAllowed(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
		this.setSelectionBackground(Color.GREEN);
		this.setRowSorter(new TableRowSorter<PartitaTableModel>((PartitaTableModel) getModel()));
		this.setDefaultRenderer(GregorianCalendar.class, new GregorianCalendarCellRenderer());
		this.setDefaultEditor(GregorianCalendar.class, new GregorianCalendarCellEditor());
	}

	@Override
	public Component prepareEditor(TableCellEditor editor, int row, int column) {
		Component c = super.prepareEditor(editor, row, column);

		/*
		 * Imposta le proprietà comuni ai componenti dei vari editor. In questo
		 * modo tutti i componenti avranno lo stesso aspetto grafico.
		 * 
		 * In particolare, durante l'editing, avranno lo stesso colore di
		 * background e lo stesso bordo.
		 */
		c.setBackground(this.getBackground());
		((JComponent) c).setBorder(new LineBorder(Color.BLACK));

		return c;
	}

	public void addPartita(Partita p) {
		((PartitaTableModel) this.getModel()).addRow(p);
	}

	private static final long serialVersionUID = 2097698111433165339L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Partite");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PartitaTable partite = new PartitaTable();
		partite.addPartita(new Partita("Milan", "Inter", "San Siro", new GregorianCalendar(2016, 11, 20)));
		partite.addPartita(new Partita("Roma", "Lazio", "Olimpico di Roma", new GregorianCalendar(2016, 11, 21)));
		partite.addPartita(new Partita("Juventus", "Torino", "Juventus Stadium", new GregorianCalendar(2016, 11, 22)));

		JScrollPane scrollPane = new JScrollPane(partite);
		frame.add(scrollPane, BorderLayout.CENTER);

		/*
		 * another way of adding JTable and his header
		 * frame.add(partite.getTableHeader(), BorderLayout.NORTH);
		 * frame.add(partite, BorderLayout.CENTER);
		 */

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
