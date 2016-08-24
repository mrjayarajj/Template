package org.thoughtworks.sales.stage1.ui;

import java.util.Scanner;

import org.thoughtworks.sales.stage1.biz.SalesService;
import org.thoughtworks.sales.stage1.exception.SystemException;

/**
 * This class is used to get the input from user.
 * @author Jayaraj Jaganathan
 */
public class CommandScanner {
	
	/**
	 * Instance of business logic.
	 */
	private SalesService salesService = new SalesService();	

	/**
	 * This Constructor shows all the item for sales and used of commands.
	 *
	 */
	public CommandScanner() {
		salesService.showAllItems();
		showComment();
	}

	/**
	 * exit command.
	 */
	private static final String EXIT_COMMAND = "SALES EXIT";

	/**
	 * To scan the user command and values entered by user.
	 */
	public void scan(Scanner in) {
		
		try {
			//this loop end when user type SALES EXIT
			while (true) {
				//read user input
				String input = null;
				
				if(in.hasNextLine()) {
					input = in.nextLine();	
				}else{
					break;
				}
				
				try {
					//process user command
					scanInput(input);
				} catch (RuntimeException e) {
					//for every wrong command user see the usage tip 
					System.out.println("your command not match");
					showComment();
				}
			}
		} catch (SystemException e) {
			//shut down the JVM
			in.close();
		}
	}

	/**
	 * process user command.
	 * @param input user entered command.
	 * @throws SystemException if user command equals to SALES EXIT , SystemException is thrown.
	 */
	private void scanInput(String input) throws SystemException {
		//split line with space.
		String command[] = input.split(" ");
		//if user command is equal to exit, then throw a Exception.
		if (EXIT_COMMAND.equals(input)) {
			throw new SystemException();
		}/* if user command matches SALES and SELECT  */ 
		else if (command[0].equals("SALES") && command[1].equals("SELECT")) { 
			//get the selected product id's
			String str_ids[] = command[2].split(",");
			
			//fill to integer array
			int ids[] = new int[str_ids.length];

			for (int i = 0; i < str_ids.length; i++) {
				//convert string to integer
				ids[i] = Integer.parseInt(str_ids[i]);				
			}
			//show the result
			salesService.showResult(ids);

		}else if (command[0].equals("SALES") && command[1].equals("SHOW")) { 
			salesService.showAllItems();
			showComment();
		}else {
			throw new RuntimeException("command not match");
		}
	}

	/**
	 * show usage tips to the client.
	 */
	public void showComment() {
		System.out.println();
		System.out.println("Usage 1: SALES SELECT [-itemids] for selection");
		System.out.println("ex) To select first 3 product, SALES SELECT 1,2,3");
		System.out.println("Usage 2: SALES EXIT to exist");
		System.out.println("Usage 3: SALES SHOW");
		System.out.println("Note: Command are case sensitive");
	}
}
