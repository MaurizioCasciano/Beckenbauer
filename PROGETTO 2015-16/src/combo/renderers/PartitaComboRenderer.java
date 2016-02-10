package combo.renderers;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import struttura.Partita;

/**
 * Renderer che gestisce la visualizzazione di una partita in una
 * {@link JComboBox}.
 */
public class PartitaComboRenderer implements ListCellRenderer<Partita> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Partita> list, Partita partita, int index,
			boolean isSelected, boolean cellHasFocus) {

		JLabel defaultRenderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, partita,
				index, isSelected, cellHasFocus);

		defaultRenderer.setText(partita.getSquadraInCasa().getNome() + " - " + partita.getSquadraInTrasferta().getNome()
				+ "   Stadio: " + partita.getStadio().getNome() + "   "
				+ Partita.DATE_FORMAT.format(partita.getData().getTime()));
		return defaultRenderer;
	}

}
