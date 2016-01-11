package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
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

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JViewport;

public class StadiumPanel extends JPanel implements MouseWheelListener {

	public StadiumPanel(int capienza) {
		super(new GridLayout(STADIUM_PANEL_ROWS, STADIUM_PANEL_COLUMNS));
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

		this.setPreferredSize(new Dimension(1200, 700));

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
			JPanel settorePanel = new JPanel(new BorderLayout());
			settorePanel.setOpaque(false);

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

				SettoreStadio settoreStadio = new SettoreStadio(this.postiPerSettore);

				settoreButton.add(settoreStadio);

				settoreButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {

						settorePanel.setPreferredSize(new Dimension(500, 500));
						settorePanel.revalidate();

						settoreStadio.showNextCard();
					}
				});

				settorePanel.add(settoreButton, BorderLayout.CENTER);
				settorePanel.setOpaque(true);
			}

			this.northPanel.add(settorePanel);
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
			JPanel settorePanel = new JPanel(new BorderLayout());
			settorePanel.setOpaque(true);

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

			SettoreStadio settoreStadio = new SettoreStadio(this.postiPerSettore);

			settoreButton.add(settoreStadio);

			settoreButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					settoreStadio.showNextCard();
				}
			});

			settorePanel.add(settoreButton, BorderLayout.CENTER);
			this.centreLeftPanel.add(settorePanel);
		}
	}

	private void initCentreCentrePanel() {
		this.centreCentrePanel = new BackgroundImagePanel(Assets.getSoccerPitch());
	}

	private void initCentreRightPanel() {
		this.centreRightPanel = new JPanel(new GridLayout(8, 10, HORIZONTAL_GAP, VERTICAL_GAP));
		this.centreRightPanel.setOpaque(false);

		for (int i = 0; i < 8 * 10; i++) {
			JPanel settorePanel = new JPanel(new BorderLayout());
			settorePanel.setOpaque(true);

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

			SettoreStadio settoreStadio = new SettoreStadio(this.postiPerSettore);

			settoreButton.add(settoreStadio);

			settoreButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					settoreStadio.showNextCard();
				}
			});

			settorePanel.add(settoreButton, BorderLayout.CENTER);
			this.centreRightPanel.add(settorePanel, BorderLayout.CENTER);
		}
	}

	private void initSouthPanel() {

		final int SOUTH_PANEL_ROWS = 8;
		final int SOUTH_PANEL_COLUMNS = 30;

		this.southPanel = new JPanel(
				new GridLayout(SOUTH_PANEL_ROWS, SOUTH_PANEL_COLUMNS, HORIZONTAL_GAP, VERTICAL_GAP));
		this.southPanel.setOpaque(false);

		for (int i = 0; i < 8 * 30; i++) {
			JPanel settorePanel = new JPanel(new BorderLayout());
			settorePanel.setOpaque(false);

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

				SettoreStadio settoreStadio = new SettoreStadio(this.postiPerSettore);

				settoreButton.add(settoreStadio);

				settoreButton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						settoreStadio.showNextCard();
					}
				});

				settorePanel.add(settoreButton, BorderLayout.CENTER);
				settorePanel.setOpaque(true);
			}

			this.southPanel.add(settorePanel);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		// stampa il background
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.LIGHT_GRAY);

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
	private JPanel northPanel, centrePanel, southPanel;

	private JPanel centreLeftPanel, centreCentrePanel, centreRightPanel;

	private int capienza;
	private static final int CAPIENZA_DEFAULT = 50000;
	private int numeroSettori;
	private static final int SETTORI_TOTALI = 620;

	private static final int STADIUM_PANEL_ROWS = 3;
	private static final int STADIUM_PANEL_COLUMNS = 1;
	private int postiPerSettore;

	private static final int HORIZONTAL_GAP = 5, VERTICAL_GAP = 5;

	private final MouseAdapter myMouseAdapter = new MyMouseAdapter();

	/**************************************************************************************/
	class MyMouseAdapter extends MouseAdapter {
		private Point origin;

		/**
		 * Ottiene il punto in cui ha avuto inizio il trascinamento del mouse.
		 */
		@Override
		public void mousePressed(MouseEvent e) {
			origin = new Point(e.getPoint());
		}

		/**
		 * Calcola il nuovo rettangolo visibile del JViewport in base alla
		 * distanza percorsa trascinando il mouse.
		 */
		@Override
		public void mouseDragged(MouseEvent e) {
			if (origin != null) {
				JViewport viewPort = (JViewport) getParent();
				if (viewPort != null) {
					int deltaX = origin.x - e.getX();
					int deltaY = origin.y - e.getY();

					Rectangle view = viewPort.getViewRect();
					view.x += deltaX;
					view.y += deltaY;

					scrollRectToVisible(view);
				}
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

		JScrollPane scrollPane = new JScrollPane(new StadiumPanel(StadiumPanel.CAPIENZA_DEFAULT));

		frame.add(scrollPane);
		frame.pack();

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
