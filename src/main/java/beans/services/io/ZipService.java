package beans.services.io;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Timid on 19.12.2015.
 */
@Service
public class ZipService {
    static InputStream getInputStream(File zip, String entry) throws IOException {
        ZipInputStream zin = new ZipInputStream(new FileInputStream(zip));
        for (ZipEntry e; (e = zin.getNextEntry()) != null; ) {
            if (e.getName().equals(entry)) {
                return zin;
            }
        }
        throw new EOFException("Cannot find " + entry);
    }

    public boolean isZipFile(File zip) throws FileNotFoundException {
        if (zip == null || !zip.isFile()) {
            return false;
        }
        return true;
    }

    public String getFile(File zip, String entry) {
        if (zip == null || entry == null || !zip.isFile()) {
            return null;
        }
        String file = null;
        try {
            InputStream inputStream = getInputStream(zip, entry);
            file = IOUtils.toString(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return file;
    }
}
