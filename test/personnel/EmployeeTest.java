/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnel;

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
public class EmployeeTest {
        private Person Person;
        private Employee Employee;
    
    public EmployeeTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.Person = new Person(1, "Name", "Lastname", "20000505", "M");
        this.Employee = new Employee(1, "Opettaja", "20190501", 3, true, "yksikkö-x", 7, false, Person);
        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test Position getter and setter, of class Employee.
     */
    @Test
    public void testSetPosition() {
        String oldPosition = "Opettaja";
        String newPosition = "Rehtori";
        assertEquals("Check position getter", oldPosition, Employee.getPosition());
        Employee.setPosition(newPosition);
        assertEquals("Check position setter", newPosition, Employee.getPosition());
    }

    /**
     * Test CompetenceClass getter and setter, of class Employee.
     */
    @Test
    public void testGetAndSetCompetenceClass() {
        Integer oldCompetenceClass = 3;
        Integer newCompetenceClass = 1;
        assertEquals("Check Competence class getter", oldCompetenceClass, Employee.getCompetenceClass());
        Employee.setCompetenceClass(newCompetenceClass);
        assertEquals("Check Competence class setter", newCompetenceClass, Employee.getCompetenceClass());
    }

    /**
     * Test IsPermanentEmployment getter and setter, of class Employee.
     */
    @Test
    public void testGetAndSetIsPermanentEmployment() {
        Boolean oldIsPermanentEmployment = Boolean.TRUE;
        Boolean newIsPermanentEmployment = Boolean.FALSE;
        assertEquals("Check IsPermanentEmployment getter", oldIsPermanentEmployment, Employee.getIsPermanentEmployment());
        Employee.setIsPermanentEmployment(newIsPermanentEmployment);
        assertEquals("Check IsPermanentEmployment setter", newIsPermanentEmployment, Employee.getIsPermanentEmployment());
    }

    /**
     * Test Unit setter and getter, of class Employee.
     */
    @Test
    public void testGetAndSetUnit() {
        String oldUnit = "yksikkö-x";
        String newUnit = "yksikkö-x";
        assertEquals("Check working Unit getter", oldUnit, Employee.getUnit());
        Employee.setUnit(newUnit);
        assertEquals("Check working Unit setter", newUnit, Employee.getUnit());
    }

    /**
     * Test PerformanceRate getter and setter, of class Employee.
     */
    @Test
    public void testGetAndSetPerformanceRate() {
        Integer oldPerformanceRate = 7;
        Integer newPerformanceRate = 50;
        assertEquals("Check PerformanceRate getter", oldPerformanceRate, Employee.getPerformanceRate());
        Employee.setPerformanceRate(newPerformanceRate);
        assertEquals("Check PerformanceRate setter", newPerformanceRate, Employee.getPerformanceRate());
    }

    /**
     * Test IsUnitSupervisor getter and setter, of class Employee.
     */
    @Test
    public void testGetAndSetIsUnitSupervisor() {
        Boolean oldIsUnitSupervisor = Boolean.FALSE;
        Boolean newIsUnitSupervisor = Boolean.TRUE;
        assertEquals("Check IsUnitSupervisor getter", oldIsUnitSupervisor, Employee.getIsUnitSupervisor());
        Employee.setIsUnitSupervisor(newIsUnitSupervisor);
        assertEquals("Check IsUnitSupervisor getter", newIsUnitSupervisor, Employee.getIsUnitSupervisor());
    }

    /**
     * Test of getPerson method, of class Employee.
     */
    @Test
    public void testGetPerson() {
        assertEquals("Check Person getter",Employee.getPerson(), Person);
    }

    /**
     * Test of setPerson method, of class Employee.
     */
    @Test
    public void testSetPerson() {
        Person newPerson = new Person(2, "Newname", "NewLastname", "19800101", "M");
        Employee.setPerson(newPerson);
        assertEquals("Check Person setter", newPerson, Employee.getPerson());
    }

    /**
     * Test getIsPermanentEmploymentStr method, of class Employee.
     */
    @Test
    public void testGetIsPermanentEmploymentStr() {
        String expResult = "vakituinen";
        assertEquals("Check IsPermanentEmploymentStr", expResult, Employee.getIsPermanentEmploymentStr());
    }

    /**
     * Test of getIsUnitSupervisorStr method, of class Employee.
     */
    @Test
    public void testGetIsUnitSupervisorStr() {
        String expResult = "työntekijä";
        assertEquals("Check IsUnitSupervisorStr", expResult, Employee.getIsUnitSupervisorStr());

    }

    /**
     * Test of toString method, of class Employee.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String[] expResultArr = {"Lastname", "Name", "Mies", "Opettaja", "aloitti - 01.05.2019", "vaativuusluokka - 3", "työsuhde - vakituinen", "yksikkö - yksikkö-x", "työntekijä", "vaativuusluokka - 7"};
        String result = Employee.toString();
        for (String string : expResultArr) {
            assertTrue(result.contains(string));
        }
    }
    
}
