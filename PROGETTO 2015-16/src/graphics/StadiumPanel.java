package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.RoundRectangle2D;
import java.io.Serializable;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

import struttura.Partita;
import struttura.StrutturaSportiva;
import user.Cliente;

public class StadiumPanel extends JPanel implements MouseWheelListener, Serializable {

	/**
	 * Crea un pannello rappresentante uno stadio, con la capienza di default.
	 * Per un corretto funzionamento (zoom e scrolling con mouse dragging) deve
	 * essere inserito in un JScrollPane.
	 */
	public StadiumPanel(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita) {
		this(strutturaSportiva, cliente, partita, CAPIENZA_DEFAULT);
	}

	/**
	 * Crea un pannello rappresentante uno stadio, con la capienza impostata.
	 * Per un corretto funzionamento (zoom e scrolling con mouse dragging) deve
	 * essere inserito in un JScrollPane.
	 * 
	 * @param capienza
	 *            La capienza dello stadio.
	 */
	public StadiumPanel(StrutturaSportiva strutturaSportiva, Cliente cliente, Partita partita, int capienza) {
		super(new GridLayout(STADIUM_PANEL_ROWS, STADIUM_PANEL_COLUMNS));
		this.strutturaSportiva = strutturaSportiva;
		this.cliente = cliente;
		this.partita = partita;
		this.capienza = capienza;

		this.postiPerSettore = capienza / SETTORI_TOTALI;
		// System.out.println(this.postiPerSettore);

		this.init();
	}

	private void init() {
		this.numeroSettori = 0;

		this.initNorthPanel();
		this.initCentrePanel();
		this.initSouthPanel();
		this.add(northPanel);
		this.add(centrePanel);
		this.add(southPanel);

		this.setPreferredSize(new Dimension(900, 600));

		/*
		 * Per monitorare il movimento della rotellina del mouse ed effettuare
		 * lo zoom di conseguenza.
		 */
		this.addMouseWheelListener(this.myMouseAdapter);
		/*
		 * Per ottenere il punto in cui inizia il trascinamento del mouse.
		 */
		this.addMouseListener(this.myMouseAdapter);
		/*
		 * Per monitorare il trascinamento del mouse e calcolare il nuovo
		 * rettangolo visibile del JViewport in base alla distanza percorsa
		 * trascinando il mouse.
		 */
		this.addMouseMotionListener(this.myMouseAdapter);
	}

	private void initNorthPanel() {
		final int NORTH_PANEL_ROWS = 8;
		final int NORTH_PANEL_COLUMNS = 30;

		this.northPanel = new JPanel(
				new GridLayout(NORTH_PANEL_ROWS, NORTH_PANEL_COLUMNS, HORIZONTAL_GAP, VERTICAL_GAP));
		this.northPanel.setOpaque(false);

		for (int i = 0; i < NORTH_PANEL_ROWS * NORTH_PANEL_COLUMNS; i++) {
			JPanel cellaPanel = new JPanel(new BorderLayout());
			cellaPanel.setOpaque(false);

			if ((i >= 3 && i <= 26) || (i >= 31 && i <= 58) || (i >= 61 && i <= 88) || (i >= 90 && i <= 239)) {
				JButton settoreButton = new JButton();
				settoreButton.setToolTipText("Settore n° " + ++numeroSettori);
				/*
				 * Per ottenere il punto in cui inizia il trascinamento del
				 * mouse.
				 */
				settoreButton.addMouseListener(this.myMouseAdapter);
				/*
				 * Per monitorare il trascinamento del mouse e calcolare il
				 * nuovo rettangolo visibile del JViewport in base alla distanza
				 * percorsa trascinando il mouse.
				 */
				settoreButton.addMouseMotionListener(this.myMouseAdapter);

				SettoreStadioPanel settoreStadioPanel = new SettoreStadioPanel(this.strutturaSportiva, this.cliente,
						this.partita, this.postiPerSettore);

				settoreButton.add(settoreStadioPanel);

				settoreButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						settoreStadioPanel.showNextCard();
					}
				});

				cellaPanel.add(settoreButton, BorderLayout.CENTER);
				cellaPanel.setOpaque(true);
			}

			this.northPanel.add(cellaPanel);
		}
	}

	private void initCentrePanel() {
		final int CENTRE_PANEL_ROWS = 1;
		final int CENTRE_PANEL_COLUMNS = 3;

		this.initCentreLeftPanel();
		this.initCentreCentrePanel();
		this.initCentreRightPanel();

		this.centrePanel = new JPanel(new GridLayout(CENTRE_PANEL_ROWS, CENTRE_PANEL_COLUMNS));
		this.centrePanel.setOpaque(false);
		this.centrePanel.add(this.centreLeftPanel);
		this.centrePanel.add(this.centreCentrePanel);
		this.centrePanel.add(this.centreRightPanel);
	}

	private void initCentreLeftPanel() {
		final int CENTRE_LEFT_PANEL_ROWS = 8;
		final int CENTRE_LEFT_PANEL_COLUMNS = 10;

		this.centreLeftPanel = new JPanel(
				new GridLayout(CENTRE_LEFT_PANEL_ROWS, CENTRE_LEFT_PANEL_COLUMNS, HORIZONTAL_GAP, VERTICAL_GAP));
		this.centreLeftPanel.setOpaque(false);

		for (int i = 0; i < CENTRE_LEFT_PANEL_ROWS * CENTRE_LEFT_PANEL_COLUMNS; i++) {
			JPanel cellaPanel = new JPanel(new BorderLayout());
			cellaPanel.setOpaque(true);

			JButton settoreButton = new JButton();
			settoreButton.setToolTipText("Settore n° " + ++numeroSettori);
			/*
			 * Per ottenere il punto in cui inizia il trascinamento del mouse.
			 */
			settoreButton.addMouseListener(this.myMouseAdapter);
			/*
			 * Per monitorare il trascinamento del mouse e calcolare il nuovo
			 * rettangolo visibile del JViewport in base alla distanza percorsa
			 * trascinando il mouse.
			 */
			settoreButton.addMouseMotionListener(this.myMouseAdapter);

			SettoreStadioPanel settoreStadioPanel = new SettoreStadioPanel(this.strutturaSportiva, this.cliente,
					this.partita, this.postiPerSettore);

			settoreButton.add(settoreStadioPanel);

			settoreButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					settoreStadioPanel.showNextCard();
				}
			});

			cellaPanel.add(settoreButton, BorderLayout.CENTER);
			this.centreLeftPanel.add(cellaPanel);
		}
	}

	private void initCentreCentrePanel() {
		this.centreCentrePanel = new BackgroundImagePanel(Assets.getSoccerPitch());
	}

	private void initCentreRightPanel() {
		this.centreRightPanel = new JPanel(new GridLayout(8, 10, HORIZONTAL_GAP, VERTICAL_GAP));
		this.centreRightPanel.setOpaque(false);

		for (int i = 0; i < 8 * 10; i++) {
			JPanel cellaPanel = new JPanel(new BorderLayout());
			cellaPanel.setOpaque(true);

			JButton settoreButton = new JButton();
			settoreButton.setToolTipText("Settore n° " + ++numeroSettori);
			/*
			 * Per ottenere il punto in cui inizia il trascinamento del mouse.
			 */
			settoreButton.addMouseListener(this.myMouseAdapter);
			/*
			 * Per monitorare il trascinamento del mouse e calcolare il nuovo
			 * rettangolo visibile del JViewport in base alla distanza percorsa
			 * trascinando il mouse.
			 */
			settoreButton.addMouseMotionListener(this.myMouseAdapter);

			SettoreStadioPanel settoreStadioPanel = new SettoreStadioPanel(this.strutturaSportiva, this.cliente,
					this.partita, this.postiPerSettore);

			settoreButton.add(settoreStadioPanel);

			settoreButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					settoreStadioPanel.showNextCard();
				}
			});

			cellaPanel.add(settoreButton, BorderLayout.CENTER);
			this.centreRightPanel.add(cellaPanel, BorderLayout.CENTER);
		}
	}

	private void initSouthPanel() {

		final int SOUTH_PANEL_ROWS = 8;
		final int SOUTH_PANEL_COLUMNS = 30;

		this.southPanel = new JPanel(
				new GridLayout(SOUTH_PANEL_ROWS, SOUTH_PANEL_COLUMNS, HORIZONTAL_GAP, VERTICAL_GAP));
		this.southPanel.setOpaque(false);

		for (int i = 0; i < 8 * 30; i++) {
			JPanel cellaPanel = new JPanel(new BorderLayout());
			cellaPanel.setOpaque(false);

			if ((i <= 149) || (i >= 151 && i <= 178) || (i >= 181 && i <= 208) || (i >= 213 && i <= 236)) {
				JButton settoreButton = new JButton();
				settoreButton.setToolTipText("Settore n° " + ++numeroSettori);
				/*
				 * Per ottenere il punto in cui inizia il trascinamento del
				 * mouse.
				 */
				settoreButton.addMouseListener(this.myMouseAdapter);
				/*
				 * Per monitorare il trascinamento del mouse e calcolare il
				 * nuovo rettangolo visibile del JViewport in base alla distanza
				 * percorsa trascinando il mouse.
				 */
				settoreButton.addMouseMotionListener(this.myMouseAdapter);

				SettoreStadioPanel settoreStadioPanel = new SettoreStadioPanel(this.strutturaSportiva, this.cliente,
						this.partita, this.postiPerSettore);

				settoreButton.add(settoreStadioPanel);

				settoreButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						settoreStadioPanel.showNextCard();
					}
				});

				cellaPanel.add(settoreButton, BorderLayout.CENTER);
				cellaPanel.setOpaque(true);
			}

			this.southPanel.add(cellaPanel);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// stampa il background
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.LIGHT_GRAY);

		/*
		 * Il rettangolo utilizzato per ottenere gli angoli arrotondati dello
		 * stadio.
		 */
		RoundRectangle2D.Double roundRectangle = new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 250, 250);

		g2.fill(roundRectangle);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		updatePreferredSize(StadiumPanel.this, e.getPreciseWheelRotation(), e.getPoint());
	}

	/**
	 * Updates the preferredSize of the component depending on the value of
	 * preciseWheelRotation.
	 * 
	 * @param component
	 *            The component to which update the preferred size.
	 * @param preciseWheelRotation
	 *            The wheel rotations of the mouse.
	 * @param mousePosition
	 *            The position of the mouse when MouseWheelEvent was generated.
	 */
	private void updatePreferredSize(JComponent component, double preciseWheelRotation, Point mousePosition) {

		double zoomFactor = preciseWheelRotation * 1.08;
		zoomFactor = (preciseWheelRotation > 0) ? 1 / zoomFactor : -zoomFactor;

		/*
		 * La nuova larghezza viene calcolata moltiplicando quella attuale per
		 * lo zoomFactor.
		 */
		int newWidth = (int) (component.getWidth() * zoomFactor);
		/*
		 * La nuova larghezza viene calcolata moltiplicando quella attuale per
		 * lo zoomFactor.
		 */
		int newHeight = (int) (component.getHeight() * zoomFactor);

		/*
		 * Imposta la nuova dimensione preferita.
		 */
		component.setPreferredSize(new Dimension(newWidth, newHeight));

		/*
		 * Calcola la posizione del mouse in seguito allo zoom (moltiplica per
		 * lo zoomFactor).
		 */
		double mouseX_AfterZoom = mousePosition.x * zoomFactor;
		double mouseY_AfterZoom = mousePosition.y * zoomFactor;

		/*
		 * Calcola lo spostamento del mouse in seguito allo zoom.
		 */
		double offsetX = mouseX_AfterZoom - mousePosition.x;
		double offsetY = mouseY_AfterZoom - mousePosition.y;

		/**
		 * Calcola le nuove coordinate del componente affinchè il mouse rimanga
		 * sulla stessa posizione anche dopo lo zoom.
		 */
		Point oldComponentLocation = component.getLocation();

		int newMouseX = Math.round((float) (oldComponentLocation.x - offsetX));
		int newMouseY = Math.round((float) (oldComponentLocation.y - offsetY));
		Point newComponentLocation = new Point(newMouseX, newMouseY);

		component.setLocation(newComponentLocation);
		component.revalidate();
	}

	public int getCapienza() {
		return this.capienza;
	}

	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}

	private static final long serialVersionUID = -1931003973640128793L;
	private StrutturaSportiva strutturaSportiva;
	private Cliente cliente;
	private Partita partita;
	private JPanel northPanel, centrePanel, southPanel;
	private JPanel centreLeftPanel, centreCentrePanel, centreRightPanel;

	private int capienza;
	public static final int CAPIENZA_DEFAULT = 50000;
	private int numeroSettori;
	private static final int SETTORI_TOTALI = 620;

	private static final int STADIUM_PANEL_ROWS = 3;
	private static final int STADIUM_PANEL_COLUMNS = 1;
	private int postiPerSettore;

	private static final int HORIZONTAL_GAP = 5, VERTICAL_GAP = 5;

	private final MouseAdapter myMouseAdapter = new MyMouseAdapter();

	/**************************************************************************************/
	class MyMouseAdapter extends MouseAdapter {
		private Point origin = null;

		/**
		 * Ottiene il punto in cui ha avuto inizio il trascinamento del mouse.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			/*
			 * Alla pressione del mouse imposta il cursore mano.
			 */
			Component component = e.getComponent();
			if (component != null) {
				component.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			this.origin = new Point(e.getPoint());
		}

		/**
		 * Calcola il nuovo rettangolo visibile del JViewport in base alla
		 * distanza percorsa trascinando il mouse.
		 */
		@Override
		public void mouseDragged(MouseEvent e) {

			Component component = e.getComponent();
			if (component != null) {
				component.setCursor(new Cursor(Cursor.MOVE_CURSOR));
			}

			JViewport viewPort;

			if (this.origin != null) {
				if (StadiumPanel.this.getParent() != null && StadiumPanel.this.getParent() instanceof JViewport) {
					viewPort = (JViewport) StadiumPanel.this.getParent();

					int deltaX = this.origin.x - e.getX();
					int deltaY = this.origin.y - e.getY();

					Rectangle view = viewPort.getViewRect();
					view.translate(deltaX, deltaY);

					StadiumPanel.this.scrollRectToVisible(view);
				}
			}
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			/*
			 * Al rilascio del mouse imposta il cursore di default.
			 */
			Component component = e.getComponent();
			if (component != null) {
				component.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		/**
		 * Gestisce l'aggiornamento della dimensione preferita di questo
		 * componente.
		 */
		@Override
		public void mouseWheelMoved(MouseWheelEvent e) {
			updatePreferredSize(StadiumPanel.this, e.getPreciseWheelRotation(), e.getPoint());
		}
	}

	/***********************************************************************/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JScrollPane scrollPane = new JScrollPane(
				new StadiumPanel(new StrutturaSportiva(""), new Cliente("", "", "", "P@ssw0rd"), new Partita()));

		frame.add(scrollPane);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
