import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.io.*;

public class WordLadder {
	private static String start;
	private static String end;
	private static StringMap T;			// This map stores the dictionary of words.
	private static StringMap R;			// This map keeps track of all the words that are visited during breadth-first-search.
																	// The key field is the word that is visited, and its value field can hold the predecessor pointer.
	private static Queue Q;					// A queue to perform the breadth-first-search.

	public static void main(String [] args) throws IOException {
		// Loading the dictionary of words into the StringMap T.
		T = new StringMap();
		File file = new File("dictionary4");
		Scanner f = new Scanner(file);
		while (f.hasNext()) {
			String word = f.nextLine();
			T.insert(word, "");
		}
		f.close();

		Scanner kb = new Scanner(System.in);
		System.out.print("Enter the start word: ");
		start = kb.nextLine();
		System.out.print("Enter the end word: ");
		end = kb.nextLine();
		
		R = new StringMap();

		// TODO: Solution to find the shortest set of words that transforms the start word to the end word.
		int length = end.length();
		StringBuffer s = new StringBuffer(start);
		Q = new Queue();
		QNode q;
		for(int i = length - 1; i > 0 ; i--) {
			  for(char j = 97; j < 123; j++) {
				  s.setCharAt(i, j);
				  if(T.find(s.toString()) != null) {
					 if(R.find(s.toString()) == null) {
						 R.insert(s.toString(), "");
						 q = new QNode(0, s.toString());
						 Q.enqueue(q);
					 }
				  }
				  s.setCharAt(i, end.charAt(i));
			  }
		  }
		  
		  q = new QNode(0, end);
		  Q.enqueue(q);
		  
		if(Q.isEmpty()) {
			  System.out.println("Duh! Impossible.");
		  } else {
			  System.out.println("Yay! A word ladder is possible.");
			  Q.print();
		  }
		

	}
}
