/**
 * 
 */
package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import java.text.NumberFormat;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 * 
 * @author Maurizio
 */
public class PrezzoCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

	public PrezzoCellRenderer() {
		super();
		this.prezzoTextField = new JFormattedTextField(NumberFormat.getCurrencyInstance());
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Double prezzo = (Double) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.prezzoTextField.setValue(prezzo);

		if (isSelected) {
			this.prezzoTextField.setForeground(table.getSelectionForeground());
			this.prezzoTextField.setBackground(table.getSelectionBackground());
			this.prezzoTextField.setBorder(defaultBorderComponent.getBorder());
		} else {
			this.prezzoTextField.setForeground(table.getForeground());
			this.prezzoTextField.setBackground(table.getBackground());
			this.prezzoTextField.setBorder(defaultBorderComponent.getBorder());
		}

		return this.prezzoTextField;
	}

	private static final long serialVersionUID = -6340687017602730222L;
	private JFormattedTextField prezzoTextField;
}
