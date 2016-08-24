package java_oo.awt.keyboard_reader;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.PointerInfo;

public class KBListener {
	
	public static void main(String[] args) throws AWTException {
		while ( true ) {
            System.out.println( MouseInfo.getPointerInfo().getLocation() );
        }
	}
}