package ripico.api;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceFactory {
    private static final Logger logger = Logger.getLogger(ServiceFactory.class.getName());

    private ServiceFactory() {
    }

    private static Map<String, String> packageMap = new HashMap<>();

    static {
        packageMap.put("Dummy", "ripico.dummy.");
        packageMap.put("Database", "ripico.database.");
        packageMap.put("Default", "ripico.service.");
    }

    public static <T> T createService(Class<T> myInterface) {
        InputStream propertiesStream = ServiceFactory.class.getResourceAsStream("../../resources/ripico.properties");
        Properties properties = new Properties();
        try {
            properties.load(propertiesStream);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Konnte keine Implementierung finden", e);
        }
        String adapterUsed = properties.getProperty("adapterUsed");
        if (myInterface.getSimpleName().endsWith("Service")) {
            adapterUsed = "Default";
        }
        try {
            Class<T> aClass = (Class<T>) Class.forName(packageMap.get(adapterUsed) + adapterUsed + myInterface.getSimpleName() + "Impl");
            return aClass.newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            return null;
        }
    }
}
