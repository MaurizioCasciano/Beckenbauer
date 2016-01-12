package calendar;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DateFormatter;
import javax.swing.text.DefaultFormatterFactory;

import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.calendar.SingleDaySelectionModel;

public class DateTimePicker extends JXDatePicker implements Serializable {

	/**
	 * Crea un nuovo oggetto DateTimePicker con la data corrente.
	 */
	public DateTimePicker() {
		this(new Date());
	}

	/**
	 * Crea un oggetto DateTimePicker con la data indicata.
	 * 
	 * @param date
	 *            La data da impostare.
	 */
	public DateTimePicker(Date date) {
		super();
		this.setFormats(new SimpleDateFormat("E dd/MM/yyyy HH:mm"));
		this.getMonthView().setSelectionModel(new SingleDaySelectionModel());
		this.getMonthView().setSelectionBackground(Color.CYAN);
		this.setDate(date);
		this.getEditor().setEditable(false);
		/***************************************************/

	}

	@Override
	public void commitEdit() throws ParseException {
		this.commitTime();
		super.commitEdit();
	}

	@Override
	public void cancelEdit() {
		super.cancelEdit();
		this.setTimeSpinner();
	}

	@Override
	public JPanel getLinkPanel() {
		super.getLinkPanel();
		if (this.timePanel == null) {
			this.timePanel = createTimePanel();
		}
		setTimeSpinner();
		return this.timePanel;
	}

	/**
	 * Crea il pannello in cui viene mostrato il timeSpinner.
	 * 
	 * @return Il pannello in cui viene mostrato il timeSpinner.
	 */
	private JPanel createTimePanel() {
		JPanel newPanel = new JPanel();
		newPanel.setLayout(new FlowLayout());

		SpinnerDateModel dateModel = new SpinnerDateModel();
		this.timeSpinner = new JSpinner(dateModel);

		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		this.timeSpinner.setEditor(timeEditor);

		DateFormatter formatter = (DateFormatter) timeEditor.getTextField().getFormatter();
		/*
		 * Non permette all'utente di digitare lettere o caratteri non validi.
		 */
		formatter.setAllowsInvalid(false);
		/*
		 * Vengono sovrascritti i caratteri presenti.
		 */
		formatter.setOverwriteMode(true);
		/*
		 * Ad ogni modifica valida viene aggiornato il valore della
		 * JFormattedTextField dello Spinner.
		 * 
		 * Questo genera un nuovo ChangeEvent (permettendo così la gestione
		 * dell'aggiornamento della JFormattedTextField di questo DateTimePicker
		 * in tempo reale).
		 */
		formatter.setCommitsOnValidEdit(true);

		/*
		 * Monitora i vari ChangeEvent generati dallo Spinner.
		 */
		timeSpinner.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				try {
					commitEdit();
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
			}
		});

		/*
		 * CONTROLLO FONDAMENTALE
		 * 
		 * L'inizializzazione di this.timeFormat non può essere fatta nel
		 * costruttore di questa classa, poichè probabilmente il metodo
		 * getLinkPanel (e di conseguenza questo stesso metodo) viene chiamato
		 * all'interno del costruttore della superclasse, quando l'oggetto
		 * this.timeFormat ancora non può essere stato inizializzato.
		 */
		if (this.timeFormat == null) {
			this.timeFormat = new SimpleDateFormat("HH:mm");
		}

		updateTimeSpinnerTextFieldFormat();
		newPanel.add(new JLabel("Orario:"));
		newPanel.add(timeSpinner);
		newPanel.setBackground(Color.WHITE);
		return newPanel;
	}

	/**
	 * Aggiorna il formato che determina i valori ammissibili per il
	 * timeSpinner.
	 */
	private void updateTimeSpinnerTextFieldFormat() {
		if (timeSpinner == null)
			return;
		JFormattedTextField formattedTextField = ((JSpinner.DefaultEditor) timeSpinner.getEditor()).getTextField();
		DefaultFormatterFactory formatterFactory = (DefaultFormatterFactory) formattedTextField.getFormatterFactory();
		DateFormatter formatter = (DateFormatter) formatterFactory.getDefaultFormatter();
		formatter.setFormat(timeFormat);
	}

	/**
	 * Aggiorna la data compresi l'ora e i minuti di questo DateTimePicker.
	 */
	private void commitTime() {
		Date oldDate = this.getDate();
		if (oldDate != null) {
			Date newTime = (Date) timeSpinner.getValue();
			GregorianCalendar newTimeCalendar = new GregorianCalendar();
			newTimeCalendar.setTime(newTime);

			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(oldDate);
			calendar.set(Calendar.HOUR_OF_DAY, newTimeCalendar.get(Calendar.HOUR_OF_DAY));
			calendar.set(Calendar.MINUTE, newTimeCalendar.get(Calendar.MINUTE));
			calendar.set(Calendar.SECOND, newTimeCalendar.get(Calendar.SECOND));
			calendar.set(Calendar.MILLISECOND, newTimeCalendar.get(Calendar.MILLISECOND));

			Date newDate = calendar.getTime();
			this.setDate(newDate);
		}

	}

	/**
	 * Imposta la data del timeSpinner.
	 */
	private void setTimeSpinner() {
		Date date = getDate();
		if (date != null) {
			this.timeSpinner.setValue(date);
		}
	}

	/**
	 * Restituisce il formato del timeSpinner.
	 * 
	 * @return Il formato del timeSpinner.
	 */
	public DateFormat getTimeFormat() {
		return this.timeFormat;
	}

	/**
	 * Imposta il nuovo formato del timeSpinner.
	 * 
	 * @param timeFormat
	 *            Il nuovo formato del timeSpinner. In caso di valore nullo,
	 *            viene ignorato.
	 */
	public void setTimeFormat(DateFormat timeFormat) {
		if (timeFormat != null) {
			this.timeFormat = timeFormat;
			this.updateTimeSpinnerTextFieldFormat();
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setTitle("Date Time Picker");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		DateTimePicker dateTimePicker = new DateTimePicker();

		frame.add(dateTimePicker, BorderLayout.CENTER);

		JButton button = new JButton("Print");
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(dateTimePicker.getDate());
			}
		});

		frame.add(button, BorderLayout.SOUTH);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	private static final long serialVersionUID = -2063695108327171838L;
	private JSpinner timeSpinner;
	private JPanel timePanel;
	private DateFormat timeFormat;
}
