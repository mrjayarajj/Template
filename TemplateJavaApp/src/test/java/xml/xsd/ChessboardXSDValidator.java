package xml.xsd;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ChessboardXSDValidator {

    static final String schemaSource = "xsd/Chessboards.xsd";

    static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

    static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";

    static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";


    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        dbf.setValidating(true);
        dbf.setAttribute(JAXP_SCHEMA_LANGUAGE, W3C_XML_SCHEMA);
        dbf.setAttribute(JAXP_SCHEMA_SOURCE, new File(schemaSource));

        DocumentBuilder builder = dbf.newDocumentBuilder();
        InputSource is = new InputSource("resource-examples/xml/Chessboards-schema-100.xml");
        Document doc = builder.parse(is);

        NodeList list = doc.getElementsByTagName("*");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            System.out.println(element.getNodeName());
            if (element.getNodeName().equals("POSITION")) {
                System.out.print(element.getAttribute("COLUMN"));
                System.out.println(element.getAttribute("ROW"));
            }
        }

    }

}
