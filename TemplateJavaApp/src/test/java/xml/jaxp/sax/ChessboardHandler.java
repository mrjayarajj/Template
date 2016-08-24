package xml.jaxp.sax;

import org.xml.sax.AttributeList;
import org.xml.sax.HandlerBase;

public class ChessboardHandler extends HandlerBase {

    public void startElement(String name, AttributeList attrs) {

        System.out.println("Name :" + name);

        if (name.equals("POSITION")) {
            if (attrs != null) {
                System.out.print(attrs.getValue("COLUMN"));
                System.out.print(" ");
                System.out.println(attrs.getValue("ROW"));
            }
        }
    }

}
