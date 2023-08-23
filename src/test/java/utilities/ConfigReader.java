package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    /**
     * This class reads Configuration.properties file.
     * The method getProperty(String key) fetches the values from Configuration.properties file
     * using the key provided as a parameter.
     */
    private static FileInputStream input;
    private static Properties properties;

    static {
         // Path to the Configuration.properties file
        String path = "C:\\Users\\UVRH01\\IdeaProjects\\MindtekTestNGFramework\\src\\test\\resources\\configurations\\Configuration.properties";
        try {
             // FileInputStream gets the file from provided path
            input = new FileInputStream(path);
            // Properties loads data from the input file
            properties = new Properties();
            properties.load(input);
        } catch (FileNotFoundException e) {
            System.out.println("Path for properties file is invalid");
        } catch (IOException e) {
            System.out.println("Failed to load the properties file");
        } finally {

            try {
                assert input != null;
                input.close();
            } catch (IOException e) {
                System.out.println("Exception occurred when trying to close input object");
            }
        }
    }

    // This method gets values from Configuration.properties file using the provided key
    public static String getProperty(String key){
        return properties.getProperty(key);
    }
}
