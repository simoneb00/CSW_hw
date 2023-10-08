package control;

import dao.CaneDao;
import dao.IndirizzoDao;
import dao.PersonaDao;
import exceptions.NonExistentAddressException;
import exceptions.NonExistentPersonException;
import model.Cane;
import model.Indirizzo;
import model.Persona;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.List;
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

        switch (code) {
            case 0:
                System.out.println("***** Inserimento di un nuovo indirizzo *****\n");
                System.out.println("Inserisci l'indirizzo: ");
                String addressName = scanner.nextLine();
                Indirizzo address = new Indirizzo(addressName);
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
                Indirizzo personAddress = new Indirizzo(personAddressName);
                List<Cane> dogs = new ArrayList<>();
                /*
                while (true) {
                    System.out.println("Inserisci il nome del cane: ");
                    String dogName = scanner.nextLine();
                    System.out.println("Inserisci la razza del cane: ");
                    String dogRace = scanner.nextLine();
                    Persona owner = new Persona(name, lastname, personAddress, null);
                    dogs.add(new Cane(dogName, dogRace, owner));
                    System.out.println("La persona possiede altri cani? (y per continuare, qualsiasi altro tasto per uscire) ");
                    String response = scanner.next();
                    scanner.nextLine();
                    if (response.equals("y") || response.equals("Y"))
                        continue;
                    else
                        break;
                }
                 */
                Persona person = new Persona(name, lastname, personAddress, dogs);
                try {
                    personaDao.insert(person);
                } catch (NonExistentAddressException e) {
                    System.err.println("Errore: l'indirizzo specificato non esiste.");
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
                Indirizzo addressOwner = new Indirizzo(addressNameOwner);
                Persona owner = new Persona(nameOwner, lastnameOwner, addressOwner, null);
                Cane dog = new Cane(dogName, race, owner);

                try {
                    caneDao.insert(dog);
                } catch (NonExistentPersonException e) {
                    System.err.println("Errore: la persona specificata (o il suo indirizzo) non esistono, oppure l'indirizzo è sbagliato.");
                }

                break;
        }

    }
    private static void select() {}

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
