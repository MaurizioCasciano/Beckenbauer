package graphics;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class Assets {

	/**
	 * Loads mages.
	 */
	public static void init() {
		Assets.greenFiel = ImageLoader.loadImage("GreenField1280x720.jpg");
		//Assets.ballSchoes = ImageLoader.loadImage("Hello3d&Schoes1280-720.jpg");
		Assets.bluCircles = ImageLoader.loadImage("blue_circles_abstraction_81512_1920x1080.jpg");
		Assets.cubeWallpaper = ImageLoader.loadImage("cube.jpg");
		Assets.lava = ImageLoader.loadImage("lava-2560x1600.jpg");
		Assets.cubes = ImageLoader.loadImage("Black&BlueCubes1280x720.jpg");
		Assets.stadium = ImageLoader.loadImage("mappa_stadio_1400.png");
		Assets.stadiumMap = ImageLoader.loadImage("mappa_stadio_1400.png");

		Assets.managerIcon = new ImageIcon(ImageLoader.loadImage("Office-Customer-Male-Light-icon.png"));
		Assets.clients = new ImageIcon(ImageLoader.loadImage("User-Clients-icon.png"));
		Assets.login = new ImageIcon(ImageLoader.loadImage("login.png"));
		Assets.signup = new ImageIcon(ImageLoader.loadImage("Add-Male-User.png"));
	}

	public static ImageIcon getBlueSeat() {
		if (blueSeat == null) {
			blueSeat = new ImageIcon(ImageLoader.loadImage("Blue-Seat-icon.png"));
		}

		return blueSeat;
	}

	public static ImageIcon getBlueSeatRollover() {
		if (blueSeatRollover == null) {
			blueSeatRollover = new ImageIcon(ImageLoader.loadImage("Blue-Seat-icon-Rollover.png"));
		}

		return blueSeatRollover;
	}

	public static BufferedImage greenFiel, ballSchoes, cubeWallpaper, bluCircles, lava, cubes, stadium, stadiumMap;
	public static ImageIcon managerIcon, clients, login, signup;

	public static ImageIcon blueSeat, blueSeatRollover;
}
