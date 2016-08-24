package xml.third_party.jaxb;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import xml.third_party.jaxb.library.dtos.Book;
import xml.third_party.jaxb.library.dtos.Fiction;
import xml.third_party.jaxb.library.dtos.Library;

public class TestLibrary {

    public static void read() throws Exception {

        JAXBContext jContext = JAXBContext.newInstance("xml.third_party.jaxb.library.dtos");
        // System.out.println("context ok");
        Unmarshaller unmarshaller = jContext.createUnmarshaller();
        // System.out.println("unmarshaller ok");
        Library lib = (Library) unmarshaller.unmarshal(new FileInputStream("library.xml"));
        // System.out.println("library object ready");
        Fiction fiction = lib.getFiction();
        // System.out.println("fiction group is ready");
        List list1 = fiction.getBook();
        // System.out.println("fiction-list is ready!");
        int n = list1.size();
        // System.out.println("" + n);
        ListIterator k = list1.listIterator(0);

        // be careful about the case! ListIterator

        while (k.hasNext()) {

            Object ob = k.next();
            Book book1 = (Book) ob;
            System.out.println(book1.getvalue());
        }
    }

    public static void main(String args[]) throws Exception {
        write();
        read();        
    }

    private static void write() throws Exception {
        JAXBContext jContext = JAXBContext.newInstance("xml.third_party.jaxb.library.dtos");
        Marshaller marshaller = jContext.createMarshaller();
        
        Unmarshaller unmarshaller = jContext.createUnmarshaller();
        Library lib = (Library) unmarshaller.unmarshal(new FileInputStream("resource-examples/xml/library.xml"));
        lib.getFiction().getBook().get(0).setvalue("t:"+new Date());
        Book newBook = new Book();
        newBook.setvalue("new");
        lib.getFiction().getBook().add(newBook);
        
        marshaller.marshal(lib, new FileWriter("library.xml"));
        
    }

}
