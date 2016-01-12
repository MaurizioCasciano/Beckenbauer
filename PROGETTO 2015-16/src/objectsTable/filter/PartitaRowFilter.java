package objectsTable.filter;

import java.io.Serializable;

import javax.swing.RowFilter;

import objectsTable.PartitaTableModel;
import struttura.Partita;
import struttura.filters.Filter;

public class PartitaRowFilter extends RowFilter<PartitaTableModel, Integer>implements Serializable {

	public PartitaRowFilter(Filter filter) {
		super();
		this.myFilter = filter;
	}

	@Override
	public boolean include(Entry<? extends PartitaTableModel, ? extends Integer> entry) {

		PartitaTableModel partitaTableModel = entry.getModel();

		Partita partita = partitaTableModel.getPartita(entry.getIdentifier());

		return myFilter.accept(partita);
	}

	private static final long serialVersionUID = 6344789223652019366L;
	private Filter myFilter;
}
