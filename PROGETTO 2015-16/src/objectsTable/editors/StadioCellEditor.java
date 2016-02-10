package objectsTable.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

import combo.renderers.StadioComboRenderer;
import struttura.Stadio;
import struttura.StrutturaSportiva;

/**
 * Classe che estende {@link AbstractCellEditor}, usatata per rappresentare
 * un'oggetto {@link Stadio} in una {@link JTable} tramite una
 * {@link JTextField}.
 * 
 * @author Maurizio
 */
public class StadioCellEditor extends AbstractCellEditor implements TableCellEditor, Serializable {

	public StadioCellEditor(StrutturaSportiva strutturaSportiva) {
		super();
		this.strutturaSportiva = strutturaSportiva;
		ArrayList<Stadio> stadi = this.strutturaSportiva.getStadi();
		stadi.sort(new Comparator<Stadio>() {

			@Override
			public int compare(Stadio s1, Stadio s2) {
				return s1.getNome().compareTo(s2.getNome());
			}
		});
		this.stadi = new JComboBox<>(this.strutturaSportiva.getStadi().toArray(new Stadio[1]));
	}

	@Override
	public boolean isCellEditable(EventObject e) {
		final int DOUBLE_CLICK = 2;

		if (e instanceof MouseEvent) {
			return ((MouseEvent) e).getClickCount() == DOUBLE_CLICK;
		} else {
			return false;
		}
	}

	@Override
	public Object getCellEditorValue() {
		return this.stadi.getSelectedItem();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Stadio stadio = (Stadio) value;

		this.stadi = new JComboBox<>(this.strutturaSportiva.getStadi().toArray(new Stadio[1]));
		this.stadi.setRenderer(new StadioComboRenderer());
		this.stadi.setSelectedItem(stadio);

		return this.stadi;
	}

	private static final long serialVersionUID = 8235516631777416016L;
	private StrutturaSportiva strutturaSportiva;
	private JComboBox<Stadio> stadi;
}
