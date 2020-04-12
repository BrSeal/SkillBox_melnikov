import org.apache.commons.io.FileUtils;

import java.io.*;

public class FileCopier
{
	public static void copyFilesApache(String from, String to) throws Exception {
		File in = new File(from);
		File dest = new File(to);
		dest.mkdir();
		File outFolder = new File(dest.getPath() + "/" + in.getName());
		FileUtils.copyDirectory(in, outFolder);
	}
	
	public static void copyFilesStreams(String from, String to) throws IOException {
		File in = new File(from);
		File dest = new File(to);
		dest.mkdir();
		File outFolder = new File(dest.getPath() + "/" + in.getName());
		copyFile(in, outFolder);
	}
	
	private static void copyFile(File from, File to) throws IOException {
		if (from.isDirectory()) {
			to.mkdir();
			for (File f : from.listFiles()) {
				copyFile(f, new File(to, f.getName()));
			}
		}
		else {
			try (InputStream is = new FileInputStream(from); OutputStream os = new FileOutputStream(to)) {
				byte[] buffer = new byte[1024];
				int length;
				while ((length = is.read(buffer)) > 0) {
					os.write(buffer, 0, length);
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		copyFilesApache("src\\main\\java\\module01", "target/apacheTo");
		copyFilesStreams("src\\main\\java\\module01", "target/streamTo");
	}
}
