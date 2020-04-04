import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImgResizer implements Runnable
{
	private File[] files;
	private String dstFolder;
	private long start;
	private int newWidth;
	
	public ImgResizer(File[] files, String dstFolder, long start, int newWidth) {
		this.files = files;
		this.dstFolder = dstFolder;
		this.start = start;
		this.newWidth = newWidth * 4;
	}
	
	@Override
	public void run() {
		try {
			for (File file : files) {
				BufferedImage image = ImageIO.read(file);
				if (image == null) {
					continue;
				}
				
				int newHeight = (int) Math.round(image.getHeight() / (image.getWidth() / (double) newWidth));
				BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				
				int widthStep = image.getWidth() / newWidth;
				int heightStep = image.getHeight() / newHeight;
				
				for (int x = 0; x < newWidth; x++) {
					for (int y = 0; y < newHeight; y++) {
						int rgb = image.getRGB(x * widthStep, y * heightStep);
						newImage.setRGB(x, y, rgb);
					}
				}
				BufferedImage finalResult = toBufferedImage(
						newImage
						.getScaledInstance(newWidth/4, newHeight/4, Image.SCALE_SMOOTH)
				);
				
				File newFile = new File(dstFolder + "/" + file.getName());
				
				ImageIO.write(finalResult, "jpg", newFile);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Duration: " + (System.currentTimeMillis() - start));
		
		
	}
	
	private static BufferedImage toBufferedImage(Image img) {
		if (img instanceof BufferedImage) {
			return (BufferedImage) img;
		}
		
		BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_RGB);
		
		Graphics2D bGr = bimage.createGraphics();
		bGr.drawImage(img, 0, 0, null);
		bGr.dispose();
		
		return bimage;
	}
}