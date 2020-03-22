package module04.task05_1.StringExperiments;

        import java.util.regex.Matcher;
        import java.util.regex.Pattern;

        public class Loader {
        public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        System.out.println(text);

        int result = 0;
        Pattern pattern = Pattern.compile("\\b\\d+\\b");
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
        result += Integer.parseInt(matcher.group(0));
        }
        System.out.println("Общая сумма заработка: " + result);
        }

        private static boolean isParsable(String test) {
        try {
        Integer.parseInt(test);
        return true;
        } catch (Exception e) {
        return false;
        }
        }
        }
