package objectsTable.renderers;

import java.awt.Component;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

import objectsTable.utilities.ObjectTextField;
import struttura.Squadra;

public class SquadraCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer {

	public SquadraCellRenderer() {
		super();
		this.squadraTextField = new ObjectTextField<>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Squadra squadra = (Squadra) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.squadraTextField.setText(squadra.getNome());
		this.squadraTextField.setObject(squadra);

		if (isSelected) {
			this.squadraTextField.setForeground(table.getSelectionForeground());
			this.squadraTextField.setBackground(table.getSelectionBackground());
			this.squadraTextField.setBorder(defaultBorderComponent.getBorder());
		} else {
			this.squadraTextField.setForeground(table.getForeground());
			this.squadraTextField.setBackground(table.getBackground());
			this.squadraTextField.setBorder(defaultBorderComponent.getBorder());
		}

		return this.squadraTextField;
	}

	private static final long serialVersionUID = 6321239846906220265L;
	private ObjectTextField<Squadra> squadraTextField;
}
