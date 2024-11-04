import java.util.Arrays;
import java.util.Comparator;

class ShapeController {
    private Shape[] shapes;

    public ShapeController(Shape[] shapes) {
        this.shapes = shapes;
    }

    // Відображення всіх фігур
    public void displayAllShapes() {
        for (Shape shape : shapes) {
            shape.draw();
        }
    }

    // Обчислення сумарної площі всіх фігур
    public double calculateTotalArea() {
        double totalArea = 0;
        for (Shape shape : shapes) {
            totalArea += shape.calcArea();
        }
        return totalArea;
    }

    // Обчислення сумарної площі фігур певного виду
    public double calculateTotalAreaByType(Class<?> shapeType) {
        double totalArea = 0;
        for (Shape shape : shapes) {
            if (shape.getClass().equals(shapeType)) {
                totalArea += shape.calcArea();
            }
        }
        return totalArea;
    }

    // Сортування за площею фігур
    public void sortByArea() {
        Arrays.sort(shapes, Comparator.comparingDouble(Shape::calcArea));
    }

    // Сортування за кольором фігур
    public void sortByColor() {
        Arrays.sort(shapes, Comparator.comparing(shape -> shape.shapeColor));
    }
}

