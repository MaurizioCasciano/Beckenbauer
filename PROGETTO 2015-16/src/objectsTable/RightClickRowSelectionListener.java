package objectsTable;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class RightClickRowSelectionListener extends MouseAdapter {

	@Override
	public void mousePressed(MouseEvent e) {
		if (e.getSource() instanceof JTable) {
			JTable table = (JTable) e.getSource();

			if (SwingUtilities.isRightMouseButton(e)) {
				Point point = e.getPoint();
				int newSelectedRowIndex = table.rowAtPoint(point);

				if (newSelectedRowIndex != -1) {
					table.setRowSelectionInterval(newSelectedRowIndex, newSelectedRowIndex);
				}
			}
		}
	}
}
