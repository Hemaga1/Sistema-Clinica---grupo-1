package util;

import java.io.InputStream;
import java.io.FileInputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        InputStream input = null;
        try {
            // try a few common locations for the resource to make runtime loading more robust
            input = Config.class.getClassLoader().getResourceAsStream("Config.properties");
            if (input == null) input = Config.class.getResourceAsStream("/Config.properties");
            if (input == null) input = Config.class.getResourceAsStream("Config.properties");
            if (input == null) input = Config.class.getResourceAsStream("/util/Config.properties");

            if (input == null) {
                // Try common filesystem locations (useful when IDE didn't copy resources to out/production)
                String userDir = System.getProperty("user.dir");
                String[] candidates = new String[] {
                        userDir + "/Config.properties",
                        userDir + "/src/main/resources/Config.properties",
                        userDir + "/src/main/Resources/Config.properties"
                };
                for (String c : candidates) {
                    Path p = Paths.get(c);
                    if (Files.exists(p)) {
                        input = new FileInputStream(p.toFile());
                        break;
                    }
                }
            }

            if (input == null) {
                String classpath = System.getProperty("java.class.path");
                throw new RuntimeException("Archivo Config.properties no encontrado en classpath: " + classpath);
            }

            try (InputStream in = input) {
                props.load(in);
            }
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar configuración", e);
        }
    }

    public static String get(String clave) {
        return props.getProperty(clave);
    }

}
