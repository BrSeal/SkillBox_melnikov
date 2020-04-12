public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.count += 7843;
    
        System.out.println( sumDigits(container.count));
    }

    public static int sumDigits(Integer number) {
        String s = number.toString();
        int result = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {

            result += Integer.parseInt(s.charAt(i) + "");
        }
        return result;
    }
}