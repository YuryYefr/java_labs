import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

// Клас для зберігання інформації про студента
class StudentRecord {
    private String lastName;
    private String firstName;
    private String birthDate;
    private String phone;
    private String address;

    public StudentRecord(String lastName, String firstName, String birthDate, String phone, String address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.birthDate = birthDate;
        this.phone = phone;
        this.address = address;
    }

    public String toString() {
        return "Прізвище: " + lastName + ", Ім'я: " + firstName + ", Дата народження: " + birthDate + ", Телефон: " + phone + ", Адреса: " + address;
    }
}

// Клас для роботи з журналом
class Journal {
    private ArrayList<StudentRecord> records = new ArrayList<>();

    // Метод для додавання запису
    public void addRecord(StudentRecord record) {
        records.add(record);
    }

    // Метод для відображення всіх записів
    public void displayAllRecords() {
        if (records.isEmpty()) {
            System.out.println("Журнал порожній.");
        } else {
            System.out.println("Записи в журналі:");
            for (StudentRecord record : records) {
                System.out.println(record);
            }
        }
    }
}

// Головний клас програми
public class Lab_2 {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Journal journal = new Journal();
        boolean running = true;

        while (running) {
            System.out.println("Виберіть дію:");
            System.out.println("1 - Додати запис");
            System.out.println("2 - Показати всі записи");
            System.out.println("3 - Вийти");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    StudentRecord newRecord = inputStudentRecord();
                    journal.addRecord(newRecord);
                    break;
                case "2":
                    journal.displayAllRecords();
                    break;
                case "3":
                    running = false;
                    break;
                default:
                    System.out.println("Неправильний вибір. Спробуйте ще раз.");
            }
        }
    }

    // Метод для введення даних студента з перевіркою
    public static StudentRecord inputStudentRecord() {
        String lastName = inputWithValidation("Введіть прізвище студента: ", "^[a-zA-Zа-яА-Я]+$", "Прізвище повинно містити тільки букви.");
        String firstName = inputWithValidation("Введіть ім'я студента: ", "^[a-zA-Zа-яА-Я]+$", "Ім'я повинно містити тільки букви.");
        String birthDate = inputWithValidation("Введіть дату народження (у форматі dd.mm.yyyy): ", "^\\d{2}\\.\\d{2}\\.\\d{4}$", "Дата народження повинна бути у форматі dd.mm.yyyy.");
        String phone = inputWithValidation("Введіть телефон студента (у форматі +380XXXXXXXXX): ", "^\\+380\\d{9}$", "Телефон повинен бути у форматі +380XXXXXXXXX.");
        String address = inputWithValidation("Введіть домашню адресу (вулиця, будинок, квартира): ", ".+", "Адреса не повинна бути порожньою.");

        return new StudentRecord(lastName, firstName, birthDate, phone, address);
    }

    // Метод для введення і перевірки даних
    public static String inputWithValidation(String prompt, String regex, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine();
            if (Pattern.matches(regex, input)) {
                break;
            } else {
                System.out.println(errorMessage);
            }
        }
        return input;
    }
}

