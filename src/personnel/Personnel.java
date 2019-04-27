package personnel;

import data.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Annika Heino
 */
public class Personnel {

    private final Map<Integer, Employee> Employees;

    public Personnel(Persons persons) {
        this.Employees = new HashMap<>();
        initializeOrRefreshEmployees(persons);
    }

    public Map<Integer, Employee> getEmployees() {
        return Employees;
    }

    public String addEmployee(Employee employee, Persons persons) {
//        int key = Employees.size() > 0 ? Collections.max(Employees.keySet()) + 1 : 1;
        try (Connection connection = DBConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(""
                        + "INSERT INTO Employees"
                        + "(Position, "
                        + "StartingDate, "
                        + "CompetenceClass, "
                        + "IsPermanentEmployment, "
                        + "Unit, "
                        + "PerformanceRate, "
                        + "IsUnitSupervisor, "
                        + "PersonId)"
                        + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, employee.getPosition());
            stmt.setString(2, employee.getStartingDateStr());
            stmt.setInt(3, employee.getCompetenceClass());
            stmt.setBoolean(4, employee.getIsPermanentEmployment());
            stmt.setString(5, employee.getUnit());
            stmt.setInt(6, employee.getPerformanceRate());
            stmt.setBoolean(7, employee.getIsUnitSupervisor());
            stmt.setInt(8, employee.getPerson().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        initializeOrRefreshEmployees(persons);
        return employee.toString();
    }

    public String updateEmployee(Employee employee, Integer Id) {
        try (Connection connection = DBConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(""
                        + "UPDATE Employees SET "
                        + "Position = ?,"
                        + "CompetenceClass = ?,"
                        + "IsPermanentEmployment = ?,"
                        + "Unit = ?,"
                        + "PerformanceRate = ?,"
                        + "IsUnitSupervisor = ? "
                        + "WHERE PersonId = ?")) {
            stmt.setString(1, employee.getPosition());
            stmt.setInt(2, employee.getCompetenceClass());
            stmt.setBoolean(3, employee.getIsPermanentEmployment());
            stmt.setString(4, employee.getUnit());
            stmt.setInt(5, employee.getPerformanceRate());
            stmt.setBoolean(6, employee.getIsUnitSupervisor());
            stmt.setInt(7, employee.getPerson().getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        this.Employees.put(Id, employee);
        return "Päivitetyn työntekijän tiedot:\n" + employee.toString();
    }

    public String deleteEmployee(Integer id) {

        String deletePersonSQL = "DELETE FROM Employees WHERE Id = ?";
        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(deletePersonSQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Employee employee = getEmployee(id);
        Employees.remove(id);
        return "Työntekijä poistettu:\n" + employee.toString();
    }

    public Employee getEmployee(int id) {
        return this.Employees.get(id);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.Employees.forEach((key, employee) -> sb.append("Id - ").append(key)
                .append(": ").append(employee.toString()).append("\n"));
        return sb.toString();
    }

    private void initializeOrRefreshEmployees(Persons persons) {
        String SelectAllEmployees = "SELECT * FROM Employees";

        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(SelectAllEmployees);
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Integer employeeKey = rs.getInt("Id");
                String position = rs.getString("Position");
                String startingDate = rs.getString("StartingDate");
                Integer competenceClass = rs.getInt("CompetenceClass");
                Boolean isPermanentEmployment = rs.getBoolean("IsPermanentEmployment");
                String unit = rs.getString("Unit");
                Integer performanceRate = rs.getInt("PerformanceRate");
                Boolean isUnitSupervisor = rs.getBoolean("IsUnitSupervisor");
                Integer personId = rs.getInt("PersonId");
                Person person = persons.getPerson(personId);
                Employees.put(employeeKey, new Employee(employeeKey, position, startingDate, competenceClass, isPermanentEmployment, unit, performanceRate, isUnitSupervisor, person));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
