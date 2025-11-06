package util;

import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Properties props = new Properties();

    static {
        try (InputStream input = Config.class.getClassLoader()
                .getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("Archivo config.properties no encontrado");
            }
            props.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Error al cargar configuración", e);
        }
    }

    public static String get(String clave) {
        return props.getProperty(clave);
    }

}
