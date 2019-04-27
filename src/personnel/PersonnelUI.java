package personnel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Annika Heino
 */
public class PersonnelUI {

    private final Persons Persons;
    private final Personnel Personnel;
    private final Salaries Salaries;
    private final Scanner Reader;

    public PersonnelUI() {
        this.Persons = new Persons();
        this.Personnel = new Personnel(Persons);
        this.Salaries = new Salaries();
        this.Reader = new Scanner(System.in);
    }

    public void startApplication() {

        System.out.println("Tervetuloa!");
        loop:
        while (true) {
            System.out.println("0 - lopetetaan");
            System.out.println("1 - näytä kaikki järjestelmässä olevat henkilöt (tehtävään kiinnitetyt ja vielä kiinnittämättömät)");
            System.out.println("2 - lisää uusi henkilö järjestelmään");
            System.out.println("3 - poista henkilö (poista henkilö järjestelmästä kokonaan)");
            System.out.println("4 - näytä tehtävään kiinnitetyt työntekijät");
            System.out.println("5 - kiinnitä järjestelmässä oleva henkilö tehtävään");
            System.out.println("6 - hae tehtävään kiinnitetty henkilö tunnuksella (Id)");
            System.out.println("7 - poista työntekijän tehtäväkiinnitys tunnuksella (Id)");
            System.out.println("8 - muokkaa tehtävään kiinnitetyn työntekijän tietoja tunnuksella (Id)");

            System.out.println();
            System.out.print("Mitä tehdään? ");

            int komento = Utils.validateNumberSelection(Reader.nextLine());

            switch (komento) {
                case 0:
                    break loop;
                case 1:
                    System.out.println(Persons.toString());
                    promtAnyKey();
                    break;
                case 2:
                    addNewPerson();
                    break;
                case 3:
                    deletePerson();
                    break;
                case 4:
                    listEmployeesAndSalaries();
                    break;
                case 5:
                    addNewEmployee();
                    break;
                case 6:
                    getEmployeeById();
                    break;
                case 7:
                    deleteEmployee();
                    break;
                case 8:
                    updateEmployee();
                    break;
                default:
                    System.out.println("Näppäilyvirhe!");
            }

        }
    }

    private void promtAnyKey() {
        System.out.println("");
        System.out.print("Jatka painamalla \"Enter\"... ");
        Reader.nextLine();
        System.out.println("");

    }

    private void addNewPerson() {
        System.out.print("Syötä uuden henkilön etunimi: ");
        String firtsName = Reader.nextLine();
        System.out.print("Syötä uuden henkilön sukunimi: ");
        String lastName = Reader.nextLine();
        System.out.print("Syötä uuden henkilön syntymäpäivän muodossa vvvvMMdd: ");
        String birthDate = Utils.validatePersonAge(Reader.nextLine());
        System.out.print("Syötä uuden henkilön sukupuoli (mies/nainen) muodossa M/N: ");
        String gender = Utils.validateGender(Reader.nextLine());
        Person person = new Person(1, lastName, firtsName, birthDate, gender);
        System.out.println(Persons.addPerson(person));
        promtAnyKey();
    }

    private void deletePerson() {
        System.out.println(Persons.toString());
        System.out.println("Syötä järjestelmästä poistettavan henkilön tunnus (Id): ");
        int id = Integer.valueOf(Reader.nextLine());
        System.out.println(Persons.deletePerson(id));
        promtAnyKey();
    }

    private void listEmployeesAndSalaries() {
        Personnel.getEmployees().forEach((key, employee) -> {
            BigDecimal employeeSalary = Utils.getMoneyRepresentation(
                    employee.getPerformanceRate(),
                    Salaries.getSalaryByKey(employee.getCompetenceClass()));
            System.out.println(key + ": " + employee.toString() + ", €" + employeeSalary);
            promtAnyKey();
        });

    }

    private void deleteEmployee() {
        System.out.println(Personnel.toString());
        System.out.println("Syötä poistettava tehtäväkiinnitys työntekijän tunnuksella (Id): ");
        int employeeId = Integer.valueOf(Reader.nextLine());
        if (Personnel.getEmployees().containsKey(employeeId)) {
            System.out.println(Personnel.deleteEmployee(employeeId));
            promtAnyKey();
        } else {
            System.out.println("Virheellinen id!");
        }
    }

    private void getEmployeeById() {
        System.out.println("Syötä tehtävään kiinnitetyn työntekijän tunnus (Id) hakua varten: ");
        int employeeId = Integer.valueOf(Reader.nextLine());
        System.out.println(Personnel.getEmployee(employeeId).toString());
        promtAnyKey();
    }

    private void addNewEmployee() {
        System.out.println(Persons.toString());
        System.out.println("");
        System.out.print("Syötä henkilön tunnus (Id) yllä olevasta listasta tehtävään kiinnitystä varten: ");
        int id = Integer.valueOf(Reader.nextLine());
        Person person = Persons.getPerson(id);
        System.out.print("Syötä tehtävän titteli: ");
        String position = Reader.nextLine();
        System.out.print("Syötä tehtävän aloitus pvm muodossa vvvvMMdd: ");
        String startingDate = Utils.validateEmploymentStartingDate(Reader.nextLine());
//        String startingDate = Reader.nextLine();
        System.out.print("Syötä tehtävän vaativuusluokka (1-3): ");
        int competenceClass = Integer.valueOf(Reader.nextLine());
        System.out.print("Onko työsopimus toistaiseksi voimassa (k) tai (ei)? ");
        String isPermanentStr = Reader.nextLine();
        boolean isPermanentEmployment = isPermanentStr.equalsIgnoreCase("k");
        System.out.print("Syötä työntekijän yksikkö. Vaihtoehdot: yksikkö-y, yksikkö-x, yksikkö-c: ");
        String unit = Reader.nextLine();
        System.out.print("Syötä työntekijän suoritusarviointi prosentti (7%-50%)muodossa esim. 7: ");
        int performanceRate = Integer.valueOf(Reader.nextLine());
        System.out.print("Syötä tehtävän rooli (esimies/työntekijä) muodossa e/t: ");
        String role = Reader.nextLine();
        boolean isUnitSupervisor = role.equalsIgnoreCase("e");
        Employee employee = new Employee(id, position, startingDate, competenceClass, isPermanentEmployment, unit, performanceRate, isUnitSupervisor, person);
        System.out.println(Personnel.addEmployee(employee, Persons));
        promtAnyKey();

    }

    private void updateEmployee() {
        System.out.println(Personnel.toString());
        System.out.println("");
        System.out.print("Anna tehtävään kiinnitetyn työntekijän tunnus (Id) tietojen muokkaamista varten: ");
        int id = Integer.valueOf(Reader.nextLine());
        if (!Personnel.getEmployees().containsKey(id)) {
            System.out.println("Virheellinen tunnus!\n");
            return;
        }
        Employee employee = Personnel.getEmployee(id);
        System.out.print("Muuta nykyisen tittelin: (" + employee.getPosition() + ") tyhjällä nykyinen jää voimaan: ");
        String position = Reader.nextLine();
        if (position.length() > 0) {
            employee.setPosition(position);
        }
        System.out.print("Muuta nykyinen vaativuusluokka: (" + employee.getCompetenceClass() + ") (1 - 3) tyhjällä nykyinen jää voimaan: ");
        String competenceClassStr = Reader.nextLine();
        if (competenceClassStr.length() > 0) {
            int competenceClass = Integer.valueOf(competenceClassStr);
            employee.setCompetenceClass(competenceClass);
        }

        System.out.print("Muuta nykyinen työsopimusmuoto: (" + employee.getIsPermanentEmploymentStr() + ") vakituinen = (v), määräaikanen = (m), tyhjällä nykyinen jää voimaan: ");
        String isPermanentStr = Reader.nextLine();
        if (isPermanentStr.equalsIgnoreCase("v")) {
            employee.setIsPermanentEmployment(Boolean.TRUE);
        } else if (isPermanentStr.equalsIgnoreCase("m")) {
            employee.setIsPermanentEmployment(Boolean.FALSE);
        }
        System.out.print("Muuta nykyinen yksikkö: (" + employee.getUnit() + ") tyhjällä nykyinen jää voimaan: ");
        String unit = Reader.nextLine();
        if (unit.length() > 0) {
            employee.setUnit(unit);
        }
        System.out.print("Muuta nykyinen suoritusarviointi: (" + employee.getPerformanceRate() + "%) (7-50) tyhjällä nykyinen jää voimaan: ");
        String PerformanceRateStr = Reader.nextLine();
        if (PerformanceRateStr.length() > 0) {
            int PerformanceRate = Integer.valueOf(PerformanceRateStr);
            employee.setPerformanceRate(PerformanceRate);
        }
        System.out.print("Muuta nykyinen tehtävän rooli: (" + employee.getIsUnitSupervisorStr() + ") esimies - (e) työntekijä - (t) tyhjällä nykyinen jää voimaan: ");
        String isUnitSupervisorStr = Reader.nextLine();
        if (isUnitSupervisorStr.equalsIgnoreCase("e")) {
            employee.setIsUnitSupervisor(Boolean.TRUE);
        } else if (isUnitSupervisorStr.equalsIgnoreCase("t")) {
            employee.setIsUnitSupervisor(Boolean.FALSE);
        }
        System.out.println(Personnel.updateEmployee(employee, id));
        promtAnyKey();

    }

}
