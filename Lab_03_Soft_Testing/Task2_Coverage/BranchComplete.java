public class BranchComplete {
    public enum Action {
        ABDUCT, PET, CRY, SMILE
    }

    /**
     * Helps alien decide on what to do.
     * @param responseToFriendshipRequest the response from an Earthling.
     */
    public static Action attemptToMakeFriend(String responseToFriendshipRequest) {
        /*
         * Hint: The try/catch block count as separate branches.
         *  It might help to draw a graph of all this.
         *
         * This try/catch block is not necessary in this case but you can imagine a more
         *  complicated method using such a tool. It is important to be able to
         *  account for such a scenario.
         */



        // First identify the level of understanding of the earthling.
        try {
            int number = Integer.parseInt(responseToFriendshipRequest);
            if (number == 42) {
                // Clearly, they know something...
                return Action.ABDUCT;
            }
        } catch (Exception ignored) {
            // The exception is ignored (nothing happens).
        }

        // Otherwise act as normal
        switch (responseToFriendshipRequest) {
            case "wolf":
                return Action.PET;
            case "ok":
                return Action.SMILE;
            default:
                return Action.CRY;
        }
    }
}