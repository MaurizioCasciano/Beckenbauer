package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import objectsTable.utilities.ObjectTextField;
import struttura.Posto;

/**
 * Classe che estende {@link DefaultTableCellRenderer}, usata per rappresentare
 * un oggetto {@link Posto} in una {@link JTable} tramite un {@link JTextField}.
 * 
 * @author Maurizio
 */
public class PostoCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {

	public PostoCellRenderer() {
		super();
		this.postoTextField = new ObjectTextField<>();
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Posto posto = (Posto) value;

		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.postoTextField.setText("Fila: " + posto.getNumeroFila() + "  Posto: " + posto.getNumeroPosto());
		this.postoTextField.setObject(posto);

		if (isSelected) {
			this.postoTextField.setForeground(table.getSelectionForeground());
			this.postoTextField.setBackground(table.getSelectionBackground());
			this.postoTextField.setBorder(defaultBorderComponent.getBorder());
		} else {
			this.postoTextField.setForeground(table.getForeground());
			this.postoTextField.setBackground(table.getBackground());
			this.postoTextField.setBorder(defaultBorderComponent.getBorder());
		}

		return this.postoTextField;
	}

	private static final long serialVersionUID = 9060986103710437954L;
	private ObjectTextField<Posto> postoTextField;
}
