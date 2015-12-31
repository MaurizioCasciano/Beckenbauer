package graphics;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Assets {

	/**
	 * Loads mages.
	 */
	public static void init() {
		Assets.greenField = ImageLoader.loadImage("GreenField1280x720.jpg");
		// Assets.ballSchoes =
		// ImageLoader.loadImage("Hello3d&Schoes1280-720.jpg");
		Assets.loginBackground = ImageLoader.loadImage("blue_circles_abstraction_81512_1920x1080.jpg");
		Assets.cubeWallpaper = ImageLoader.loadImage("cube.jpg");
		Assets.lava = ImageLoader.loadImage("lava-2560x1600.jpg");
		Assets.cubes = ImageLoader.loadImage("Black&BlueCubes1280x720.jpg");
		Assets.stadium = ImageLoader.loadImage("mappa_stadio_1400.png");
		Assets.stadiumMap = ImageLoader.loadImage("mappa_stadio_1400.png");

		Assets.managerIcon = new ImageIcon(ImageLoader.loadImage("Office-Customer-Male-Light-icon.png"));
		Assets.customerIcon = new ImageIcon(ImageLoader.loadImage("User-Clients-icon.png"));
		Assets.login = new ImageIcon(ImageLoader.loadImage("login.png"));
		Assets.registerIcon = new ImageIcon(ImageLoader.loadImage("Add-Male-User.png"));
	}

	public static BufferedImage getSoccerPitch() {
		if (Assets.soccerPitch == null) {
			Assets.soccerPitch = ImageLoader.loadImage("footballpitch.png");
		}

		return Assets.soccerPitch;
	}

	public static ImageIcon getCustomerIcon() {
		if (Assets.customerIcon == null) {
			Assets.customerIcon = new ImageIcon(ImageLoader.loadImage("User-Clients-icon.png"));
		}

		return Assets.customerIcon;
	}

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

	public static ImageIcon getRegisterIcon() {
		if (Assets.registerIcon == null) {
			Assets.registerIcon = new ImageIcon(ImageLoader.loadImage("Add-Male-User.png"));
		}

		return Assets.registerIcon;
	}

	public static ImageIcon getBlueSeat() {
		if (Assets.blueSeat == null) {
			Assets.blueSeat = new ImageIcon(ImageLoader.loadImage("Blue-Seat-icon.png"));
		}

		return blueSeat;
	}

	public static ImageIcon getBlueSeatRollover() {
		if (blueSeatRollover == null) {
			blueSeatRollover = new ImageIcon(ImageLoader.loadImage("Blue-Seat-icon-Rollover.png"));
		}

		return blueSeatRollover;
	}

	public static BufferedImage greenField, ballSchoes, cubeWallpaper, loginBackground, lava, cubes, stadium,
			stadiumMap, soccerPitch;
	public static ImageIcon managerIcon, customerIcon, login, registerIcon;

	public static ImageIcon blueSeat, blueSeatRollover;
}
