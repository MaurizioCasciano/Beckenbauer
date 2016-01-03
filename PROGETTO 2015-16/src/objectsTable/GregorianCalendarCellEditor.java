package objectsTable;

import java.awt.Component;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.EventObject;
import java.util.GregorianCalendar;
import javax.swing.AbstractCellEditor;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

public class GregorianCalendarCellEditor extends AbstractCellEditor implements TableCellEditor {

	public GregorianCalendarCellEditor() {
		super();
		this.textField = new JTextField();
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
	public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

		GregorianCalendar date = (GregorianCalendar) value;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		if (this.myTable == null) {
			this.myTable = table;
		}

		// il testo visualizzato durante l'editing
		this.textField.setText(dateFormat.format(date.getTime()));

		if (isSelected) {
			this.textField.setForeground(table.getSelectionForeground());
			this.textField.setBackground(table.getSelectionBackground());
		} else {
			this.textField.setForeground(table.getForeground());
			this.textField.setBackground(table.getBackground());
		}

		return textField;
	}

	@Override
	public Object getCellEditorValue() {

		GregorianCalendar date = new GregorianCalendar();

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Date time = new Date();

		try {
			time = dateFormat.parse(this.textField.getText());
		} catch (ParseException e) {

			e.printStackTrace();
		}

		date.setTime(time);

		return date;
	}

	private static final long serialVersionUID = 2619307260231595833L;
	private JTextField textField;
	private JTable myTable = null;
}
