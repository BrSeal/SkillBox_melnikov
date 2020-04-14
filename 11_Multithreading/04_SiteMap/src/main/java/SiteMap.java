import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class SiteMap
{
	
	public static List<String> getRefs(String url) throws IOException {
		Document doc = Jsoup.connect(url).get();
		
		return doc.select("a[href]")
				.stream()
				.map(e -> {
					String ref = e.attr("href");
					if (ref.endsWith("/")) {
						if (ref.startsWith(url)) return ref;
						else if (ref.startsWith("/")) return url + ref.substring(1);
					}
					return null;
				})
				.distinct()
				.filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
}
