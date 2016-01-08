package beans.services.io;

import beans.settings.Settings;
import com.google.common.io.Files;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Timid on 19.12.2015.
 */
@Service
public class FileService {
    @Autowired
    private Settings settings;

    private final FilenameFilter filter = setFilter();

    private FilenameFilter setFilter() {
        if (filter != null) {
            return filter;
        }
        FilenameFilter filter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.startsWith(settings.get(Settings.FILE_MASK))) {
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

    public List<File> getFiles() {
        File path = new File(settings.get(Settings.PATH_INCOMING));
        if (!path.exists()) {
            return null;
        }
        return new ArrayList<>(Arrays.asList(path.listFiles(filter)));
    }

    public boolean saveFile(File file) {
        String archivePath = getArchivePath();
        if (new File(archivePath).exists()) {
            try {
                Files.copy(file, new File(archivePath + file.getName()));
                Files.move(file, new File(settings.get(Settings.PATH_TO_VIPNET) + file.getName()));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            try {
                new File(archivePath).mkdirs();
                Files.copy(file, new File(archivePath + file.getName()));
                Files.move(file, new File(settings.get(Settings.PATH_TO_VIPNET) + file.getName()));
                return true;
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
    }

    public String getArchivePath() {
        Calendar calendar = Calendar.getInstance();
        String separator = System.getProperty("file.separator");
        return settings.get(Settings.PATH_ARCHIVE) +
                calendar.get(Calendar.YEAR) +
                separator + (calendar.get(Calendar.MONTH) + 1) +
                separator + calendar.get(Calendar.DAY_OF_MONTH) + separator;
    }
}
