package hibernate.control;

import hibernate.model.Domicilio;
import hibernate.model.Residenza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);

    private static SessionFactory sessionFactory;
    private static Session session;


    private static void insert() {
        System.out.println("Seleziona un'operazione: \n" +
                "0 - inserimento di una nuova persona\n" +
                "1 - inserimento di una nuova residenza\n" +
                "2 - inserimento di un nuovo domicilio\n" +
                "3 - indietro");
        int opCode = scanner.nextInt();
        scanner.nextLine();

        if (opCode == 0) {



        } else if (opCode == 1) {
            System.out.println("Inserisci l'indirizzo: ");
            String indirizzo = scanner.nextLine();
            System.out.println("Inserisci la città: ");
            String città = scanner.nextLine();
            System.out.println("Inserisci il cap: ");
            int cap = scanner.nextInt();

            Residenza residenza = new Residenza(indirizzo, città, cap);

            session.beginTransaction();
            session.save(residenza);
            session.getTransaction().commit();

            System.out.println("Record inserito correttamente.");

        } else if (opCode == 2) {
            System.out.println("Inserisci l'indirizzo: ");
            String indirizzo = scanner.nextLine();
            System.out.println("Inserisci la città: ");
            String città = scanner.nextLine();
            System.out.println("Inserisci il cap: ");
            int cap = scanner.nextInt();

            Domicilio domicilio = new Domicilio(indirizzo, città, cap);

            System.out.println("Record inserito correttamente");

        } else {
            return;
        }
    }

    private static void select() {

    }

    public static void main(String[] args) {

        sessionFactory = new Configuration().configure().buildSessionFactory();
        session = sessionFactory.openSession();

        while (true) {
            System.out.println("Seleziona un'operazione: \n" +
                    "0 - inserimento di un record\n" +
                    "1 - query su una tabella\n" +
                    "2 - esci\n");
            int opCode = scanner.nextInt();
            scanner.nextLine();

            if (opCode == 0)
                insert();
            else if (opCode == 1)
                select();
            else if (opCode == 2)
                return;
            else {
                System.out.println("Codice non valido");
            }
        }
    }
}
