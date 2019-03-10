
package personnel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author Annika Heino
 */
public class Utils {
    protected LocalDate string_yyyyMMdd_ToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate localDate = LocalDate.parse(date, formatter);
        return localDate;
    }
}
