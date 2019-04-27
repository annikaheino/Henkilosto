/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
class Salaries {

    private final Map<Integer, Double> Salaries;

    public Salaries() {
        this.Salaries = new HashMap<>();
        initializeOrRefreshSalaries();
    }

    public Map<Integer, Double> getSalaries() {
        return Salaries;
    }

    public Double getSalaryByKey(Integer key) {
        return Salaries.getOrDefault(key, 0.0);
    }

    private void initializeOrRefreshSalaries() {
        String SelectSalary = "SELECT * FROM Salary";

        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(SelectSalary);
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Integer salaryKey = rs.getInt("Id");
                Double amount = rs.getDouble("Amount");
                Salaries.put(salaryKey, amount);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
