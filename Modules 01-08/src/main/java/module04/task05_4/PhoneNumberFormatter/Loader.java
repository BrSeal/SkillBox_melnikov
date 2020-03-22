package module04.task05_4.PhoneNumberFormatter;

        import javax.swing.text.MaskFormatter;
        import java.util.Scanner;

        public class Loader {

        public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input phone number:");
        String phoneNumber;
        StringBuilder rawPhoneNum;

        while (true) {
        phoneNumber = scanner.nextLine();

        rawPhoneNum = new StringBuilder(phoneNumber.replaceAll("\\D", ""));
        if (rawPhoneNum.length() == 11) {
        break;
        }
        System.out.println("Неверный формат ввода!");
        }

        if (rawPhoneNum.indexOf("8") == 0) {
        rawPhoneNum.replace(0, 1, "7");
        }
        MaskFormatter mf = new MaskFormatter();
        mf.setValueContainsLiteralCharacters(false);
        mf.setMask("# ### ###-##-##");

        System.out.println(mf.valueToString(rawPhoneNum));

        scanner.close();
        }
        }
