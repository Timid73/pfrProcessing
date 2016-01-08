package beans.settings;

import beans.services.DataBaseConnection.PersistanceService;
import entity.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Timid on 19.12.2015.
 */

@Service
public class Settings {
    public static final String PATH_INCOMING = "incoming";
    public static final String PATH_TO_VIPNET = "vipnet";
    public static final String PATH_TO_DIPOST = "dipost";
    public static final String PATH_ARCHIVE = "archive";
    public static final String FILE_MASK = "mask";

    @Autowired
    private PersistanceService persistanceService;
    private Map<String, String> map;

    @PostConstruct
    public void load() {
        map = new HashMap<>();
        List<Property> properties = persistanceService.getAllByQuery(Property.FIND_ALL, Collections.EMPTY_MAP);
        properties.forEach(p -> map.put(p.getKey(), p.getValue()));
    }

    public String get(String key) {
        return map.get(key);
    }
}
