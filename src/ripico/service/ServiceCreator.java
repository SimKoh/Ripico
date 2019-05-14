package ripico.service;

import ripico.api.dal.WettenAdapter;
import ripico.database.DatabaseWettenAdapter;
import ripico.dummy.DummyWettenAdapter;
import ripico.ui.AppStart;

public class ServiceCreator {


    public static WettenAdapter createWettenAdapter() {
        String isDummy = AppStart.properties.getProperty("dummy");
        if("true".equals(isDummy)) {
            return new DummyWettenAdapter();
        }
        return new DatabaseWettenAdapter();
    }
}
