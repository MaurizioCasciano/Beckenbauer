package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ImageLoader implements Serializable{

	/**
	 * Loads a BufferedImage.
	 * 
	 * @param path
	 *            The path of the image.
	 * @return The loaded image.
	 */
	public static BufferedImage loadImage(String imageName) {
		try {
			return ImageIO.read(ImageLoader.class.getResource("/images/" + imageName));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}
	
	private static final long serialVersionUID = 2976834870464266114L;
}
