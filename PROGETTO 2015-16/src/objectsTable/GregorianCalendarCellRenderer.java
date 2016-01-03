package objectsTable;

import java.awt.Component;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellRenderer;

public class GregorianCalendarCellRenderer implements TableCellRenderer {

	private JTextField textField;

	public GregorianCalendarCellRenderer() {
		super();
		this.textField = new JTextField();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Date date = ((GregorianCalendar) value).getTime();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		String formattedDate = dateFormat.format(date);

		this.textField.setText(formattedDate);
		this.textField.setBorder(null);

		if (isSelected) {
			this.textField.setForeground(table.getSelectionForeground());
			this.textField.setBackground(table.getSelectionBackground());
		} else {
			this.textField.setForeground(table.getForeground());
			this.textField.setBackground(table.getBackground());
		}

		return textField;
	}

}
