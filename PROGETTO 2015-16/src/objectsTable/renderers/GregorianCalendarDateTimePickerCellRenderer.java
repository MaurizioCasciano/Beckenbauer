package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import calendar.DateTimePicker;

public class GregorianCalendarDateTimePickerCellRenderer extends DefaultTableCellRenderer
		implements TableCellRenderer, Serializable {

	public GregorianCalendarDateTimePickerCellRenderer() {
		super();
		this.dateTimePicker = new DateTimePicker();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		GregorianCalendar date = (GregorianCalendar) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		if (isSelected) {
			this.dateTimePicker.getComponent(0).setForeground(table.getSelectionForeground());
			this.dateTimePicker.getComponent(0).setBackground(table.getSelectionBackground());
			((JComponent) this.dateTimePicker.getComponent(0)).setBorder(defaultBorderComponent.getBorder());
		} else {
			this.dateTimePicker.getComponent(0).setForeground(table.getForeground());
			this.dateTimePicker.getComponent(0).setBackground(table.getBackground());
			((JComponent) this.dateTimePicker.getComponent(0)).setBorder(defaultBorderComponent.getBorder());
		}

		this.dateTimePicker.setDate(date.getTime());
		return this.dateTimePicker;
	}

	private static final long serialVersionUID = 4706397252216317746L;
	private DateTimePicker dateTimePicker;
}
