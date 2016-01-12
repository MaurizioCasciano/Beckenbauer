package objectsTable.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import objectsTable.utilities.ObjectTextField;
import struttura.Stadio;

public class StadioCellEditor extends AbstractCellEditor implements TableCellEditor, Serializable {

	public StadioCellEditor() {
		super();
		this.stadioTextField = new ObjectTextField<>();
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

		String nuovoNome = this.stadioTextField.getText();
		this.stadioTextField.getObject().setNome(nuovoNome);
		return this.stadioTextField.getObject();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
		Stadio stadio = (Stadio) value;

		// il testo visualizzato durante l'editing
		this.stadioTextField.setText(stadio.getNome());
		this.stadioTextField.setObject(stadio);

		if (isSelected) {
			this.stadioTextField.setForeground(table.getSelectionForeground());
			this.stadioTextField.setBackground(table.getSelectionBackground());
		} else {
			this.stadioTextField.setForeground(table.getForeground());
			this.stadioTextField.setBackground(table.getBackground());
		}

		return this.stadioTextField;
	}

	private static final long serialVersionUID = 8235516631777416016L;
	private ObjectTextField<Stadio> stadioTextField;
}
