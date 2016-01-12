package objectsTable.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import objectsTable.utilities.ObjectTextField;
import struttura.Partita;

public class GregorianCalendarCellEditor extends AbstractCellEditor implements TableCellEditor, Serializable {

	public GregorianCalendarCellEditor() {
		super();
		this.dateTextField = new ObjectTextField<>();
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

		try {
			Date time = Partita.DATE_FORMAT.parse(this.dateTextField.getText());
			this.dateTextField.getObject().setTime(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return this.dateTextField.getObject();
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		GregorianCalendar date = (GregorianCalendar) value;

		// il testo visualizzato durante l'editing
		this.dateTextField.setText(Partita.DATE_FORMAT.format(date.getTime()));
		this.dateTextField.setObject(date);

		if (isSelected) {
			this.dateTextField.setForeground(table.getSelectionForeground());
			this.dateTextField.setBackground(table.getSelectionBackground());
		} else {
			this.dateTextField.setForeground(table.getForeground());
			this.dateTextField.setBackground(table.getBackground());
		}

		return this.dateTextField;
	}

	private static final long serialVersionUID = 2619307260231595833L;
	private ObjectTextField<GregorianCalendar> dateTextField;
}
