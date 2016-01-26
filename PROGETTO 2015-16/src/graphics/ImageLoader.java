package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * Classe utilizzata per il caricamento delle immagini da Resource.
 * 
 * @author Maurizio
 */
public class ImageLoader implements Serializable {

	/**
	 * Carica una {@link BufferedImage}.
	 * 
	 * @param imageName
	 *            Il nome dell'immagine da caricare.
	 * @return L'immagine caricata.
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
