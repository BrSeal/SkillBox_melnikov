import org.junit.jupiter.api.Test;

import java.io.IOException;

class HTMLPictureStealerTest
{
	
	@Test
	void tmpTest() throws IOException
	{
		HTMLPictureStealer stealer = new HTMLPictureStealer();
		stealer.getPictures("https://lenta.ru/","target/picturesFromSite");
	}
}
