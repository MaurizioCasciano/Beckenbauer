package objectsTable;

import java.awt.BorderLayout;
import java.io.Serializable;

import javax.swing.*;

public class JButtonTableModel extends RowObjectTableModel<JButton> implements Serializable{

	private static final long serialVersionUID = -2669056263334698191L;
	private static final String[] COLUMN_NAMES = { "Text", "Tool Tip Text", "Enabled", "Visible" };

	public JButtonTableModel() {
		super(COLUMN_NAMES);

		setColumnClass(2, Boolean.class);
		setColumnClass(3, Boolean.class);
	}

	@Override
	public Object getValueAt(int row, int column) {
		JButton button = getRow(row);

		switch (column) {
		case 0:
			return button.getText();
		case 1:
			return button.getToolTipText();
		case 2:
			return button.isEnabled();
		case 3:
			return button.isVisible();
		default:
			return null;
		}
	}

	@Override
	public void setValueAt(Object value, int rowIndex, int columnIndex) {
		JButton button = getRow(rowIndex);

		switch (columnIndex) {
		case 0:
			button.setText((String) value);
			break;
		case 1:
			button.setToolTipText((String) value);
			break;
		case 2:
			button.setEnabled((Boolean) value);
			break;
		case 3:
			button.setVisible((Boolean) value);
			break;
		}

		fireTableCellUpdated(rowIndex, columnIndex);
	}

	public static void main(String[] args) {
		JButton one = new JButton("One");
		JButton two = new JButton("Two");
		JButton three = new JButton("Three");

		// Use the custom model

		JButtonTableModel model = new JButtonTableModel();

		// Use the BeanTableModel

		// BeanTableModel<JButton> model =
		// new BeanTableModel<JButton>(JButton.class, java.awt.Component.class);

		model.addRow(one);
		model.addRow(two);
		model.addRow(three);

		JTable table = new JTable(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);

		JPanel south = new JPanel();
		south.add(one);
		south.add(two);
		south.add(three);

		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(scrollPane);
		frame.getContentPane().add(south, BorderLayout.SOUTH);
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		JButton first = model.getRow(0);
		System.out.println(first);
	}
}
