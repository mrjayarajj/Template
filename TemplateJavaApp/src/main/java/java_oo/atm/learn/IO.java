package java_oo.atm.learn;

import java.util.Scanner;

public abstract class IO {
	
	private Scanner sc = new Scanner(System.in);
	
	public abstract String getDisplayMessage();
	public abstract String getErrorMessage();

	public void execute() {
		System.out.println(getDisplayMessage());
		
		while (sc.hasNextLine()) {
			String input = sc.nextLine();
			if(!validate(input)){
				System.out.println(getErrorMessage());
				execute();
			}else{					
				//sc.close();				
				break;
			}
		}
	}

	public abstract boolean validate(String command);

}
