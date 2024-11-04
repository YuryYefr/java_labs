import java.io.Serializable;

class Passenger implements Serializable {
    private String name;

    public Passenger(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "name='" + name + '\'' +
                '}';
    }
}

class Firefighter extends Passenger {
    public Firefighter(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Firefighter{" + "name='" + getName() + '\'' + '}';
    }
}

class Policeman extends Passenger {
    public Policeman(String name) {
        super(name);
    }

    @Override
    public String toString() {
        return "Policeman{" + "name='" + getName() + '\'' + '}';
    }
}

