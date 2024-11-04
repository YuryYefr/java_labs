import java.util.Scanner;


public class Lab_6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Translator translator = new Translator();

        // Додавання слів до словника
        System.out.println("Додавання слів до словника:");
        while (true) {
            System.out.print("Введіть англійське слово (або 'exit' для завершення): ");
            String englishWord = scanner.nextLine();
            if (englishWord.equalsIgnoreCase("exit")) {
                break;
            }

            System.out.print("Введіть українське слово: ");
            String ukrainianWord = scanner.nextLine();
            translator.addTranslation(englishWord, ukrainianWord);
            System.out.println("Слово додано!\n");
        }

        // Переклад фрази
        System.out.print("Введіть фразу англійською для перекладу: ");
        String phrase = scanner.nextLine();
        String translatedPhrase = translator.translate(phrase);

        System.out.println("Переклад: " + translatedPhrase);
        scanner.close();
    }
}

