import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	private Pixel[][] imageArray;
	private int height;
	private int width;

	public Image(String imagePath) {
		File image = new File(imagePath);
		imageToArray(image);
	}

	private void imageToArray(File imageFile) {
		BufferedImage bufferedImage;
		try {
			bufferedImage = ImageIO.read(imageFile);
			this.height = bufferedImage.getHeight();
			this.width = bufferedImage.getWidth();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}

		for (int i = 0; i < bufferedImage.getHeight(); i++) {
			for (int j = 0; j < bufferedImage.getWidth(); j++) {

				int[] rgbValues = bufferedImage.getRGB(i, j,
						bufferedImage.getWidth(), bufferedImage.getHeight(),
						null, 0, bufferedImage.getWidth());

				Pixel pixel = new Pixel(i, j, (rgbValues[100] >> 16) & 0xFF,
						(rgbValues[100] >> 8) & 0xFF,
						(rgbValues[100] >> 0) & 0xFF,
						(rgbValues[100] >> 24) & 0xFF);

				imageArray[i][j] = pixel;
			}
		}

	}
}
