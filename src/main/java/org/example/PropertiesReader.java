package org.example;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;

public class PropertiesReader {
    public static String read(){
        Properties properties = new Properties();
        FileReader fr = null;
        String url = "jdbc:mysql://localhost:55555/warsztat?user=root&serverTimezone=UTC";
        try {
            //fr = new FileReader("properties.txt");                                        //for jar
            fr = new FileReader("src/main/java/org/example/properties.txt");        //for IDE
        } catch(FileNotFoundException e){
            System.out.println("Config file not found, using default db address");
        } try {
            properties.load(fr);
            url = String.format("jdbc:mysql://%s:%s/warsztat?user=%s&serverTimezone=%s",
                    properties.getProperty("host"),
                    properties.getProperty("port"),
                    properties.getProperty("user"),
                    properties.getProperty("timezone"));
        } catch (Exception e){
            e.printStackTrace();
        }

        return url;
    }
}
