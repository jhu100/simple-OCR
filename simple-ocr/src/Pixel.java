/**
 * Simple Pixel object that has RED/GREEN/BLUE/ALPHA (RGBA) values and knowledge
 * of its position in an image.
 * 
 * @author Dustin Pho
 * 
 */
public class Pixel {

	/**
	 * x-coordinate
	 */
	public final int X;

	/**
	 * y-coordinate
	 */
	public final int Y;

	/**
	 * red value of RGBA
	 */
	public int RED;

	/**
	 * green value of RGBA
	 */
	public int GREEN;

	/**
	 * blue value of RGBA
	 */
	public int BLUE;

	/**
	 * alpha value of RGBA
	 */
	public int ALPHA;

	/**
	 * Constructs a pixel object.
	 * 
	 * @param x
	 *            x-coordinate of Pixel
	 * @param y
	 *            y-coordinate of Pixel
	 * @param red
	 *            red RGBA value
	 * @param green
	 *            green RGBA value
	 * @param blue
	 *            blue RGBA value
	 * @param alpha
	 *            alpha RGBA value
	 */
	public Pixel(int x, int y, int red, int green, int blue, int alpha) {
		this.X = x;
		this.Y = y;
		this.RED = red;
		this.GREEN = green;
		this.BLUE = blue;
		this.ALPHA = alpha;
	}

}
