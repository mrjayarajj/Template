package org.thoughtworks.sales.stage1.main;

import java.util.Scanner;

import org.thoughtworks.sales.stage1.ui.CommandScanner;

/**
 * Main class.
 * @author Jayaraj Jaganathan
 *
 */
public class Main {

	/**
	 * default constructor.
	 */
	public Main() {
	}
	
	

	/**
	 * client program starts here.
	 * @param args command line argument
	 */
	public static void main(String[] args) {		
		/**
		 * Scanner for user input.
		 */
		Scanner in = new Scanner(System.in);
		
		CommandScanner commandScanner = new CommandScanner();
		commandScanner.scan(in);
	}
}
