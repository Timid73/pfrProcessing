package beans.quartz;

import beans.services.DataBaseConnection.PersistanceService;
import beans.services.io.FileService;
import beans.services.io.ZipService;
import beans.services.xml.XmlService;
import org.jdom.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import settings.Settings;

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

    @Scheduled(fixedRate = 50000)
    @Transactional
    protected void executeInternal() {
        File zip = fileService.getFiles(Settings.PATH_INCOMING).get(0);
        InputStream inputStream = zipService.getInputStream(zip, "packageDescription.xml");
        Document document = xmlService.getXmlDocument(inputStream);
        entity.Package pack = xmlService.createPackage(document);
        persistanceService.create(pack);
    }
}
