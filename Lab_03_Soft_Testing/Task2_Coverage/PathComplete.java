public class


PathComplete {

	/*
	 * This method finds something!
	 * The code here is difficult to understand on purpose
	 * 	(Use it as an example of what your tutors go through).
	 * 	In reality, please write proper code with proper
	 * 	names and comments!
	 *
	 * A bit of a side note on this:
	 * 	Companies you will work for WILL expect quality code!
	 * 	They have entire review processes and standardisations
	 * 	surrounding code quality. "Spaghetti code" as it is known,
	 * 	costs companies time, money and human resources.
	 */
	public static int findSomething(int a, int b, int c) {
		if (a < 0 || b < 0 || c < 0) {
			a = Math.abs(a);
			b = Math.abs(b);
			c = Math.abs(c);
		}

		if(a > b) {
			if(a > c) {
				System.out.println("a");
				return a;
			}else {
				System.out.println("c");
				return c;
			}
		}else if(b > c) { 
			System.out.println("b");
			return b;
		}else {
			System.out.println("c");
			return c;
		}
	}
}
