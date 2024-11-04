import java.util.Arrays;

public class Lab_4 {
    public static void main(String[] args) throws Exception {
        // Створюємо транспортні засоби
        Bus bus = new Bus(3);
        Taxi taxi = new Taxi(2);
        FireTruck fireTruck = new FireTruck(2);
        PoliceCar policeCar = new PoliceCar(2);

        // Створюємо пасажирів
        Passenger taxipassenger = new Passenger("Carl");
        Passenger buspassenger = new Passenger("John");
        Firefighter firefighter1 = new Firefighter("James");
        Policeman policeman1 = new Policeman("Mark");

        // Посадка пасажирів
        bus.boardPassenger(buspassenger);
        taxi.boardPassenger(taxipassenger);
        fireTruck.boardPassenger(firefighter1);
        policeCar.boardPassenger(policeman1);

        // Додаємо транспорт на дорогу
        Road road = new Road();
        road.addCarToRoad(bus);
        road.addCarToRoad(taxi);
        road.addCarToRoad(fireTruck);
        road.addCarToRoad(policeCar);

        // Підрахунок кількості людей на дорозі
        System.out.println("Кількість людей на дорозі: " + road.getCountOfHumans());

        // Серіалізація пасажирів
        FileManager.savePassengers(Arrays.asList(buspassenger, taxipassenger, firefighter1, policeman1), "passengers.dat");

        // Десеріалізація пасажирів
        System.out.println("Завантажені пасажири з файлу: " + FileManager.loadPassengers("passengers.dat"));
    }
}

