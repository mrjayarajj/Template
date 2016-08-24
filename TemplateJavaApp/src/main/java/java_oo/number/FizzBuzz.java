package java_oo.number;

import java.math.BigInteger;
import java.util.Scanner;

public class FizzBuzz {
	
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int C = sc.nextInt();			
				
		if( ( C>0 && C< (BigInteger.valueOf(10).pow(7).intValue()) ) == false  ){
			return;
		}
		
		for(int i=0;i<C;i++){
			
			int N = i+1;
			
			String logic1 = ( N % 3 ) == 0 ? "Fizz" : ""; 
			String logic2 = ( N % 5 ) == 0 ? "Buzz" : "";
			
			String sol = ( logic1.length()==0 && logic2.length()==0 ) ? ""+N : logic1+logic2 ; 
			
			System.out.println(sol);
			
		}
		
		sc.close();
		
	}

}
