package objectsTable;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JTable;
import javax.swing.SwingUtilities;

/**
 * Classe che estende {@link MouseAdapter}, usata per permettere la selezione di
 * una riga di una {@link JTable} alla pressione del tasto destro del mouse.
 * 
 * @author Maurizio
 */
public class RightClickRowSelectionListener extends MouseAdapter implements Serializable {

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

	private static final long serialVersionUID = -7553201238779522408L;
}
