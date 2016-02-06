package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import objectsTable.utilities.ObjectTextField;
import struttura.Settore;
/**
 * Classe che estende {@link DefaultTableCellRenderer}, usata per rappresentare
 * un oggetto {@link Settore} in una {@link JTable} tramite un
 * {@link JTextField}.
 * 
 * @author Maurizio
 */
public class SettoreCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

	public SettoreCellRenderer() {
		super();
		this.stadioTextField = new ObjectTextField<>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Settore settore = (Settore) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.stadioTextField.setText(settore.getNomeSettore());
		this.stadioTextField.setObject(settore);

		if (isSelected) {
			this.stadioTextField.setForeground(table.getSelectionForeground());
			this.stadioTextField.setBackground(table.getSelectionBackground());
			this.stadioTextField.setBorder(defaultBorderComponent.getBorder());
		} else {
			this.stadioTextField.setForeground(table.getForeground());
			this.stadioTextField.setBackground(table.getBackground());
			this.stadioTextField.setBorder(defaultBorderComponent.getBorder());
		}

		return this.stadioTextField;
	}

	private static final long serialVersionUID = 9060986103710437954L;
	private ObjectTextField<Settore> stadioTextField;
}
