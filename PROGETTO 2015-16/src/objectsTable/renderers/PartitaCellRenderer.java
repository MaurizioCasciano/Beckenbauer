package objectsTable.renderers;

import java.awt.Component;
import java.io.Serializable;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import objectsTable.utilities.ObjectTextField;
import struttura.Partita;
/**
 * Classe che estende {@link DefaultTableCellRenderer}, usata per rappresentare
 * un oggetto {@link Partita} in una {@link JTable} tramite un
 * {@link JTextField}.
 * 
 * @author Maurizio
 */
public class PartitaCellRenderer extends DefaultTableCellRenderer implements TableCellRenderer, Serializable {
	
	public PartitaCellRenderer() {
		super();
		this.partitaTextField = new ObjectTextField<>();
	}
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
			int row, int column) {

		Partita partita = (Partita)value;
		
		JComponent defaultBorderComponent = (JComponent) super.getTableCellRendererComponent(table, value, isSelected,
				hasFocus, row, column);

		this.partitaTextField.setText(partita.getSquadraInCasa().getNome() + " - " + partita.getSquadraInTrasferta().getNome());
		this.partitaTextField.setObject(partita);

		if (isSelected) { 
			this.partitaTextField.setForeground(table.getSelectionForeground());
			this.partitaTextField.setBackground(table.getSelectionBackground());
			this.partitaTextField.setBorder(defaultBorderComponent.getBorder());
		} else {
			this.partitaTextField.setForeground(table.getForeground());
			this.partitaTextField.setBackground(table.getBackground());
			this.partitaTextField.setBorder(defaultBorderComponent.getBorder());
		}

		return this.partitaTextField;
	}
	
	private static final long serialVersionUID = -6340687017602730222L;
	private ObjectTextField<Partita> partitaTextField;
}
