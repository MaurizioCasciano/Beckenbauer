package combo.renderers;

import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import struttura.Stadio;

/**
 * Renderer che gestisce la visualizzazione di uno stadio in una
 * {@link JComboBox}.
 */
public class StadioComboRenderer implements ListCellRenderer<Stadio> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Stadio> list, Stadio stadio, int index,
			boolean isSelected, boolean cellHasFocus) {

		JLabel defaultRenderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, stadio,
				index, isSelected, cellHasFocus);

		defaultRenderer.setText(stadio.getNome());
		return defaultRenderer;
	}
}
