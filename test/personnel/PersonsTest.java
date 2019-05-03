/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package personnel;

import java.util.ArrayList;
import java.util.List;
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
public class PersonsTest {

    private final Persons Persons;
    private final List<Integer> TeardownIds;
    private final Person Person;

    public PersonsTest() {
        this.Persons = new Persons();
        this.TeardownIds = new ArrayList<>();
        this.Person = new Person(0, "Name", "Lastname", "19980505", "M");
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Persons.addPerson(Person);
        Persons.getPersonList().forEach(p -> {
            if (p.getBirthDate().equals(Person.getBirthDate())
                    && p.getLastName().equals(Person.getLastName())
                    && p.getFirstName().equals(Person.getFirstName())
                    && p.getGender().equals(Person.getGender())) {
                TeardownIds.add(p.getId());
            }
        });
    }

    @After
    public void tearDown() {
        TeardownIds.forEach(id -> Persons.deletePerson(id));
    }

    /**
     * Test of getPersonList method, of class Persons.
     */
    @Test
    public void testGetPersonList() {
        System.out.println("getPersonList");
        TeardownIds.forEach(id
                -> {
            System.out.println(Persons.getPerson(id).toString());
            assertEquals("Check Age", Person.getAgeInYears(), Persons.getPerson(id).getAgeInYears());
            assertEquals("Check Birthdate", Person.getBirthDate(), Persons.getPerson(id).getBirthDate());
            assertEquals("Check Firstname", Person.getFirstName(), Persons.getPerson(id).getFirstName());
            assertEquals("Check Lastname", Person.getLastName(), Persons.getPerson(id).getLastName());
            assertEquals("Check Gender", Person.getGender(), Persons.getPerson(id).getGender());
        });
    }

    /**
     * Test of toString method, of class Persons.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        String expected = Person.toString().substring(Person.toString().indexOf(",") + 1);
        String actual = Persons.getPersonByFirstNameLastNameBirthdayGender(Person).toString();
        actual = actual.substring(actual.indexOf(",") + 1);
        assertEquals("toString test", expected, actual);

    }

    /**
     * Test of getPerson method, of class Persons.
     */
    @Test
    public void testGetPerson() {
        System.out.println("getPerson");
        TeardownIds.forEach(id -> {
            assertTrue(Persons.getPerson(id) instanceof Person);
        });
    }

    /**
     * Test of getPersonByFirstNameLastNameBirthdayGender method, of class
     * Persons.
     */
    @Test
    public void testGetPersonByFirstNameLastNameBirthdayGender() {
        System.out.println("testGetPersonByFirstNameLastNameBirthdayGender");
        Person personFromDB = Persons.getPersonByFirstNameLastNameBirthdayGender(Person);
        assertNotEquals(Person.getId(), personFromDB.getId());
        assertEquals(Person.getAgeInYears(), personFromDB.getAgeInYears());
        assertEquals(Person.getBirthDate(), personFromDB.getBirthDate());
        assertEquals(Person.getFirstName(), personFromDB.getFirstName());
        assertEquals(Person.getLastName(), personFromDB.getLastName());
        assertEquals(Person.getGender(), personFromDB.getGender());

    }

    /**
     * Test of addPerson method, of class Persons.
     */
    @Test
    public void testAddandDeletePerson() {
        System.out.println("addPerson");
        Person person = new Person(0, "Testaddpersonname", "Testaddpersonlastname", "19980505", "M");
        Persons.addPerson(person);
        assertFalse("After adding new person to DB, person id on should be changed automatically", Persons.isPersonIdFound(person.getId()));
        person = Persons.getPersonByFirstNameLastNameBirthdayGender(person);
        assertEquals(person, Persons.getPersonByFirstNameLastNameBirthdayGender(person));
        assertEquals(person, Persons.getPersonByFirstNameLastNameBirthdayGender(person));
        String deleteMsg = "Henkilö poistettu: " + person.toString();
        assertEquals(deleteMsg, Persons.deletePerson(person.getId()));
    }

    /**
     * Test of isPersonIdFound method, of class Persons.
     */
    @Test
    public void testIsPersonIdFound() {
        System.out.println("isPersonIdFound");
        assertFalse(Persons.isPersonIdFound(Person.getId()));
        TeardownIds.forEach(id -> {
            assertTrue(Persons.isPersonIdFound(id));
        });
    }
}
