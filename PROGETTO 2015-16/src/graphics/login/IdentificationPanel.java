package graphics.login;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.Arrays;
import javax.swing.AbstractAction;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import graphics.Window;
import graphics.Assets;
import graphics.BackgroundImagePanel;
import password.Password;
import password.WeakPasswordException;
import struttura.StrutturaSportiva;
import user.AlreadyRegisteredUserException;
import user.Cliente;
import user.UserNotFoundException;
import user.Utente;

/**
 * Classe che estende {@link JPanel}, usata per gestire la registrazione e il
 * login di un {@link Utente} ad una {@link StrutturaSportiva}.
 * 
 * @author Maurizio
 */
public class IdentificationPanel extends JPanel implements Serializable {

	public IdentificationPanel(Window myWindow, BufferedImage bufferedImage) {
		super();
		this.myBackGroundImage = bufferedImage;
		this.setLayout(new BorderLayout());
		this.initializeIdentificationBox();
		this.add(identificationBox, BorderLayout.CENTER);
		this.myWindow = myWindow;
		this.strutturaSportiva = this.myWindow.getStrutturaSportiva();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.drawImage(myBackGroundImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	/**
	 * Initializes the loginButton and adds it an ActionListener.
	 */
	private void initializeLoginButton() {
		this.loginButton = new JButton();
		this.loginButton.setAction(new LoginAction());
		// MUST SET all properties after set action, otherwise will be deleted
		this.loginButton.setIcon(Assets.getLoginIcon());
		this.loginButton.setToolTipText("Login");
	}

	/**
	 * Initializes the loginPasswordField.
	 */
	private void initializeLoginPasswordField() {
		this.loginPasswordField = new JPasswordField(7);
		// An ActionEvent is dispatched by the JTextField when enter is pressed.
		this.loginPasswordField.setAction(new LoginAction());
		this.loginPasswordField.setForeground(Color.BLACK);
		this.loginPasswordField.setToolTipText("Password");
		this.loginPasswordField.setCaretColor(Color.BLUE);
		this.loginPasswordField.setBorder(BorderFactory.createEmptyBorder());
		this.loginPasswordField.addMouseListener(new ShowHidePasswordListener());
	}

	/**
	 * Initializes the LoginUserNameTextField.
	 */
	private void initializeLoginUserNameTextField() {
		this.loginUserNameTextField = new JTextField(7);
		this.loginUserNameTextField.setForeground(Color.BLACK);
		this.loginUserNameTextField.setToolTipText("Username");
		this.loginUserNameTextField.setCaretColor(Color.BLUE);
		this.loginUserNameTextField.setBorder(BorderFactory.createEmptyBorder());
	}

	/**
	 * Initializes the LoginComponentsPanel.
	 */
	private void initializeLoginComponentsPanel() {
		this.initializeLoginButton();
		this.initializeLoginPasswordField();
		this.initializeLoginUserNameTextField();

		this.loginComponentsPanel = new BackgroundImagePanel(Assets.getLoginBackground());
		this.loginComponentsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
		this.loginComponentsPanel.add(loginUserNameTextField);
		this.loginComponentsPanel.add(loginPasswordField);
		this.loginComponentsPanel.add(loginButton);
		this.loginComponentsPanel.setBorder(
				new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.GRAY), "Existing User",
						TitledBorder.LEFT, TitledBorder.CENTER, new Font("myFont", Font.ITALIC, 15), Color.WHITE));
	}

	private void initializeRegisterNameLabel() {
		this.registerNameLabel = new JLabel("Name");
		this.registerNameLabel.setForeground(Color.WHITE);
	}

	private void initializeRegisterNameTextField() {
		this.registerNameTextField = new JTextField();
		this.registerNameTextField.setForeground(Color.BLACK);
		this.registerNameTextField
				.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
	}

	private void initializeRegisterSurnameLabel() {
		this.registerSurnameLabel = new JLabel("Surname");
		this.registerSurnameLabel.setForeground(Color.WHITE);
	}

	private void initializeRegisterSurnameTextField() {
		this.registerSurnameTextField = new JTextField();
		this.registerSurnameTextField.setForeground(Color.BLACK);
		this.registerSurnameTextField
				.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
	}

	private void initializeRegisterUserNameLabel() {
		this.registerUserNameLabel = new JLabel("Username");
		this.registerUserNameLabel.setForeground(Color.WHITE);
	}

	private void initializeRegisterUsernameTextField() {
		this.registerUsernameTextField = new JTextField();
		this.registerUsernameTextField.setForeground(Color.BLACK);
		this.registerUsernameTextField
				.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
	}

	private void initializeRegisterPasswordLabel() {
		this.registerPasswordLabel = new JLabel("Password");
		this.registerPasswordLabel.setForeground(Color.WHITE);
	}

	private void initializeRegisterPasswordField() {
		this.registerPasswordField = new JPasswordField();
		this.registerPasswordField
				.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));

		this.registerPasswordField.addMouseListener(new ShowHidePasswordListener());
		this.registerPasswordField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (Password.check(String.valueOf(registerPasswordField.getPassword()))) {
					registerPasswordField.setForeground(Color.GREEN);
				} else {
					registerPasswordField.setForeground(Color.RED);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (Password.check(String.valueOf(registerPasswordField.getPassword()))) {
					registerPasswordField.setForeground(Color.GREEN);
				} else {
					registerPasswordField.setForeground(Color.RED);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// DO-NOTHING
			}
		});
	}

	private void initializeRegisterPasswordConfirmLabel() {
		this.registerPasswordConfirmLabel = new JLabel("Confirm Password");
		this.registerPasswordConfirmLabel.setForeground(Color.WHITE);
	}

	private void initializeRegisterPasswordConfirmField() {
		this.registerPasswordConfirmField = new JPasswordField();
		this.registerPasswordConfirmField.setForeground(Color.BLACK);
		this.registerPasswordConfirmField
				.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED, Color.BLACK, Color.LIGHT_GRAY));
		this.registerPasswordConfirmField.addMouseListener(new ShowHidePasswordListener());

		this.registerPasswordConfirmField.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void removeUpdate(DocumentEvent e) {
				if (Password.check(String.valueOf(registerPasswordConfirmField.getPassword())) && Arrays
						.equals(registerPasswordField.getPassword(), registerPasswordConfirmField.getPassword())) {

					registerPasswordConfirmField.setForeground(Color.GREEN);
				} else {
					registerPasswordConfirmField.setForeground(Color.RED);
				}
			}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if (Password.check(String.valueOf(registerPasswordConfirmField.getPassword())) && Arrays
						.equals(registerPasswordField.getPassword(), registerPasswordConfirmField.getPassword())) {
					registerPasswordConfirmField.setForeground(Color.GREEN);
				} else {
					registerPasswordConfirmField.setForeground(Color.RED);
				}
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				// DO-NOTHING
			}
		});
	}

	private void initializeRegisterButton() {
		this.registerButton = new JButton(Assets.getRegisterIcon());
		this.registerButton.setToolTipText("Sign-Up");

		this.registerButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				boolean isAllFilledIn = (registerNameTextField.getText().length() > 0)
						&& (registerSurnameTextField.getText().length() > 0)
						&& (registerUsernameTextField.getText().length() > 0)
						&& (Arrays.equals(registerPasswordField.getPassword(),
								registerPasswordConfirmField.getPassword()))
						&& registerPasswordField.getPassword().length > 0;

				if (isAllFilledIn) {

					try {
						Cliente cliente = new Cliente(registerNameTextField.getText(),
								registerSurnameTextField.getText(), registerUsernameTextField.getText(),
								String.valueOf(registerPasswordField.getPassword()));

						strutturaSportiva.addUtente(cliente);
						myWindow.storeStrutturaSportiva();

						JOptionPane.showMessageDialog(myWindow, "Registrazione effettuata con successo.",
								"Registrazione Effettuata", JOptionPane.INFORMATION_MESSAGE);
						myWindow.setUtente(cliente);
						setLoginFields();
						resetRegistrationFields();
					} catch (WeakPasswordException e1) {

						JTextArea passwordRequirements = new JTextArea(6, 6);
						passwordRequirements.setText("I requisiti minimi di sicurezza impongono che una password\n");
						passwordRequirements.append("sicura sia composta da almeno un carattere numerico,\n");
						passwordRequirements.append("un carattere maiuscolo, un carattere minuscolo\n");
						passwordRequirements.append("ed un carattere speciale (@#$%?£€^&+=).\n\n");
						passwordRequirements.append("E' inoltra necessario che la lunghezza della password\n");
						passwordRequirements.append("non sia inferiore ad 8 caratteri.");
						passwordRequirements.setEditable(false);

						JOptionPane.showMessageDialog(IdentificationPanel.this, passwordRequirements, e1.getMessage(),
								JOptionPane.ERROR_MESSAGE);
					} catch (AlreadyRegisteredUserException e1) {
						JOptionPane.showMessageDialog(IdentificationPanel.this, "Username gia' presente. RIPROVARE.",
								"Username gia' presente", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}

				} else {//IS NOT ALL FILLED IN

					if (Arrays.equals(registerPasswordField.getPassword(),
							registerPasswordConfirmField.getPassword())) {
						JOptionPane.showMessageDialog(IdentificationPanel.this, "Completare tutti i campi.");

					} else {
						JOptionPane.showMessageDialog(IdentificationPanel.this, "Le password non coincidono",
								"Different Passwords", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}

	private void initializeRegisterBox() {
		this.registerBox = Box.createVerticalBox();
		this.registerBox.add(Box.createVerticalStrut(10));
		this.registerBox.add(this.registerNameLabel);
		this.registerBox.add(this.registerNameTextField);
		this.registerBox.add(Box.createVerticalStrut(10));
		this.registerBox.add(this.registerSurnameLabel);
		this.registerBox.add(this.registerSurnameTextField);
		this.registerBox.add(Box.createVerticalStrut(10));
		this.registerBox.add(this.registerUserNameLabel);
		this.registerBox.add(this.registerUsernameTextField);
		this.registerBox.add(Box.createVerticalStrut(10));
		this.registerBox.add(this.registerPasswordLabel);
		this.registerBox.add(this.registerPasswordField);
		this.registerBox.add(Box.createVerticalStrut(10));
		this.registerBox.add(this.registerPasswordConfirmLabel);
		this.registerBox.add(this.registerPasswordConfirmField);
		this.registerBox.add(Box.createVerticalStrut(10));
		this.registerBox.add(this.registerButton);
	}

	private void initializeRegisterComponentsPanel() {
		this.initializeRegisterNameLabel();
		this.initializeRegisterNameTextField();
		this.initializeRegisterSurnameLabel();
		this.initializeRegisterSurnameTextField();
		this.initializeRegisterUserNameLabel();
		this.initializeRegisterUsernameTextField();
		this.initializeRegisterPasswordLabel();
		this.initializeRegisterPasswordField();
		this.initializeRegisterPasswordConfirmLabel();
		this.initializeRegisterPasswordConfirmField();
		this.initializeRegisterButton();
		this.initializeRegisterBox();

		this.registerComponentsPanel = new JPanel(new GridLayout(10, 1));
		this.registerComponentsPanel.setBackground(new Color(77, 0, 177, 200));
		this.registerComponentsPanel.add(registerBox);

		this.registerComponentsPanel
				.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.RAISED, Color.BLUE, Color.GRAY), "New User?",
						TitledBorder.LEFT, TitledBorder.CENTER, new Font("myFont", Font.ITALIC, 15), Color.WHITE));

	}

	private void initializeIdentificationBox() {
		this.initializeLoginComponentsPanel();
		this.initializeRegisterComponentsPanel();

		this.identificationBox = Box.createVerticalBox();
		this.identificationBox.add(this.loginComponentsPanel);
		this.identificationBox.add(this.registerComponentsPanel);
	}

	private void resetRegistrationFields() {
		this.registerNameTextField.setText(null);
		this.registerSurnameTextField.setText(null);
		this.registerUsernameTextField.setText(null);
		this.registerPasswordField.setText(null);
		this.registerPasswordConfirmField.setText(null);
	}

	/**
	 * Imposta i campi per il login con i valori presenti nei campi di
	 * registrazione. Usato per impostare il testo che sarà visualizzato al
	 * momento del logout.
	 * 
	 * @author Maurizio
	 */
	private void setLoginFields() {
		this.loginUserNameTextField.setText(this.registerUsernameTextField.getText());
		this.loginPasswordField.setText(new String(this.registerPasswordField.getPassword()));
	}

	public class LoginAction extends AbstractAction implements Serializable {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean loginIsAllFilledIn = (loginUserNameTextField.getText().length() > 0)
					&& (loginPasswordField.getPassword().length > 0);

			if (loginIsAllFilledIn) {

				Utente utente = null;
				try {
					utente = strutturaSportiva.getUtente(loginUserNameTextField.getText());

					if (utente.matchPassword(String.valueOf(loginPasswordField.getPassword()))) {
						myWindow.setUtente(utente);
						resetRegistrationFields();
					} else {
						JOptionPane.showMessageDialog(IdentificationPanel.this, "Password errata. Riprovare.",
								"Password mismatch.", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (UserNotFoundException e1) {
					JOptionPane.showMessageDialog(IdentificationPanel.this, e1.getMessage(), "Utente non trovato",
							JOptionPane.INFORMATION_MESSAGE);
					// e1.printStackTrace();
				}
			} else if (!loginIsAllFilledIn) {
				JOptionPane.showMessageDialog(IdentificationPanel.this, "Completare tutti i campi.", "",
						JOptionPane.INFORMATION_MESSAGE);
			}
		}

		private static final long serialVersionUID = 7243830090421644194L;
	}

	private static final long serialVersionUID = 5940403846029260534L;
	private BufferedImage myBackGroundImage;

	private JButton loginButton, registerButton;
	private JPanel loginComponentsPanel, registerComponentsPanel;
	private JLabel registerNameLabel, registerSurnameLabel, registerUserNameLabel, registerPasswordLabel,
			registerPasswordConfirmLabel;
	private JTextField loginUserNameTextField, registerNameTextField, registerSurnameTextField,
			registerUsernameTextField;
	private JPasswordField loginPasswordField, registerPasswordField, registerPasswordConfirmField;
	private Box identificationBox, registerBox;
	private StrutturaSportiva strutturaSportiva;
	private Window myWindow;
}
