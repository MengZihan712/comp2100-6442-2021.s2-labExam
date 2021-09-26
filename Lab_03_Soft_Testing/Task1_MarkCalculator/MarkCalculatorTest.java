import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class MarkCalculatorTest {
	/**
	 * Return a list of parameters which are different implementation of 
	 * interface {@linkplain MarkCalculator}. 
	 * Do NOT Modify this part
	*/
	@Parameters
    public static Iterable<? extends Object> getImplementations() {
        return Arrays.asList(
                new MarkCalculator0(),
                new MarkCalculator1(),
                new MarkCalculator2(),
                new MarkCalculator3(),
                new MarkCalculator4(),
                new MarkCalculator5(),
                new MarkCalculator6(),
                new MarkCalculator7(),
                new MarkCalculator8(),
                new MarkCalculator9(),
				new MarkCalculator10(),
				new MarkCalculator11(),
				new MarkCalculator12(),
				new MarkCalculator13(),
				new MarkCalculator14(),
				new MarkCalculator15()
        );
    }

	@Parameter
	public MarkCalculator calculator;

	/*
	 * Hint: Many students get stuck in this lab. Ensure you create out-of-bounds,
	 * 	tests which expect an exception. Furthermore, note that if the first,
	 * 	statement in such a test results in an exception, then later statements
	 * 	will not be executed.
	 */
	// ########## YOUR CODE STARTS HERE ##########


	//-----------------------------------------ComponentOutOfRangeException-------------------------------------------
	/* EXAMPLE Test case 1 */
	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testLabExceptionSmaller() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(-1, 0, 0, 0, true, false);
	}

	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testLabExceptionLarger() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(11, 0, 0, 0, true, false);
	}

	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testAss1ExceptionSmaller() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(8, -1, 0, 0, true, false);
	}

	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testAss1ExceptionLarger() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(8, 12, 0, 0, true, false);
	}

	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testAss2ExceptionSmaller() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(8, 8, -1, 0, true, false);
	}

	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testAss2ExceptionLarger() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(8, 8, 12, 0, true, false);
	}

	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testFinalExceptionSmaller() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(8, 8, 8, -20, true, false);
	}

	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testFinalExceptionLarger() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(8, 8, 8, 110, true, false);
	}

	//???????????????没法输入不是boolean的啊?????????
	/*
	@Test(timeout = 1000, expected = ComponentOutOfRangeException.class)
	public void testAttendFinalException() throws ComponentOutOfRangeException {
		this.calculator.calculateMark(8, 8, 8, 88, "true", false);
	}
	 */

//---------------------------------------------      0,N      ------------------------------------------------------------------------------------

	/* EXAMPLE Test case 2 */
	@Test(timeout = 1000)
	public void testGrade0() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(0, Grade.N), this.calculator.calculateMark(0, 0, 0, 0, true, false));
		assertEquals(new MarkGrade(0, Grade.N), this.calculator.calculateMark(0, 0, 0, 0, true, true));
	}

//--------------------------------------------        null,NCN     ---------------------------------------------------------------------------------------------
	//test absence in final exam
	@Test(timeout = 1000)
	public void testFinalAbsence() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(null, Grade.NCN), this.calculator.calculateMark(0, 0, 0, 0, false, false));
	}

//------------------------------------------     N    ---------------------------------------------------------------------------
	//test not redeemableN
	@Test(timeout = 1000)
	public void testNotRedeemableN() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(0, Grade.N), this.calculator.calculateMark(0, 0, 0, 0, true, false));
		//rounded up
		assertEquals(new MarkGrade(25, Grade.N), this.calculator.calculateMark(2, 4, 3, 20, true, false));

		//rounded down

		assertEquals(new MarkGrade(28, Grade.N), this.calculator.calculateMark(2, 4, 4, 24, true, false));
		assertEquals(new MarkGrade(44, Grade.N), this.calculator.calculateMark(8, 10, 10, 10, true, false));

	}
	//test redeemableN
	@Test(timeout = 1000)
	public void testRedeemableN() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(0, Grade.N), this.calculator.calculateMark(0, 0, 0, 0, true, true));

		assertEquals(new MarkGrade(44, Grade.N), this.calculator.calculateMark(8, 10, 10, 10, true, true));
	}

	//------------------------------------      PX      -----------------------------------------------------------------------------------------
	//test not redeemablePX
	@Test(timeout = 1000)
	public void testNotRedeemablePX() throws ComponentOutOfRangeException {
		//up
		assertEquals(new MarkGrade(45, Grade.PX), this.calculator.calculateMark(2, 2, 1, 64, true, false));
		assertEquals(new MarkGrade(45, Grade.PX), this.calculator.calculateMark(3, 1, 1, 65, true, false));
		//up
		assertEquals(new MarkGrade(46, Grade.PX), this.calculator.calculateMark(3, 1, 1, 66, true, false));
		//down
		assertEquals(new MarkGrade(46, Grade.PX), this.calculator.calculateMark(3, 1, 1, 67, true, false));
		assertEquals(new MarkGrade(49, Grade.PX), this.calculator.calculateMark(8, 0, 1, 66, true, false));
	}
	//test redeemablePX
	@Test(timeout = 1000)
	public void testRedeemablePX() throws ComponentOutOfRangeException {

		assertEquals(new MarkGrade(45, Grade.PX), this.calculator.calculateMark(1, 1, 1, 45, true, true));
		assertEquals(new MarkGrade(49, Grade.PX), this.calculator.calculateMark(1, 1, 1, 49, true, true));
	}

	//------------------------------------------       P       ---------------------------------------------------------------------------
	//test not redeemableP
	@Test(timeout = 1000)
	public void testNotRedeemableP() throws ComponentOutOfRangeException {
		//up=-1+1.5-0.6
		assertEquals(new MarkGrade(50, Grade.P), this.calculator.calculateMark(7, 2, 1, 64, true, false));
		//up=-1+1.5-0.6
		assertEquals(new MarkGrade(53, Grade.P), this.calculator.calculateMark(9, 8, 5, 40, true, false));

		assertEquals(new MarkGrade(50, Grade.P), this.calculator.calculateMark(8, 1, 1, 65, true, false));
		assertEquals(new MarkGrade(59, Grade.P), this.calculator.calculateMark(8, 4, 4, 65, true, false));
		//down=+1-1.5+0.6
		assertEquals(new MarkGrade(59, Grade.P), this.calculator.calculateMark(9, 3, 4, 66, true, false));

	}
	//test redeemableP
	@Test(timeout = 1000)
	public void testRedeemableP() throws ComponentOutOfRangeException {

		assertEquals(new MarkGrade(50, Grade.P), this.calculator.calculateMark(1, 1, 1, 50, true, true));
		assertEquals(new MarkGrade(59, Grade.P), this.calculator.calculateMark(1, 1, 1, 59, true, true));

	}

	//--------------------------------------       C        ---------------------------------------------------------------------
	//test not redeemableC
	@Test(timeout = 1000)
	public void testNotRedeemableC() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(60, Grade.C), this.calculator.calculateMark(9, 1, 1, 80, true, false));
		assertEquals(new MarkGrade(69, Grade.C), this.calculator.calculateMark(9, 4, 4, 80, true, false));
		//up=-1+1.5-0.6
		//down=+1-1.5+0.6
		assertEquals(new MarkGrade(60, Grade.C), this.calculator.calculateMark(8, 2, 1, 79, true, false));
		assertEquals(new MarkGrade(69, Grade.C), this.calculator.calculateMark(10, 3, 4, 81, true, false));

	}
	//test redeemableC
	@Test(timeout = 1000)
	public void testRedeemableC() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(60, Grade.C), this.calculator.calculateMark(1, 1, 1, 60, true, true));
		assertEquals(new MarkGrade(69, Grade.C), this.calculator.calculateMark(1, 1, 1, 69, true, true));
	}

	//--------------------------------------      D        --------------------------------------------------------------------------
	//test not redeemableD
	@Test(timeout = 1000)
	public void testNotRedeemableD() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(70, Grade.D), this.calculator.calculateMark(7, 5, 5, 80, true, false));
		assertEquals(new MarkGrade(79, Grade.D), this.calculator.calculateMark(4, 9, 9, 80, true, false));
		//up=-1+1.5-0.6
		//down=+1-1.5+0.6
		assertEquals(new MarkGrade(70, Grade.D), this.calculator.calculateMark(6, 6, 5, 79, true, false));
		assertEquals(new MarkGrade(79, Grade.D), this.calculator.calculateMark(5, 8, 9, 81, true, false));

	}
	//test redeemableD
	@Test(timeout = 1000)
	public void testRedeemableD() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(70, Grade.D), this.calculator.calculateMark(1, 1, 1, 70, true, true));
		assertEquals(new MarkGrade(79, Grade.D), this.calculator.calculateMark(1, 1, 1, 79, true, true));
	}

	//--------------------------------------      HD        --------------------------------------------------------------------------
	//test not redeemableHD
	@Test(timeout = 1000)
	public void testNotRedeemableHD() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(80, Grade.HD), this.calculator.calculateMark(8, 5, 5, 95, true, false));
		assertEquals(new MarkGrade(100, Grade.HD), this.calculator.calculateMark(10, 10, 10, 100, true, false));
		//up=-1+1.5-0.6

		assertEquals(new MarkGrade(80, Grade.HD), this.calculator.calculateMark(7, 6, 5, 94, true, false));


	}
	//test redeemableHD
	@Test(timeout = 1000)
	public void testRedeemableHD() throws ComponentOutOfRangeException {
		assertEquals(new MarkGrade(80, Grade.HD), this.calculator.calculateMark(1, 1, 1, 80, true, true));
		assertEquals(new MarkGrade(100, Grade.HD), this.calculator.calculateMark(1, 1, 1, 100, true, true));
	}


	//TODO: write other test cases

	// ########## YOUR CODE ENDS HERE ##########
}
