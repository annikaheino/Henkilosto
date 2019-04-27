package personnel;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Annika Heino
 */
public class Person {

    private final Integer Id;
    private String LastName;
    private String FirstName;
    private final LocalDate BirthDate;
    private String Gender;

    public Person(Integer id, String LastName, String FirstName, String BirthDate, String Gender ) {
        this.Id = id;
        this.LastName = Utils.capitalizeString(LastName);
        this.FirstName = Utils.capitalizeString(FirstName);
        this.BirthDate = Utils.string_yyyyMMdd_ToLocalDate(BirthDate);
        this.Gender = Gender;
    }

    public LocalDate getBirthDate() {
        return BirthDate;
    }
    
    public String getBirthdayString_yyyyMMdd() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return BirthDate.format(formatter);
    }

    public Integer getId() {
        return Id;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {
        this.FirstName = Utils.capitalizeString(FirstName);
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String LastName) {
        this.LastName = Utils.capitalizeString(LastName);
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String Gender) {
        this.Gender = Utils.capitalizeString(Gender);
    }
    
    public String getAgeInYears() {
        return String.valueOf(Period.between(BirthDate, LocalDate.now()).getYears());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Id: " + Id + ", ");
        sb.append(LastName).append(" ").append(FirstName).append(", ").append(Gender.equals("F") ? "nainen": "mies").append(", ")
                .append(" ikä - ").append(Period.between(BirthDate, LocalDate.now()).getYears());
        return sb.toString();
    }
}