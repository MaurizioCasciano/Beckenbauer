package login;

import java.awt.BorderLayout;
import java.sql.SQLException;
import javax.swing.JFrame;
import graphics.Assets;

public class IdentificationTester {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
		JFrame frame = new JFrame();
		Assets.init();
		frame.add(new IdentificationPanel(Assets.cubes, null), BorderLayout.EAST);
		
		frame.setSize(1200, 700);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
