/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package translationdatabasegui;

/**
 * The Model manages the data, logic and rules of the application It contains
 * all the functions that make the foundation of the application
 *
 * @author Charlie Cho
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Model {

    /**
     * The Translation class is the core of the application which contains the
     * three major variables of the application, the english input, target
     * language and translation result. The language and translation are arrays
     * as there are multiple results per english input.
     */
    public class Translation implements Serializable {

        public String englishIN;
        private final ArrayList<String> languageList = new ArrayList<>();
        private final ArrayList<String> translationResult = new ArrayList<>();

        // Basic constructor that takes in english variable. 
        // The other two are set through functions
        public Translation(String english) {
            this.englishIN = english;
        }
    }
    // The array list of translations is instantiated
    private final ArrayList<Translation> Translations = new ArrayList<>();

    /**
     * Get method for the translation array list
     *
     * @return array of translations
     */
    public ArrayList<Translation> getTranslations() {
        return Translations;
    }

    /**
     * Get method for the array list of languages Outputs all the existing
     * languages for a given english input
     *
     * @param input - english input
     * @return sorted array list of matching languages
     */
    public ArrayList<String> returnLanguage(String input) {
        ArrayList<String> tempList = new ArrayList<>();
        // iterate through translations and check for matching languages
        for (Translation t : Translations) {
            if (t.englishIN.equals(input)) {
                tempList = t.languageList;
                break;
            }

        }
        Collections.sort(tempList);
        return tempList;
    }

    /**
     * Get method for the array list of translations. Takes in the english input
     * and desired language, and outputs all relevant translations
     *
     * @param input - the input english to be translated
     * @param language - the desired language
     * @return array list of matching translations
     */
    public ArrayList<String> returnTranslation(String input, String language) {
        ArrayList<String> tempList = new ArrayList<>();
        // iterate through translations and check for matching inputs
        Translations.stream().filter(t -> (t.englishIN.equals(input))).forEachOrdered(t -> {
            // iterate through the matching languages and check for matching translations
            // split the language and translation and return the translation only
            t.translationResult.stream().filter(trans -> (trans.split(":")[0].equals(language))).forEachOrdered(trans -> {
                tempList.add(trans);
            });
        });
        return tempList;
    }

    /**
     * Set method for translations. Takes in the input, desired language and
     * translation result.
     *
     * @param input - the english input
     * @param language - the desired language
     * @param translation - the translated text
     */
    public void addTranslations(String input, String language, String translation) {
        Translation NewTranslation;
        Boolean TranslationExists = false;
        // iterate through all translation and check if translation already exists
        for (int i = 0; i < Translations.size(); i++) {
            if (Translations.get(i).englishIN.equals(input)) {
                if (!Translations.get(i).languageList.contains(language)) {
                    Translations.get(i).languageList.add(language);
                }
                Translations.get(i).translationResult.add(language + ":" + translation);
                TranslationExists = true;
                break;
            }
        }
        // if it does not exist add the input, language and translations
        if (!TranslationExists) {
            NewTranslation = new Translation(input);
            NewTranslation.languageList.add(language);
            NewTranslation.translationResult.add(language + ":" + translation);
            Translations.add(NewTranslation);
        }
    }

    /**
     * Function to populate the scroll plane with translations
     *
     * @return - a sorted list of all translations
     */
    public ArrayList<String> fillDisplay() {
        ArrayList<String> tempList = new ArrayList<>();
        Translations.forEach(t -> {
            tempList.add(t.englishIN);
        });
        Collections.sort(tempList);
        return tempList;
    }

    /**
     * A find function that iterates through the translations and finds matching
     * translations
     *
     * @param input - the input to be searched for
     * @return - an array list with the matching translations
     */
    public ArrayList<String> find(String input) {
        ArrayList<String> tempList = new ArrayList<>();
        // iterate through the translations and check if input matches
        // both input and result are set to lowercase 
        Translations.stream().filter(t -> (t.englishIN.toLowerCase().contains(input.toLowerCase()))).forEachOrdered(t -> {
            tempList.add(t.englishIN);
        });
        if (!tempList.isEmpty()) {
            Collections.sort(tempList);
        }
        return tempList;
    }

    /**
     * A removal function to remove unwanted translations from the database
     *
     * @param input - the input to be removed
     */
    public void removeTranslation(String input) {
        // iterates through the translations and removes matching inputs
        for (int i = 0; i < Translations.size(); i++) {
            if (Translations.get(i).englishIN.equals(input)) {
                Translations.remove(i);
            }
        }
    }
}
