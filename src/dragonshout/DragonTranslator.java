/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dragonshout;

import java.util.*;
import java.io.*;

/**
 * Dragon Translator --- class to translate words between English and Dragon
 * Language
 *
 * @author Ashwika Katiyar
 */
public class DragonTranslator {

    private Map<String, String> translations;
    private String fileName;

    /**
     * Initializes the map containing all translations and the name of the file
     * containing the translations
     * The translations Map uses English words as the keys and their Dragon
     * Language translations as the values
     *
     * @param file A String that contains the name of the file that has all the
     * translations between English and Dragon Language words
     * @throws FileNotFoundException
     */
    public DragonTranslator(String file) throws FileNotFoundException {
        translations = new TreeMap<>();
        fileName = file;
        translations = buildMap(fileName, translations);
    }

    /**
     * Builds a map of translations off of the file indicated by the given file
     * name 
     * Pre-condition: Structure of the file must be so that each pair of
     * words is on its own line with a space between the word and its
     * translation. The second word on the line will be used as the key when 
     * building the map, and the first word will be the value stored. 
     *
     * @param file A String that contains the name of the file to be added to 
     * the map
     * @param map A Map object with both the keys and the values being Strings 
     * where the contents of the file will be added to
     * @return A Map object with both the keys and the values being String that 
     * contains the contents of the indicated file
     * @throws FileNotFoundException
     */
    private Map<String, String> buildMap(String file, Map<String, String> map) throws FileNotFoundException {
        Scanner read = new Scanner(new File(fileName));
        while (read.hasNext()){
            String val = read.next();
            val = val.substring(0, 1).toUpperCase() + val.substring(1).toLowerCase();
            String key = read.next();
            key = key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase();
            map.put(key, val);
        }
        return map;
    }

    /**
     * Returns the Map containing translations as a String
     * @return A String containing the contents of the translations Map
     */
    @Override
    public String toString() {
        return translations.toString();
    }

    /**
     * Receives an English word and returns its Dragon Language Translation
     * Returns "??" if the translation is not found
     * 
     * @param key A String containing the English word to be translated
     * @return A String containing the Dragon Language translation of the given
     * word or "??" if the translation is not found
     */
    public String translate(String key) {
        key = key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase();
        if (translations.containsKey(key)){
            return translations.get(key);
        }
        return "??";
    }

    /**
     * Adds a new translation to the file of translations indicated by fileName
     * and rebuilds the translations Map with the updated file
     * Does nothing if the translation already exists
     * 
     * @param val A String that contains the Dragon Language word to be added
     * @param key A String that contains the English translation to be added
     * @return A Boolean containing true if the translation was successfully 
     * added and false if the translation was not added to the file
     * @throws IOException 
     */
    public boolean add(String val, String key) throws IOException {
        val = val.substring(0, 1).toUpperCase() + val.substring(1).toLowerCase();
        key = key.substring(0, 1).toUpperCase() + key.substring(1).toLowerCase();
        if(translations.containsKey(key) || translations.containsValue(val)){
            return false;
        }
        Set<String> keys = translations.keySet();
        String newFile = "";
        for (String x : keys){
            newFile = newFile + translations.get(x) + " " + x + "\n";
        }
        newFile = newFile + val + " " + key;
        FileWriter writer = new FileWriter(fileName);
        writer.write(newFile);
        writer.close();
        buildMap(fileName, translations);
        return true;
    }
}
