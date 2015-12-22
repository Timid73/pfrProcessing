package beans.services.xml;

import com.google.common.io.CharStreams;
import entity.Package;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.sql.Timestamp;
import java.util.Calendar;

/**
 * Created by Timid on 20.12.2015.
 */
@Service
public class XmlService {

    public Document getXmlDocument(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        try {
            String content = CharStreams.toString(new InputStreamReader(inputStream, "windows-1251"));
            SAXBuilder saxBuilder = new SAXBuilder();
            return saxBuilder.build(new StringReader(content));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JDOMException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Package createPackage(Document xml) {
        if (xml == null) {
            return null;
        }
        Element root = xml.getRootElement();
        Calendar calendar = Calendar.getInstance();
        Package pack = new Package();
        pack.setDate(new Timestamp(calendar.getTimeInMillis()));
        pack.setSender(root.getChild("отправитель").getAttributeValue("идентификаторСубъекта"));
        pack.setRecipient(root.getChild("получатель").getAttributeValue("идентификаторСубъекта"));
        pack.setType(root.getAttributeValue("типДокументооборота"));
        return pack;
    }
}
