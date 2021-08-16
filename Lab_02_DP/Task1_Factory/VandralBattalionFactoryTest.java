import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Tests Vandral battalion factory
 */
public class VandralBattalionFactoryTest {

    @Test(timeout = 1000, expected = Exception.class)
    public void playerLevelTooLowTest() throws Exception {
        VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 0);
    }

    @Test(timeout = 1000, expected = Exception.class)
    public void playerLevelTooHighTest() throws Exception {
        VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 101);
    }

    @Test(timeout = 1000)
    public void noVandralsTest() throws Exception {
        VandralBattalion battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 1);
        assertEquals(new VandralBattalion(0,0,0,0), battalion);
        assertEquals(0, battalion.strength());
    }

    @Test(timeout = 1000)
    public void easyDifficultyTest() throws Exception {
        // One muggle edge test
        VandralBattalion battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 2);
        assertEquals(new VandralBattalion(1,0,0,0), battalion);

        // No healers test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 14);
        assertEquals(new VandralBattalion(7,0,2,0), battalion);

        // Single healer test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 15);
        assertEquals(new VandralBattalion(7,1,3,0), battalion);

        // In-between level test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 50);
        assertEquals(new VandralBattalion(25,4,10,0), battalion);

        // Max out level test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.EASY, 100);
        assertEquals(new VandralBattalion(50,12,15,5), battalion);
    }

    @Test(timeout = 1000)
    public void hardDifficultyTest() throws Exception {
        // One muggle edge test
        VandralBattalion battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.HARD, 1);
        assertEquals(new VandralBattalion(1,0,0,0), battalion);

        // No healers test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.HARD, 5);
        assertEquals(new VandralBattalion(5,0,2,0), battalion);

        // 2 healers test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.HARD, 14);
        assertEquals(new VandralBattalion(14,2,7,0), battalion);

        // In-between level test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.HARD, 50);
        assertEquals(new VandralBattalion(50,10,25,0), battalion);

        // Max out level test
        battalion = VandralBattalionFactory.createVandralBattalion(Difficulty.HARD, 100);
        assertEquals(new VandralBattalion(100,29,37,12), battalion);
    }
}
