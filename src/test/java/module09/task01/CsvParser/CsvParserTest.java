package module09.task01.CsvParser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CsvParserTest
{
	private static CsvParser parser;
	private static HashMap<String,Long> expected;
	
	@BeforeAll
	static void init(){
		File csv=new File("src/test/java/module09/task01/CsvParser/testCsv.csv");
		parser=new CsvParser(csv);
		expected=new HashMap<>();
		expected.put("one",  -3L);
		expected.put("two", -4L);
		expected.put("three", 4L);
	}
	
	@Test
	void parse() throws Exception {
		parser.parse();
		assertEquals(10,parser.getTotalIncome());
		assertEquals(13,parser.getTotalSpent());
		assertEquals(expected,parser.getGroupMap());
	}
	
	@Test
	void homeworkModule9() throws Exception{
	File csv=new File("src/main/resources/09_FilesAndNetwork/files/movementList.csv");
		parser=new CsvParser(csv);
		parser.parse();
		
		System.out.println("Общий доход:  "+parser.getTotalIncome());
		System.out.println("Общий расход: "+parser.getTotalSpent());
		System.out.println("\nРазбивка расходов:");
		parser.printMap();
	}
}