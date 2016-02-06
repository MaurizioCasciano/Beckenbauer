package graphics;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import javax.swing.ImageIcon;

/**
 * Classe ultilizzata per ottenere le immagini salvate nella ResourceFolder.
 * 
 * @author Maurizio
 */
public class Assets implements Serializable {

	/**
	 * Restituisce l'icona del Cliente.
	 * 
	 * @return l'icona del Cliente.
	 * @author Maurizio
	 */
	public static ImageIcon getCustomerIcon() {
		if (Assets.customerIcon == null) {
			Assets.customerIcon = new ImageIcon(ImageLoader.loadImage("User-Clients-icon.png"));
		}

		return Assets.customerIcon;
	}

	/**
	 * Restituisce l'icona del Gestore.
	 * 
	 * @return l'icona del Gestore.
	 * @author Maurizio
	 */
	public static ImageIcon getManagerIcon() {
		if (Assets.managerIcon == null) {
			Assets.managerIcon = new ImageIcon(ImageLoader.loadImage("Office-Customer-Male-Light-icon.png"));
		}

		return Assets.managerIcon;
	}

	public static BufferedImage getCubes() {
		if (Assets.cubes == null) {
			Assets.cubes = ImageLoader.loadImage("Black&BlueCubes1280x720.jpg");
		}

		return Assets.cubes;
	}

	/**
	 * Restituisce l'icona di login.
	 * 
	 * @return l'icona di login.
	 * @author Maurizio
	 */
	public static ImageIcon getLoginIcon() {
		if (Assets.login == null) {
			Assets.login = new ImageIcon(ImageLoader.loadImage("login.png"));
		}

		return Assets.login;
	}

	public static BufferedImage getLoginBackground() {
		if (Assets.loginBackground == null) {
			Assets.loginBackground = ImageLoader.loadImage("blue_circles_abstraction_81512_1920x1080.jpg");
		}

		return Assets.loginBackground;
	}

	public static BufferedImage getGreenField() {
		if (Assets.greenField == null) {
			Assets.greenField = ImageLoader.loadImage("GreenField1280x720.jpg");
		}

		return Assets.greenField;
	}

	/**
	 * Restituisce l'icona di registrazione.
	 * 
	 * @return l'icona di registrazione.
	 * @author Maurizio
	 */
	public static ImageIcon getRegisterIcon() {
		if (Assets.registerIcon == null) {
			Assets.registerIcon = new ImageIcon(ImageLoader.loadImage("Add-Male-User.png"));
		}

		return Assets.registerIcon;
	}

	private static final long serialVersionUID = -6046582922518387221L;
	public static BufferedImage greenField, cubeWallpaper, loginBackground, cubes;
	public static ImageIcon managerIcon, customerIcon, login, registerIcon;
}
