package beans.services.io;

import com.google.common.io.CharStreams;
import org.springframework.stereotype.Service;
import settings.Settings;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by Timid on 19.12.2015.
 */
@Service
public class ZipService {
    public InputStream getInputStream(File zip, String entry) {
        ZipInputStream zin = null;
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

    public File unzipEntry(File zip, String entry) {
        if (zip == null || entry == null || !zip.isFile()) {
            return null;
        }
        File unzip = null;
        try {
            InputStream inputStream = getInputStream(zip, entry);
            unzip = new File(Settings.TMP);
            unzip.createNewFile();
            FileWriter fileWriter = new FileWriter(unzip);
            String content = CharStreams.toString(new InputStreamReader(inputStream, "windows-1251"));
            fileWriter.write(content);
            fileWriter.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return unzip;
    }
}
