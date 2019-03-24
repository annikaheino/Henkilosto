package personnel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    protected static Connection connect() {
        String url = "jdbc:sqlite:henkilosto.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    protected static String capitlizeString(String instance) {
        String capitalizedString = instance.toLowerCase().trim();
        if (capitalizedString.length() > 1) {
            capitalizedString = capitalizedString.substring(0, 1).toUpperCase() + capitalizedString.substring(1);
        } else if (capitalizedString.length() == 1) {
            capitalizedString = capitalizedString.toUpperCase();
        }
        return capitalizedString;
    }
}
