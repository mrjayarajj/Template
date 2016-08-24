package xml.xsl.xslt;

import java.io.IOException;

import org.jdom.Document;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.jdom.transform.XSLTransformer;

public class Sample {

    public static void main(String[] args) throws JDOMException, IOException {
        // tranform("xsl/Paul.xml", "xsl/Beatle.xsl");
        tranform("xsl/FirstName.xml", "xsl/FirstName.xsl");
        // tranform("xsl/Beatles.xml", "xsl/FirstName.xsl");
        // tranform("xsl/Beatles.xml", "xsl/ValueOf1.xsl");
    }

    static void tranform(String xml, String xsl) throws JDOMException,
        IOException {

        XSLTransformer transformer = new XSLTransformer(xsl);

        SAXBuilder builder = new SAXBuilder();
        Document x = builder.build(xml);

        Document x2 = transformer.transform(x); // x is a Document

        // Output the tree to the console
        XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
        out.output(x2, System.out);
    }
}
