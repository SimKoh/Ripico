package ripico.service;

import ripico.api.dal.SpielAdapter;
import ripico.api.dal.WettenAdapter;
import ripico.database.DatabaseWettenAdapter;
import ripico.dummy.DummySpielAdapter;
import ripico.dummy.DummyWettenAdapter;
import ripico.ui.AppStart;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class ServiceCreator {

    private static Map<Class, Class> classmap = new HashMap<>();
    private static Map<Class, Class> dummyMap = new HashMap<>();

    static {
        //fill dummymap
        dummyMap.put(WettenAdapter.class, DummyWettenAdapter.class);
        dummyMap.put(SpielAdapter.class, DummySpielAdapter.class);

        //fill map for db
        classmap.put(WettenAdapter.class, DatabaseWettenAdapter.class);
    }

    public static <T> T createService(Class<T> clazz) {
        try {
            if ("true".equals(AppStart.properties.getProperty("dummy"))) {
                return (T)dummyMap.get(clazz).getDeclaredConstructor().newInstance();
            }
            return (T) classmap.get(clazz).getDeclaredConstructor().newInstance();
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new IllegalArgumentException("Fehler in den Klassenmaps", e);
        }
    }
}
