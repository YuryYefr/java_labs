import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Vehicle<T extends Passenger> implements Serializable {
    private String name;
    private int maxSeats;
    private List<T> passengers = new ArrayList<>();

    public Vehicle(String name, int maxSeats) {
        this.name = name;
        this.maxSeats = maxSeats;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getOccupiedSeats() {
        return passengers.size();
    }

    public void boardPassenger(T passenger) throws Exception {
        if (passengers.size() < maxSeats) {
            passengers.add(passenger);
        } else {
            throw new Exception("No available seats for " + passenger.getName());
        }
    }

    public void unboardPassenger(T passenger) throws Exception {
        if (passengers.contains(passenger)) {
            passengers.remove(passenger);
        } else {
            throw new Exception("Passenger " + passenger.getName() + " is not on board.");
        }
    }

    @Override
    public String toString() {
        return name + " - Passengers: " + passengers;
    }

    public double getPassengerCount() {
        return getOccupiedSeats();
    }
}

class Bus extends Vehicle<Passenger> {
    public Bus(int maxSeats) {
        super("Bus", maxSeats);
    }
}

class Taxi extends Vehicle<Passenger> {
    public Taxi(int maxSeats) {
        super("Taxi", maxSeats);
    }
}

class FireTruck extends Vehicle<Firefighter> {
    public FireTruck(int maxSeats) {
        super("FireTruck", maxSeats);
    }
}

class PoliceCar extends Vehicle<Policeman> {
    public PoliceCar(int maxSeats) {
        super("PoliceCar", maxSeats);
    }
}

