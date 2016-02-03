package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import objectsTable.utilities.ObjectTextField;
import user.Cliente;

/**
 * Classe che estende {@link DefaultTableCellRenderer}, usata per rappresentare
 * un oggetto {@link Cliente} in una {@link JTable} tramite una
 * {@link JTextField}.
 * 
 * @author Maurizio
 */
public class ClienteCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

	public ClienteCellRenderer() {
		super();
		this.clienteTextField = new ObjectTextField<>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Cliente cliente = (Cliente) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.clienteTextField.setText(cliente.getNome() + " " + cliente.getCognome());
		this.clienteTextField.setObject(cliente);

		if (isSelected) {
			this.clienteTextField.setForeground(table.getSelectionForeground());
			this.clienteTextField.setBackground(table.getSelectionBackground());
			this.clienteTextField.setBorder(defaultBorderComponent.getBorder());
		} else {
			this.clienteTextField.setForeground(table.getForeground());
			this.clienteTextField.setBackground(table.getBackground());
			this.clienteTextField.setBorder(defaultBorderComponent.getBorder());
		}

		return this.clienteTextField;
	}

	private static final long serialVersionUID = -6340687017602730222L;
	private ObjectTextField<Cliente> clienteTextField;
}
