import lombok.Getter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.RecursiveTask;

@Getter
public class SiteMapper extends RecursiveTask<Boolean>
{
	@Getter
	private static ConcurrentHashMap<String, HashSet<String>> links = new ConcurrentHashMap<>();
	private static final HashSet<String> allLinks = new HashSet<>();
	
	private String root;
	
	public SiteMapper(String rootUrl) {
		root = rootUrl;
		synchronized (allLinks) {
			allLinks.add(rootUrl);
		}
		links.put(rootUrl, getRefsFrom(rootUrl));
	}
	
	private HashSet<String> getRefsFrom(String url) {
		HashSet<String> result = new HashSet<>();
		try {
			Document doc = Jsoup.connect(url).maxBodySize(0).get();
			Elements references = doc.select("a[href]");
			for (Element element : references) {
				String absUrl = element.absUrl("href");
				synchronized (allLinks) {
					if (isValidUrl(absUrl) && !allLinks.contains(absUrl)) {
						allLinks.add(absUrl);
						result.add(absUrl);
					}
				}
			}
		}catch (Exception ex) {
			System.out.println(ex.getMessage()+" "+url);
		}
		return result;
	}
	
	private boolean isValidUrl(String absUrl) {
		return absUrl.startsWith(root) && absUrl.endsWith("/");
	}
	
	public HashSet<String> getReferences(String url){
		return links.get(url);
	}
	
	@Override
	protected Boolean compute() {
		List<SiteMapper> taskList = new ArrayList<>();
		
		for (String reference : links.get(root)) {
			if (links.containsKey(reference)) { continue; }
			SiteMapper task = new SiteMapper(reference);
			task.fork();
			taskList.add(task);
		}
		taskList.forEach(SiteMapper::join);
		return Boolean.TRUE;
	}
}