import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * Fill in the items below with your UID and name:
 * @author:
 * @UID:
 */
public class BSTBranchCompleteTest {


	@Test(timeout=1000)
	public void test1() {
		BranchComplete bc = new BranchComplete();
		bc.tree = new BST();
		bc.tree.insert(50, 'A');
		bc.tree.insert(30, 'C');
		bc.tree.insert(25, 'T');
		bc.tree.insert(12, 'A');

		assertEquals(118,bc.DNATreeCalc(110,125,1));
	}

	@Test(timeout=1000)
	public void test2() {
		BranchComplete bc = new BranchComplete();
		bc.tree = new BST();
		bc.tree.insert(50, 'A');
		bc.tree.insert(30, 'C');
		bc.tree.insert(25, 'T');
		bc.tree.insert(12, 'A');

		assertEquals(0,bc.DNATreeCalc(110,125,50));
	}

	@Test(timeout=1000)
	public void test3() {
		BranchComplete bc = new BranchComplete();
		bc.tree = new BST();
		bc.tree.insert(50, 'A');
		bc.tree.insert(30, 'C');
		bc.tree.insert(25, 'T');
		bc.tree.insert(12, 'A');

		assertEquals(118,bc.DNATreeCalc(120,125,1));

	}

	@Test(timeout=1000)
	public void test4() {
		BranchComplete bc = new BranchComplete();
		bc.tree = new BST();
		bc.tree.insert(50, 'A');
		bc.tree.insert(30, 'C');
		bc.tree.insert(25, 'T');
		bc.tree.insert(12, 'A');


		assertEquals(237,bc.DNATreeCalc(120,125,10));

	}

	@Test(timeout=1000)
	public void test5() {
		BranchComplete bc = new BranchComplete();
		bc.tree = new BST();
		bc.tree.insert(50, 'A');
		bc.tree.insert(30, 'C');
		bc.tree.insert(25, 'T');
		bc.tree.insert(12, 'A');

		assertEquals(17,bc.DNATreeCalc(120,110,-100));

	}

	@Test(timeout=1000)
	public void test6() {
		BranchComplete bc = new BranchComplete();
		bc.tree = new BST();
		bc.tree.insert(50, 'A');
		bc.tree.insert(30, 'C');
		bc.tree.insert(25, 'T');
		bc.tree.insert(12, 'A');


		assertEquals(237,bc.DNATreeCalc(120,110,0));
	}

}
