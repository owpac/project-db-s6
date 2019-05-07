package gestion;

import app.Database;
import app.Input;
import app.Statement;

import static app.Constants.*;


public class Epreuve {

    private static Database database;

    private static String type;
    private static int note;
    private static String matricule_eleve;

    public Epreuve() {
        database = new Database();
    }

    public Epreuve(Database database) {
        Epreuve.database = database;
    }

    public static void setType() {
        System.out.println();
        System.out.println( "1) DE." );
        System.out.println( "2) TP." );
        System.out.println( "3) Projet." );
        int answer = Input.askInt( "Choisissez le type de l'epreuve >  > ", 1, 3 );

        switch (answer) {
            case 1:
                type = "DE";

            case 2:
                type = "TP";

            case 3:
                type = "Projet";
        }
    }

    public static void setNote() {
        note = Input.askInt( "Saisissez la note > ", 0, 20 );
    }

    public static void setMatricule_eleve() {
        matricule_eleve = Statement.askQuery( "eleve", "Saisissez a quel eleve appartient la note > " );
    }

    private static void query() {
        setType();
        setNote();
        setMatricule_eleve();
    }

    public static void add() {
        query();
        String query = Statement.add( DEF_TABLE_EPREUVE, type, note, matricule_eleve );
        database.execute( query );
    }

    public static void update() {
        String id = Statement.askQuery( "epreuve", "Choisissez une epreuve a modifier > ", NBR_COLUMNS_EPREUVE );
        System.out.println();
        System.out.println( "0) Annuler." );
        System.out.println( "1) Type de l'epreuve." );
        System.out.println( "2) Note de l'epreuve." );
        System.out.println( "3) Matricule de l'eleve a qui appartient l'epreuve." );
        System.out.println( "4) Tout." );
        int answer = Input.askInt( "Que voulez-vous modifier > ", 0, 3 );
        System.out.println();

        switch (answer) {
            case 0:
                break;

            case 1:
                setType();
                database.execute( Statement.update( "epreuve", 1, "type", type, "numero", id ) );
                break;

            case 2:
                setNote();
                database.execute( Statement.update( "epreuve", 1, "note", note, "numero", id ) );
                break;

            case 3:
                setMatricule_eleve();
                database.execute( Statement.update( "epreuve", 1, "matricule_eleve", matricule_eleve, "numero", id ) );
                break;

            case 4:
                query();
                database.execute( Statement.update( "epreuve", 3, "type", type, "note", note, "matricule_eleve", matricule_eleve, "numero", id ) );
        }
    }

    public static void remove() {
        String id = Statement.askQuery( "epreuve", "Choisissez une epreuve a supprimer > ", NBR_COLUMNS_EPREUVE );
        String query = Statement.remove( "epreuve", "numero", id );
        database.execute( query );
    }
}
