/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Annika Heino
 */
public class PersonnelTest {

    private Person Person;
    private final Persons Persons;
    private final Personnel Personnel;
    private Employee Employee;

    public PersonnelTest() {
        this.Person = new Person(0, "Name", "Lastname", "19980505", "M");
        this.Persons = new Persons();
        this.Personnel = new Personnel(Persons);
        this.Employee = null;
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        System.out.println("setUp():");
        String currentDateStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
        Persons.addPerson(Person);
        Person = Persons.getPersonByFirstNameLastNameBirthdayGender(Person);
        Employee = new Employee(0, "Positiontest", currentDateStr, 1, Boolean.FALSE, "yksikkö-x", 10, Boolean.TRUE, Person);
        System.out.println("Id(new): " + Employee.getId() + ", " + Employee.toString());
        Personnel.addEmployee(Employee, Persons);
        Personnel.getEmployees().forEach((k, v) -> {
            if (v.getEmployeeByPerson(Person) != null) {
                if (Person.equals(v.getPerson())) {
                    Employee = v;
                }
            }
        });
        System.out.println("Id(from DB): " + Employee.getId() + ", " + Employee.toString());
    }

    @After
    public void tearDown() {
        System.out.println("TearDown(): ");
        System.out.println(Personnel.deleteEmployee(Employee.getId()));
        System.out.println(Persons.deletePerson(Person.getId()));
    }

    /**
     * Test of getEmployees method, of class Personnel.
     */
    @Test
    public void testGetEmployees() {
        System.out.println("getEmployees");
        assertTrue("Return type - HashMap", Personnel.getEmployees() instanceof HashMap);
        assertTrue("At least one employee found", Personnel.getEmployees().size() >= 1);
    }

    /**
     * Test of addEmployee method, of class Personnel.
     */
    @Test
    public void testAddEmployee() {
        System.out.println("addEmployee");
        assertEquals("Personnel contains employee added in setUp method: "
                + Employee.toString(), Employee, Personnel.getEmployee(Employee.getId()));
    }

    /**
     * Test of updateEmployee method, of class Personnel.
     */
    @Test
    public void testUpdateEmployee() {
        System.out.println("updateEmployee");
        Employee updatedEmployee = new Employee(Employee.getId(), 
                "UpdatedPosition", 
                Employee.getStartingDateStr(), 
                Employee.getCompetenceClass(), 
                Employee.getIsPermanentEmployment(), 
                "yksikkö-y", 
                33, 
                Boolean.FALSE, 
                Person);
        System.out.println("EmployeeUpdateTest old: " + Personnel.getEmployee(Employee.getId()));
        assertEquals(Employee, Personnel.getEmployee(Employee.getId()));
        Personnel.updateEmployee(updatedEmployee, updatedEmployee.getId());
        System.out.println("EmployeeUpdateTest updated: " + Personnel.getEmployee(Employee.getId()));
        assertNotEquals(Employee, Personnel.getEmployee(Employee.getId()));
        
        
        
    }

    /**
     * Test of deleteEmployee method, of class Personnel.
     */
    @Test
    public void testDeleteEmployee() {
        System.out.println("deleteEmployee");
        assertEquals("Non-existing employee delete attempt", "Tunnusta 0 ei ole olemassa!", Personnel.deleteEmployee(0));
        assertEquals("Employee delete by id", "Työntekijä poistettu:\n" + Employee, Personnel.deleteEmployee(Employee.getId()));
    }

    /**
     * Test of getEmployee method, of class Personnel.
     */
    @Test
    public void testGetEmployee() {
        System.out.println("getEmployee");
        assertEquals(Employee, Personnel.getEmployee(Employee.getId()));
    }

    /**
     * Test of toString method, of class Personnel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        assertTrue("Personnel toString() method contains Employee data", Personnel.toString().contains(Employee.toString()));
    }
    
}
