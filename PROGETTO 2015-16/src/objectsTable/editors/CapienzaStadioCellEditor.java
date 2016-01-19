/**
 * 
 */
package objectsTable.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.SpinnerNumberModel;
import javax.swing.table.TableCellEditor;

/**
 * 
 * @author Maurizio
 */
public class CapienzaStadioCellEditor extends AbstractCellEditor implements TableCellEditor, Serializable {

	public CapienzaStadioCellEditor() {
		super();
		this.capienzaSpinner = new JSpinner(new SpinnerNumberModel(30000, 30000, 200000, 1000));
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
		return this.capienzaSpinner.getValue();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		int capienza = (int) value;

		this.capienzaSpinner.setValue(capienza);

		if (isSelected) {
			this.capienzaSpinner.setForeground(table.getSelectionForeground());
			this.capienzaSpinner.setBackground(table.getSelectionBackground());
		} else {
			this.capienzaSpinner.setForeground(table.getForeground());
			this.capienzaSpinner.setBackground(table.getBackground());
		}

		return this.capienzaSpinner;
	}

	private static final long serialVersionUID = 5062661614610111590L;
	private JSpinner capienzaSpinner;
}
