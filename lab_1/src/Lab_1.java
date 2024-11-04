import java.util.ArrayList;

public class Lab_1 {

    // Метод для знаходження середньої довжини рядків
    public static float findAverageLength(String[] strings) {
        int totalLength = 0;
        for (String s : strings) {
            totalLength += s.length();
        }
        return (float) totalLength / strings.length;
    }

    // Метод для знаходження рядків, довжина яких більша або менша за середню
    public static String[] filterStringsByLength(String[] strings, boolean lessThanAverage) {
        double averageLength = findAverageLength(strings);
        ArrayList<String> result = new ArrayList<>();

        for (String s : strings) {
            if (lessThanAverage) {
                if (s.length() < averageLength) {
                    result.add(s);
                }
            } else {
                if (s.length() > averageLength) {
                    result.add(s);
                }
            }
        }

        // Перетворюємо ArrayList у масив і повертаємо результат
        return result.toArray(new String[0]);
    }

    public static void main(String[] args) {
        // Приклад використання
        String[] input = {"apple", "banana", "kiwi", "orange", "plum", "strawberry"};

        // Рядки, довжина яких менша за середню
        String[] lessThanAverage = filterStringsByLength(input, true);
        System.out.println("Рядки з довжиною меншою за середню:");
        for (String s : lessThanAverage) {
            System.out.println(s);
        }

        // Рядки, довжина яких більша за середню
        String[] greaterThanAverage = filterStringsByLength(input, false);
        System.out.println("\nРядки з довжиною більшою за середню:");
        for (String s : greaterThanAverage) {
            System.out.println(s);
        }
    }
}

