package app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {

    private Connection connection;

    public Database() {

        String url = "jdbc:mysql://den1.mysql3.gear.host:3306/projectdbs6";
        String user = "projectdbs6";
        String password = "password!";

        try {
            /*Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver loaded!");*/
            this.connection = DriverManager.getConnection( url, user, password );
            System.out.println( "Database connected !" );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void execute(String query) {
        try (Statement statement = this.connection.createStatement()) {
            statement.execute( query );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}