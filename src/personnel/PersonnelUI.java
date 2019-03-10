package personnel;

import java.util.Scanner;

/**
 *
 * @author Annika Heino
 */
public class PersonnelUI {

    public void startApplication() {
        Scanner reader = new Scanner(System.in);
                
        Utils utils = new Utils();
        Personnel personnel = new Personnel();
        personnel.addEmployee(new Person("Satu", "Suomalainen", utils.string_yyyyMMdd_ToLocalDate("19880525"), "Female", "Opettaja", utils.string_yyyyMMdd_ToLocalDate("20140402"), true, 3));
        personnel.addEmployee(new Person("Pauli", "Vaasalainen", utils.string_yyyyMMdd_ToLocalDate("19941206"), "Male", "Rehtori", utils.string_yyyyMMdd_ToLocalDate("20171102"), false, 1));
        personnel.addEmployee(new Person("Lyydia", "Lappalainen", utils.string_yyyyMMdd_ToLocalDate("19921109"), "Male", "Keittäjä", utils.string_yyyyMMdd_ToLocalDate("20180110"), false, 2));
        personnel.addEmployee(new Person("Liisa", "Lahtelainen", utils.string_yyyyMMdd_ToLocalDate("19550821"), "Female", "Siivoja", utils.string_yyyyMMdd_ToLocalDate("20140402"), true, 1));
        personnel.addEmployee(new Person("Matti", "Espoolainen", utils.string_yyyyMMdd_ToLocalDate("19800122"), "Male", "Opettaja", utils.string_yyyyMMdd_ToLocalDate("20150819"), true, 3));

        System.out.println("Tervetuloa!");
        while (true) {
            System.out.println("0 - lopetetaan");
            System.out.println("1 - lisää uuden työntekijän");
            System.out.println("2 - hae työntekijän tunnuksella (Id)");
            System.out.println("3 - näytä työntekijät");
            System.out.println("4 - poista työntekijän");

            System.out.println("Mitä tehdään?");

            int komento = Integer.valueOf(reader.nextLine());

            if (komento == 0) {
                break;
            }
            if(komento == 1) {
                System.out.print("Syötä uuden työntekijän etunimi: ");
                String firtsName = reader.nextLine();
                System.out.print("Syötä uuden työntekijän sukunimi: ");
                String lastName = reader.nextLine();
                System.out.print("Syötä uuden työntekijän syntymäpäivän muodossa vvvvMMdd: ");
                String birthDate = reader.nextLine();
                System.out.print("Syötä uuden työntekijän sukupuoli: ");
                String gender = reader.nextLine();
                System.out.print("Syötä uuden työntekijän titteli: ");
                String position = reader.nextLine();
                System.out.print("Syötä työsopimuksen aloituspäivän muodossa vvvvMMdd: ");
                String startingDate = reader.nextLine();
                System.out.print("Onko tuleva työsuhde vakituinen? (k) - kyllä, (n) tai muu merkki - ei: ");
                Boolean isPermanent = reader.nextLine().equals("k") ? true : false;
                System.out.print("Syötä vaativuusluokka 1, 2 tai 3: ");
                int competenceClass = Integer.valueOf(reader.nextLine());
                Person newPerson = new Person(firtsName, lastName, utils.string_yyyyMMdd_ToLocalDate(birthDate), gender, position, utils.string_yyyyMMdd_ToLocalDate(startingDate), isPermanent, competenceClass);
                
                personnel.addEmployee(newPerson);
                System.out.println("Uusi työntekijä lisätty, tiedot: ");
                System.out.println(newPerson.toString());
            }
            if(komento == 2) {
                System.out.println("Syötä haettavan työntekijän tunnus (Id): ");
                int id = Integer.valueOf(reader.nextLine());
                System.out.println(personnel.getEmployee(id).toString());
            }
            if(komento == 3) {
                System.out.println(personnel.toString());
            }
            if(komento == 4) {
                System.out.println("Syötä poistettavan työntekijän tunnus (Id): ");
                int id = Integer.valueOf(reader.nextLine());
                Person removedPerson = personnel.getEmployee(id);
                System.out.println("Työntekijä - " + removedPerson.toString() + " poistettu.");
                personnel.removeEmaployee(id);
            }
            
        }
    }

}
