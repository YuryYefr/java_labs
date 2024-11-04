import java.util.ArrayList;
import java.util.List;

class Road {
    private List<Vehicle<? extends Passenger>> carsInRoad = new ArrayList<>();

    public void addCarToRoad(Vehicle<? extends Passenger> car) {
        carsInRoad.add(car);
    }

    public int getCountOfHumans() {
        int total = 0;
        for (Vehicle<? extends Passenger> car : carsInRoad) {
            total += car.getOccupiedSeats();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Cars on road: " + carsInRoad;
    }
}

