package objectsTable.editors;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;

import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.table.TableCellEditor;

import org.jdesktop.swingx.JXDatePicker;

public class GregorianCalendarDatePickerCellEditor extends AbstractCellEditor implements TableCellEditor {

	public GregorianCalendarDatePickerCellEditor() {
		super();
		this.datePicker = new JXDatePicker();
		this.datePicker.getEditor().setEditable(false);
		this.datePicker.getMonthView().setLowerBound(new Date());
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

		/*
		 * try { Date time =
		 * Partita.DATE_FORMAT.parse(this.dateTextField.getText());
		 * this.dateTextField.getObject().setTime(time); } catch (ParseException
		 * e) { e.printStackTrace(); }
		 * 
		 * return this.dateTextField.getObject();
		 */

		GregorianCalendar date = new GregorianCalendar();
		date.setTime(this.datePicker.getDate());
		return date;
	}

	@Override
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		GregorianCalendar date = (GregorianCalendar) value;

		// il testo visualizzato durante l'editing
		this.datePicker.setDate(date.getTime());

		if (isSelected) {
			this.datePicker.getComponent(0).setForeground(table.getSelectionForeground());
			this.datePicker.getComponent(0).setBackground(table.getSelectionBackground());
		} else {
			this.datePicker.getComponent(0).setForeground(table.getForeground());
			this.datePicker.getComponent(0).setBackground(table.getBackground());
		}

		return this.datePicker;
	}

	private static final long serialVersionUID = 2619307260231595833L;
	private JXDatePicker datePicker;
}
