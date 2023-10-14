package hibernate.control;

import hibernate.model.Domicilio;
import hibernate.model.Persona;
import hibernate.model.Residenza;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.query.Query;

import javax.persistence.PersistenceException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
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

        try {

            if (opCode == 0) {
                System.out.println("Inserisci il codice fiscale: ");
                String codiceFiscale = scanner.nextLine();
                System.out.println("Inserisci il nome: ");
                String nome = scanner.nextLine();
                System.out.println("Inserisci il cognome: ");
                String cognome = scanner.nextLine();

                /* retrieving all Residenza and Domicilio entries */
                String residenzaHql = "FROM Residenza";
                String domicilioHql = "FROM Domicilio";
                Query<Residenza> residenzaQuery = session.createQuery(residenzaHql, Residenza.class);
                Query<Domicilio> domicilioQuery = session.createQuery(domicilioHql, Domicilio.class);
                List<Residenza> residenze = residenzaQuery.list();
                List<Domicilio> domicili = domicilioQuery.list();

                Residenza residenzaScelta;
                System.out.println("Seleziona una residenza tra le seguenti (inserisci l'indice corrispondente): ");
                for (int i = 0; i < residenze.size(); i++) {
                    System.out.println(i + " - " + residenze.get(i).getIndirizzo() + ", " + residenze.get(i).getCittà());
                }
                int index = scanner.nextInt();
                if (index < 0 || index >= residenze.size()) {
                    System.err.println("Indice non valido!");
                    return;
                } else {
                    residenzaScelta = residenze.get(index);
                }

                List<Domicilio> domiciliScelti = new ArrayList<>();

                while (true) {
                    System.out.println("Seleziona un domicilio tra i seguenti (inserisci l'indice corrispondente): ");
                    for (int i = 0; i < domicili.size(); i++) {
                        System.out.println(i + " - " + domicili.get(i).getIndirizzo() + ", " + domicili.get(i).getCittà());
                    }
                    index = scanner.nextInt();
                    if (index < 0 || index >= domicili.size()) {
                        System.err.println("Indice non valido!");
                        return;
                    } else {
                        domiciliScelti.add(domicili.get(index));
                    }
                    System.out.println("Selezionare un altro domicilio? (y/n)");
                    String response = scanner.next();
                    if (!response.equals("y") && !response.equals("Y")) {
                        break;
                    }
                }

                Persona persona = new Persona(codiceFiscale, nome, cognome, residenzaScelta, domiciliScelti);

                session.beginTransaction();
                session.save(persona);
                session.getTransaction().commit();

                System.out.println("Record inserito correttamente.");

            } else if (opCode == 1) {
                System.out.println("Inserisci l'indirizzo: ");
                String indirizzo = scanner.nextLine();
                System.out.println("Inserisci la città: ");
                String città = scanner.nextLine();
                System.out.println("Inserisci il cap: ");
                String cap = scanner.nextLine();

                Residenza residenza = new Residenza(indirizzo, città, cap);
                Domicilio domicilio = new Domicilio(indirizzo, città, cap);

                session.beginTransaction();
                session.save(residenza);
                session.getTransaction().commit();

                session.beginTransaction();
                session.save(domicilio);
                session.getTransaction().commit();

                System.out.println("Record inserito correttamente.");

            } else if (opCode == 2) {
                System.out.println("Inserisci l'indirizzo: ");
                String indirizzo = scanner.nextLine();
                System.out.println("Inserisci la città: ");
                String città = scanner.nextLine();
                System.out.println("Inserisci il cap: ");
                String cap = scanner.nextLine();

                Domicilio domicilio = new Domicilio(indirizzo, città, cap);

                session.beginTransaction();
                session.save(domicilio);
                session.getTransaction().commit();

                System.out.println("Record inserito correttamente");

            } else {
                return;
            }
        } catch (PersistenceException e) {
            System.err.println("Record duplicato!");
        }
    }

    private static void select() {
        System.out.println("Seleziona un'operazione: \n" +
                "0 - Ottieni residenza e domicili di una persona\n" +
                "1 - Ottieni residenti in un indirizzo\n" +
                "2 - Indietro\n");
        int opCode = scanner.nextInt();
        scanner.nextLine();

        if (opCode == 0) {

            System.out.println("Inserisci il codice fiscale: ");
            String input = scanner.nextLine();

            String hql = "FROM Persona P WHERE P.codiceFiscale = :codiceFiscale";
            Query query = session.createQuery(hql);
            query.setParameter("codiceFiscale", input);
            Persona persona = (Persona) query.uniqueResult();

            if (persona == null) {
                System.out.println("Record non trovato");
                return;
            }

            System.out.println("***** residenza *****");
            Residenza residenza = persona.getResidenza();
            System.out.println(residenza.getIndirizzo() + ", " + residenza.getCittà() + " (" + residenza.getCap() + ")");
            System.out.println("*********************");
            System.out.println("***** domicili ******");
            for (Domicilio domicilio : persona.getDomicili()) {
                System.out.println(domicilio.getIndirizzo() + ", " + domicilio.getCittà() + " (" + domicilio.getCap() + ")");
            }
            System.out.println("*********************");

        } else if (opCode == 1) {

            System.out.println("Inserisci l'indirizzo: ");
            String indirizzo = scanner.nextLine();
            System.out.println("Inserisci la città: ");
            String città = scanner.nextLine();
            System.out.println("Inserisci il cap: ");
            String cap = scanner.nextLine();

            Residenza residenza = new Residenza(indirizzo, città, cap);

            String hql = "FROM Persona P WHERE P.residenza = :residenza";
            Query<Persona> query = session.createQuery(hql, Persona.class);
            query.setParameter("residenza", residenza);

            List<Persona> results = query.list();

            System.out.println("***** risultati *****");
            for (Persona p : results) {
                System.out.printf("%s %s%n", p.getNome(), p.getCognome());
            }

        } else {
            return;
        }
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
