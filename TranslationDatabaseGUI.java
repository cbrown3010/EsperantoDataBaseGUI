package translationdatabasegui;

/**
 * The translation database GUI is an application that can store translations.
 * This is the graphical user interface that loads the translation from the
 * embedded Apache Derby database and displays them on a graphical user
 * interface. The user can populate the database with their translation device
 * then view and edit them on this application.
 *
 * Functions include manually adding new translations, removing translations and
 * searching for translations.
 *
 * The application is divided in accordance to the MVC, model view controller
 * principle.
 *
 * Translation application can be found on the github.
 * https://github.com/cbrown3010
 *
 * @author Charlie Cho
 */
public class TranslationDatabaseGUI {

    public static void main(String[] args) {
        // establish connected to embedded database
        DBsetup.establishConnection();
        // below function can be used to create new table
        //DBsetup.createTable();
        // instantiate view
        View GUI = new View();
        // load translations from database
        GUI.Controller.LoadTranslation();
        // initialize GUIs
        GUI.menuInitialization();
    }
}
