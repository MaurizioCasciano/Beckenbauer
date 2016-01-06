package objectsTable;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
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

import org.jdesktop.swingx.JXDatePicker;

import objectsTable.editors.GregorianCalendarDatePickerCellEditor;
import objectsTable.editors.SquadraCellEditor;
import objectsTable.editors.StadioCellEditor;
import objectsTable.filter.PartitaRowFilter;
import objectsTable.renderers.GregorianCalendarDatePickerCellRenderer;
import objectsTable.renderers.SquadraCellRenderer;
import objectsTable.renderers.StadioCellRenderer;
import struttura.Partita;
import struttura.Squadra;
import struttura.Stadio;
import struttura.filters.MatchNotYetStartedFilter;

public class PartitaTable extends JTable {

	public PartitaTable() {
		super(new PartitaTableModel());
		this.init();
	}

	public PartitaTable(ArrayList<Partita> partite) {
		super(new PartitaTableModel(partite));
		this.init();
	}

	private void init() {
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setColumnSelectionAllowed(false);
		this.setAutoCreateColumnsFromModel(true);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		this.setSelectionBackground(Color.GREEN);
		this.getColumnModel().getColumn(3).setMinWidth(90);

		this.setCellRenderers();
		this.setCellEditors();

		final TableRowSorter<PartitaTableModel> sorter = new TableRowSorter<PartitaTableModel>(
				(PartitaTableModel) getModel());
		this.setRowSorter(sorter);

		sorter.setRowFilter(new PartitaRowFilter(new MatchNotYetStartedFilter()));

		this.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					// System.out.println(getSelectedPartita());
				}
			}
		});
	}

	/**
	 * Aggiunge i renderers per le celle di questa tabella.
	 */
	private void setCellRenderers() {
		this.setDefaultRenderer(Squadra.class, new SquadraCellRenderer());
		this.setDefaultRenderer(Stadio.class, new StadioCellRenderer());
		// this.setDefaultRenderer(GregorianCalendar.class, new
		// GregorianCalendarCellRenderer());
		this.setDefaultRenderer(GregorianCalendar.class, new GregorianCalendarDatePickerCellRenderer());
	}

	/**
	 * Aggiunge gli editors per le celle di questa tabella.
	 */
	private void setCellEditors() {
		this.setDefaultEditor(Squadra.class, new SquadraCellEditor());
		this.setDefaultEditor(Stadio.class, new StadioCellEditor());
		// this.setDefaultEditor(GregorianCalendar.class, new
		// GregorianCalendarCellEditor());
		this.setDefaultEditor(GregorianCalendar.class, new GregorianCalendarDatePickerCellEditor());
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

		// System.out.println(this);

		if (c instanceof JXDatePicker) {
			((JXDatePicker) c).getComponent(0).setBackground(Color.WHITE);
			((JComponent) ((JXDatePicker) c).getComponent(0)).setBorder(new LineBorder(Color.BLACK));
		} else {
			c.setBackground(Color.WHITE);
			((JComponent) c).setBorder(new LineBorder(Color.BLACK));
		}

		return c;
	}

	public void addPartita(Partita p) {
		((PartitaTableModel) this.getModel()).addRow(p);
	}

	public Partita getSelectedPartita() {
		// l'indice della riga selezionata nella parte visiva
		int viewIndex = this.getSelectedRow();
		//System.out.println("viewIndex = " + viewIndex);

		if (viewIndex == -1) {
			viewIndex = 0;
		}

		// (INDISPENSABILE PER POTER UTILIZZARE SORTING e FILTERING)
		int modelIndex = this.convertRowIndexToModel(viewIndex);
		//System.out.println("modelIndex =" + modelIndex);

		return ((PartitaTableModel) this.getModel()).getPartita(modelIndex);
	}

	private static final long serialVersionUID = 2097698111433165339L;

	public static void main(String[] args) {
		JFrame frame = new JFrame("Partite");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		PartitaTable partite = new PartitaTable();
		partite.addPartita(new Partita(new Squadra("Milan"), new Squadra("Inter"), new Stadio("San Siro", 81277),
				new GregorianCalendar(2016, Calendar.JANUARY, 11, 20, 45)));
		partite.addPartita(new Partita(new Squadra("Roma"), new Squadra("Lazio"), new Stadio("Stadio Olimpico", 73261),
				new GregorianCalendar(2016, Calendar.JANUARY, 4, 24, 00)));
		partite.addPartita(new Partita(new Squadra("Juventus"), new Squadra("Torino"),
				new Stadio("Juventus Stadium", 41475), new GregorianCalendar(2016, Calendar.JANUARY, 22)));

		JScrollPane scrollPane = new JScrollPane(partite);
		frame.add(scrollPane, BorderLayout.CENTER);

		/*
		 * another way of adding JTable and his header
		 * frame.add(partite.getTableHeader(), BorderLayout.NORTH);
		 * frame.add(partite, BorderLayout.CENTER);
		 */

		JButton button = new JButton("Show info");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/*
				 * int viewIndex = partite.getSelectedRow();
				 * 
				 * System.out.println("viewIndex = " + viewIndex);
				 * 
				 * int modelIndex =
				 * partite.getRowSorter().convertRowIndexToModel(viewIndex);
				 * System.out.println("modelIndex =" + modelIndex);
				 */

				System.out.println(partite.getSelectedPartita());
			}
		});

		frame.add(button, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		Date data = new Date();
		System.out.println(data);
	}
}
