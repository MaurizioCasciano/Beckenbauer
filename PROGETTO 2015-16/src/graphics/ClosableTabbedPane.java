package graphics;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Classe che estende {@link JTabbedPane} per permettere la chiusura delle Tab.
 * 
 * @author Maurizio
 */
public class ClosableTabbedPane extends JTabbedPane implements Serializable{

	/**
	 * Crea un {@link JTabbedPane} vuoto con un posizionamento delle tab di
	 * default JTabbedPane.TOP. Possibilita di chiudere le tab cliccando sulla
	 * X.
	 * 
	 * @author Maurizio
	 */
	public ClosableTabbedPane() {
		super();
	}

	/**
	 * Crea un {@link JTabbedPane} vuoto con il posizionamento delle tab
	 * specificato, scelto tra: JTabbedPane.TOP, JTabbedPane.BOTTOM,
	 * JTabbedPane.LEFT, or JTabbedPane.RIGHT. Possibilita di chiudere le tab
	 * cliccando sulla X.
	 * 
	 * @param tabPlacement
	 *            Il posizionamento per le tab.
	 * @author Maurizio
	 */
	public ClosableTabbedPane(int tabPlacement) {
		super(tabPlacement);
	}

	/**
	 * Crea un {@link JTabbedPane} vuoto con il posizionamento delle tab e la
	 * layout policy specificati.
	 * 
	 * Il posizionamento delle tab può essere : JTabbedPane.TOP,
	 * JTabbedPane.BOTTOM, JTabbedPane.LEFT, or JTabbedPane.RIGHT. La Tab layout
	 * policy può essere: JTabbedPane.WRAP_TAB_LAYOUT or
	 * JTabbedPane.SCROLL_TAB_LAYOUT. Possibilita di chiudere le tab cliccando
	 * sulla X.
	 * 
	 * @param tabPlacement
	 *            Il posizionamento per le tab.
	 * @param tabLayoutPolicy
	 *            La layout policy per disporre le tab quando tutte le tab non
	 *            possono essere disposte contemporaneamente.
	 * @author Maurizio
	 */
	public ClosableTabbedPane(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.closeUI.paint(g);
	}

	@Override
	public void addTab(String title, Component component) {
		/*
		 * Aggiunti due spazi bianchi alla fine del titolo per lasciare spazio
		 * alla X di chiusura tab.
		 */
		super.addTab(title + "  ", component);
	}

	/**
	 * Restituisce il titolo della tab al indice indicato.
	 * 
	 * @param index
	 *            L'indice della tab.
	 * @return Il titolo della tab.
	 * @author Maurizio
	 */
	public String getTabTitleAt(int index) {
		return super.getTitleAt(index).trim();
	}

	/**
	 * Classe destinata alla gestione della X di chiusura.
	 * 
	 * @author Maurizio
	 */
	private class TabCloseUI extends MouseAdapter {
		private ClosableTabbedPane tabbedPane;
		private int closeX = 0, closeY = 0, mouseEventX = 0, mouseEventY = 0;
		private int selectedTab;
		private static final int CLOSE_X_RECT_WIDTH = 8, CLOSE_X_RECT_HEIGHT = 8;
		private Rectangle closeX_Rectangle = new Rectangle(0, 0, CLOSE_X_RECT_WIDTH, CLOSE_X_RECT_HEIGHT);

		public TabCloseUI(ClosableTabbedPane pane) {
			this.tabbedPane = pane;
			this.tabbedPane.addMouseMotionListener(this);
			this.tabbedPane.addMouseListener(this);
		}

		public void mouseClicked(MouseEvent me) {
			if (this.isCloseXRectUnderMouse(me.getX(), me.getY())) {
				boolean isToCloseTab = isToCloseTab(selectedTab);
				if (isToCloseTab && selectedTab > -1) {
					this.tabbedPane.removeTabAt(selectedTab);
				}
				this.selectedTab = this.tabbedPane.getSelectedIndex();
				this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		public void mouseMoved(MouseEvent me) {
			/*
			 * Mantiene aggiornata la posizione corrente del mouse.
			 */
			this.mouseEventX = me.getX();
			this.mouseEventY = me.getY();
			if (isMouseOverTab(mouseEventX, mouseEventY)) {
				this.controlCursor();
				this.tabbedPane.repaint();
			}
		}

		/**
		 * Cambia il cursore del mouse a seconda della sua posizione.
		 * 
		 * @author Maurizio
		 */
		private void controlCursor() {
			if (this.tabbedPane.getTabCount() > 0)
				if (this.isCloseXRectUnderMouse(mouseEventX, mouseEventY)) {
					this.tabbedPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
					if (selectedTab > -1) {
						this.tabbedPane.setToolTipTextAt(selectedTab, "Close " + tabbedPane.getTabTitleAt(selectedTab));
					}
				} else {
					this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if (selectedTab > -1) {
						this.tabbedPane.setToolTipTextAt(selectedTab, tabbedPane.getTabTitleAt(selectedTab));
					}
				}
		}

		/**
		 * Controlla se il mouse si trova al di sopra del rettangolo che
		 * delimita la X di chiusura.
		 * 
		 * @param x
		 *            La x della posizione dl mouse.
		 * @param y
		 *            La y della posizione del mosue.
		 * @returnb true se il mouse si trova al di sopra della X di chiusura,
		 *          false altrimenti.
		 * @author Maurizio
		 */
		private boolean isCloseXRectUnderMouse(int x, int y) {
			this.closeX_Rectangle.x = closeX;
			this.closeX_Rectangle.y = closeY;
			return closeX_Rectangle.contains(x, y);
		}

		/**
		 * Disegna la X di chiusura per la tab attualmente mostrata a schermo.
		 * 
		 * @param g
		 *            Il contesto grafico in cui disegnare.
		 * @author Maurizio
		 */
		public void paint(Graphics g) {

			int tabCount = this.tabbedPane.getTabCount();
			for (int j = 0; j < tabCount; j++) {
				/*
				 * Disegna la X sulla tab attualmente mostrata a schermo.
				 */
				if (this.tabbedPane.getComponent(j).isShowing()) {

					/**
					 * Ricalcola le coordinate x, y del vertice in alto a
					 * sinistra del rettangolo contenente la X di chiusura da
					 * disegnare.
					 */
					int x = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - CLOSE_X_RECT_WIDTH - 5;
					int y = tabbedPane.getBoundsAt(j).y + 5;
					drawCloseX(g, x, y);
					break;
				}
			}

			/*
			 * Disegna la X sulla Tab con il mouse sopra.
			 */
			if (isMouseOverTab(mouseEventX, mouseEventY)) {
				drawCloseX(g, closeX, closeY);
			}
		}

		/**
		 * Delega il metodo drawColoredCloseX per disegnare la X di chiusura.
		 * 
		 * @param g
		 *            Il contesto grafico in cui disegnare.
		 * @param x
		 *            La x del vertice superiore sinistro del rettangolo che
		 *            delimita la X di chiusura.
		 * @param y
		 *            La y del vertice superiore sinistro del rettangolo che
		 *            delimita la X di chiusura.
		 * @author Maurizio
		 */
		private void drawCloseX(Graphics g, int x, int y) {
			if (this.tabbedPane != null && this.tabbedPane.getTabCount() > 0) {
				Graphics2D g2 = (Graphics2D) g;
				this.drawColoredCloseX(g2, isCloseX_UnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
			}
		}

		/**
		 * Disegna la X di chiusura utilizzando il colore passato in input.
		 * 
		 * @param g2
		 *            Il contesto grafico in cui disegnare.
		 * @param color
		 *            Il colore da utilizzare per disegnare la X di chiusura.
		 * @param x
		 *            La x del vertice superiore sinistro del rettangolo che
		 *            delimita la X di chiusura.
		 * @param y
		 *            La y del vertice superiore sinistro del rettangolo che
		 *            delimita la X di chiusura.
		 * @author Maurizio
		 */
		private void drawColoredCloseX(Graphics2D g2, Color color, int x, int y) {
			g2.setStroke(new BasicStroke(3, BasicStroke.JOIN_BEVEL, BasicStroke.CAP_ROUND));
			g2.setColor(Color.BLACK);

			/*
			 * Disegna la linea \ della X.
			 */
			g2.drawLine(x, y, x + CLOSE_X_RECT_WIDTH, y + CLOSE_X_RECT_HEIGHT);

			/*
			 * Disegna la linea / della X.
			 */
			g2.drawLine(x + CLOSE_X_RECT_WIDTH, y, x, y + CLOSE_X_RECT_HEIGHT);
			g2.setColor(color);
			g2.setStroke(new BasicStroke(2, BasicStroke.JOIN_BEVEL, BasicStroke.CAP_ROUND));

			/*
			 * Disegna la linea \ della X.
			 */
			g2.drawLine(x, y, x + CLOSE_X_RECT_WIDTH, y + CLOSE_X_RECT_HEIGHT);

			/*
			 * Disegna la linea / della X.
			 */
			g2.drawLine(x + CLOSE_X_RECT_WIDTH, y, x, y + CLOSE_X_RECT_HEIGHT);
		}

		/**
		 * Controlla se il mouse si trova al di sopre del rettangolo della
		 * Close_X.
		 * 
		 * @param x
		 *            La x della posizione del mouse.
		 * @param y
		 *            La y della posizion del mouse.
		 * @return true se il mouse si trova al di sopra del rettangolo della
		 *         Close_X, false altrimenti.
		 * @author Maurizio
		 */
		private boolean isCloseX_UnderMouse(int x, int y) {
			if (Math.abs(x - mouseEventX) < CLOSE_X_RECT_WIDTH && Math.abs(y - mouseEventY) < CLOSE_X_RECT_HEIGHT) {
				return true;
			} else {
				return false;
			}
		}

		/**
		 * Controlla se il mouse si trova al di sopra di una tab.
		 * 
		 * @param x
		 *            La x della posizione del mouse.
		 * @param y
		 *            La y della posizion del mouse.
		 * @return true se il mouse si trova al di sopra di una tab, false
		 *         altrimenti.
		 * @author Maurizio
		 */
		private boolean isMouseOverTab(int x, int y) {
			int tabCount = tabbedPane.getTabCount();
			for (int j = 0; j < tabCount; j++) {
				/*
				 * Controlla se il mouse si trova al di sopra di una tab.
				 */
				if (tabbedPane.getBoundsAt(j).contains(mouseEventX, mouseEventY)) {
					selectedTab = j;

					/*
					 * Aggiorna le coordinate del vertice in alto a sinistra del
					 * rettangolo contenente la X di chiusura.
					 */
					closeX = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - CLOSE_X_RECT_WIDTH - 5;
					closeY = tabbedPane.getBoundsAt(j).y + 5;

					return true;
				}
			}

			return false;
		}

	}

	/**
	 * Da l'ok oppure nega la chiusura di una tab.
	 * 
	 * @author Maurizio
	 * @param tabIndex
	 *            L'indice della tab da chiudere.
	 * @return true se puo essere chiusa, false altrimenti.
	 */
	public boolean isToCloseTab(int tabIndex) {
		/**
		 * Permette la chiusura di tutte le tab ad eccezione della prima che ha
		 * appunto indice uguale a 0.
		 */
		return tabIndex > 0;
	}

	private static final long serialVersionUID = 4127918357842462304L;
	private final TabCloseUI closeUI = new TabCloseUI(ClosableTabbedPane.this);

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new ClosableTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		tabbedPane.addTab("Tab1", new JPanel());
		tabbedPane.addTab("Tab2", new JPanel());
		tabbedPane.addTab("Tab3", new JPanel());

		frame.add(tabbedPane);
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
