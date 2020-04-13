import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvParser
{
	private static String outputMessage = "Общий %s по позиции %-40s составляет %d\n";
	
	private long totalIncome, totalSpent;
	
	private HashMap<String, Long> groupMap;
	private File csvFile;
	
	public long getTotalIncome()
	{
		return totalIncome;
	}
	
	public long getTotalSpent()
	{
		return totalSpent;
	}
	
	public HashMap<String,Long> getGroupMap(){
		return (HashMap<String,Long>)groupMap.clone();
	}
	
	public CsvParser(File csvFile)
	{
		groupMap = new HashMap<>();
		this.csvFile = csvFile;
	}
	
	void parse() throws IOException
	{
		List<String> lines = Files.readAllLines(Paths.get(csvFile.getPath()));
		
		ArrayList<String[]> parsedList = lines.stream().map(f -> f.split(",")).skip(1).collect(Collectors.toCollection(ArrayList::new));
		
		totalIncome = 0;
		totalSpent = 0;
		
		for (String[] line : parsedList)
		{
			String key = line[5].split("\\s{4,}")[1].trim();
			if (line[6].equals("0"))
			{
				long sp = Long.parseLong(line[7].replace("\"", ""));
				totalSpent += sp;
				addToMap(key, -sp);
			}
			else
			{
				long in = Long.parseLong(line[6].replace("\"", ""));
				totalIncome += in;
				addToMap(key, in);
			}
		}
	}
	
	private void addToMap(String key, long value)
	{
		if (groupMap.containsKey(key))
		{
			groupMap.put(key, (groupMap.get(key) + value));
		}
		else
		{
			groupMap.put(key, value);
		}
	}
	
	public void printMap()
	{
		for (Map.Entry<String, Long> pair : groupMap.entrySet())
		{
			long val = pair.getValue();
			if (val > 0)
			{
				System.out.printf(outputMessage, "приход", pair.getKey(), val);
			}
			else
			{
				System.out.printf(outputMessage, "расход", pair.getKey(), val * -1);
			}
		}
	}
}
