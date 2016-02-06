package objectsTable;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import objectsTable.renderers.ClienteCellRenderer;
import objectsTable.renderers.GregorianCalendarCellRenderer;
import objectsTable.renderers.PartitaCellRenderer;
import objectsTable.renderers.PostoCellRenderer;
import objectsTable.renderers.PrezzoCellRenderer;
import objectsTable.renderers.SettoreCellRenderer;
import objectsTable.renderers.StadioCellRenderer;
import struttura.Partita;
import struttura.Posto;
import struttura.Prenotazione;
import struttura.Settore;
import struttura.Stadio;
import user.Cliente;

/**
 * Classe che estende {@link JTable}, usata per permettere la visualizzazione di
 * una lista di {@link Prenotazione}.
 * 
 * @author Maurizio
 */
public class PrenotazioneTable extends JTable implements Serializable {

	public PrenotazioneTable() {
		super(new PrenotazioneTableModel());
		this.init();
	}

	public PrenotazioneTable(ArrayList<Prenotazione> prenotazioni) {
		super(new PrenotazioneTableModel(prenotazioni));
		this.init();
	}

	private void init() {
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.setColumnSelectionAllowed(false);
		this.setAutoResizeMode(JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
		this.setSelectionBackground(Color.GREEN);
		this.setRowHeight(this.getRowHeight() + 5);
		this.addMouseListener(new RightClickRowSelectionListener());

		this.setDefaultRenderer(Cliente.class, new ClienteCellRenderer());
		this.setDefaultRenderer(Partita.class, new PartitaCellRenderer());
		this.setDefaultRenderer(Stadio.class, new StadioCellRenderer());
		this.setDefaultRenderer(Settore.class, new SettoreCellRenderer());
		this.setDefaultRenderer(Posto.class, new PostoCellRenderer());
		this.setDefaultRenderer(GregorianCalendar.class, new GregorianCalendarCellRenderer());
		this.setDefaultRenderer(Double.class, new PrezzoCellRenderer());
	}

	public Prenotazione getSelectedPrenotazione() {
		// l'indice della riga selezionata nella parte visiva
		int viewIndex = this.getSelectedRow();
		// System.out.println("viewIndex = " + viewIndex);

		// (INDISPENSABILE PER POTER UTILIZZARE SORTING e FILTERING)
		int modelIndex = this.convertRowIndexToModel(viewIndex);
		// System.out.println("modelIndex =" + modelIndex);

		return ((PrenotazioneTableModel) this.getModel()).getAcquisto(modelIndex);
	}

	private static final long serialVersionUID = 8865381633135165349L;
}
