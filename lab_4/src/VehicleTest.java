import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleTest {
    private Bus bus;
    private FireTruck fireTruck;
    private PoliceCar policeCar;
    private Firefighter firefighter;
    private Policeman policeman;

    @Before
    public void setUp() {
        // Ініціалізація транспортних засобів та пасажирів перед кожним тестом
        bus = new Bus(3); // Максимум 3 місця
        Taxi taxi = new Taxi(2); // Максимум 2 місця
        fireTruck = new FireTruck(2); // Максимум 2 місця
        policeCar = new PoliceCar(1); // Максимум 1 місце
        firefighter = new Firefighter("James");
        policeman = new Policeman("Mark");
    }

    @Test
    public void testBoardPassenger() throws Exception {
        // Тест на успішну посадку пасажира
        assertEquals(0, bus.getOccupiedSeats());
        bus.boardPassenger(new Passenger("John"));
        assertEquals(1, bus.getOccupiedSeats());
    }

    @Test(expected = Exception.class)
    public void testBoardPassengerNoSeats() throws Exception {
        // Тест на виключення при відсутності вільних місць
        bus.boardPassenger(new Passenger("John"));
        bus.boardPassenger(new Passenger("Doe"));
        bus.boardPassenger(new Passenger("Jane"));
        bus.boardPassenger(new Passenger("Carl")); // Повинно викликати виключення
    }

    @Test
    public void testUnboardPassenger() throws Exception {
        // Тест на успішну висадку пасажира
        Passenger passenger = new Passenger("John");
        bus.boardPassenger(passenger);
        assertEquals(1, bus.getOccupiedSeats());
        bus.unboardPassenger(passenger);
        assertEquals(0, bus.getOccupiedSeats());
    }

    @Test(expected = Exception.class)
    public void testUnboardPassengerNotOnBoard() throws Exception {
        // Тест на виключення при спробі висадки неіснуючого пасажира
        Passenger passenger = new Passenger("John");
        bus.unboardPassenger(passenger); // Повинно викликати виключення
    }

    @Test
    public void testOccupiedSeats() throws Exception {
        // Тест на перевірку кількості зайнятих місць
        bus.boardPassenger(new Passenger("John"));
        bus.boardPassenger(new Passenger("Doe"));
        assertEquals(2, bus.getOccupiedSeats());
    }

    @Test
    public void testMultipleVehicleTypes() throws Exception {
        // Тест на різні типи транспортних засобів
        fireTruck.boardPassenger(firefighter);
        assertEquals(1, fireTruck.getOccupiedSeats());

        policeCar.boardPassenger(policeman);
        assertEquals(1, policeCar.getOccupiedSeats());
    }
}
