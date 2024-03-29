package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import objectsTable.utilities.ObjectTextField;
import struttura.Stadio;

/**
 * Classe che estende {@link DefaultTableCellRenderer}, usata per rappresentare
 * un oggetto {@link Stadio} in una {@link JTable} tramite un {@link JTextField}
 * 
 * @author Maurizio
 */
public class StadioCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

	public StadioCellRenderer() {
		super();
		this.stadioTextField = new ObjectTextField<>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Stadio stadio = (Stadio) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.stadioTextField.setText(stadio.getNome());
		this.stadioTextField.setObject(stadio);

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
	private ObjectTextField<Stadio> stadioTextField;
}
