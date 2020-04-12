import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class HTMLPictureStealer
{
	public void getPictures(String url, String targetDirPath) throws IOException {
		Document doc = Jsoup.connect(url).get();
		long counter = 0;
		for (Element e : doc.select("img")) {
			String picUrl = e.attr("src");
			if (!picUrl.matches("http.+\\.\\w{3}")) continue;
			
			File picture = new File(targetDirPath + "/" + counter++ + picUrl.replaceFirst(".+(?=\\.\\w{3})", ""));
			FileUtils.copyURLToFile(new URL(picUrl), picture);
		}
	}
}
