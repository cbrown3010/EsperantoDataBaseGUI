/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translationdatabasegui;

/**
 * The Controller accepts and converts it to commands for model and view. It
 * works as the connector between the model and view. It also contains the
 * reading and writing of the database.
 *
 * @author Charlie Cho
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static translationdatabasegui.DBsetup.connection;

public class Controller {

    // instantiate the model to use the functionalities
    public Model Model = new Model();

    /**
     * The AddTranslations function connects to the database and adds all the
     * translations performed when the application is shut
     *
     */
    public void SaveTranslations() {
        try {
            Statement statement = connection.createStatement();
            // iterate through all the translations and save the three variables
            // input, language and translation to the database
            for (Model.Translation trans : Model.getTranslations()) {
                // save the three variables using functions into strings
                String english = trans.englishIN;
                String language = Model.returnLanguage(english).get(0);
                String text = Model.returnTranslation(english, language).get(0);
                String text2 = text.split(":")[0];
                // check query to prevent duplicates
                String sqlCheck = "SELECT * FROM translationDB8 WHERE english = '" + english + "' AND language = '" + language + "' AND text = '" + text2 + "'";
                // insert query to insert new values
                String sqlInsert = "insert into translationDB8 values('" + english + "', '" + language + "', '" + text2 + "')";

                try {
                    // if doesn't exist, update
                    if (statement.execute(sqlCheck) == true) {
                        statement.executeUpdate(sqlInsert);
                    } else {
                        statement.executeUpdate(sqlInsert);
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * The readReadTranslations function reads through the database and
     * populations the preexisting data into the translations array.
     *
     */
    public void LoadTranslation() {
        try {
            ResultSet results;
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            // SQL command to retrieve the three relevant columns
            String sqlQuery = "select english, language, text from translationDB8";
            // save the results into variable
            results = statement.executeQuery(sqlQuery);
            // move pointer to default position from current position
            results.beforeFirst();
            // iterate through results and extract columns and add using function
            while (results.next()) {
                String english = results.getString("english");
                String language = results.getString("language");
                String text = results.getString("text");
                String text2 = text.split(":")[0];
                Model.addTranslations(english, language, text2);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
