import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ForkJoinPool;

public class Mapper
{
	private static final String INDENT_SYMBOL = "\t";
	private static String output;
	private static SiteMapper root;
	
	public static void mapSite(String url)  throws IOException{
		mapSite(url,"out/"+url.split("/")[2]+".txt");
	}
	
	public static void mapSite(String url,String outputPath) throws IOException{
		root=new SiteMapper(url);
		output=outputPath;
		new ForkJoinPool().invoke(root);
		printSiteMap();
	}
	
	public static void printSiteMap() throws IOException {
		FileWriter writer = new FileWriter(new File(output), true);
		recursivePrintWithIndent(root.getRoot(), "", writer);
		writer.flush();
	}
	
	private static void recursivePrintWithIndent(String url, String indent, FileWriter writer) throws IOException {
		writer.write(indent + url + "\n");
		if (root.getReferences(url).isEmpty()) { return; }
		for (String reference : root.getReferences(url)) recursivePrintWithIndent(reference, indent + INDENT_SYMBOL, writer);
	}
}
