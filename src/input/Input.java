package input;

import java.util.Scanner;

public class Input {
    private static final Scanner input = new Scanner(System.in);

    public static int inputNumber() {
        int number;
        do {
            try {
                number = Integer.parseInt(input.nextLine());
                break;
            } catch (NumberFormatException exception) {
                System.out.println("Sai định dạng! Mời nhập lại: ");
            }
        } while (true);
        return number;
    }

    public static String inputString() {
        return input.nextLine();
    }
}
