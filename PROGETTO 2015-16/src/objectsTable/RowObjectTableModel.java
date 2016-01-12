package objectsTable;

import java.io.Serializable;
import java.lang.reflect.*;
import java.util.*;
import javax.swing.table.*;

public abstract class RowObjectTableModel<T> extends AbstractTableModel implements Serializable {

	/**
	 * Constructs a <code>RowObjectTableModel</code> with column names.
	 *
	 * Each column's name will be taken from the <code>columnNames</code>
	 * ArrayList and the number of columns is determined by the number of items
	 * in the <code>columnNames</code> ArrayList.
	 *
	 * @param rowClass
	 *            the class of row data to be added to the model
	 * @param columnNames
	 *            <code>ArrayList</code> containing the names of the new columns
	 */
	protected RowObjectTableModel(String[] columnNames, Class<T> rowClass) {
		this(new ArrayList<T>(), columnNames, rowClass);
	}

	/**
	 * Full Constructor for creating a <code>RowTableModel</code>.
	 *
	 * Each item in the <code>modelData</code> List must also be a List Object
	 * containing items for each column of the row.
	 *
	 * Each column's name will be taken from the <code>columnNames</code> List
	 * and the number of colums is determined by thenumber of items in the
	 * <code>columnNames</code> List.
	 *
	 * @param modelData
	 *            the data of the table
	 * @param columnNames
	 *            <code>List</code> containing the names of the new columns
	 * @param rowClass
	 *            the class of row data to be added to the model
	 */
	protected RowObjectTableModel(ArrayList<T> modelData, String[] columnNames, Class<T> rowClass) {
		setDataAndColumnNames(modelData, columnNames);
		setRowClass(rowClass);
	}

	/**
	 * Reset the data and column names of the model.
	 *
	 * A fireTableStructureChanged event will be generated.
	 *
	 * @param modelData
	 *            the data of the table
	 * @param columnNames
	 *            <code>List</code> containing the names of the new columns
	 */
	protected void setDataAndColumnNames(ArrayList<T> modelData, String[] columnNames) {
		this.tableObjects = modelData;
		this.columnNames = columnNames;
		this.columnClasses = new Class[getColumnCount()];
		this.isColumnEditable = new Boolean[getColumnCount()];
		super.fireTableStructureChanged();
	}

	/**
	 * The class of the Row being stored in the TableModel
	 *
	 * This is required for the getRowsAsArray() method to return the proper
	 * class of row.
	 *
	 * @param rowClas
	 *            the class of the row
	 */
	protected void setRowClass(Class<T> rowClass) {
		this.rowClass = rowClass;
	}

	// Implementation of the TableModel interface

	/**
	 * Returns the Class of the queried <code>column</code>.
	 * 
	 * First it will check to see if a Class has been specified for the
	 * <code>column</code> by using the <code>setColumnClass</code> method. If
	 * not, then the superclass value is returned.
	 *
	 * @param column
	 *            the column being queried
	 * @return the Class of the column being queried
	 */

	public Class<?> getColumnClass(int column) {
		Class<?> columnClass = Object.class;

		for (int row = 0; row < getRowCount(); row++) {
			Object o = getValueAt(row, column);

			if (o != null) {
				columnClass = o.getClass();
				break;
			}
		}

		return columnClass;
	}

	/**
	 * Sets the Class for the specified column.
	 *
	 * @param column
	 *            the column whose Class is being changed
	 * @param columnClass
	 *            the new Class of the column
	 * @exception ArrayIndexOutOfBoundsException
	 *                if an invalid column was given
	 */
	public void setColumnClass(int column, Class<?> columnClass) {
		columnClasses[column] = columnClass;
		fireTableRowsUpdated(0, getRowCount() - 1);
	}

	/**
	 * Returns the number of columns in this table model.
	 *
	 * @return the number of columns in the model
	 */
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Returns the column name.
	 *
	 * @return a name for this column using the string value of the appropriate
	 *         member in <code>columnNames</code>. If <code>columnNames</code>
	 *         does not have an entry for this index then the default name
	 *         provided by the superclass is returned
	 */
	public String getColumnName(int column) {
		Object columnName = null;

		if (column < columnNames.length) {
			columnName = columnNames[column];
		}

		return (columnName == null) ? super.getColumnName(column) : columnName.toString();
	}

	/**
	 * Restituisce il numero di righe in questo TableModel.
	 *
	 * @return il numero di righe in questo TableModel.
	 */
	public int getRowCount() {
		return tableObjects.size();
	}

	/**
	 * Returns true regardless of parameter values.
	 *
	 * @param row
	 *            the row whose value is to be queried
	 * @param column
	 *            the column whose value is to be queried
	 * @return true
	 */
	public boolean isCellEditable(int row, int column) {
		Boolean isEditable = null;

		// Check is column editability has been set

		if (column < isColumnEditable.length)
			isEditable = isColumnEditable[column];

		return (isEditable == null) ? isModelEditable : isEditable.booleanValue();
	}

	//
	// Implement custom methods
	//
	/**
	 * Adds a row of data to the end of the model. Notification of the row being
	 * added will be generated.
	 *
	 * @param rowData
	 *            data of the row being added
	 */
	protected void addRow(T rowData) {
		insertRow(getRowCount(), rowData);
	}

	/**
	 * Returns the Object of the requested <code>row</code>.
	 *
	 * @return the Object of the requested row.
	 */
	public T getRow(int row) {
		return tableObjects.get(row);
	}

	/**
	 * Returns an array of Objects for the requested <code>rows</code>.
	 *
	 * @return an array of Objects for the requested rows.
	 */
	@SuppressWarnings("unchecked")
	public T[] getRowsAsArray(int... rows) {
		List<T> rowData = getRowsAsList(rows);
		T[] array = (T[]) Array.newInstance(rowClass, rowData.size());
		return (T[]) rowData.toArray(array);
	}

	/**
	 * Returns a List of Objects for the requested <code>rows</code>.
	 *
	 * @return a List of Objects for the requested rows.
	 */
	public List<T> getRowsAsList(int... rows) {
		ArrayList<T> rowData = new ArrayList<T>(rows.length);

		for (int i = 0; i < rows.length; i++) {
			rowData.add(getRow(rows[i]));
		}

		return rowData;
	}

	/**
	 * Insert a row of data at the <code>row</code> location in the model.
	 * Notification of the row being added will be generated.
	 *
	 * @param row
	 *            row in the model where the data will be inserted
	 * @param rowData
	 *            data of the row being added
	 */
	public void insertRow(int row, T rowData) {

		System.out.println("Before Adding: " + tableObjects.size());

		tableObjects.add(row, rowData);

		System.out.println("After Adding: " + tableObjects.size());

		fireTableRowsInserted(row, row);
	}

	/**
	 * Insert multiple rows of data at the <code>row</code> location in the
	 * model. Notification of the row being added will be generated.
	 *
	 * @param row
	 *            row in the model where the data will be inserted
	 * @param rowList
	 *            each item in the list is a separate row of data
	 */
	public void insertRows(int row, List<T> rowList) {
		tableObjects.addAll(row, rowList);
		fireTableRowsInserted(row, row + rowList.size() - 1);
	}

	/**
	 * Insert multiple rows of data at the <code>row</code> location in the
	 * model. Notification of the row being added will be generated.
	 *
	 * @param row
	 *            row in the model where the data will be inserted
	 * @param rowArray
	 *            each item in the Array is a separate row of data
	 */
	public void insertRows(int row, T[] rowArray) {
		List<T> rowList = new ArrayList<T>(rowArray.length);

		for (int i = 0; i < rowArray.length; i++) {
			rowList.add(rowArray[i]);
		}

		insertRows(row, rowList);
	}

	/**
	 * Moves one or more rows from the inlcusive range <code>start</code> to
	 * <code>end</code> to the <code>to</code> position in the model. After the
	 * move, the row that was at index <code>start</code> will be at index
	 * <code>to</code>. This method will send a <code>tableRowsUpdated</code>
	 * notification message to all the listeners.
	 * <p>
	 *
	 * <pre>
	 *  Examples of moves:
	 *  <p>
	 *  1. moveRow(1,3,5);
	 *		  a|B|C|D|e|f|g|h|i|j|k   - before
	 *		  a|e|f|g|h|B|C|D|i|j|k   - after
	 *  <p>
	 *  2. moveRow(6,7,1);
	 *		  a|b|c|d|e|f|G|H|i|j|k   - before
	 *		  a|G|H|b|c|d|e|f|i|j|k   - after
	 *  <p>
	 * </pre>
	 *
	 * @param start
	 *            the starting row index to be moved
	 * @param end
	 *            the ending row index to be moved
	 * @param to
	 *            the destination of the rows to be moved
	 * @exception IllegalArgumentException
	 *                if any of the elements would be moved out of the table's
	 *                range
	 */
	public void moveRow(int start, int end, int to) {
		if (start < 0) {
			String message = "Start index must be positive: " + start;
			throw new IllegalArgumentException(message);
		}

		if (end > getRowCount() - 1) {
			String message = "End index must be less than total rows: " + end;
			throw new IllegalArgumentException(message);
		}

		if (start > end) {
			String message = "Start index cannot be greater than end index";
			throw new IllegalArgumentException(message);
		}

		int rowsMoved = end - start + 1;

		if (to < 0 || to > getRowCount() - rowsMoved) {
			String message = "New destination row (" + to + ") is invalid";
			throw new IllegalArgumentException(message);
		}

		// Save references to the rows that are about to be moved

		ArrayList<T> temp = new ArrayList<T>(rowsMoved);

		for (int i = start; i < end + 1; i++) {
			temp.add(tableObjects.get(i));
		}

		// Remove the rows from the current location and add them back
		// at the specified new location

		tableObjects.subList(start, end + 1).clear();
		tableObjects.addAll(to, temp);

		// Determine the rows that need to be repainted to reflect the move

		int first;
		int last;

		if (to < start) {
			first = to;
			last = end;
		} else {
			first = start;
			last = to + end - start;
		}

		fireTableRowsUpdated(first, last);
	}

	/**
	 * Remove the specified rows from the model. Rows between the starting and
	 * ending indexes, inclusively, will be removed. Notification of the rows
	 * being removed will be generated.
	 *
	 * @param start
	 *            starting row index
	 * @param end
	 *            ending row index
	 * @exception ArrayIndexOutOfBoundsException
	 *                if any row index is invalid
	 */
	public void removeRowRange(int start, int end) {
		tableObjects.subList(start, end + 1).clear();
		fireTableRowsDeleted(start, end);
	}

	/**
	 * Remove the specified rows from the model. The row indexes in the array
	 * must be in increasing order. Notification of the rows being removed will
	 * be generated.
	 *
	 * @param rows
	 *            array containing indexes of rows to be removed
	 * @exception ArrayIndexOutOfBoundsException
	 *                if any row index is invalid
	 */
	public void removeRows(int... rows) {
		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];
			tableObjects.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}

	/**
	 * Replace a row of data at the <code>row</code> location in the model.
	 * Notification of the row being replaced will be generated.
	 *
	 * @param rowIndex
	 *            row in the model where the data will be replaced
	 * @param rowData
	 *            data of the row to replace the existing data
	 * @exception IllegalArgumentException
	 *                when the Class of the row data does not match the row
	 *                Class of the model.
	 */
	public void replaceRow(int rowIndex, T rowData) {
		tableObjects.set(rowIndex, rowData);
		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	/**
	 * Sets the editability for the specified column.
	 *
	 * @param column
	 *            the column whose Class is being changed
	 * @param isEditable
	 *            indicates if the column is editable or not
	 * @exception ArrayIndexOutOfBoundsException
	 *                if an invalid column was given
	 */
	public void setColumnEditable(int column, boolean isEditable) {
		isColumnEditable[column] = isEditable ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Set the ability to edit cell data for the entire model
	 *
	 * Note: values set by the setColumnEditable(...) method will have prioritiy
	 * over this value.
	 *
	 * @param isModelEditable
	 *            true/false
	 */
	public void setModelEditable(boolean isModelEditable) {
		this.isModelEditable = isModelEditable;
	}

	/*
	 * Convert an unformatted column name to a formatted column name. That is:
	 *
	 * - insert a space when a new uppercase character is found, insert multiple
	 * upper case characters are grouped together. - replace any "_" with a
	 * space
	 *
	 * @param columnName unformatted column name
	 * 
	 * @return the formatted column name
	 */
	public static String formatColumnName(String columnName) {
		if (columnName.length() < 3)
			return columnName;

		StringBuffer buffer = new StringBuffer(columnName);
		boolean isPreviousLowerCase = false;

		for (int i = 1; i < buffer.length(); i++) {
			boolean isCurrentUpperCase = Character.isUpperCase(buffer.charAt(i));

			if (isCurrentUpperCase && isPreviousLowerCase) {
				buffer.insert(i, " ");
				i++;
			}

			isPreviousLowerCase = !isCurrentUpperCase;
		}

		return buffer.toString().replaceAll("_", " ");
	}

	private static final long serialVersionUID = 831989111979834244L;
	protected ArrayList<T> tableObjects;
	protected String[] columnNames;
	protected Class<?>[] columnClasses;
	protected Boolean[] isColumnEditable;
	private Class<?> rowClass = Object.class;
	private boolean isModelEditable = true;
}
