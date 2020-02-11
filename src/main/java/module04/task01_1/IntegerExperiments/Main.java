package module04.task01_1.IntegerExperiments;

public class Main
{
    public static void main(String[] args)
    {
        Container container = new Container();
        container.count += 7843;

    }

    public Integer sumDigits(Integer number)
    {
        //@TODO: write code here
        String s = number.toString();
        int result = 0;
        for (int i = s.charAt(0) == '-' ? 1 : 0; i < s.length(); i++) {

            result += Integer.parseInt(s.charAt(i) + "");
        }
        return result;
    }
}