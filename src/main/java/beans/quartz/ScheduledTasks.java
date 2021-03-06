package beans.quartz;

import beans.services.DataBaseConnection.PersistanceService;
import beans.services.io.FileService;
import beans.services.io.ZipService;
import beans.services.xml.XmlService;
import beans.settings.Settings;
import org.jdom.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.InputStream;

/**
 * Created by Timid on 19.12.2015.
 */
@Repository
public class ScheduledTasks {
    @Autowired
    private PersistanceService persistanceService;
    @Autowired
    private FileService fileService;
    @Autowired
    private ZipService zipService;
    @Autowired
    private XmlService xmlService;
    @Autowired
    private Settings settings;

    @Scheduled(fixedRate = 5000)
    @Transactional
    protected void executeInternal() {
        for (File zip : fileService.getFiles()) {
            InputStream inputStream = zipService.getInputStream(zip, "packageDescription.xml");
            Document document = xmlService.getXmlDocument(inputStream);
            entity.Package pack = xmlService.createPackage(document);
            pack.setFile(fileService.getArchivePath() + zip.getName());
            if (pack.getSender().length() > 7) {
                if (fileService.copyToArchive(zip) && fileService.moveFile(zip, settings.get(Settings.PATH_TO_VIPNET))) {
                    persistanceService.create(pack);
                }
            } else {
                if (fileService.copyToArchive(zip)) {
                    persistanceService.create(pack);
                }
            }
        }
    }
}
