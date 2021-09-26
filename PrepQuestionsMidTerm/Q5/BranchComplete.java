public class BranchComplete {
	
	BST tree;
	


	public int DNATreeCalc(int a, int b, int c) {
		int ret = 0;

		int sum = this.tree.sum(this.tree.root);
				
		if (sum < a) {
			if (sum > b) {
				ret = sum + b;
			}
			
			ret = sum + a;
		}

		if (b - a > c) {
			ret = sum + c;
		}

		return ret;
	}
}
