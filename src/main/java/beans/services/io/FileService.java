package beans.services.io;

import org.springframework.stereotype.Service;
import settings.Settings;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Timid on 19.12.2015.
 */
@Service
public class FileService {
    private final FilenameFilter filter = setFilter(Settings.FILE_MASK);

    private FilenameFilter setFilter(String regex) {
        if (filter != null) {
            return filter;
        }
        FilenameFilter filter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.startsWith(regex)) {
                return true;
            } else {
                return false;
            }
        };
        return filter;
    }

    public boolean filesExist(String existPath) {
        File path = new File(existPath);
        if (!path.exists()) {
            return false;
        }
        return path.list(filter).length == 0 ? false : true;
    }

    public List<File> getFiles(String existPath) {
        File path = new File(existPath);
        if (!path.exists()) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(path.listFiles(filter)));
    }
}
