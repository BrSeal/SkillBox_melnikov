import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Objects;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class SiteMap
{
	private static final char indentSymbol = '\t';
	
	@Getter
	private String root;
	
	@Getter
	private TreeSet<String> links;
	
	public SiteMap(String root) {
		this.root = root;
		links=new TreeSet<>();
	}
	
	public TreeSet<String> getRefs(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		
		return doc.select("a[href]")
				  .stream()
	  			  .map(e -> {
			String link = e.attr("href");
			if (!link.startsWith(url) && !link.startsWith("/") || !link.endsWith("/")) { return null; }
			if (link.startsWith("/")) { return root + link.substring(1); }
			return link;
		})
				  .filter(Objects::nonNull)
				  .collect(Collectors.toCollection(TreeSet::new));
	}
	
	public void createSiteMap() throws IOException{
		createSiteMap(root);
	}
	
	private void createSiteMap(String url) throws IOException{
		if(links.contains(url)) return;
		links.add(url);
		for (String link: getRefs(url))
			createSiteMap(link);
	}
	
	public void printSiteMap(){
		for (String link : links) {
			int countIndent = -3;
			for (char c : link.toCharArray()) if(c=='/') countIndent++;
			for(int i=0;i<countIndent;i++) System.out.print(indentSymbol);
			System.out.println(link);
		}
	}
}