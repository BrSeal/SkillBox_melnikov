package module09.task01.SizeCalculator;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.nio.file.InvalidPathException;


public class SizeCalculator {
    public static final long BYTES_IN_KB = 1024;
    public static final long BYTES_IN_MB = 1048576;
    public static final long BYTES_IN_GB = 1099511627776L;

    public static final String helloMsg = "Insert path to file or directory you want to measure or \"exit\" to quit:";
    public static final String errMsg = "Enter path to existing file or directory!";

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;

        while (true) {
            try {
                System.out.println(helloMsg);
                input = reader.readLine();
                if (input.equalsIgnoreCase("exit")) break;

            } catch (FileNotFoundException e) {
                System.out.println("File not found!");
                System.out.println(errMsg);
            } catch (InvalidPathException e) {
                System.out.println("Path is " + e.getInput() + "!");
                System.out.println(errMsg);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static long calculate(String path) throws FileNotFoundException {
        if (path == null) throw new InvalidPathException("null", "Path is null!", -1);
        if (path.isEmpty()) throw new InvalidPathException("empty", "Path is empty!", -1);

        File file = new File(path);
        if (!file.exists()) throw new FileNotFoundException();
        return calculate(new File(path));
    }

    private static long calculate(File file) {
        long size = 0;

        if (file.isDirectory()) {
            for (File f : file.listFiles()) size += calculate(f);
        } else size += file.length();
        return size;
    }

    public static String formatSizeApache(long size){
        return FileUtils.byteCountToDisplaySize(size);
    }

    public static String formatSizeManual(long size) {
        StringBuilder sb = new StringBuilder();

        if ((size / BYTES_IN_GB) > 0) sb.append(size / BYTES_IN_GB).append(" Gb ");
        size %= BYTES_IN_GB;

        if ((size / BYTES_IN_MB) > 0) sb.append(size / BYTES_IN_MB).append(" Mb ");
        size %= BYTES_IN_MB;

        if ((size / BYTES_IN_KB) > 0) sb.append(size / BYTES_IN_KB).append(" Kb ");
        if (size % BYTES_IN_KB != 0) sb.append(size % BYTES_IN_KB).append(" b");

        return sb.length() == 0 ? "0 bytes" : sb.toString().trim();
    }
}
