package beans.services.xml;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Timid on 20.12.2015.
 */
@Service
public class XmlService {
    private final String LANGUAGE = XMLConstants.W3C_XML_SCHEMA_NS_URI;
    private final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

    public Document getXmlDocument(InputStream inputStream) {
        if (inputStream == null) {
            return null;
        }
        Document document = null;
        DocumentBuilder dBuilder = null;
        try {
            dBuilder = factory.newDocumentBuilder();
            document = dBuilder.parse(inputStream);
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }
}
