package xml.jaxp.xstl;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.apache.xpath.XPathAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;




public class ChessboardXPathPrinter {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, TransformerException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();
        InputSource is = new InputSource("resource-examples/xml/Chessboards-[10-5000].xml");
        Document doc = builder.parse(is);

        NodeList allPieces = XPathAPI.selectNodeList(doc.getDocumentElement(), "//*[self::WHITEPIECES or self::BLACKPIECES]/*");

        for (int i = 0; i < allPieces.getLength(); i++) {
            Element piece = (Element) allPieces.item(i);
            Element pieces = (Element) piece.getParentNode();
            //Element position = (Element) piece.getChildNodes().item(0);
            System.out.println((pieces.getTagName().equals("WHITEPIECES") ? "White " : "Black ") + piece.getTagName().toLowerCase() + ": " );
            //+ position.getAttribute("COLUMN") + position.getAttribute("ROW"));
            // out.println(XPathAPI.eval(piece,
            // "concat(substring-before(name(..), \'PIECES\'),"
            // + "\' \', name(),\': \',"
            // + "POSITION/@COLUMN, POSITION/@ROW)"));
        }

    }
}
