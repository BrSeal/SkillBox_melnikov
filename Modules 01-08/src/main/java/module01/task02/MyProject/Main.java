package module01.task02.MyProject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd.MM.yyyy");
        System.out.println(sdf.format(date));
    }
}
