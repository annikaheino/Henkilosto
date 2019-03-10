package personnel;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Annika Heino
 */
public class Personnel {

    private Map<Integer, Person> Employees;

    public Personnel() {
        this.Employees = new HashMap<>();
    }

    public void addEmployee(Person person) {
        int key = Employees.size() > 0 ? Collections.max(Employees.keySet()) + 1 : 1;
        Employees.put(key, person);

    }

    public void removeEmaployee(Integer id) {
        Employees.remove(id);
    }
    
    public Person getEmployee(int id) {
        return this.Employees.get(id);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        this.Employees.forEach((key, person) -> sb.append("Id - " + key).append(": ").append(person.toString()).append("\n"));
        return sb.toString();
    }

}
