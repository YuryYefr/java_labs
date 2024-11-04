import java.io.*;
import java.util.List;

class FileManager {
    @SuppressWarnings("unchecked") // Додаємо анотацію для попередження компілятора
    public static List<Passenger> loadPassengers(String fileName) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            return (List<Passenger>) ois.readObject(); // Тепер ми ігноруємо попередження
        }
    }

    public static void savePassengers(List<Passenger> passengers, String fileName) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            oos.writeObject(passengers);
        }
    }
}
