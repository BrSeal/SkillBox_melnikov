import lombok.Getter;
import lombok.Setter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;

@Getter
public class SiteMap
{
	private static final String indentSymbol = "\t";
	
	private String root;
	@Setter
	private String outputPath;
	
	private HashMap<String, HashSet<String>> links;
	private HashSet<String> allLinks;
	
	public SiteMap(String rootUrl) {
		root = rootUrl;
		outputPath="out//"+root.split("/")[2]+".txt";
		allLinks = new HashSet<>();
		links = new HashMap<>();
	}
	
	public void createSiteMap(){
		recursiveAddictionToMap(root);}
	
	private void recursiveAddictionToMap(String url){
		if(links.containsKey(url)) return;
		
		allLinks.add(url);
		HashSet<String> refs = getRefs(url);
		
		links.put(url, refs);
		for (String ref : refs) recursiveAddictionToMap(ref);
	}
	
	public void printSiteMap() throws IOException {
		FileWriter writer=new FileWriter(new File(outputPath),true);
		recursivePrintWithIndent(root, "",writer);
		writer.flush();
	}
	
	private void recursivePrintWithIndent(String url, String indent,FileWriter writer) throws IOException {
		writer.write(indent + url+"\n");
		if(links.get(url).isEmpty()) return;
		for (String link : links.get(url)) recursivePrintWithIndent(link, indent + indentSymbol,writer);
	}
	
	public HashSet<String> getRefs(String url){
		HashSet<String> result = new HashSet<>();
		try {
			Document doc = Jsoup.connect(url).maxBodySize(0).get();
			Elements references = doc.select("a[href]");
			
			for (Element element : references) {
				String absUrl = element.absUrl("href");
				if (isValidUrl(absUrl) && !allLinks.contains(absUrl)) {
					allLinks.add(absUrl);
					result.add(absUrl);
				}
			}
		}catch (Exception ex){
			ex.printStackTrace();
		}
		return result;
	}
	
	private boolean isValidUrl(String absUrl) {
		return absUrl.startsWith(root) && absUrl.endsWith("/");
	}
}