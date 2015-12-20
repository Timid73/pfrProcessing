package beans.services.io;

import org.springframework.stereotype.Service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Timid on 19.12.2015.
 */
@Service
public class ZipService {
    public InputStream getInputStream(File zip, String entry) {
        ZipInputStream zin;
        try {
            zin = new ZipInputStream(new FileInputStream(zip));
            for (ZipEntry e; (e = zin.getNextEntry()) != null; ) {
                if (e.getName().equals(entry)) {
                    return zin;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean isZipFile(File zip) throws FileNotFoundException {
        if (zip == null || !zip.isFile()) {
            return false;
        }
        return true;
    }
}
