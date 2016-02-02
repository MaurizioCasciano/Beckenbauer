package objectsTable.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.EventObject;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;
import objectsTable.utilities.ObjectTextField;
import struttura.Squadra;
/**
 * Classe che estende {@link AbstractCellEditor}, usatata per rappresentare
 * un'oggetto {@link Squadra} in una {@link JTable} tramite una
 * {@link JTextField}.
 * 
 * @author Maurizio
 */
public class SquadraCellEditor extends AbstractCellEditor implements TableCellEditor, Serializable {

	public SquadraCellEditor() {
		super();
		this.squadraTextField = new ObjectTextField<>();
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

		String nuovoNome = this.squadraTextField.getText();
		this.squadraTextField.getObject().setNome(nuovoNome);
		return this.squadraTextField.getObject();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		Squadra squadra = (Squadra) value;

		// il testo visualizzato durante l'editing
		this.squadraTextField.setText(squadra.getNome());
		this.squadraTextField.setObject(squadra);

		if (isSelected) {
			this.squadraTextField.setForeground(table.getSelectionForeground());
			this.squadraTextField.setBackground(table.getSelectionBackground());
		} else {
			this.squadraTextField.setForeground(table.getForeground());
			this.squadraTextField.setBackground(table.getBackground());
		}

		return this.squadraTextField;
	}

	private static final long serialVersionUID = 3074483062702818063L;
	private ObjectTextField<Squadra> squadraTextField;
}
