import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.InvalidPathException;

import static SizeCalculator.SizeCalculator.*;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance (TestInstance.Lifecycle.PER_CLASS)
class SizeCalculatorTest
{
	private File[] folders;
	private File[] files;
	
	/**
	 * folder0
	 * +-file1.txt
	 * +-file2.txt
	 * +-folder1
	 * +-folder2
	 * |    |   +-file3
	 * |    |   +-emptyFile.txt
	 * +-emptyFolder
	 */
	@BeforeEach
	public void init() {
		initFolders();
		initFiles();
		
	}
	
	@Test
	void calculateNullPath() {
		assertThrows(InvalidPathException.class, () -> calculate(null));
	}
	
	@Test
	void calculateEmptyPath() {
		assertThrows(InvalidPathException.class, () -> calculate(""));
	}
	
	@Test
	void calculateEmptyFile() throws Exception {
		long actual = calculate(files[3].getPath());
		assertEquals(0, actual);
	}
	
	@Test
	void calculateFileSize() throws Exception {
		long actual = calculate(files[0].getPath());
		assertEquals(1, actual);
	}
	
	@Test
	void calculateFolderSize1() throws Exception {
		long actual = calculate(folders[3].getPath());
		assertEquals(0, actual);
	}
	
	@Test
	void calculateFolderSize2() throws Exception {
		long actual = calculate(folders[2].getPath());
		assertEquals(1, actual);
	}
	
	@Test
	void calculateFolderSize3() throws Exception {
		long actual = calculate(folders[1].getPath());
		assertEquals(1, actual);
	}
	
	@Test
	void calculateFolderSize4() throws Exception {
		long actual = calculate(folders[0].getPath());
		assertEquals(3, actual);
	}
	
	@Test
	void formatTest1() {
		String actual= formatSizeManual(0);
		String expected="0 bytes";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest2() {
		String actual= formatSizeManual(1);
		String expected="1 b";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest3() {
		String actual= formatSizeManual(BYTES_IN_KB);
		String expected="1 Kb";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest4() {
		String actual= formatSizeManual(BYTES_IN_MB);
		String expected="1 Mb";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest5() {
		String actual= formatSizeManual(BYTES_IN_GB);
		String expected="1 Gb";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest6() {
		String actual= formatSizeManual(BYTES_IN_GB+BYTES_IN_KB+BYTES_IN_MB+45);
		String expected="1 Gb 1 Mb 1 Kb 45 b";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest7() {
		String actual= formatSizeManual(BYTES_IN_GB*12+BYTES_IN_KB*345+BYTES_IN_MB*77+945);
		String expected="12 Gb 77 Mb 345 Kb 945 b";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest8() {
		String actual= formatSizeManual(BYTES_IN_GB*12+945);
		String expected="12 Gb 945 b";
		assertEquals(expected,actual);
	}
	
	@Test
	void formatTest9() {
		String actual= formatSizeManual(BYTES_IN_KB*345+BYTES_IN_MB*77+945);
		String expected="77 Mb 345 Kb 945 b";
		assertEquals(expected,actual);
	}
	
	
	@AfterAll
	void clear() {
		for (File i : files) i.delete();
		for (int i = 3; i >= 0; i--) {
			folders[i].delete();
		}
	}
	
	//=======================================================================
	private void initFolders() {
		folders = new File[4];
		folders[0] = new File("target/folder0");
		folders[1] = new File("target/folder0/folder1");
		folders[2] = new File("target/folder0/folder1/folder2");
		folders[3] = new File("target/folder0/folder1/emptyFolder");
		try {
			for (File dir : folders) {
				dir.mkdir();
				dir.createNewFile();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void initFiles() {
		files = new File[4];
		files[0] = new File("target/folder0/file1.txt");
		files[1] = new File("target/folder0/file2.txt");
		files[2] = new File("target/folder0/folder1/folder2/file3.txt");
		files[3] = new File("target/folder0/folder1/folder2/emptyFile.txt");
		try {
			for (File dir : files) dir.createNewFile();
			
			
			for (int i = 0; i < 3; i++) {
				FileWriter writer = new FileWriter(files[i]);
				writer.write(i + 50);
				writer.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}