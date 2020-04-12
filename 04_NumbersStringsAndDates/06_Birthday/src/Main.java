import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Calendar birthday = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        birthday.set(1990, Calendar.OCTOBER, 30);

        Locale rus = new Locale("ru");

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy - EEE", rus);
        int years = 0;
        while (birthday.before(now)) {
            System.out.println(years++ + " - " + sdf.format(birthday.getTime()));
            birthday.add(Calendar.YEAR, 1);
        }
    }
}
