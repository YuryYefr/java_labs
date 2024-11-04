import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String[] labNumbers = {"1", "2", "3", "4", "6", "8", "11"};
        System.out.printf("Choose a number from %s \n", Arrays.toString(labNumbers));
        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                Lab_1.main(new String[]{});
                break;
            case "2":
                Lab_2.main(new String[]{});
                break;
            case "3":
                Lab_3.main(new String[]{});
                break;
            case "4":
                Lab_4.main(new String[]{});
                break;
            case "6":
                Lab_6.main(new String[]{});
                break;
            case "8":
                System.out.println("кількість потоків \n");
                Lab_8.main(new String[]{String.valueOf(scanner.nextLine())});
                break;
            case "11":
                Office.main(new String[]{});
                break;
            default:
                System.out.println("Invalid choice. Please choose a number from 1 to 7.");
                break;
        }

        scanner.close();
    }
}
