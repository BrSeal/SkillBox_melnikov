import org.imgscalr.Scalr;

import javax.imageio.ImageIO;
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
		this.newWidth = newWidth*4;
	}
	
	@Override
	public void run() {
		try {
			for (File file : files) {
				BufferedImage image = ImageIO.read(file);
				if (image == null) {
					continue;
				}
				
				double proportion =(double)image.getWidth() / newWidth;
				
				int newHeight = (int) Math.round(image.getHeight() / proportion);
				BufferedImage newImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
				
				for (int x = 0; x < newWidth; x++) {
					for (int y = 0; y < newHeight; y++) {
						int rgb = image.getRGB((int)Math.round(x * proportion), (int)Math.round(y * proportion));
						newImage.setRGB(x, y, rgb);
					}
				}
				
				File newFile = new File(dstFolder + "/" + file.getName());
				newImage= Scalr.resize(newImage, Scalr.Method.ULTRA_QUALITY,newWidth/4,newHeight/4);
				ImageIO.write(newImage, "jpg", newFile);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Duration: " + (System.currentTimeMillis() - start));
		
	}
}