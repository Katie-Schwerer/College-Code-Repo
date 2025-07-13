import java.util.Scanner;

public class RunwayReservation {
	private static int n;
	private static int k;

	public static void main(String [] args) {
		Scanner kb = new Scanner(System.in);
		n = kb.nextInt(); // The total number of requests.
		k = kb.nextInt(); // Grace time between requests.

		// Variables for getting the input.
		String cmd;
		int time = 0;
		String flightname = null;
		String flightnumber = null;
		String source = null;
		String destination = null;
		int curtime = 0; // Current time, initialized to 0.

		// An array of requests. This is the data stored outside of our binary search tree.
		Requests [] reqs = new Requests[n];
		int i = 0;

		// Reading the input. All requests are read from the input file and stored in array reqs.
		while(kb.hasNext()) {
			cmd = kb.next();

			if (cmd.equals("r")) {
				time = kb.nextInt();
				flightname = kb.next();
				flightnumber = kb.next();
				source = kb.next();
				destination = kb.next();

				reqs[i++] = new Requests(cmd, time, flightname, flightnumber, source, destination);
			}
			else {
				time = kb.nextInt();
				reqs[i++] = new Requests(cmd, time);
			}
			kb.nextLine();
		}
		
		i = 0;
		BST t = new BST();
		int sum = 0;
		// TODO: Code to process each request and solve the Runway Reservation problem.
		while(reqs[i] != null) {
			if(reqs[i].getCommand().equals('r')) {
				int value = reqs[i].getTime();
				int lowest = value - k;
				int highest = value + k;
				int count = 0;
				for (int j = lowest; j < highest; j++) {
					if(t.find(j)) {
						count += 1;
					}
				}
				if(count == 0) {
					t.insert(value, i);
				}
			} else {
				int value = reqs[i].getTime();
				sum += value;
				System.out.println("Current time = " + value);
			}
		}
	}
}
