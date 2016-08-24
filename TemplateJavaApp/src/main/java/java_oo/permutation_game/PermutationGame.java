package java_oo.permutation_game;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
/**
 * 
Alice and Bob play the following game:

They choose a permutation of the first NN numbers to begin with.
They play alternately and Alice plays first.
In a turn, they can remove any one remaining number from the permutation.
The game ends when the remaining numbers form an increasing sequence. The person who played the last turn (after which the sequence becomes increasing) wins the game.
Assuming both play optimally, who wins the game?

Input Format: 
The first line contains the number of test cases TT. TT test cases follow. Each case contains an integer NN on the first line, followed by a permutation of the integers 1..N1..N on the second line.

Output Format: 
Output TT lines, one for each test case, containing "Alice" if Alice wins the game and "Bob" otherwise.

Constraints:

1<=T<=1001<=T<=100
2<=N<=152<=N<=15 
The permutation will not be an increasing sequence initially.

Sample Input:

2
3
1 3 2
5
5 3 2 1 4
Sample Output:

Alice
Bob
Explanation: 
For the first example, Alice can remove the 33 or the 22 to make the sequence increasing and wins the game. 

For the second example, if 44 is removed then the only way to have an increasing sequence is to only have 11 number left, which would take a total of 44 moves, thus allowing Bob to win. On the first move if Alice removes the 44, it will take 33 more moves to create an increasing sequence thus Bob wins. If Alice does not remove the 44, then Bob can remove it on his next turn since Alice can not win in one move.

 * @author jjaganat
 *
 */
public class PermutationGame {

	public static void main(String[] args) {
		/*
		 * Enter your code here. Read input from STDIN. Print output to STDOUT.
		 * Your class should be named Solution.
		 */
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();// total number of test case

		String currentPlayer = null;
		
		for (int i = 0; i < T; i++) {

			int N = sc.nextInt();// choose a permutation of the first N numbers
									// to begin with

			List<Integer> C = new ArrayList<Integer>();

			for (int j = 0; j < N; j++) {
				C.add(sc.nextInt());
			}
			
			
			currentPlayer = choosePlayer(currentPlayer);
			startGame(N,C,currentPlayer);
			
		}
	}
	
	public static void startGame(int N,List<Integer> C,String currentPlayer){

		for (int j = 0; j < N; j++) {			
			//System.out.println(currentPlayer+" is playing with "+C);
			C = playGame(C);
			if (hasIncreasingSequence(C)) {
				System.out.println(currentPlayer);
				break;// game end
			}
			currentPlayer = choosePlayer(currentPlayer);
		}
	}

	public static List<Integer> playGame(List<Integer> C) {

		Iterator<Integer> itr = C.iterator();

		for (int count = 0; count < C.size(); count++) {

			List<Integer> tempC = new ArrayList<Integer>(C);

			if (tempC.size() > 0) {
				tempC.remove(count);
			}

			if (hasIncreasingSequence(tempC)) {
				return tempC;
			}
		}

		if (C.size() > 0) {
			C.remove(0);
		}

		return C;
	}

	private static boolean hasIncreasingSequence(List<Integer> tempC) {

		Iterator<Integer> itr = tempC.iterator();

		int init = 0;

		while (itr.hasNext()) {

			if (init == 0) {
				init = itr.next();
				continue;
			}

			if (init < itr.next()) {
				continue;
			} else {
				return false;
			}
		}

		return true;
	}

	public static String choosePlayer(String currentPlayer) {

		String player[] = new String[] { "Alice", "Bob" };

		if (currentPlayer == null) {
			currentPlayer = player[0];
			return currentPlayer;
		}

		if (currentPlayer.equals(player[0])) {
			currentPlayer = player[1];
		} else {
			currentPlayer = player[0];
		}

		return currentPlayer;
	}

}

/*
100
11
11 9 10 5 8 3 2 7 6 4 1
10
10 7 9 2 5 8 4 1 3 6
5
3 4 5 1 2
15
15 2 4 10 12 13 8 7 11 14 1 6 5 9 3
15
9 4 7 10 13 12 8 6 2 5 1 14 3 15 11
9
6 9 1 2 3 7 8 4 5
15
13 5 6 7 3 10 1 14 9 2 12 8 15 11 4
15
15 3 13 4 14 5 8 12 2 10 1 11 9 6 7
12
12 9 10 6 3 4 11 2 5 1 7 8
11
11 6 8 4 3 9 2 7 1 5 10
15
8 13 1 9 10 12 6 5 3 15 4 7 2 14 11
15
15 12 8 3 2 6 10 5 13 4 11 14 9 1 7
15
14 4 6 5 12 9 11 10 7 8 15 3 2 13 1
7
2 5 4 3 7 1 6
8
8 7 4 2 1 5 6 3
13
11 10 7 5 1 3 13 12 8 6 2 9 4
13
4 12 1 2 11 5 6 8 13 9 7 10 3
12
5 11 7 10 6 8 9 3 4 12 2 1
13
4 5 2 12 10 1 9 13 6 7 8 3 11
5
1 3 4 5 2
13
9 2 12 8 4 5 11 10 6 3 13 1 7
9
6 2 3 1 9 5 8 7 4
15
12 7 9 5 6 4 10 15 14 8 1 2 13 11 3
9
8 1 6 3 5 2 9 4 7
12
8 4 9 1 7 6 3 11 5 10 12 2
9
4 5 3 7 8 6 2 9 1
14
6 13 10 12 5 14 8 1 7 3 11 2 4 9
12
6 5 4 10 9 11 7 3 1 2 12 8
12
5 11 2 9 7 8 1 6 10 4 12 3
11
5 6 4 7 10 9 8 1 3 2 11
15
5 11 10 9 8 3 1 4 14 6 15 12 7 13 2
10
2 5 7 1 6 8 3 4 9 10
14
4 6 2 14 8 1 9 11 10 5 7 12 13 3
5
3 1 4 5 2
13
5 7 6 10 1 3 4 9 12 11 2 8 13
13
5 2 3 12 6 8 9 4 11 1 7 13 10
5
3 2 1 4 5
15
4 7 12 15 8 1 9 6 11 10 5 13 3 2 14
11
11 9 4 10 3 1 6 7 5 2 8
8
1 6 7 2 5 4 8 3
11
11 4 6 9 10 1 8 2 7 3 5
15
3 14 10 2 1 13 9 8 7 12 6 4 5 11 15
15
2 14 1 5 3 6 9 10 15 8 12 13 4 7 11
10
5 9 3 10 1 4 2 7 6 8
15
12 4 13 7 9 14 8 10 15 1 3 6 2 5 11
9
8 3 7 9 1 2 4 6 5
13
13 9 10 11 2 1 4 8 6 12 3 5 7
5
4 3 1 5 2
7
6 3 7 5 1 4 2
10
8 1 9 10 7 2 4 5 6 3
11
6 3 10 11 9 7 5 8 1 2 4
14
10 8 7 11 6 5 4 13 3 1 2 12 9 14
9
4 3 6 2 9 1 7 5 8
5
5 1 3 2 4
9
8 4 5 7 9 6 2 3 1
6
4 2 5 3 1 6
7
6 1 5 2 7 4 3
6
5 1 2 4 6 3
15
4 5 1 10 14 2 3 6 8 7 13 9 15 11 12
14
8 2 11 9 6 1 4 10 7 13 14 5 12 3
5
2 3 1 5 4
11
4 3 5 6 8 11 7 10 9 1 2
14
4 10 2 3 11 6 8 5 7 1 13 12 14 9
15
2 7 8 6 9 3 12 10 5 13 4 14 1 15 11
12
7 1 2 8 3 5 9 10 12 4 11 6
8
3 4 5 7 8 1 2 6
12
5 3 7 1 2 9 10 6 4 8 12 11
9
1 4 6 5 8 7 9 3 2
12
2 9 7 10 11 4 6 1 5 12 8 3
14
14 10 9 1 3 2 6 11 13 4 12 7 5 8
15
7 11 13 2 9 1 12 10 15 3 4 5 6 14 8
5
4 3 5 2 1
7
4 7 5 3 1 2 6
13
9 12 8 5 4 6 10 11 7 13 2 3 1
13
6 3 10 2 9 7 5 11 8 12 1 4 13
13
13 5 8 10 7 3 6 11 1 4 12 2 9
14
13 3 8 11 4 6 9 2 14 1 10 12 5 7
5
1 5 4 2 3
10
3 2 9 6 8 5 1 10 4 7
9
9 2 4 6 8 5 1 7 3
5
5 2 4 1 3
14
2 3 7 1 6 4 5 11 8 10 9 14 13 12
8
1 3 8 7 6 4 5 2
6
3 2 4 5 1 6
6
6 5 2 3 4 1
13
9 11 8 5 7 10 1 12 2 3 13 6 4
14
5 10 6 7 3 1 13 4 2 12 9 14 11 8
12
9 1 2 11 10 7 6 8 12 3 5 4
11
3 4 8 6 10 9 11 2 5 1 7
6
4 3 2 6 1 5
15
12 4 1 9 5 11 10 14 6 15 7 13 8 3 2
15
1 11 2 3 10 5 7 12 15 9 14 13 6 8 4
11
8 10 11 4 1 7 5 3 2 6 9
13
11 7 8 2 6 10 12 1 13 4 9 3 5
12
5 1 11 3 2 4 12 6 7 10 8 9
13
9 13 8 6 7 5 3 10 4 12 2 11 1
6
6 4 1 5 3 2
5
1 3 2 4 5
6
6 3 5 4 1 2
9
8 1 6 9 2 3 4 5 7
*/

/*
Bob
Alice
Alice
Bob
Alice
Alice
Alice
Alice
Alice
Bob
Alice
Alice
Bob
Bob
Alice
Alice
Bob
Alice
Alice
Alice
Bob
Alice
Alice
Alice
Alice
Bob
Bob
Bob
Alice
Bob
Alice
Bob
Alice
Alice
Alice
Alice
Bob
Bob
Alice
Alice
Alice
Bob
Alice
Bob
Alice
Alice
Alice
Alice
Alice
Alice
Alice
Alice
Alice
Bob
Bob
Alice
Bob
Alice
Alice
Bob
Alice
Alice
Bob
Alice
Bob
Bob
Bob
Bob
Alice
Bob
Alice
Bob
Bob
Bob
Alice
Bob
Bob
Bob
Bob
Alice
Alice
Bob
Alice
Alice
Alice
Alice
Bob
Alice
Alice
Alice
Alice
Alice
Alice
Alice
Bob
Bob
Alice
Alice
Alice
Alice
*/ 
