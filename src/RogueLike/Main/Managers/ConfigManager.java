package RogueLike.Main.Managers;

import java.awt.event.KeyEvent;
import java.io.*;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigManager {

    public static Config config;
    private static final String CONFIG_PATH = "src/RogueLike/Main/Managers/config.ini";


    public static void init() {
        try {
            config = parseConfig(new FileReader(CONFIG_PATH));
        } catch (FileNotFoundException e) {
            // TODO create config file
            System.out.println("Config file not found!");
        }

        if (config != null) {
            applyConfig(config);
        }
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

                if (line.startsWith(";")) continue; // Skip comments

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
        // Apply the keybinds section
        if (config.sections.containsKey("keybinds")) {
            ConfigSection keybindSection = config.sections.get("keybinds");
            keybindSection.values.forEach((k, v) -> {
                try {
                    Class<?> c = KeybindManager.class;
                    Field f = c.getDeclaredField(k);
                    int destVal = f.getInt(null);
                    int sourceVal = -1;
                    // If we can parse the input as an int, do it and be done with this
                    if (v.matches("\\d+")) {
                        sourceVal = Integer.parseInt(v);
                    } else {

                        Class<?> c_k = KeyEvent.class;
                        //TODO add VK_ prefixing
                        Field f_k = c_k.getDeclaredField(v.toUpperCase());
                        sourceVal = f_k.getInt(null);
                    }

                    KeybindManager.addKeybind(sourceVal, destVal);

                } catch (NoSuchFieldException e) {
                    System.out.println("Unknown keybind " + k);
                } catch (IllegalAccessException e) {
                    System.out.println(e);
                }
            });
        }
    }


}
