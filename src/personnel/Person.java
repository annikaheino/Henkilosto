package personnel;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Annika Heino
 */
public class Person {

    private String FirstName;
    private String LastName;
    private final LocalDate BirthDate;
    private String Gender;
    private String Position;
    private final LocalDate EmploymentStartDate;
    private int CompetenceClass;
    private Boolean IsPermanentEmployment;

    public Person(String FirstName, String LastName, LocalDate BirthDate, String Gender, String Position, LocalDate EmploymentStartDate, Boolean IsPermanentEmployment, int competenceClass ) {
        this.FirstName = FirstName;
        this.LastName = LastName;
        this.BirthDate = BirthDate;
        this.Gender = Gender;
        this.Position = Position;
        this.EmploymentStartDate = EmploymentStartDate;
        this.IsPermanentEmployment = IsPermanentEmployment;
        this.CompetenceClass = competenceClass;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = LastName;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Gender;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public LocalDate getEmploymentStartDate() {
        return EmploymentStartDate;
    }

    public Boolean getIsPermanentEmployment() {
        return IsPermanentEmployment;
    }

    public void setIsPermanentEmployment(Boolean IsPermanentEmployment) {
        this.IsPermanentEmployment = IsPermanentEmployment;
    }

    public int getCompetenceClass() {
        return CompetenceClass;
    }

    public void setCompetenceClass(int CompetenceClass) {
        this.CompetenceClass = CompetenceClass;
    }

    @Override
    public String toString() {

        DateTimeFormatter ldtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append(LastName).append(" ").append(FirstName).append(", ").append(Gender).append(", ")
                .append(" age - ").append(Period.between(BirthDate, LocalDate.now()).getYears()).append(", ")
                .append(Position).append(", competence class ").append(Integer.toString(CompetenceClass))
                .append(", starting date - ").append(EmploymentStartDate.format(ldtFormatter))
                .append(IsPermanentEmployment ? " permanent" : " fixed-period");
        return sb.toString();
    }
}
