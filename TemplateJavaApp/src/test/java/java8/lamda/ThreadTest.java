package java8.lamda;

import javax.swing.JFrame;
import javax.swing.JTextArea;

public class ThreadTest {

	public static void main(String a[]){
		JFrame jFrame = new JFrame();

		JTextArea jTextArea = new JTextArea();
		jTextArea.append( "Hello World." );

		jFrame.add( jTextArea );
	}
	
}
