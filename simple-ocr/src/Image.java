import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Image class that contains a 2-Dimensional array of Pixel objects.
 * 
 * @author Dustin
 * 
 */
public class Image {

	private Pixel[][] imageArray;
	private int height;
	private int width;

	/**
	 * Creates an Image object.
	 * 
	 * @param imagePath
	 *            path to our image
	 */
	public Image(String imagePath) {
		File image = new File(imagePath);
		imageToArray(image);
	}

	/**
	 * Initializes our height, width and imageArray
	 * 
	 * @param imageFile
	 *            Our image.
	 */
	private void imageToArray(File imageFile) {
		// Creating a BufferedImage and initializing height, width
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imageFile);
			this.height = bufferedImage.getHeight();
			this.width = bufferedImage.getWidth();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error reading image file.");
			return;
		}

		// Initializing our imageArray
		for (int i = 0; i < bufferedImage.getHeight(); i++) {
			for (int j = 0; j < bufferedImage.getWidth(); j++) {

				int rgbaValues = bufferedImage.getRGB(i, j);

				Pixel pixel = new Pixel(i, j, (rgbaValues >> 16) & 0xFF,
						(rgbaValues >> 8) & 0xFF, (rgbaValues >> 0) & 0xFF,
						(rgbaValues >> 24) & 0xFF);

				imageArray[i][j] = pixel;
			}
		}

	}

	/**
	 * Gets height of image
	 * 
	 * @return height in pixels
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Gets width of image
	 * 
	 * @return width in pixels
	 */
	public int getWidth() {
		return width;
	}

}
