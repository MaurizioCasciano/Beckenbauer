package objectsTable.filter;

import java.io.Serializable;

import javax.swing.JTable;
import javax.swing.RowFilter;

import objectsTable.PartitaTableModel;
import struttura.Partita;
import struttura.filters.Filter;

/**
 * Classe che estende {@link RowFilter}, utilizzata per filtrare le righe della
 * {@link JTable}.
 */
public class PartitaRowFilter extends RowFilter<PartitaTableModel, Integer>implements Serializable {

	/**
	 * Crea un nuovo PartitaRowFilter con il filtro passato in input.
	 * 
	 * @param filter
	 *            Il filtro da applicare.
	 * @author Maurizio
	 */
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
