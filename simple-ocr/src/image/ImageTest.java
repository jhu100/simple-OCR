package image;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.net.MalformedURLException;
import java.net.URL;
import org.junit.Test;

/**
 * Very simple tests for Image and Pixel classes.
 * 
 * @author Dustin Pho
 * 
 */
public class ImageTest {

	Image	testImage;

	/**
	 * Called before each test method.
	 */
	public void setUp() {
		// image is 4 blocks of solid color
		//	________________
		//	|		|		|
		//	| white	| red	|
		//	|_______|_______|
		//	|		|		|
		//	| green	| blue	|
		//	|_______|_______|
		
		URL image;		
		try {
			image = new URL(
					"https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQi8IcXDXlZBWhm_xnpe1rn-XvAfeYPwNmhErnzFU1TBWkv_vbz_lEtbP1e");
		}
		catch (MalformedURLException e) {
			e.printStackTrace();
			return;
		}
		testImage = new Image(image);
	}

	/**
	 * Tests for initializing of image into pixel array
	 */
	@Test
	public void testImage() {
		// test that all pixels were initialized in the array
		for (Pixel p : testImage.getPixels()) {
			assertNotNull(p);
		}

		Pixel p;
		// checking that the top left pixel is white
		p = testImage.getPixel(0, 0);
		assertTrue(p.RED > 250 && p.GREEN > 250 && p.BLUE > 250);
		// checking that the top right pixel is red
		p = testImage.getPixel(testImage.getWidth() - 1, 0);
		assertTrue(p.RED > 250 && p.GREEN < 10 && p.BLUE < 10);
		// checking that the bottom left pixel is green
		p = testImage.getPixel(0, testImage.getHeight() - 1);
		assertTrue(p.RED < 10 && p.GREEN > 250 && p.BLUE < 10);
		// checking that the bottom right pixel is blue
		p = testImage.getPixel(testImage.getWidth() - 1, testImage.getHeight() - 1);
		assertTrue(p.RED < 10 && p.GREEN < 10 && p.BLUE > 250);
	}

}
