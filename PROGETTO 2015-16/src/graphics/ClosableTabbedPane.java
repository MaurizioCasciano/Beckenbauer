/**
 * 
 */
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

import javax.swing.Icon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 * Classe che estende {@link JTabbedPane} per permettere la chiusura delle Tab.
 * 
 * @author Maurizio
 */
public class ClosableTabbedPane extends JTabbedPane {

	public ClosableTabbedPane() {
		super();
	}

	public ClosableTabbedPane(int tabPlacement) {
		super(tabPlacement);
	}

	public ClosableTabbedPane(int tabPlacement, int tabLayoutPolicy) {
		super(tabPlacement, tabLayoutPolicy);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		this.closeUI.paint(g);
	}

	@Override
	public void addTab(String title, Icon icon, Component component, String tip) {
		super.addTab(title + "  ", icon, component, tip);
	}

	@Override
	public void addTab(String title, Icon icon, Component component) {
		super.addTab(title + "  ", icon, component);
	}

	@Override
	public void addTab(String title, Component component) {
		super.addTab(title + "  ", component);
	}

	public String getTabTitleAt(int index) {
		return super.getTitleAt(index).trim();
	}

	private class TabCloseUI extends MouseAdapter {
		private ClosableTabbedPane tabbedPane;
		private int closeX = 0, closeY = 0, mouseEventX = 0, mouseEventY = 0;
		private int selectedTab;
		private static final int WIDTH = 8, HEIGHT = 8;
		private Rectangle rectangle = new Rectangle(0, 0, WIDTH, HEIGHT);

		public TabCloseUI(ClosableTabbedPane pane) {
			this.tabbedPane = pane;
			this.tabbedPane.addMouseMotionListener(this);
			this.tabbedPane.addMouseListener(this);
		}

		public void mouseClicked(MouseEvent me) {
			if (closeUnderMouse(me.getX(), me.getY())) {
				boolean isToCloseTab = tabAboutToClose(selectedTab);
				if (isToCloseTab && selectedTab > -1) {
					this.tabbedPane.removeTabAt(selectedTab);
				}
				this.selectedTab = this.tabbedPane.getSelectedIndex();
				this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
		}

		public void mouseMoved(MouseEvent me) {
			this.mouseEventX = me.getX();
			this.mouseEventY = me.getY();
			if (mouseOverTab(mouseEventX, mouseEventY)) {
				this.controlCursor();
				this.tabbedPane.repaint();
			}
		}

		private void controlCursor() {
			if (this.tabbedPane.getTabCount() > 0)
				if (this.closeUnderMouse(mouseEventX, mouseEventY)) {
					this.tabbedPane.setCursor(new Cursor(Cursor.HAND_CURSOR));
					if (selectedTab > -1) {
						this.tabbedPane.setToolTipTextAt(selectedTab, "Close " + tabbedPane.getTitleAt(selectedTab));
					}
				} else {
					this.tabbedPane.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					if (selectedTab > -1) {
						this.tabbedPane.setToolTipTextAt(selectedTab, "");
					}
				}
		}

		private boolean closeUnderMouse(int x, int y) {
			this.rectangle.x = closeX;
			this.rectangle.y = closeY;
			return rectangle.contains(x, y);
		}

		public void paint(Graphics g) {

			int tabCount = this.tabbedPane.getTabCount();
			for (int j = 0; j < tabCount; j++)
				if (this.tabbedPane.getComponent(j).isShowing()) {
					int x = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - WIDTH - 5;
					int y = tabbedPane.getBoundsAt(j).y + 5;
					drawClose(g, x, y);
					break;
				}
			if (mouseOverTab(mouseEventX, mouseEventY)) {
				drawClose(g, closeX, closeY);
			}
		}

		private void drawClose(Graphics g, int x, int y) {
			if (this.tabbedPane != null && this.tabbedPane.getTabCount() > 0) {
				Graphics2D g2 = (Graphics2D) g;
				this.drawColored(g2, isUnderMouse(x, y) ? Color.RED : Color.WHITE, x, y);
			}
		}

		private void drawColored(Graphics2D g2, Color color, int x, int y) {
			g2.setStroke(new BasicStroke(2, BasicStroke.JOIN_BEVEL, BasicStroke.CAP_ROUND));
			g2.setColor(Color.BLACK);
			g2.drawLine(x, y, x + WIDTH, y + HEIGHT);
			g2.drawLine(x + WIDTH, y, x, y + HEIGHT);
			g2.setColor(color);
			//g2.setStroke(new BasicStroke(2, BasicStroke.JOIN_BEVEL, BasicStroke.CAP_ROUND));
			g2.drawLine(x, y, x + WIDTH, y + HEIGHT);
			g2.drawLine(x + WIDTH, y, x, y + HEIGHT);
		}

		private boolean isUnderMouse(int x, int y) {
			if (Math.abs(x - mouseEventX) < WIDTH && Math.abs(y - mouseEventY) < HEIGHT) {
				return true;
			} else {
				return false;
			}
		}

		private boolean mouseOverTab(int x, int y) {
			int tabCount = tabbedPane.getTabCount();
			for (int j = 0; j < tabCount; j++)
				if (tabbedPane.getBoundsAt(j).contains(mouseEventX, mouseEventY)) {
					selectedTab = j;
					closeX = tabbedPane.getBoundsAt(j).x + tabbedPane.getBoundsAt(j).width - WIDTH - 5;
					closeY = tabbedPane.getBoundsAt(j).y + 5;
					return true;
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
	public boolean tabAboutToClose(int tabIndex) {
		return tabIndex > 0;
	}

	private static final long serialVersionUID = 4127918357842462304L;
	private final TabCloseUI closeUI = new TabCloseUI(ClosableTabbedPane.this);

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JTabbedPane tabbedPane = new ClosableTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
		tabbedPane.addTab("Tab1", new JPanel());

		frame.add(tabbedPane);
		frame.setSize(1000, 600);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
}
