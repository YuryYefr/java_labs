interface Drawable {
    void draw();  // Метод для відображення фігури
}

abstract class Shape implements Drawable {
    protected String shapeColor;

    public Shape(String shapeColor) {
        this.shapeColor = shapeColor;
    }

    // Абстрактний метод для обчислення площі
    public abstract double calcArea();

    @Override
    public String toString() {
        return "Фігура: " + this.getClass().getSimpleName() + ", Колір: " + shapeColor;
    }
}
