package personnel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Annika Heino
 */
public class Employee {

    private final Integer Id;
    private String Position;
    private final LocalDate StartingDate;
    private Integer CompetenceClass;
    private Boolean IsPermanentEmployment;
    private String Unit;
    private Integer PerformanceRate;
    private Boolean IsUnitSupervisor;
    private Person Person;

    public Employee(Integer Id, String Position, String StartingDate, Integer CompetenceClass, Boolean IsPermanentEmployment, String Unit, Integer PerformanceRate, Boolean IsUnitSupervisor, Person Person) {
        this.Id = Id;
        this.Position = Position;
        this.StartingDate = Utils.string_yyyyMMdd_ToLocalDate(StartingDate);
        this.CompetenceClass = CompetenceClass;
        this.IsPermanentEmployment = IsPermanentEmployment;
        this.Unit = Unit;
        this.PerformanceRate = PerformanceRate;
        this.IsUnitSupervisor = IsUnitSupervisor;
        this.Person = Person;
    }

    public String getPosition() {
        return Position;
    }

    public void setPosition(String Position) {
        this.Position = Position;
    }

    public Integer getCompetenceClass() {
        return CompetenceClass;
    }

    public void setCompetenceClass(Integer CompetenceClass) {
        this.CompetenceClass = CompetenceClass;
    }

    public Boolean getIsPermanentEmployment() {
        return IsPermanentEmployment;
    }

    public void setIsPermanentEmployment(Boolean IsPermanentEmployment) {
        this.IsPermanentEmployment = IsPermanentEmployment;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public Integer getPerformanceRate() {
        return PerformanceRate;
    }

    public void setPerformanceRate(Integer PerformanceRate) {
        this.PerformanceRate = PerformanceRate;
    }

    public Boolean getIsUnitSupervisor() {
        return IsUnitSupervisor;
    }

    public void setIsUnitSupervisor(Boolean IsUnitSupervisor) {
        this.IsUnitSupervisor = IsUnitSupervisor;
    }

    public Person getPerson() {
        return Person;
    }

    public void setPerson(Person Person) {
        this.Person = Person;
    }

    public String getStartingDateStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        return StartingDate.format(formatter);
    }
    
    public String getIsPermanentEmploymentStr() {
        return getIsPermanentEmployment() ? "vakituinen" : "määräaikainen";
    }
    
    public String getIsUnitSupervisorStr() {
        return getIsUnitSupervisor() ? "esimies" : "työntekijä";
    }
    
    

    @Override
    public String toString() {
        DateTimeFormatter ldtFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        StringBuilder sb = new StringBuilder();
        sb.append(Person.getLastName())
                .append(" ").append(Person.getFirstName()).append(", ").append(Person.getGender().equals("F") ? "Nainen, " : "Mies, ")
                .append("ikä - ").append(Person.getAgeInYears()).append(", ")
                .append("titteli - ").append(getPosition()).append(", ")
                .append("aloitti - ").append(StartingDate.format(ldtFormatter)).append(", ")
                .append("vaativuusluokka - ").append(CompetenceClass).append(", ")
                .append("työsuhde - ").append(IsPermanentEmployment ? "vakituinen, " : "määräaikainen, ")
                .append("yksikkö - ").append(Unit).append(", ").append(IsUnitSupervisor ? "esimies, " : "työntekijä, ")
                .append("vaativuusluokka - ").append(PerformanceRate);
        return sb.toString();
    }

}
