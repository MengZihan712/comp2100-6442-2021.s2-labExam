/**
 * The factory class which produces Vandral battalions according to the schema provided.
 * Please note that you may edit this class as much as you like (create helper methods if you want!). So long as you genuinely pass the tests.
 * Ask questions if you are lost or unsure!
 */
public class VandralBattalionFactory {
    /*
    Copy of schema:
        At easy difficulty:
        - For every even level, an additional muggle is spawned.
        - For every 20 Muggles, 3 Low-Archons or 1 High-Archon an additional healer is spawned.
        - For every 5 levels up until level 75 a Low-Archon is spawned.
        - Beyond level 75, every 5 levels a High-Archon is spawned.

        At hard difficulty:
        - All spawn rates, except for healers, are halved rounding down.
        - Note that you will still spawn Low-Archons up until 75 and then High-Archons.

        For examples, please look at the lab slides.
     */

    //两个难度的生成数量.: return int[4] numbers.

    //先算判断难度
    //再判断等级0-75:
    //  1.生成muggle
    //  2.生成healer
    //  3.生成low
    //  4.生成high
    public static int[] creatEasyNumbers(int playerLevel) throws Exception {

        //beyond level exception
        if (playerLevel < 1 || playerLevel > 100) {
            throw new Exception("Player level out of bounds");
        }

        int[] easyNumbersArr = new int[4];
        //playerLevel<=75
        if (playerLevel <= 75) {
            //muggle
            easyNumbersArr[0] = playerLevel / 2;
            //low
            easyNumbersArr[2] = playerLevel / 5;
            //no high, thus healer here.
            easyNumbersArr[1] = easyNumbersArr[0] / 20 + easyNumbersArr[2] / 3;
        }
        //playerLevel>75
        if (playerLevel > 75) {
            //muggle
            easyNumbersArr[0] = playerLevel / 2;
            //low
            easyNumbersArr[2] = 15;
            //high
            easyNumbersArr[3] = (playerLevel - 75) / 5;
            //healer here
            easyNumbersArr[1] = easyNumbersArr[0] / 20 + 5 + easyNumbersArr[3];
        }

        return easyNumbersArr;
    }

    public static int[] creatHardNumbers(int playerLevel) throws Exception {

        //beyond level exception
        if (playerLevel < 1 || playerLevel > 100) {
            throw new Exception("Player level out of bounds");
        }

        int[] hardNumbersArr = new int[4];
        //playerLevel<=75
        if (playerLevel <= 75) {
            //muggle
            hardNumbersArr[0] = playerLevel;
            //low
            hardNumbersArr[2] = playerLevel / 2;
            //no high, thus healer here.
            hardNumbersArr[1] = hardNumbersArr[0] / 20 + hardNumbersArr[2] / 3;
        }
        //playerLevel>75
        if (playerLevel > 75) {
            //muggle
            hardNumbersArr[0] = playerLevel ;
            //low
            hardNumbersArr[2] = 37;
            //high
            hardNumbersArr[3] = (playerLevel - 75) / 2;
            //healer here
            hardNumbersArr[1] = hardNumbersArr[0] / 20 + 12 + hardNumbersArr[3];
        }

        return hardNumbersArr;
    }

    /**
     * Creates a battalion to fight against!
     *
     * @param difficulty  set by user
     * @param playerLevel level that player is currently at between 1 and 100 inclusive
     * @return a Vandral battalion according to the schema
     * @throws Exception if the player level is not within 1 and 100 inclusive
     */
    public static VandralBattalion createVandralBattalion(Difficulty difficulty, int playerLevel)
            throws Exception {

        // We have done the exception handling for you. :)
        if (playerLevel < 1 || playerLevel > 100) {
            throw new Exception("Player level out of bounds");
        }

        // TODO: complete this method
        // If you are unsure of where to start, write your factory code below.
        // Keep in mind that you may use helper methods or edit this class as you please.

        int[] numberDemo = new int[4];

        if (difficulty==Difficulty.EASY){
            numberDemo=creatEasyNumbers(playerLevel);
        }

        if(difficulty==Difficulty.HARD){
            numberDemo=creatHardNumbers(playerLevel);
        }

        VandralBattalion result = new VandralBattalion(numberDemo[0],numberDemo[1],numberDemo[2],numberDemo[3] );

        // The following code is just here to prevent an error with regards to the method's promise to return something. Delete it when you start coding.
        return  result;

        // If you are unsure of where to start, write your factory code above.
    }

    //


}
