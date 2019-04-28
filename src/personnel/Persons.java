package personnel;

import data.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Annika Heino
 */
public class Persons {

    private final List<Person> PersonList;

    public Persons() {
        this.PersonList = new ArrayList<>();
        initializeOrRefreshPersons();
    }

    public List<Person> getPersonList() {
        return PersonList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.PersonList.stream().forEach(person -> sb.append(person.toString()).append("\n"));
        return sb.toString();
    }

    public Person getPerson(Integer id) {
        return PersonList.stream()
                .filter(person -> Objects.equals(person.getId(), id))
                .findFirst().get();
    }
    public Person getPersonByFirstNameLastNameBirthdayGender(Person p) {
        return PersonList.stream()
                .filter(person -> Objects.equals(person.getBirthDate(), p.getBirthDate()))
                .filter(person -> Objects.equals(person.getFirstName(), p.getFirstName()))
                .filter(person -> Objects.equals(person.getLastName(), p.getLastName()))
                .filter(person -> Objects.equals(person.getGender(), p.getGender()))
                .findFirst().get();
    }

    public String addPerson(Person person) {
        try (Connection connection = DBConnection.connect();
                PreparedStatement stmt = connection.prepareStatement(""
                        + "INSERT INTO Persons"
                        + "(LastName, FirstName, BirthDate, Gender)"
                        + "VALUES (?, ?, ?, ? )")) {
            stmt.setString(1, person.getLastName());
            stmt.setString(2, person.getFirstName());
            stmt.setString(3, person.getBirthdayString_yyyyMMdd());
            stmt.setString(4, person.getGender());

            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        initializeOrRefreshPersons();
        return "Uusi henkilö lisätty: " + getPersonByFirstNameLastNameBirthdayGender(person).toString();
    }

    public String deletePerson(Integer id) {
        String deletePersonSQL = "DELETE FROM Persons WHERE Id = ?";
        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(deletePersonSQL)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        Person person = getPerson(id);
        initializeOrRefreshPersons();
        return "Henkilö poistettu: " + person.toString();
    }
    
    public boolean isPersonIdFound(Integer id) {
        return PersonList.stream().anyMatch((person) -> (Objects.equals(person.getId(), id)));
    }

    private void initializeOrRefreshPersons() {
        PersonList.clear();
        String selectAllPersonsSQL = "SELECT * FROM Persons";

        try (Connection conn = DBConnection.connect();
                PreparedStatement stmt = conn.prepareStatement(selectAllPersonsSQL);
                ResultSet rs = stmt.executeQuery();) {

            while (rs.next()) {
                Integer id = rs.getInt("Id");
                String lastName = rs.getString("LastName");
                String firstName = rs.getString("FirstName");
                String birthdate = rs.getString("BirthDate");
                String gender = rs.getString("Gender");
                PersonList.add(new Person(id, lastName, firstName, birthdate, gender));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
