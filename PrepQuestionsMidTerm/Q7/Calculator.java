public class Calculator {

	public double add(double a, double b) {

		//START YOUR CODE
		Addition addition = new Addition(a,b);
		return addition.evaluate();


		//END YOUR CODE
	}

	public double subtract(double a, double b) {

		//START YOUR CODE
		Subtraction sub = new Subtraction(a,b);
		return sub.evaluate();

		//END YOUR CODE
	}

	public double multiply(double a, double b) {

		//START YOUR CODE
		Multiplication mul = new Multiplication(a,b);
		return mul.evaluate();

		//END YOUR CODE
	}

	public double divide(double a, double b) {

		//START YOUR CODE
		Division di = new Division(a,b);
		return di.evaluate();

		//END YOUR CODE
	}
}
