package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.jdesktop.swingx.JXDatePicker;

/**
 * Classe che estende {@link DefaultTableCellRenderer}, usata per rappresentare
 * un oggetto {@link GregorianCalendar} in una {@link JTable} tramite un
 * {@link JXDatePicker}.
 * 
 * @author Maurizio
 */
public class GregorianCalendarDatePickerCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

	public GregorianCalendarDatePickerCellRenderer() {
		super();
		this.datePicker = new JXDatePicker();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		GregorianCalendar date = (GregorianCalendar) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		if (isSelected) {
			this.datePicker.getComponent(0).setForeground(table.getSelectionForeground());
			this.datePicker.getComponent(0).setBackground(table.getSelectionBackground());
			((JComponent) this.datePicker.getComponent(0)).setBorder(defaultBorderComponent.getBorder());
		} else {
			this.datePicker.getComponent(0).setForeground(table.getForeground());
			this.datePicker.getComponent(0).setBackground(table.getBackground());
			((JComponent) this.datePicker.getComponent(0)).setBorder(defaultBorderComponent.getBorder());
		}

		this.datePicker.setDate(date.getTime());
		return this.datePicker;
	}

	private static final long serialVersionUID = 4706397252216317746L;
	private JXDatePicker datePicker;
}
