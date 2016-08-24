package xml.jaxp.dom;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class ChessboardDOMPrinter {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setValidating(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        InputSource is = new InputSource("resource-examples/xml/Chessboards-[10-5000].xml");
        Document doc = builder.parse(is);

        NodeList list = doc.getElementsByTagName("*");
        for (int i = 0; i < list.getLength(); i++) {
            Element element = (Element) list.item(i);
            System.out.println(element.getNodeName());
            if (element.getNodeName().equals("POSITION")) {
                System.out.print(element.getAttribute("COLUMN"));
                System.out.print(" ");
                System.out.println(element.getAttribute("ROW"));
            }
        }
    }

}
