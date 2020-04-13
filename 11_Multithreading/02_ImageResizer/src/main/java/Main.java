import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Main
{
	private static final String srcFolder = "src/main/resources";
	private static final String dstFolder = "src/main/dest";
	private static final File srcDir = new File(srcFolder);
	
	public static void main(String[] args) throws IOException {
		File[] files = srcDir.listFiles();
		File dst=new File(dstFolder);
		dst.mkdir();
		dst.createNewFile();
		
		int[] countFilesToThread=countFilesToThreads(files);
		
		long start = System.currentTimeMillis();
		int initPos=0;
		for (int a:countFilesToThread) {
			File[] filesToThread= Arrays.copyOfRange(files,initPos,a+initPos);
			initPos+=a;
			new Thread(new ImgResizer(filesToThread,dstFolder,start,300)).start();
		}
	}
	
	private static int[] countFilesToThreads(File[] files){
		int filesRemaining = files.length;
		int processorsCount = Runtime.getRuntime().availableProcessors();
		int avgInThread = files.length / processorsCount;
		int[] countFilesToThread=new int[processorsCount];
		
		for(int i=0;i<processorsCount;i++) {
			countFilesToThread[i]=avgInThread;
			filesRemaining-=avgInThread;
		}
		for(int i=0;i<processorsCount&&filesRemaining>0;i++) {
			countFilesToThread[i]+=1;
			filesRemaining--;
		}
		return countFilesToThread;
	}
}
