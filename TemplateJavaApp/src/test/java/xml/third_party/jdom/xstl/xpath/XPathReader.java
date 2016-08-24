package xml.third_party.jdom.xstl.xpath;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.Text;
import org.jdom.input.SAXBuilder;
import org.jdom.xpath.XPath;



/**
 * <p><code>XPathReader</code> demonstrates how to
 *   read a Servlet 2.2 Web Archive file using XPath.
 * </p>
 * 
 * @author Jason Hunter
 * @version 1.0
 */
public class XPathReader {
	
	 public static void main(String[] args) throws IOException, JDOMException {
		 args = new String[]{"resource-examples/xml/web.xml"};
		 xpath(args);
	 }
    
    public static void xpath(String[] args) throws IOException, JDOMException {
        if (args.length != 1) {
            System.err.println("Usage: java XPathReader [web.xml]");
            return;
        }
        String filename = args[0];
        PrintStream out = System.out;

        SAXBuilder builder = new SAXBuilder();
        Document doc = builder.build(new File(filename));

        // Print servlet information
        XPath servletPath = XPath.newInstance("//servlet");
        List servlets = servletPath.selectNodes(doc);

        out.println("This WAR has "+ servlets.size() +" registered servlets:");
        Iterator i = servlets.iterator();
        while (i.hasNext()) {
            Element servlet = (Element) i.next();
            out.print("\t" + servlet.getChild("servlet-name")
                                    .getTextTrim() +
                      " for " + servlet.getChild("servlet-class")
                                       .getTextTrim());
            List initParams = servlet.getChildren("init-param");
            out.println(" (it has " + initParams.size() + " init params)"); 
        }
            
        // Print security role information
        XPath rolePath = XPath.newInstance("//security-role/role-name/text()");
        List roleNames = rolePath.selectNodes(doc);

        if (roleNames.size() == 0) {
            out.println("This WAR contains no roles");
        } else {
            out.println("This WAR contains " + roleNames.size() + " roles:");
            i = roleNames.iterator();
            while (i.hasNext()) {
                out.println("\t" + ((Text)i.next()).getTextTrim());
            }
        }
    }    
}
