package module04.task04_2.StringExperiments;

        public class Loader {
        public static void main(String[] args) {
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        System.out.println(text);

        int vasya = Integer.parseInt(text.substring((text.indexOf("Вася заработал ") + 15), text.indexOf(" рублей, ")));
        int masha = Integer.parseInt(text.substring((text.indexOf("Маша") + 7), (text.lastIndexOf(" рублей"))));
        System.out.println("Cуммарный заработок Васи и Маши состравил: "+ (vasya + masha));
        }
        }