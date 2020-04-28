import org.junit.jupiter.api.Test;

import java.io.IOException;

class SiteMapTest
{
	
	@Test
	void jazzFriendsTest() throws IOException {
		String url = "http://jazz-friends.ru/";
		Mapper.mapSite(url);
	}
	
	@Test
	void skillBoxTest() throws IOException {
		String url = "https://skillbox.ru/";
		Mapper.mapSite(url);
	}
	
	@Test
	void tProggerTest() throws IOException {
		String url = "https://tproger.ru/";
		Mapper.mapSite(url);
	}
	
	@Test
	void lentaTest() throws IOException {
		String url = "https://lenta.ru/";
		Mapper.mapSite(url);
	}
}