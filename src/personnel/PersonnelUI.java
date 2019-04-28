package personnel;

import java.math.BigDecimal;
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
        this.Reader = new Scanner(System.in, "UTF-8");
    }

    public void startApplication() {

        System.out.println("Tervetuloa!");
        loop:
        while (true) {
            System.out.println("1 - näytä kaikki järjestelmässä olevat henkilöt (tehtävään kiinnitetyt ja vielä kiinnittämättömät)");
            System.out.println("2 - lisää uusi henkilö järjestelmään");
            System.out.println("3 - poista henkilö (poista henkilö järjestelmästä kokonaan)");
            System.out.println("4 - näytä tehtävään kiinnitetyt työntekijät");
            System.out.println("5 - kiinnitä järjestelmässä oleva henkilö tehtävään");
            System.out.println("6 - hae tehtävään kiinnitetty henkilö tunnuksella (Id)");
            System.out.println("7 - poista työntekijän tehtäväkiinnitys tunnuksella (Id)");
            System.out.println("8 - muokkaa tehtävään kiinnitetyn työntekijän tietoja tunnuksella (Id)");
            System.out.println("0 - lopetetaan");

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
        System.out.println("Poistu edelliseen valikkoon painamalla 0: ");

        System.out.print("Syötä uuden henkilön etunimi, (tai poistu - 0): ");
        String firtsName = Reader.nextLine();
        if (Utils.isZeroKeyPressed(firtsName)) {
            return;
        }
        firtsName = Utils.validatePersonFirstName(firtsName);

        System.out.print("Syötä uuden henkilön sukunimi, (tai poistu - 0): ");
        String lastName = Reader.nextLine();
        if (Utils.isZeroKeyPressed(lastName)) {
            return;
        }
        lastName = Utils.validatePersonLastName(lastName);
        System.out.print("Syötä uuden henkilön syntymäpäivä muodossa vvvvMMdd, (tai poistu - 0): ");
        String birthDate = Reader.nextLine();
        if (Utils.isZeroKeyPressed(birthDate)) {
            return;
        }
        birthDate = Utils.validatePersonAge(birthDate);
        System.out.print("Syötä uuden henkilön sukupuoli (mies/nainen) muodossa M/N, (tai poistu - 0): ");
        String gender = Reader.nextLine();
        if (Utils.isZeroKeyPressed(gender)) {
            return;
        }
        gender = Utils.validateGender(gender);
        Person person = new Person(1, lastName, firtsName, birthDate, gender);
        System.out.println(Persons.addPerson(person));
        promtAnyKey();
    }

    private void deletePerson() {
        System.out.println(Persons.toString());
        System.out.print("Syötä järjestelmästä poistettavan henkilön tunnus (Id), (tai poistu - 0): ");
        String strId = Reader.nextLine();
        if (Utils.isZeroKeyPressed(strId)) {
            return;
        }
        int id = Utils.validatePersonId(strId, Persons);
        System.out.println(Persons.deletePerson(id));
        promtAnyKey();
    }

    private void listEmployeesAndSalaries() {
        Personnel.getEmployees().forEach((key, employee) -> {
            BigDecimal employeeSalary = Utils.getMoneyRepresentation(
                    employee.getPerformanceRate(),
                    Salaries.getSalaryByKey(employee.getCompetenceClass()));
            System.out.println(key + ": " + employee.toString() + ", €" + employeeSalary);
        });
        promtAnyKey();

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

        System.out.print("Syötä henkilön tunnus (Id) yllä olevasta listasta tehtävään kiinnitystä varten, (tai poistu - 0): ");
        String stringId = Reader.nextLine();
        if (Utils.isZeroKeyPressed(stringId)) {
            return;
        }
        int id = Utils.validatePersonId(stringId, Persons);
        Person person = Persons.getPerson(id);

        System.out.print("Syötä tehtävän titteli, (tai poistu - 0): ");
        String position = Reader.nextLine();
        if (Utils.isZeroKeyPressed(position)) {
            return;
        }

        System.out.print("Syötä tehtävän aloitus pvm muodossa vvvvMMdd, (tai poistu - 0): ");
        String startingDate = Reader.nextLine();
        if (Utils.isZeroKeyPressed(startingDate)) {
            return;
        }
        startingDate = Utils.validateEmploymentStartingDate(startingDate);

        System.out.print("Syötä tehtävän vaativuusluokka (1-3), (tai poistu - 0): ");
        String strCompetenceClass = Reader.nextLine();
        if (Utils.isZeroKeyPressed(strCompetenceClass)) {
            return;
        }
        int competenceClass = Utils.validateCompetenceClass(strCompetenceClass);

        System.out.print("Onko työsopimus toistaiseksi voimassa (k) tai (ei), (poistu - 0):  ");
        String isPermanentStr = Reader.nextLine();
        if (Utils.isZeroKeyPressed(isPermanentStr)) {
            return;
        }

        boolean isPermanentEmployment = Utils.validateEiOrK(isPermanentStr);

        System.out.print("Syötä työntekijän yksikkö. Vaihtoehdot: yksikkö-y, yksikkö-x, yksikkö-c, (tai poistu - 0): ");
        String unit = Reader.nextLine();
        if (Utils.isZeroKeyPressed(unit)) {
            return;
        }
        unit = Utils.validateWorkingUnit(unit);

        System.out.print("Syötä työntekijän suoritusarviointi prosentti (7%-50%)muodossa esim. 7, (tai poistu - 0): ");
        String strPerformaneRate = Reader.nextLine();
        if (Utils.isZeroKeyPressed(strPerformaneRate)) {
            return;
        }
        int performanceRate = Utils.validatePerformanceRate(strPerformaneRate);

        System.out.print("Syötä tehtävän rooli (esimies/työntekijä) muodossa e/t, (tai poistu - 0): ");
        String role = Reader.nextLine();
        if (Utils.isZeroKeyPressed(role)) {
            return;
        }
        boolean isUnitSupervisor = Utils.isUnitSupervisor(role);
        Employee employee = new Employee(id, position, startingDate, competenceClass, isPermanentEmployment, unit, performanceRate, isUnitSupervisor, person);
        System.out.println(Personnel.addEmployee(employee, Persons));
        promtAnyKey();

    }

    private void updateEmployee() {
        System.out.println(Personnel.toString());
        System.out.println("");
        System.out.print("Anna tehtävään kiinnitetyn työntekijän tunnus (Id) tietojen muokkaamista varten, (tai poistu - 0): ");
        String stringId = Reader.nextLine();
        if (Utils.isZeroKeyPressed(stringId)) {
            return;
        }
        int id = Utils.validateEmployeeId(stringId, Personnel);

        Employee employee = Personnel.getEmployee(id);
        System.out.print("Muuta nykyisen tittelin: (" + employee.getPosition() + ") tyhjällä nykyinen jää voimaan, (tai poistu - 0): ");
        String position = Reader.nextLine();
        if (Utils.isZeroKeyPressed(position)) {
            return;
        }
        if (position.length() > 0) {
            employee.setPosition(position);
        }

        System.out.print("Muuta nykyinen vaativuusluokka: (" + employee.getCompetenceClass() + ") (1 - 3) tyhjällä nykyinen jää voimaan, (tai poistu - 0): ");
        String competenceClassStr = Reader.nextLine();
        if (Utils.isZeroKeyPressed(competenceClassStr)) {
            return;
        }
        if (competenceClassStr.length() > 0) {
            employee.setCompetenceClass(Utils.validateCompetenceClass(competenceClassStr));
        }

        System.out.print("Muuta nykyinen työsopimusmuoto: (" + employee.getIsPermanentEmploymentStr() + ") toistaiseksi voimassa = (k), ei = (ei), tyhjällä nykyinen jää voimaan, (tai poistu - 0): ");
        String isPermanentStr = Reader.nextLine();
        if (Utils.isZeroKeyPressed(isPermanentStr)) {
            return;
        }
        if (isPermanentStr.length() > 0) {
            employee.setIsPermanentEmployment(Utils.validateEiOrK(isPermanentStr));
        }

        System.out.print("Muuta nykyinen yksikkö: (" + employee.getUnit() + ") tyhjällä nykyinen jää voimaan, (tai poistu - 0): ");
        String unit = Reader.nextLine();
        if (Utils.isZeroKeyPressed(unit)) {
            return;
        }
        if (unit.length() > 0) {
            employee.setUnit(Utils.validateWorkingUnit(unit));
        }

        System.out.print("Muuta nykyinen suoritusarviointi: (" + employee.getPerformanceRate() + "%) (7-50) tyhjällä nykyinen jää voimaan, (tai poistu - 0): ");
        String performanceRateStr = Reader.nextLine();
        if (Utils.isZeroKeyPressed(performanceRateStr)) {
            return;
        }
        if (performanceRateStr.length() > 0) {
            employee.setPerformanceRate(Utils.validatePerformanceRate(performanceRateStr));
        }

        System.out.print("Muuta nykyinen tehtävän rooli: (" + employee.getIsUnitSupervisorStr() + ") esimies - (e) työntekijä - (t) tyhjällä nykyinen jää voimaan, (tai poistu - 0): ");
        String isUnitSupervisorStr = Reader.nextLine();
        if (Utils.isZeroKeyPressed(isUnitSupervisorStr)) {
            return;
        }
        if (isUnitSupervisorStr.length() > 0) {
            employee.setIsUnitSupervisor(Utils.isUnitSupervisor(isUnitSupervisorStr));
        }

        System.out.println(Personnel.updateEmployee(employee, id));
        promtAnyKey();

    }

}
