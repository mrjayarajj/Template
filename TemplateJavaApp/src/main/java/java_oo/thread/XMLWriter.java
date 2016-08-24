package java_oo.thread;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

public class XMLWriter implements DataWriter {

	private OutputStream out;

	private XMLStreamWriter xtw;

	public XMLWriter() throws Exception {

		String fileName = "yourXML.xml";
		XMLOutputFactory xof = XMLOutputFactory.newInstance();
		out = new FileOutputStream(fileName);
		xtw = xof.createXMLStreamWriter(out);
		xtw.writeComment("all elements here are explicitly in the HTML namespace");
		// xtw.writeStartDocument("utf-8", "1.0");
		// xtw.writeStartDocument("ISO-8859-1", "1.0");

		xtw.writeStartElement("rss");
		xtw.writeAttribute("xmlns:g", "http://base.google.com/ns/1.0");
		xtw.writeAttribute("xmlns:c", "http://base.google.com/cns/1.0");
		// Namespace declaration for outfits.
		xtw.writeAttribute("xmlns:o", "http://gymboree.com/outfits/outfit/1.0");
		xtw.writeAttribute("version", "2.0");

		xtw.writeStartElement("channel");
	}

	public void close() {
		try {
			xtw.writeEndDocument();
			xtw.flush();
			xtw.close();
			out.close();
			System.out.println("Done");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void write(Map<String, String> m) {

		try {
			xtw.writeStartElement("item");
			xtw.writeStartElement("id");
			xtw.writeAttribute("type", "string");
			xtw.writeCharacters(m.get("id"));
			xtw.writeEndElement();

			xtw.writeStartElement("desc");
			xtw.writeCData(m.get("desc"));
			xtw.writeEndElement();

			xtw.writeEndElement();
			xtw.flush();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}


