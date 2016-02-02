package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import objectsTable.utilities.ObjectTextField;
import struttura.Partita;
/**
 * Classe che estende {@link DefaultTableCellRenderer}, usata per rappresentare
 * un oggetto {@link GregorianCalendar} in una {@link JTable} tramite una
 * {@link JTextField}.
 * 
 * @author Maurizio
 */
public class GregorianCalendarCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

	public GregorianCalendarCellRenderer() {
		super();
		this.dateTextField = new ObjectTextField<>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		GregorianCalendar date = (GregorianCalendar) value;

		Date time = ((GregorianCalendar) value).getTime();
		String formattedDate = Partita.DATE_FORMAT.format(time);

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.dateTextField.setText(formattedDate);
		this.dateTextField.setObject(date);

		if (isSelected) {
			this.dateTextField.setForeground(table.getSelectionForeground());
			this.dateTextField.setBackground(table.getSelectionBackground());
			this.dateTextField.setBorder(defaultBorderComponent.getBorder());
		} else {
			this.dateTextField.setForeground(table.getForeground());
			this.dateTextField.setBackground(table.getBackground());
			this.dateTextField.setBorder(defaultBorderComponent.getBorder());
		}

		return this.dateTextField;
	}

	private static final long serialVersionUID = 4706397252216317746L;
	private ObjectTextField<GregorianCalendar> dateTextField;
}
