import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DepartmentDAO {

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/departments";
    private static final String USERNAME = "your_username";
    private static final String PASSWORD = "your_password";

    // SQL query to insert a new department
    private static final String INSERT_DEPARTMENT_SQL = "INSERT INTO department (id, name) VALUES (?, ?)";

    // Method to insert a new department into the database
    public void insertDepartment(Department department) {
        try (
            // Establishing a connection to the database
            Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            
            // Creating a prepared statement
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_DEPARTMENT_SQL)
        ) {
            // Setting parameters for the prepared statement
            preparedStatement.setInt(1, department.getId());
            preparedStatement.setString(2, department.getName());
            
            // Executing the query
            preparedStatement.executeUpdate();
            
            System.out.println("Department inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        DepartmentDAO departmentDAO = new DepartmentDAO();
        
        // Example of how to use the insertDepartment method
        Department department = new Department(1, "IT");
        departmentDAO.insertDepartment(department);
    }
}

// Department class representing the structure of a department
class Department {
    private int id;
    private String name;

    public Department(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
