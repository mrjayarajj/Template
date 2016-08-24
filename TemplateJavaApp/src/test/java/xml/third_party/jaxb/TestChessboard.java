package xml.third_party.jaxb;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import xml.third_party.jaxb.chessboard.dtos.CHESSBOARD;
import xml.third_party.jaxb.chessboard.dtos.KING;

public class TestChessboard {

	public static void main(String args[]) throws JAXBException, FileNotFoundException {

		JAXBContext jContext = JAXBContext.newInstance(CHESSBOARD.class);
		Unmarshaller unmarshaller = jContext.createUnmarshaller();
		CHESSBOARD CB = (CHESSBOARD) unmarshaller.unmarshal(new FileInputStream("resource-examples/xml/Chessboards-schema-100.xml"));
		KING k = (KING) CB.getWHITEPIECES().getKINGOrQUEENOrBISHOPOrROOKOrKNIGHTOrPAWN().get(0);
		System.out.println(k.getPOSITION().getROW());

	}

}
