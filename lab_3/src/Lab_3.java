public class Lab_3 {
    public static void main(String[] args) {
        // Створення набору фігур
        ShapeController controller = getShapeController();

        // Виведення всіх фігур
        System.out.println("Всі фігури:");
        controller.displayAllShapes();

        // Обчислення сумарної площі всіх фігур
        double totalArea = controller.calculateTotalArea();
        System.out.println("\nСумарна площа всіх фігур: " + totalArea);

        // Обчислення сумарної площі лише кіл
        double totalCircleArea = controller.calculateTotalAreaByType(Circle.class);
        System.out.println("Сумарна площа кіл: " + totalCircleArea);

        // Сортування за площею
        controller.sortByArea();
        System.out.println("\nФігури після сортування за площею:");
        controller.displayAllShapes();

        // Сортування за кольором
        controller.sortByColor();
        System.out.println("\nФігури після сортування за кольором:");
        controller.displayAllShapes();
    }

    private static ShapeController getShapeController() {
        Shape[] shapes = new Shape[]{
                new Rectangle("Red", 4, 5),
                new Circle("Blue", 3),
                new Triangle("Green", 4, 6),
                new Rectangle("Yellow", 2, 3),
                new Circle("Black", 7),
                new Triangle("White", 5, 7),
                new Rectangle("Pink", 6, 9),
                new Circle("Purple", 2),
                new Triangle("Orange", 8, 9),
                new Circle("Gray", 1)
        };
        // Створюємо контролер для обробки фігур
        return new ShapeController(shapes);
    }
}

