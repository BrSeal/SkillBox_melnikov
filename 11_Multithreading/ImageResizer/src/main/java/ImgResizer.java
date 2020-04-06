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
				
				image=Scalr.resize(image, Scalr.Method.SPEED,newWidth,newHeight);
				image= Scalr.resize(image, Scalr.Method.ULTRA_QUALITY,newWidth/4,newHeight/4);
				File newFile = new File(dstFolder + "/" + file.getName());
				ImageIO.write(image, "jpg", newFile);
			}
		}catch (Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("Duration: " + (System.currentTimeMillis() - start));
		
	}
}