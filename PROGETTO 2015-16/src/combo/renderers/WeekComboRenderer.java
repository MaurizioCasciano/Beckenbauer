package combo.renderers;

import java.awt.Component;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import calendar.Week;

/**
 * Renderer che gestisce la visualizzazione di una settimana ({@link Week}) in
 * una {@link JComboBox}.
 */
public class WeekComboRenderer implements ListCellRenderer<Week> {

	@Override
	public Component getListCellRendererComponent(JList<? extends Week> list, Week settimana, int index,
			boolean isSelected, boolean cellHasFocus) {

		JLabel defaultRenderer = (JLabel) new DefaultListCellRenderer().getListCellRendererComponent(list, settimana,
				index, isSelected, cellHasFocus);

		defaultRenderer.setText(Week.DATE_FORMAT.format(settimana.getStart().getTime()) + " - "
				+ Week.DATE_FORMAT.format(settimana.getEnd().getTime()));
		return defaultRenderer;
	}

}
