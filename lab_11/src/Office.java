import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Office {
    private static final String URL = "jdbc:postgresql://localhost:5432/office_java";
    private static final String USER = System.getenv("USER");
    private static final String PASSWORD = System.getenv("Password");

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
            if (!doesTableExist(connection)) {
                createTables(connection);
                populateData(connection);
            }
            Scanner scanner = new Scanner(System.in);
            int choice;

            do {
                System.out.println("\n1. Get all employees");
                System.out.println("2. Get all tasks");
                System.out.println("3. Get employees by department");
                System.out.println("4. Add task for an employee");
                System.out.println("5. Get tasks for an employee");
                System.out.println("6. Delete an employee");
                System.out.println("0. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline

                switch (choice) {
                    case 1:
                        getAllEmployees(connection);
                        break;
                    case 2:
                        getAllTasks(connection);
                        break;
                    case 3:
                        System.out.print("Enter department ID: ");
                        int departmentId = scanner.nextInt();
                        getEmployeesByDepartment(connection, departmentId);
                        break;
                    case 4:
                        System.out.print("Enter employee ID: ");
                        int employeeId = scanner.nextInt();
                        scanner.nextLine(); // consume newline
                        System.out.print("Enter task description: ");
                        String description = scanner.nextLine();
                        addTask(connection, employeeId, description);
                        break;
                    case 5:
                        System.out.print("Enter employee ID: ");
                        int empId = scanner.nextInt();
                        getTasksForEmployee(connection, empId);
                        break;
                    case 6:
                        System.out.print("Enter employee ID to delete: ");
                        int empToDelete = scanner.nextInt();
                        deleteEmployee(connection, empToDelete);
                        break;
                    case 0:
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid option! Try again.");
                }
            } while (choice != 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createTables(Connection connection) throws Exception {
        String createDepartmentsTable = "CREATE TABLE IF NOT EXISTS departments (" +
                "department_id SERIAL PRIMARY KEY," +
                "name CHAR(30)," +
                "phone CHAR(15)" +
                ");";
        String createEmployeesTable = "CREATE TABLE IF NOT EXISTS employees (" +
                "employee_id SERIAL PRIMARY KEY," +
                "last_name VARCHAR(20)," +
                "first_name VARCHAR(10)," +
                "position VARCHAR(20)," +
                "department_id INTEGER REFERENCES departments(department_id)" +
                ");";
        String createTasksTable = "CREATE TABLE IF NOT EXISTS tasks (" +
                "task_id SERIAL PRIMARY KEY," +
                "description VARCHAR(50)," +
                "employee_id INTEGER REFERENCES employees(employee_id)" +
                ");";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(createDepartmentsTable);
            statement.executeUpdate(createEmployeesTable);
            statement.executeUpdate(createTasksTable);
        }
    }

    private static void populateData(Connection connection) throws Exception {
        String insertDepartments = "INSERT INTO departments (name, phone) VALUES " +
                "('HR', '123456')," +
                "('IT', '234567')," +
                "('Finance', '345678') " +
                "ON CONFLICT DO NOTHING;";

        String insertEmployees = "INSERT INTO employees (last_name, first_name, position, department_id) VALUES " +
                "('Smith', 'John', 'Manager', 1)," +
                "('Doe', 'Jane', 'Developer', 2)," +
                "('Brown', 'Mike', 'Analyst', 3) " +
                "ON CONFLICT DO NOTHING;";

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(insertDepartments);
            statement.executeUpdate(insertEmployees);
        }
    }

    private static void getAllEmployees(Connection connection) throws Exception {
        String query = "SELECT * FROM employees";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("\nEmployees:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("employee_id") +
                        ", Name: " + resultSet.getString("first_name") + " " +
                        resultSet.getString("last_name") +
                        ", Position: " + resultSet.getString("position"));
            }
        }
    }

    private static void getAllTasks(Connection connection) throws Exception {
        String query = "SELECT * FROM tasks";
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(query)) {
            System.out.println("\nTasks:");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("task_id") +
                        ", Description: " + resultSet.getString("description") +
                        ", Employee ID: " + resultSet.getInt("employee_id"));
            }
        }
    }

    private static void getEmployeesByDepartment(Connection connection, int departmentId) throws Exception {
        String query = "SELECT * FROM employees WHERE department_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, departmentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\nEmployees in Department " + departmentId + ":");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("employee_id") +
                        ", Name: " + resultSet.getString("first_name") + " " +
                        resultSet.getString("last_name") +
                        ", Position: " + resultSet.getString("position"));
            }
        }
    }

    private static void addTask(Connection connection, int employeeId, String description) throws Exception {
        String query = "INSERT INTO tasks (description, employee_id) VALUES (?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, description);
            preparedStatement.setInt(2, employeeId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Task added successfully!");
            } else {
                System.out.println("Failed to add task.");
            }
        }
    }

    private static void getTasksForEmployee(Connection connection, int employeeId) throws Exception {
        String query = "SELECT * FROM tasks WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\nTasks for Employee ID " + employeeId + ":");
            while (resultSet.next()) {
                System.out.println("ID: " + resultSet.getInt("task_id") +
                        ", Description: " + resultSet.getString("description"));
            }
        }
    }

    private static void deleteEmployee(Connection connection, int employeeId) throws Exception {
        String query = "DELETE FROM employees WHERE employee_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, employeeId);
            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully!");
            } else {
                System.out.println("No employee found with ID: " + employeeId);
            }
        }
    }

    private static boolean doesTableExist(Connection connection) throws Exception {
        String query = "SELECT EXISTS (" +
                "SELECT FROM information_schema.tables " +
                "WHERE table_schema = 'public' " +
                "AND table_name = ?);";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, "departments");
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getBoolean(1);
            }
        }
        return false;
    }
}

