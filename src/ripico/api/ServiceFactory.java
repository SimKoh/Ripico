package ripico.api;

import ripico.ui.AppStart;

import java.util.HashMap;
import java.util.Map;

public class ServiceFactory {

    private ServiceFactory() {
    }

    private static Map<String, String> packageMap = new HashMap<>();

    static {
        packageMap.put("Dummy", "ripico.dummy.");
        packageMap.put("Database", "ripico.database.");
        packageMap.put("Default", "ripico.service.");
    }

    public static <T> T createService(Class<T> myInterface) {
        String adapterUsed = AppStart.properties.getProperty("adapterUsed");
        if(myInterface.getSimpleName().endsWith("Service")) {
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
