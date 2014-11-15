package image;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

/**
 * Image class that contains a 2-Dimensional array of Pixel objects.
 * 
 * @author Dustin Pho
 */
public class Image {
	// Values for ARGB extend from 0 to 255
	private final static int	MAX_RGB_VALUE			= 255;
	private final static int	MINIMUM_RGB_VALUE		= 0;

	// Threshold for alpha value visibility.
	private final static int	ALPHA_THRESHOLD			= 150;
	// Threshold for luminosity
	private final static int	LUMINOSITY_THRESHOLD	= 150;

	// Orientation of pixels in an image
	// (0, 0) is located at the top left of the image.
	// X increases to the left.
	// Y increases downwards.
	// Thus, (maxWidth, maxHeight) will be located at the bottom right.
	private Pixel[]				imageArray;

	private int					height;
	private int					width;

	/**
	 * Creates an Image object.
	 * 
	 * @param image
	 *            path to our image
	 */
	public Image(URL image) {
		imageToArray(image);
	}

	/**
	 * Initializes our height, width and imageArray
	 * 
	 * @param imageFile
	 *            Our image.
	 */
	private void imageToArray(URL imageURL) {
		// Creating a BufferedImage and initializing height, width
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imageURL);
			this.height = bufferedImage.getHeight();
			this.width = bufferedImage.getWidth();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
			return;
		}

		// initialize imageArray
		imageArray = new Pixel[bufferedImage.getWidth() * bufferedImage.getHeight()];

		// Initializing our imageArray
		for (int x = 0; x < bufferedImage.getWidth(); x++) {
			for (int y = 0; y < bufferedImage.getHeight(); y++) {

				// Grabbing ARGB from each pixel in the image
				// 32 bits, first 8 bits are the alpha value, second 8 bits are
				// the red value, third 8 bits are the green value, last 8 bits
				// are the blue value
				int rgbaValues = bufferedImage.getRGB(x, y);

				// bit fiddling to find individual ARGB values
				Pixel pixel = new Pixel(x, y, (rgbaValues >> 16) & 0xFF, (rgbaValues >> 8) & 0xFF,
						(rgbaValues >> 0) & 0xFF, (rgbaValues >> 24) & 0xFF);

				imageArray[getIndex(x, y)] = pixel;
			}
		}

	}

	/**
	 * Turns the image into black and white based on its luminosity and alpha values.
	 */
	public void blackAndWhite() {
		for (Pixel p : getPixels()) {
			if (computeLuminosity(p) > LUMINOSITY_THRESHOLD && p.ALPHA > ALPHA_THRESHOLD)
				setToBlack(p);
			else
				setToWhite(p);
		}
	}

	/**
	 * Gets an individual pixel.
	 * 
	 * @param x
	 *            x coordinate of pixel
	 * @param y
	 *            y coordinate of pixel
	 * @return pixel at (x, y)
	 */
	public Pixel getPixel(int x, int y) {
		return imageArray[getIndex(x, y)];
	}

	/**
	 * Grabs all pixels.
	 * 
	 * @return array of pixels in the image
	 */
	public Pixel[] getPixels() {
		return imageArray;
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

	/**
	 * Calculates the index of pixel depending on it's x and y coordinate. The
	 * pixel array for an image is 1-dimensional.
	 * 
	 * @param x
	 *            x coordinate
	 * @param y
	 *            y coordinate
	 * @return correct index in the array
	 */
	private int getIndex(int x, int y) {
		return x + y * width;
	}

	/**
	 * Finds the luminosity of the pixel.
	 * 
	 * @param p
	 *            pixel
	 * @return average of the pixel's RGB values
	 */
	private int computeLuminosity(Pixel p) {
		return (int) (p.RED * 0.299 + p.GREEN * 0.587 + p.BLUE * 0.114);
	}

	/**
	 * Sets the pixel to black (0, 0, 0) and alpha to 255.
	 * 
	 * @param p
	 *            pixel to change
	 */
	private void setToBlack(Pixel p) {
		p.setColor(MINIMUM_RGB_VALUE, MINIMUM_RGB_VALUE, MINIMUM_RGB_VALUE, MAX_RGB_VALUE);
	}

	/**
	 * Sets the pixel to white (255, 255, 255) and alpha to 255.
	 * 
	 * @param p
	 *            pixel to change
	 */
	private void setToWhite(Pixel p) {
		p.setColor(MAX_RGB_VALUE, MAX_RGB_VALUE, MAX_RGB_VALUE, MAX_RGB_VALUE);
	}
}
