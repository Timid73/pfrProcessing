package beans.services.xml;

import com.google.common.io.CharStreams;
import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;

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
        return null;
    }
}
