package module09.task02.FIleCopier;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

import static module09.task02.FIleCopier.FileCopier.copyFilesApache;
import static module09.task02.FIleCopier.FileCopier.copyFilesStreams;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileCopierTest {
    static String fileToCopy;
    static String copiedApache = "target/apacheTo";
    static String copiedStreams = "target/streamTo";
    static File expected;
    static File actual ;
    @BeforeEach
   void init(){
        expected = new File(copiedApache);
        actual = new File(copiedStreams);
    }

    @Test
    void testCopyFilesStreams1() throws Exception {
        fileToCopy = "src/main/java/module01";
        copyFilesApache(fileToCopy, copiedApache);
        copyFilesStreams(fileToCopy, copiedStreams);

        assertTrue(compareFiles(expected, actual));
    }

    @Test
    void testCopyFilesStreams2() throws Exception {
        fileToCopy = "src/main/resources";
        copyFilesApache(fileToCopy, copiedApache);
        copyFilesStreams(fileToCopy, copiedStreams);

        expected = new File(copiedApache);
        actual = new File(copiedStreams);

        assertTrue(compareFiles(expected, actual));
    }

    @AfterAll
    public static void clear() throws Exception{
        FileUtils.deleteDirectory(expected);
        FileUtils.deleteDirectory(actual);
    }

    boolean compareFiles(File a, File b) {
        if (a.isDirectory()) {
            if (!b.isDirectory()) return false;
            File[] aL = a.listFiles();
            File[] bL = b.listFiles();

            if (aL.length != bL.length) return false;
            for (int i = 0; i < a.length(); i++) {
                compareFiles(aL[i], bL[i]);
            }
        } else {
            if (b.isDirectory()) return false;
            if (a.hashCode() != b.hashCode()) return false;
        }
        return true;
    }
}