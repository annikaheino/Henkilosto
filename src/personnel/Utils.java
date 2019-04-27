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
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (!entry.matches("-?\\d+(\\.\\d+)?")) {
                System.out.print("Söytä numero (0 - 8): ");
                entry = reader.nextLine();
                continue;
            }
            if (Integer.valueOf(entry) >= 0 && Integer.valueOf(entry) < 9) {
                break;
            }
            System.out.print("Söytä numero (0 - 8): ");
            entry = reader.nextLine();

        }
        System.out.println();
        return Integer.valueOf(entry);
    }

    static String validateEmploymentStartingDate(String entry) {
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (!entry.matches("-?\\d+(\\.\\d+)?") || entry.length() != 8) {
                System.out.print("Söytä päivämäärä muodossa vvvvMMdd: ");
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
                System.out.print("Aloitus PVM voi olla korkeintaan 2 vuotta vanha tai 6 kuukautta tulevaisuuteen!\n\nSöytä päivämäärä muodossa vvvvMMdd: ");
                entry = reader.nextLine();
                continue;
            }

            System.out.print("Söytä päivämäärä muodossa vvvvMMdd: ");
            entry = reader.nextLine();

        }
        return entry;

    }

    static String validatePersonAge(String entry) {
        Scanner reader = new Scanner(System.in);
        while (true) {
            if (!entry.matches("-?\\d+(\\.\\d+)?") || entry.length() != 8) {
                System.out.print("Söytä syntymäpäivä muodossa vvvvMMdd: ");
                entry = reader.nextLine();
                continue;
            }
            if (isValid(entry)) {
                LocalDate date = string_yyyyMMdd_ToLocalDate(entry);
                Integer years = Period.between(date, LocalDate.now()).getYears();
                if (years > 17 && years < 80) {
                    break;
                }
                System.out.print("Emme palkkaa alaikäisiä tai 80+ vuotiaita!\n\nSöytä syntymäpäivä muodossa vvvvMMdd: ");
                entry = reader.nextLine();
                continue;
            }

            System.out.print("Söytä syntymäpäivä muodossa vvvvMMdd: ");
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
        Scanner reader = new Scanner(System.in);
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
}
