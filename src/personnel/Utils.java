package personnel;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Scanner;

/**
 *
 * @author Annika Heino
 */
public class Utils {

    static Scanner reader = new Scanner(System.in, "UTF-8");

    protected static LocalDate string_yyyyMMdd_ToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }

    protected static String capitalizeString(String instance) {
        String capitalizedString = instance.toLowerCase().trim();
        if (capitalizedString.length() > 1) {
            capitalizedString = capitalizedString.substring(0, 1).toUpperCase() + capitalizedString.substring(1);
        } else if (capitalizedString.length() == 1) {
            capitalizedString = capitalizedString.toUpperCase();
        }
        return capitalizedString;
    }

    protected static BigDecimal getMoneyRepresentation(Integer performanceRate, Double amount) {
        BigDecimal salary = BigDecimal.valueOf(amount)
                .multiply(BigDecimal.ONE.add(BigDecimal.valueOf(performanceRate).divide(BigDecimal.valueOf(100))));
        return salary.setScale(2, RoundingMode.CEILING);
    }

    protected static Integer validateNumberSelection(String entry) {
        while (true) {
            if (!entry.matches("-?\\d+(\\.\\d+)?")) {
                System.out.print("Syötä numero (0 - 8): ");
                entry = reader.nextLine();
                continue;
            }
            if (Integer.valueOf(entry) >= 0 && Integer.valueOf(entry) < 9) {
                break;
            }
            System.out.print("Syötä numero (0 - 8): ");
            entry = reader.nextLine();

        }
        System.out.println();
        return Integer.valueOf(entry);
    }

    static String validateEmploymentStartingDate(String entry) {
        while (true) {
            if (!entry.matches("-?\\d+(\\.\\d+)?") || entry.length() != 8) {
                System.out.print("Syötä päivämäärä muodossa vvvvMMdd: ");
                entry = reader.nextLine();
                continue;
            }
            if (isValid(entry)) {
                LocalDate date = string_yyyyMMdd_ToLocalDate(entry);
                Integer years = Period.between(date, LocalDate.now()).getYears();
                Integer months = Period.between(date, LocalDate.now()).getMonths();
                if ((date.isAfter(LocalDate.now()) && months < 6) || (date.isBefore(LocalDate.now()) && years < 2)) {
                    break;
                }
                System.out.print("Aloitus PVM voi olla korkeintaan 2 vuotta vanha tai 6 kuukautta tulevaisuuteen!\n\nSyötä päivämäärä muodossa vvvvMMdd: ");
                entry = reader.nextLine();
                continue;
            }

            System.out.print("Syötä päivämäärä muodossa vvvvMMdd: ");
            entry = reader.nextLine();

        }
        return entry;

    }

    static String validatePersonAge(String entry) {
        while (true) {
            if (!entry.matches("-?\\d+(\\.\\d+)?") || entry.length() != 8) {
                System.out.print("Syötä syntymäpäivä muodossa vvvvMMdd: ");
                entry = reader.nextLine();
                continue;
            }
            if (isValid(entry)) {
                LocalDate date = string_yyyyMMdd_ToLocalDate(entry);
                Integer years = Period.between(date, LocalDate.now()).getYears();
                if (years > 17 && years < 80) {
                    break;
                }
                System.out.println("Emme palkkaa alaikäisiä tai 80+ vuotiaita!");
                System.out.print("Syötä syntymäpäivä muodossa vvvvMMdd: ");
                entry = reader.nextLine();
                continue;
            }

            System.out.print("Syötä syntymäpäivä muodossa vvvvMMdd: ");
            entry = reader.nextLine();

        }
        return entry;

    }

    private static boolean isValid(String input) {
        DateTimeFormatter[] formatters = {
            new DateTimeFormatterBuilder().appendPattern("yyyy")
            .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter(),
            new DateTimeFormatterBuilder().appendPattern("yyyyMM")
            .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
            .toFormatter(),
            new DateTimeFormatterBuilder().appendPattern("yyyyMMdd")
            .parseStrict().toFormatter()};
        for (DateTimeFormatter formatter : formatters) {
            try {
                LocalDate.parse(input, formatter);
                return true;
            } catch (DateTimeParseException e) {
            }
        }
        return false;
    }

    protected static String validateGender(String entry) {
        while (true) {
            if ("M".equals(capitalizeString(entry)) || "N".equals(capitalizeString(entry))) {
                break;
            }
            System.out.print("Syötä uuden henkilön sukupuoli (mies/nainen) muodossa M/N: ");
            entry = reader.nextLine();
        }
        if ("N".equals(capitalizeString(entry))) {
            return "F";
        }

        return capitalizeString(entry);

    }

    static boolean isZeroKeyPressed(String entry) {
        if (entry.equals("0")) {
            System.out.println("Olet poistunut päävalikkoon.\n");
            return entry.equals("0");
        }
        return false;
    }

    static int validatePersonId(String stringId, Persons Persons) {
        while (true) {
            if (stringId.matches("-?\\d+(\\.\\d+)?") && Persons.isPersonIdFound(Integer.valueOf(stringId))) {
                return Integer.valueOf(stringId);
            }
            System.out.print("Syötä henkilön tunnus (id) henkilölistasta!");
            stringId = reader.nextLine();
        }
    }

    static int validateCompetenceClass(String strCompetenceClass) {
        while (!(strCompetenceClass.equals("1") || strCompetenceClass.equals("2") || strCompetenceClass.equals("3"))) {
            System.out.print("Syötä vaativusluokka (1 - 3): ");
            strCompetenceClass = reader.nextLine();
        }
        return Integer.valueOf(strCompetenceClass);
    }

    static boolean validateEiOrK(String permanentStr) {
        while (!(permanentStr.equalsIgnoreCase("k") || permanentStr.equalsIgnoreCase("ei"))) {
            System.out.print("Syötä (k) tai (ei): ");
            permanentStr = reader.nextLine();
        }
        return permanentStr.equalsIgnoreCase("k");
    }

    static String validateWorkingUnit(String unit) {

        while (true) {
            if (unit.equalsIgnoreCase("yksikkö-y") || unit.equalsIgnoreCase("yksikkö-x") || unit.equalsIgnoreCase("yksikkö-c")) {
                return unit;
            }
            if (unit.length() == 9) {
                String scandinavicOHexCode = String.format("%x", (int) unit.charAt(6));
                String chunkBefore = unit.substring(0, 6);
                String chunkAfter = unit.substring(7, 9);
                if (chunkBefore.equalsIgnoreCase("yksikk")
                        && (chunkAfter.equalsIgnoreCase("-y") || chunkAfter.equalsIgnoreCase("-x") || chunkAfter.equalsIgnoreCase("-c"))
                        && scandinavicOHexCode.equals("fffd")) {
                    if (chunkAfter.equals("-y")) {
                        return "yksikkö-y";
                    }
                    if (chunkAfter.equals("-x")) {
                        return "yksikkö-x";
                    }
                    if (chunkAfter.equals("-c")) {
                        return "yksikkö-c";
                    }
                }
            }
            System.out.print("Syötä työntekijän yksikkö. Vaihtoehdot: yksikkö-y, yksikkö-x, yksikkö-c: ");
            unit = reader.nextLine();
        }
    }

    static int validatePerformanceRate(String strPerformaneRate) {
        while (true) {
            if (strPerformaneRate.matches("-?\\d+(\\.\\d+)?")) {
                int performanceRate = Integer.valueOf(strPerformaneRate);
                if (performanceRate >= 7 && performanceRate <= 50) {
                    return performanceRate;
                }
            }
            System.out.print("Syötä työntekijän suoritusarviointi prosentti (7%-50%)muodossa esim. 7! ");
            strPerformaneRate = reader.nextLine();
        }
    }

    static boolean isUnitSupervisor(String role) {
        while (!(role.equalsIgnoreCase("e") || role.equalsIgnoreCase("t"))) {
            System.out.print("Syötä (e) - esimeies tai (t) - työntekijä: ");
            role = reader.nextLine();
        }
        return role.equalsIgnoreCase("e");
    }

    static int validateEmployeeId(String stringId, Personnel Personnel) {
        while (true) {
            if (stringId.matches("-?\\d+(\\.\\d+)?") && Personnel.getEmployees().containsKey(Integer.valueOf(stringId))) {
                return Integer.valueOf(stringId);
            }
            System.out.print("Syötä oikea työntekijän tunnus (id)!");
            stringId = reader.nextLine();
        }

    }

    static String validatePersonFirstName(String firstName) {
        while(firstName.length() < 0 || firstName.length() > 30 || !firstName.toLowerCase().matches("[a-z]+")){
            System.out.print("Syötä etunimeksi vain merrkkejä a-z: ");
            firstName = reader.nextLine();
        }
        return firstName;
    }

    static String validatePersonLastName(String lastName) {
        while(lastName.length() < 0 || lastName.length() > 30 || !lastName.toLowerCase().matches("[a-z]+")){
            System.out.print("Syötä sukunimeksi vain merrkkejä a-z: ");
            lastName = reader.nextLine();
        }
        return lastName;
    }
}
