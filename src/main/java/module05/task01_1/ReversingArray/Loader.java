package module05.task01_1.ReversingArray;

        public class Loader
        {

        public static void main(String[] args)
        {
        String[] words = "каждый охотник желает знать где сидит фазан".split(" ");
        reverseArray(words);
        outputArray(words);

        }

        private static void outputArray(String[] words)
        {
        for (String word : words)
        {
        System.out.println(word);
        }
        }

        private static void reverseArray(String[] array)
        {
        for (int i = 0; i
< array.length / 2; i++)
        {
        String temp = array[i];
        array[i] = array[array.length - 1 - i];
        array[array.length - 1 - i] = temp;
        }
        }

        }
