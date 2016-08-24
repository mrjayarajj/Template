package xml.jaxp.sax;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

public class ChessboardSAXPrinter {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();      
        SAXParser parser = factory.newSAXParser();        
        parser.parse("resource-examples/xml/Chessboards-[10-5000].xml", new ChessboardHandler());       
    }

}
