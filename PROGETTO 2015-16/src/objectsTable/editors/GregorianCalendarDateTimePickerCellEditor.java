package objectsTable.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;
import calendar.DateTimePicker;

/**
 * Classe che estende {@link AbstractCellEditor}, usatata per rappresentare
 * un'oggetto {@link GregorianCalendar} in una {@link JTable} tramite un
 * {@link DateTimePicker}.
 * 
 * @author Maurizio
 */
public class GregorianCalendarDateTimePickerCellEditor extends AbstractCellEditor
		implements TableCellEditor, Serializable {

	public GregorianCalendarDateTimePickerCellEditor() {
		super();
		this.dateTimePicker = new DateTimePicker();
		this.dateTimePicker.getEditor().setEditable(false);
		this.dateTimePicker.getMonthView().setLowerBound(new Date());
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
		GregorianCalendar date = new GregorianCalendar();
		date.setTime(this.dateTimePicker.getDate());
		return date;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		GregorianCalendar date = (GregorianCalendar) value;

		// il testo visualizzato durante l'editing
		this.dateTimePicker.setDate(date.getTime());

		if (isSelected) {
			this.dateTimePicker.getComponent(0).setForeground(table.getSelectionForeground());
			this.dateTimePicker.getComponent(0).setBackground(table.getSelectionBackground());
		} else {
			this.dateTimePicker.getComponent(0).setForeground(table.getForeground());
			this.dateTimePicker.getComponent(0).setBackground(table.getBackground());
		}

		return this.dateTimePicker;
	}

	private static final long serialVersionUID = 2619307260231595833L;
	private DateTimePicker dateTimePicker;
}
