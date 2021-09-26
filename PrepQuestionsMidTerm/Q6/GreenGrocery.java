import java.util.ArrayList;
import java.util.List;

public class GreenGrocery implements Grocery<Fruit> {

	private final List<Fruit> fruits = new ArrayList<>();

	public GreenGrocery() {
	}

	public List<Fruit> getFruits() {
		return fruits;
	}

	@Override
	public void restock(List<Fruit> fruits) {

		//START YOUR CODE
		this.fruits.addAll(fruits);
		//END YOUR CODE
	}

	@Override
	public Iterator<Fruit> getIterator() {
		return new FruitIterator(this);
	}




	public class FruitIterator implements Iterator<Fruit> {

		private final GreenGrocery grocery;

		public FruitIterator(GreenGrocery grocery) {
			this.grocery = grocery;
		}

		int index=-1;

		@Override
		public boolean hasNext() {

			// START YOUR CODE
			if (index+1<this.grocery.getFruits().size()){
				return true;
			} else{
				return false;
			}
		}

		@Override
		public Fruit next() {
			// START YOUR CODE
			Fruit res= null;

			if (this.hasNext()){
				res=this.grocery.getFruits().get(++index);

			}


			return res;

		}
	}
}
