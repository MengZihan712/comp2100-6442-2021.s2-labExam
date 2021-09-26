
public class Division extends Operation {

	public Division(double a, double b) {
		super(a, b);
	}

	@Override
	public double evaluate() throws ArithmeticException{

		//START YOUR CODE
		if (this.b==0){
			throw new ArithmeticException();
		} else {
			return this.a/this.b;
		}



		//END YOUR CODE
	}
}
