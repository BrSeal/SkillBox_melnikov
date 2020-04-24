import org.junit.jupiter.api.Test;

import java.io.IOException;

class SiteMapTest
{
	SiteMap m;
	
	@Test
	void jazzFriendsTest() throws IOException {
		String url = "http://jazz-friends.ru/";
		SiteMap m=new SiteMap(url);
		m.createSiteMap();
		m.printSiteMap();
	}
	
	
	@Test
	void skillBoxTest() throws IOException {
		String url = "https://skillbox.ru/";
		m = new SiteMap(url);
		m.createSiteMap();
		m.printSiteMap();
	}
	
	@Test
	void tProggerTest() throws IOException {
		String url = "https://tproger.ru/";
		m = new SiteMap(url);
		m.createSiteMap();
		m.printSiteMap();
	}
}