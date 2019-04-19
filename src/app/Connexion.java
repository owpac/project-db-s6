package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Connexion {

    private Connection connexion;

    public Connexion() {

        String url = "jdbc:mysql://den1.mysql3.gear.host:3306/projectdbs6";
        String user = "projectdbs6";
        String password = "password!";

        try {

            /*Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");*/

            this.connexion = DriverManager.getConnection( url, user, password );

            System.out.println( "Database connected !" );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnexion() {
        return connexion;
    }

    public static String format(Object... elements) {
        StringBuilder str = new StringBuilder();

        str.append( "(" );
        for (Object element : elements) {
            str.append( "\"" );
            str.append( element );
            str.append( "\"" );
            str.append( "," );
        }

        // Remove the last "," before the parenthesis
        str.deleteCharAt( str.lastIndexOf( "," ) );

        str.append( ")" );

        return str.toString();
    }

    public void execute(String query) {
        try (Statement statement = this.connexion.createStatement()) {

            statement.execute( query );

        } catch (SQLException e) {

            e.printStackTrace();

        }
    }
}