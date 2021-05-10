/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translationdatabasegui;

/**
 * The View contains all the graphical user components that the user sees. It
 * connects with the controller to receive data from the model.
 *
 * @author Charlie Cho
 */
// imports for Abstract Window Toolkit and Java Swing
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;

public class View extends JFrame {

    // Instantiate the controller which is connected to the model
    Controller Controller = new Controller();
    // Variable declarations for the GUI, such as buttons, text fields
    // panels, scroll panes and lists.
    private JButton InsertButton;
    private JButton RemoveButton;
    private JButton BackButton;
    private JTextField FindTextField;
    private JPanel MainPanel;
    private JButton FindButton;
    private JScrollPane DisplayScroll;
    private JList<String> List;

    // array of translations to display
    private ArrayList<String> Display;

    // variables that store which language/translation was clicked
    private String clickedLanguage;
    private String clickedTranslation;

    /**
     * This function initializes the menu as well as all the buttons and labels
     * on the main panel.
     */
    public void menuInitialization() {
        setTitle("Translation Database");
        setResizable(false);
        setSize(800, 580);

        // Main panel initialization
        MainPanel = new JPanel();
        MainPanel.setLayout(null);

        // Title label and configuration
        JLabel TitleLabel = new JLabel("Translation DB");
        TitleLabel.setBounds(30, 10, 200, 30);
        TitleLabel.setFont(new Font("Arial Black", Font.PLAIN, 18));

        // Main display that contains translations
        DisplayScroll = new JScrollPane();
        // Populate with translations from database through the controller
        Display = Controller.Model.fillDisplay();
        // New JList that contains all the english inputs
        List = new JList(Display.toArray());
        List.setName("Input");
        List.addMouseListener(ClickListener);
        // Populate main display with list of inputs
        DisplayScroll.setViewportView(List);
        DisplayScroll.setBounds(50, 50, 700, 400);

        // Buttons for the functions find, insert, remove and back.
        // The button listeners are also added here.
        BackButton = new JButton("Back");
        BackButton.setBounds(325, 470, 150, 30);
        BackButton.addActionListener(new ButtonListener(BackButton));
        BackButton.setVisible(false);
        RemoveButton = new JButton("Remove");
        RemoveButton.setBounds(600, 470, 150, 30);
        RemoveButton.addActionListener(new ButtonListener(RemoveButton));
        FindTextField = new JTextField();
        FindTextField.setBounds(200, 10, 430, 30);
        FindButton = new JButton();
        FindButton.addActionListener(new ButtonListener(FindButton));
        FindButton.setText("Find");
        FindButton.setBounds(650, 10, 100, 30);
        InsertButton = new JButton("Insert");
        InsertButton.setBounds(50, 470, 150, 30);
        InsertButton.addActionListener(new ButtonListener(InsertButton));

        // Adding all components to the main panel
        MainPanel.add(TitleLabel);
        MainPanel.add(DisplayScroll);
        MainPanel.add(BackButton);
        MainPanel.add(FindButton);
        MainPanel.add(InsertButton);
        MainPanel.add(RemoveButton);
        MainPanel.add(FindTextField);
        getContentPane().add(MainPanel);

        // Window listener that checks when the application is closed
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent event) {
                // add the translations to the database
                Controller.SaveTranslations();
                System.exit(0);
            }
        });
        setVisible(true);
    }

    /**
     * The button listener responds to all the button pushes and also contains
     * the sub panel to add translations
     */
    private class ButtonListener implements ActionListener {

        // setup sub frame and panels for insert panel
        JFrame subFrame = new JFrame();
        JPanel subPanel = new JPanel();

        // instantiate subpanel labels, textfields and buttons
        JLabel InputLabel = new JLabel("Input:");
        JLabel LanguageLabel = new JLabel("Language:");
        JLabel TranslationLabel = new JLabel("Translation:");
        JTextField TranslationTextField = new JTextField();
        JTextField InputTextField = new JTextField();
        JTextField LanguageTextField = new JTextField();
        JLabel SubTitle = new JLabel("Translation DB");

        JButton pressedButton;

        public ButtonListener(JButton pressed) {
            pressedButton = pressed;
        }

        @Override
        public void actionPerformed(ActionEvent arg0) {

            // Extracts the button pressed and changes to appropriate switch case
            switch (pressedButton.getText()) {
                // When the insert button is pressed
                case "Insert":
                    // configure frame and other components
                    subFrame.setSize(600, 300);
                    subFrame.setTitle("Add Translation");
                    InputLabel.setBounds(10, 50, 150, 20);
                    InputTextField.setBounds(80, 50, 450, 30);
                    LanguageLabel.setBounds(10, 100, 150, 20);
                    LanguageTextField.setBounds(80, 100, 450, 30);
                    TranslationLabel.setBounds(10, 150, 150, 20);
                    TranslationTextField.setBounds(80, 150, 450, 30);
                    JButton InputButton = new JButton("Add");
                    InputButton.setBounds(150, 200, 300, 50);
                    SubTitle.setBounds(200, 0, 200, 30);
                    SubTitle.setFont(new Font("Arial Black", Font.PLAIN, 21));

                    // add components to sub panel
                    subPanel.setLayout(null);
                    subPanel.add(InputTextField);
                    subPanel.add(LanguageTextField);
                    subPanel.add(TranslationTextField);
                    subPanel.add(InputButton);
                    subPanel.add(SubTitle);
                    subPanel.add(InputLabel);
                    subPanel.add(LanguageLabel);
                    subPanel.add(TranslationLabel);
                    subFrame.add(subPanel);

                    setResizable(false);
                    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    subFrame.setVisible(true);

                    // action listener for the input button
                    InputButton.addActionListener((ActionEvent e) -> {
                        // extracts the inputs and add to translation array
                        Controller.Model.addTranslations(InputTextField.getText(), LanguageTextField.getText(), TranslationTextField.getText());
                        // repopulate display
                        Display = Controller.Model.fillDisplay();
                        List = new JList(Display.toArray());
                        List.setName("Input");
                        DisplayScroll.setViewportView(List);
                        List.addMouseListener(ClickListener);
                        subFrame.dispose();
                        List.setSelectedIndex(0);
                    });
                    break;

                // When the remove button is pressed
                case "Remove":
                    // check which translation is highlighted and remove
                    String highlightedTranslation = List.getSelectedValue();
                    Controller.Model.removeTranslation(highlightedTranslation);
                    // repopulate display
                    Display = Controller.Model.fillDisplay();
                    List = new JList(Display.toArray());
                    List.setName("Input");
                    DisplayScroll.setViewportView(List);
                    List.addMouseListener(ClickListener);
                    List.setSelectedIndex(0);
                    break;

                // When the find button is pressed
                case "Find":
                    // use find function to search for text in field
                    String findText = FindTextField.getText();
                    Display = Controller.Model.find(findText);

                    // if no result is found
                    if (Display.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "No translations found", "Find", JOptionPane.INFORMATION_MESSAGE);
                        Display = Controller.Model.fillDisplay();
                    }
                    // repopulate display
                    List = new JList(Display.toArray());
                    List.setName("Input");
                    List.addMouseListener(ClickListener);
                    DisplayScroll.setViewportView(List);
                    List.setSelectedIndex(0);
                    break;

                // When the back button is pressed (in the subpanel)
                case "Back":
                    // check the name assigned to list to determine which
                    // page to load, input, languages or translation
                    // if on the languages page
                    if (List.getName().equals("Languages")) {
                        Display = Controller.Model.fillDisplay();
                        List = List = new JList(Display.toArray());
                        // resset list name to input
                        List.setName("Input");
                        // change visibility of buttons in accordance
                        BackButton.setVisible(false);
                        InsertButton.setVisible(true);
                        RemoveButton.setVisible(true);
                        FindTextField.setVisible(true);
                        FindButton.setVisible(true);
                        List.addMouseListener(ClickListener);
                        DisplayScroll.setViewportView(List);
                        List.setSelectedIndex(0);

                        // if on the translation page
                    } else if (List.getName().equals("Translations")) {
                        // return to languages page, repopulate to languages
                        Display = Controller.Model.returnLanguage(clickedLanguage);
                        List = new JList(Display.toArray());
                        // reset list name to languages
                        List.setName("Languages");
                        DisplayScroll.setViewportView(List);
                        List.addMouseListener(ClickListener);
                        List.setSelectedIndex(0);
                    }
                    break;
                default:
                    break;
            }
        }
    }

    /**
     * The mouse listener responds to al the mouse clicks to navigate through
     * pages. Clicking an input will lead to languages, clicking a language will
     * lead to the translation.
     */
    MouseListener ClickListener = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            JList<String> clickSource = (JList) mouseEvent.getSource();
            // check for double click
            if (mouseEvent.getClickCount() == 2) {
                // set index to location of click
                int index = clickSource.locationToIndex(mouseEvent.getPoint());
                // if a non blank is clicked
                if (index >= 0) {
                    // set trans to equal the translation clicked
                    Object trans = List.getModel().getElementAt(index);
                    // if on the input page
                    if (List.getName().equals("Input")) {
                        clickedLanguage = trans.toString();
                        // return the list of languages available for the input
                        Display = Controller.Model.returnLanguage(clickedLanguage);
                        List = new JList(Display.toArray());
                        // check the array isn't 0, i.e. there are languages available
                        List.addMouseListener(this);
                        // change page to languages and change visibility
                        List.setName("Languages");
                        DisplayScroll.setViewportView(List);
                        InsertButton.setVisible(false);
                        RemoveButton.setVisible(false);
                        FindButton.setVisible(false);
                        FindTextField.setVisible(false);
                        BackButton.setVisible(true);

                        // if on the languages page
                    } else if (List.getName().equals("Languages")) {
                        clickedTranslation = trans.toString();
                        Display = Controller.Model.returnTranslation(clickedLanguage, clickedTranslation);
                        List = new JList(Display.toArray());
                        // set page to translation
                        List.setName("Translations");
                        DisplayScroll.setViewportView(List);
                    }
                }
            }
        }
    };
}
