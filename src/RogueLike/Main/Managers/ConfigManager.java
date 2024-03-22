package RogueLike.Main.Managers;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManager {

    public static Config config;
    private static final String CONFIG_PATH = "config.ini";


    public static void init() {
        try {
            config = parseConfig(new FileReader(CONFIG_PATH));
        } catch (FileNotFoundException e) {
            // TODO create config file
            System.out.println("Config file not found!");
        }

        applyConfig(config);
    }

    public static class ConfigSection {
        public Map<String, String> values = new HashMap<>();

        @Override
        public String toString() {
            return values.toString();
        }
    }

    public static class Config {
        public Map<String, ConfigSection> sections = new HashMap<>();

        @Override
        public String toString() {
            return sections.toString();
        }
    }

    public static Config parseConfig(Reader reader) {
        BufferedReader br = new BufferedReader(reader);
        Config result = new Config();

        ConfigSection currentSection = new ConfigSection();
        String currentSectionTitle = "default";
        try {
            while (true) {
                String line = br.readLine();
                if (line == null) {
                    break;
                }
                line = line.strip();



                if (line.startsWith("[")) {
                    // Start a new section with the appropriate title
                    result.sections.put(currentSectionTitle, currentSection);
                    currentSection = new ConfigSection();
                    currentSectionTitle = line.substring(1, line.length()-1);
                } else if (line.contains("=")) {
                    String[] split = line.split("=");
                    currentSection.values.put(split[0].strip(), split[1].strip());
                }
            }
        } catch (java.io.IOException e) {
            System.out.println(e);
        }
        result.sections.put(currentSectionTitle, currentSection);

        return result;
    };

    public static void applyConfig(Config config) {
        System.out.println(config);
    }


}
