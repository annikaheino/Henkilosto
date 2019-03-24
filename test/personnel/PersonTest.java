/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnel;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
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
public class PersonTest {
    private Person person;
    
    public PersonTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        this.person = new Person(1, "TestLastName", "TestFirstName", "20150505", "F");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getBirthDate method, of class Person.
     */
    @Test
    public void testGetBirthDate() {
        LocalDate expResult = null;
        LocalDate result = person.getBirthDate();
        assertNotEquals(expResult, result);
        expResult = LocalDate.of(2015, Month.MAY, 5);
        assertEquals(expResult, result);
    }

    /**
     * Test of getBirthdayString_yyyyMMdd method, of class Person.
     */
    @Test
    public void testGetBirthdayString_yyyyMMdd() {
        String expResult = "20150505";
        String result = person.getBirthdayString_yyyyMMdd();
        assertEquals(expResult, result);
    }

    /**
     * Test of getFirstName method, of class Person.
     */
    @Test
    public void testGetFirstName() {
        String expResult = "TestFirstName";
        String result = person.getFirstName();
        assertNotEquals(expResult, result);
        expResult = Utils.capitlizeString(expResult);
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Person.
     */
    @Test
    public void testSetFirstName() {
        String FirstName = "Anotherfirstname";
        person.setFirstName(FirstName);
        assertEquals(FirstName, person.getFirstName());
    }

    /**
     * Test of getLastName method, of class Person.
     */
    @Test
    public void testGetLastName() {
        String expResult = "Testlastname";
        String result = person.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Person.
     */
    @Test
    public void testSetLastName() {
        String expResult = "Anotherlastname";
        person.setLastName(expResult);
        String result = person.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of getGender method, of class Person.
     */
    @Test
    public void testGetGender() {
        String expResult = "F";
        String result = person.getGender();
        assertEquals(expResult, result);
    }

    /**
     * Test of setGender method, of class Person.
     */
    @Test
    public void testSetGender() {
        String Gender = "M";
        person.setGender(Gender);
        assertEquals(Gender, person.getGender());
    }

    /**
     * Test of getAgeInYears method, of class Person.
     */
    @Test
    public void testGetAgeInYears() {
        String expResult = String.valueOf(Period.between(LocalDate.of(2015, Month.MAY, 5), LocalDate.now()).getYears());
        String result = person.getAgeInYears();
        assertEquals(expResult, result);
    }

    /**
     * Test of toString method, of class Person.
     */
    @Test
    public void testToString() {
        String expResult = "";
        String result = person.toString();
        assertNotEquals(expResult, result);
    }
    
}
