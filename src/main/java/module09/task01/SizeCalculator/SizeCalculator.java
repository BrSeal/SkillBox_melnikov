package module09.task01.SizeCalculator;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.InvalidPathException;


public class SizeCalculator {
    public static final long BYTES_IN_KB = 1024;
    public static final long BYTES_IN_MB = 1048576;
    public static final long BYTES_IN_GB = 1099511627776L;


    public static long calculate(String path) throws FileNotFoundException {
        if(path==null) throw new InvalidPathException("","Path is null!", -1);
        if(path.isEmpty()) throw new InvalidPathException("","Path is empty!", -1);

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

    public static String formatSize(long size) {
        StringBuilder sb=new StringBuilder();

        if((size/BYTES_IN_GB)>0) sb.append(size/BYTES_IN_GB).append(" Gb ");
        size%=BYTES_IN_GB;

        if((size/BYTES_IN_MB)>0) sb.append(size/BYTES_IN_MB).append(" Mb ");
        size%=BYTES_IN_MB;

        if((size/BYTES_IN_KB)>0) sb.append(size/BYTES_IN_KB).append(" Kb ");
        if(size%BYTES_IN_KB!=0) sb.append(size%BYTES_IN_KB).append(" b");

        return sb.length()==0?"0 bytes":sb.toString().trim();
    }
}
