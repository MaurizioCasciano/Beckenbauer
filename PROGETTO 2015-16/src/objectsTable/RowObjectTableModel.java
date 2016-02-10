package objectsTable;

import java.io.Serializable;
import java.util.*;
import javax.swing.JTable;
import javax.swing.table.*;

/**
 * Classe che estende {@link AbstractTableModel} per permettere la gestione
 * delle righe della {@link JTable} come oggetti. Ogni riga della {@link JTable}
 * mostra i vari campi dell'oggetto.
 * 
 * @param <T>
 *            Il tipo dell'oggetto da inserire nelle righe della tabella.
 * @author Maurizio
 */
public abstract class RowObjectTableModel<T> extends AbstractTableModel implements Serializable {

	/**
	 * Costruisce un {@link RowObjectTableModel} con i nomi delle colonne
	 * passati in input. Il nome di ogni colonna verrà preso dall'array
	 * <code>columnNames</code> passato in input, il numero di colonne è
	 * determinato dalla lunghezza dell'array <code>columnNames</code>.
	 * 
	 * @param columnNames
	 *            L'array contenente i nomi delle colonne della {@link JTable}.
	 * 
	 * @param rowClass
	 *            La classe del tipo di oggetto delle righe da inserire in
	 *            questo modello.
	 * @author Maurizio
	 */
	protected RowObjectTableModel(String[] columnNames) {
		this(new ArrayList<T>(), columnNames);
	}

	/**
	 * Costruisce un {@link RowObjectTableModel} con gli oggetti dell'
	 * {@link ArrayList} passata in input.
	 *
	 * Ogni elemento nell' {@link ArrayList} <code>modelData</code> deve anche
	 * contenere i campi per ogni colonna della riga.
	 * 
	 * Il nome di ogni colonna verrà preso dall'Array <code>columnNames</code>
	 * ed il numero di colonne è determinato dal numero di elementi nell'
	 * {@link ArrayList} <code>columnNames</code>.
	 *
	 * @param modelData
	 *            Gli oggetti da aggiungere alla {@link JTable}.
	 * @param columnNames
	 *            L'array contenente i nomi delle colonne della {@link JTable}.
	 * @param rowClass
	 *            La classe del tipo di oggetto delle righe da inserire in
	 *            questo modello.
	 * @author Maurizio
	 */
	protected RowObjectTableModel(ArrayList<T> modelData, String[] columnNames) {
		setDataAndColumnNames(modelData, columnNames);
	}

	/**
	 * Imposta i dati e i nomi delle colonne del modello.
	 *
	 * Verrà generato un evento fireTableStructureChanged.
	 *
	 * @param modelData
	 *            Gli oggetti da aggiungere alla {@link JTable}.
	 * @param columnNames
	 *            L'array contenente i nomi delle colonne della {@link JTable}.
	 */
	protected void setDataAndColumnNames(ArrayList<T> modelData, String[] columnNames) {
		this.tableObjects = modelData;
		this.columnNames = columnNames;
		this.columnClasses = new Class[getColumnCount()];
		this.isColumnEditable = new Boolean[getColumnCount()];
		fireTableStructureChanged();
	}

	/**
	 * Sostituisce i dati visualizzati nella tabella con i nuovi dati.
	 * 
	 * @param modelData
	 *            I nuovi dati da visualizzare nella tabella.
	 * @author Maurizio
	 */
	protected void replaceData(ArrayList<T> modelData) {
		this.tableObjects = modelData;
		fireTableDataChanged();
	}

	/**
	 * Restituisce la classe della colonna richiesta.
	 * 
	 * Inizialmente controlla se è stata specificata una classe per la colonna
	 * tramite il metodo setColumnClass, in caso affermativo restituisce la
	 * classe impostata.
	 * 
	 * In caso negativo scorre le righe della {@link JTable} e al primo elemento
	 * non nullo nella colonna specificata, restituisce la sua classe.
	 * 
	 * @param column
	 *            La colonna da interrogare.
	 * @return la Classe della colonna interrogata.
	 */
	@Override
	public Class<?> getColumnClass(int column) {

		if (this.columnClasses != null && column < this.columnClasses.length && this.columnClasses[column] != null) {
			return this.columnClasses[column];
		}

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
	 * Imposta la Classe per la colonna specificata.
	 *
	 * @param column
	 *            La colonna di cui si vuole impostare la Classe.
	 * @param columnClass
	 *            La nuova Classe della colonna.
	 * @exception ArrayIndexOutOfBoundsException
	 *                se viene passato un indice di colonna non valido.
	 */
	public void setColumnClass(int column, Class<?> columnClass) {
		columnClasses[column] = columnClass;
		fireTableRowsUpdated(0, getRowCount() - 1);
	}

	/**
	 * Restituisce il numero di colonne in questo modello.
	 *
	 * @return Il numero di colonne in questo modello.
	 */
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	/**
	 * Restituisce il nome della colonna passata in input.
	 *
	 * @return Il nome per la colonna passata in input usando il valore
	 *         restituito dal metodo toString().
	 * 
	 *         Se <code>columnNames</code> non ha un valore per questo indice
	 *         viene restituito il nome fornito dalla superclasse.
	 */
	@Override
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
	@Override
	public int getRowCount() {
		return tableObjects.size();
	}

	/**
	 * Controlla se una cella è modificabile.
	 *
	 * @param row
	 *            La riga il cui valore deve essere interrogato.
	 * @param column
	 *            La colonna il cui valore deve essere interrogato.
	 * @return true se la cella e' editable, false altrimenti.
	 */
	@Override
	public boolean isCellEditable(int row, int column) {
		Boolean isEditable = null;

		if (column < isColumnEditable.length) {
			isEditable = isColumnEditable[column];
		}

		return (isEditable == null) ? isModelEditable : isEditable.booleanValue();
	}

	/**
	 * Aggiunge una riga di dati alla fine del modello. Verra generata una
	 * notifica indicante che si sta aggiungendo una riga.
	 *
	 * @param rowData
	 *            L'oggetto della riga da aggiungere al modello.
	 */
	protected void addRow(T rowData) {
		insertRow(getRowCount(), rowData);
	}

	/**
	 * Restituisce l'oggetto della riga all'indice <code>row</code> richiesta.
	 *
	 * @param row
	 *            L'indice della riga dell'oggetto richiesto.
	 * @return L'oggetto della riga richiesta.
	 */
	public T getRow(int row) {
		return tableObjects.get(row);
	}

	/**
	 * Restituisce un' ArrayList di oggetti per le righe agli indici
	 * <code>rows</code> richiesti.
	 *
	 * @param rows
	 *            Gli indici delle righe degli oggetti da restituire
	 *            nell'ArrayList.
	 *
	 * @return un'ArrayList di oggetti per le righe agli indici
	 *         <code>rows</code> richiesti.
	 */
	public ArrayList<T> getRowsAsList(int... rows) {
		ArrayList<T> rowData = new ArrayList<T>(rows.length);

		for (int i = 0; i < rows.length; i++) {
			rowData.add(getRow(rows[i]));
		}

		return rowData;
	}

	/**
	 * Inserisce una riga di dati all'indice <code>row</code> nel modello. Viene
	 * generata una notifica per la riga che si aggiunge.
	 * 
	 * @param row
	 *            L'indice del modello dove i dati saranno inseriti.
	 * @param rowData
	 *            I dati della riga da aggiungere.
	 */
	public void insertRow(int row, T rowData) {
		tableObjects.add(row, rowData);

		fireTableRowsInserted(row, row);
	}

	/**
	 * Inserisce una lista di righe all'indice <code>row</code> nel modello.
	 * Viene generata una notifica per le rige che si aggiungono.
	 * 
	 * @param row
	 *            L'indice nel modello dove i dati verranno aggiunti.
	 * @param rowList
	 *            Ogni elemento nella lista è una riga di dati.
	 */
	public void insertRows(int row, ArrayList<T> rowList) {
		tableObjects.addAll(row, rowList);
		fireTableRowsInserted(row, row + rowList.size() - 1);
	}

	/**
	 * Rimuove le righe specificate dal modello. Le righe tra gli indici tra
	 * start ed end, estremi inclusi, verranno rimosse. Viene generata una
	 * notifica per le righe rimosse.
	 * 
	 * @param start
	 *            L'indice iniziale da cui iniziare la rimozione delle righe.
	 * @param end
	 *            L'indice final delle righe da rimuovere.
	 * @exception ArrayIndexOutOfBoundsException
	 *                Se un qualsiasi indice è invalido.
	 */
	public void removeRowRange(int start, int end) {
		tableObjects.subList(start, end + 1).clear();
		fireTableRowsDeleted(start, end);
	}

	/**
	 * Rimuove le righe specificate dal modello. Gli indici delle righe
	 * nell'array deve essere in ordine crescente. Viene generata una notifica
	 * per le righe rimosse.
	 * 
	 * @param rows
	 *            L'array contenente gli indici delle righe da rimuovere.
	 * 
	 * @exception ArrayIndexOutOfBoundsException
	 *                Se un qualsiasi indice è invalido.
	 */
	public void removeRows(int... rows) {
		for (int i = rows.length - 1; i >= 0; i--) {
			int row = rows[i];
			this.tableObjects.remove(row);
			fireTableRowsDeleted(row, row);
		}
	}

	/**
	 * Sostituisce la riga di dati all'indice <code>row</code> nel modello.
	 * Viene generata una notifica per la riga sostituita.
	 * 
	 * @param rowIndex
	 *            L'indice della riga nel modello, dove i dati verranno
	 *            sostituiti.
	 * 
	 * @param rowData
	 *            I dati da sostituire alla vecchia riga.
	 */
	public void replaceRow(int rowIndex, T rowData) {
		tableObjects.set(rowIndex, rowData);
		fireTableRowsUpdated(rowIndex, rowIndex);
	}

	/**
	 * Imposta l'editabilità di una specifica colonna.
	 *
	 * @param column
	 *            La colonna di cui si vuole impostare l'editabilità.
	 * @param isEditable
	 *            Indica se la colonna è editabile o meno.
	 * 
	 * @exception ArrayIndexOutOfBoundsException
	 *                Se viene passata un indice di colonna non valido.
	 */
	public void setColumnEditable(int column, boolean isEditable) {
		isColumnEditable[column] = isEditable ? Boolean.TRUE : Boolean.FALSE;
	}

	/**
	 * Imposta l'editabilità dell'intero modello.
	 * 
	 * NB: I valori impostati tramite il metodo setColumnEditable(...) avranno
	 * la priorità su questo valore.
	 *
	 * @param isModelEditable
	 *            true/false
	 */
	public void setModelEditable(boolean isModelEditable) {
		this.isModelEditable = isModelEditable;
	}

	private static final long serialVersionUID = 831989111979834244L;
	protected ArrayList<T> tableObjects;
	protected String[] columnNames;
	protected Class<?>[] columnClasses;
	protected Boolean[] isColumnEditable;
	private boolean isModelEditable = true;
}
