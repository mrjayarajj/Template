package xml.third_party.jdom.dom;

import java.io.IOException;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;


public class ChessboardJDOMPrinter {

    public static void main(String[] args) throws JDOMException, IOException {
        SAXBuilder builder = new SAXBuilder();
        builder.setValidation(true);
        Document document = builder.build("resource-examples/xml/Chessboards-[10-5000].xml");
        System.out.println("-"+document.getRootElement().getChildren());
        Element root = document.getRootElement();
        List chessboards = root.getChildren();        
        for (int i = 0; i < chessboards.size(); i++) {
            Element chessboard = (Element) chessboards.get(i);
            String[] pieceSetTags = { "WHITEPIECES", "BLACKPIECES" };
            for (int j = 0; j < pieceSetTags.length; j++) {
                List pieceSets = chessboard.getChildren();
                for (int k = 0; k < pieceSets.size(); k++) {
                    Element pieceSet = (Element) pieceSets.get(k);
                    String[] pieceTags = { "KING", "QUEEN", "BISHOP", "ROOK", "KNIGHT", "PAWN" };
                    for (int l = 0; l < pieceTags.length; l++) {
                        List pieces = pieceSet.getChildren();
                        for (int m = 0; m < pieces.size(); m++) {
                            Element piece = (Element) pieces.get(m);
                            Element position = piece.getParentElement();
                            System.out.println((j == 0 ? "White " : "Black ") + pieceTags[l].toLowerCase() ); 
                            //System.out.print(": " + position.getAttributeValue("COLUMN") + position.getAttributeValue("ROW"));
                        }
                    }
                }
            }
        }

    }
}
