import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

class SiteMapTest
{
	
	@Test
	void getRefsSkillBox() throws IOException {
		String url="http://skillbox.ru/";
		List<String> links=SiteMap.getRefs(url);
		links.forEach(System.out::println);
	}
	
	@Test
	void getRefsJazzFriends() throws IOException {
		String url="http://jazz-friends.ru/";
		List<String> links=SiteMap.getRefs(url);
		links.forEach(System.out::println);
	}
	
	@Test
	void getRefsJavaRush() throws IOException {
		String url="https://javarush.ru/";
		List<String> links=SiteMap.getRefs(url);
		links.forEach(System.out::println);
	}
	
	@Test
	void getRefsLenta() throws IOException {
		String url="https://lenta.ru";
		List<String> links=SiteMap.getRefs(url);
		links.forEach(System.out::println);
	}
	
	@Test
	void getRefsTProger() throws IOException {
		String url="https://tproger.ru/";
		List<String> links=SiteMap.getRefs(url);
		links.forEach(System.out::println);
	}
}