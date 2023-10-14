package jdbc.control;

import jdbc.dao.CaneDao;
import jdbc.dao.IndirizzoDao;
import jdbc.dao.PersonaDao;
import jdbc.exceptions.DuplicateEntryException;
import jdbc.exceptions.NonExistentAddressException;
import jdbc.exceptions.NonExistentPersonException;
import jdbc.model.Cane;
import jdbc.model.Indirizzo;
import jdbc.model.Persona;

import java.util.Scanner;

public class Application {

    private static final Scanner scanner = new Scanner(System.in);
    private static final IndirizzoDao indirizzoDao = new IndirizzoDao();
    private static final PersonaDao personaDao = new PersonaDao();
    private static final CaneDao caneDao = new CaneDao();

    private static void insert() {
        System.out.println("Seleziona una tabella:\n" +
                "0 - Indirizzo\n" +
                "1 - Persona\n" +
                "2 - Cane\n" +
                "3 - indietro\n");
        int code = scanner.nextInt();
        scanner.nextLine();

        try {
            switch (code) {
                case 0:
                    System.out.println("***** Inserimento di un nuovo indirizzo *****\n");
                    System.out.println("Inserisci l'indirizzo: ");
                    String addressName = scanner.nextLine();
                    System.out.println("Inserisci la città");
                    String city = scanner.nextLine();
                    Indirizzo address = new Indirizzo(addressName, city);
                    indirizzoDao.insert(address);
                    break;
                case 1:
                    System.out.println("***** Inserimento di una nuova persona *****\n");
                    System.out.println("Inserisci il nome: ");
                    String name = scanner.next();
                    System.out.println("Inserisci il cognome: ");
                    String lastname = scanner.next();
                    scanner.nextLine();
                    System.out.println("Inserisci l'indirizzo: ");
                    String personAddressName = scanner.nextLine();
                    System.out.println("Inserisci la città");
                    city = scanner.nextLine();
                    Indirizzo personAddress = new Indirizzo(personAddressName, city);
                    Persona person = new Persona(name, lastname, personAddress);
                    try {
                        personaDao.insert(person);
                    } catch (NonExistentAddressException e) {
                        System.err.println("Errore: l'indirizzo specificato non esiste oppure questa entry è già stata inserita.");
                    }
                    break;

                case 2:
                    System.out.println("***** Inserimento di un nuovo cane *****");
                    System.out.println("Inserisci il nome: ");
                    String dogName = scanner.nextLine();
                    System.out.println("Inserisci la razza: ");
                    String race = scanner.nextLine();
                    System.out.println("Inserisci il nome del padrone: ");
                    String nameOwner = scanner.nextLine();
                    System.out.println("Inserisci il cognome del padrone: ");
                    String lastnameOwner = scanner.nextLine();
                    System.out.println("Inserisci l'indirizzo del padrone: ");
                    String addressNameOwner = scanner.nextLine();
                    System.out.println("Inserisci la città");
                    city = scanner.nextLine();
                    Indirizzo addressOwner = new Indirizzo(addressNameOwner, city);
                    Persona owner = new Persona(nameOwner, lastnameOwner, addressOwner);
                    Cane dog = new Cane(dogName, race, owner);

                    try {
                        caneDao.insert(dog);
                    } catch (NonExistentPersonException e) {
                        System.err.println("Errore: la persona specificata (o il suo indirizzo) non esistono, oppure l'indirizzo è sbagliato, oppure la entry è già stata inserita.");
                    }

                    break;

                default:
                    break;
            }
        } catch (DuplicateEntryException e) {
            System.err.println("Errore: entry duplicata.\n");
        }

    }
    private static void select() {
        System.out.println("Seleziona un'operazione:\n" +
                "0 - Trova le persone che vivono presso un indirizzo\n" +
                "1 - Trova i cani posseduti da una persona\n" +
                "2 - Indietro\n"
        );
        int code = scanner.nextInt();
        scanner.nextLine();

        switch (code) {
            case 0:
                System.out.println("***** Trova le persone che vivono presso un indirizzo *****");
                System.out.println("Inserisci l'indirizzo: ");
                String addressName = scanner.nextLine();
                System.out.println("Inserisci la città: ");
                String city = scanner.nextLine();
                Indirizzo address = new Indirizzo(addressName, city);
                personaDao.findPeopleLivingAtAddress(address);
                break;
            case 1:
                System.out.println("***** Trova i cani posseduti da una persona *****");
                System.out.println("Inserisci il nome della persona: ");
                String name = scanner.nextLine();
                System.out.println("Inserisci il cognome della persona: ");
                String lastname = scanner.nextLine();
                System.out.println("Inserisci l'indirizzo della persona: ");
                addressName = scanner.nextLine();
                address = new Indirizzo(addressName, null);
                Persona person = new Persona(name, lastname, address);
                caneDao.findDogsByOwner(person);
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) {
        // create DB and tables
        CreateDatabase.create();

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
