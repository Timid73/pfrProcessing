package io;

import org.springframework.stereotype.Service;

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
    private FilenameFilter filter;

    public void setFilter(String regex) {
        filter = (dir, name) -> {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.startsWith(regex)) {
                return true;
            } else {
                return false;
            }
        };
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
