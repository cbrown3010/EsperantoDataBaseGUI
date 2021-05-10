/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translationdatabasegui;

/**
 * The DBsetup class configures and sets up the embedded database to store
 * translations
 *
 * @author Charlie Cho
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The different parameters required are set and passed through to the driver
 * manager, which then prints out whether the database has been connected.
 *
 */
public class DBsetup {

    public static Connection connection;
    public static String url = "jdbc:derby:translations; create=true";
    public static String username = "charliecho";
    public static String password = "password";

    /**
     * The establishConnection function passes all the parameters to the driver
     * manager and connects the database.
     */
    public static void establishConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            System.err.println("SQLException: " + ex.getMessage());
        }
    }

    /**
     * The createTable function initializes the table for the database with the
     * three columns as the variables, input, language and translated text
     */
    public static void createTable() {
        try {
            Statement statement = connection.createStatement();
            String newTableName = "translationDB8";
            String sqlCreate = "create table " + newTableName + " (english varchar(100), language varchar(100), text varchar(100))";
            statement.executeUpdate(sqlCreate);
        } catch (SQLException ex) {
            Logger.getLogger(DBsetup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
