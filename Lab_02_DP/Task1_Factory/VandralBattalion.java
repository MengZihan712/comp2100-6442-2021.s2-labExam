/**
 * The Vandral Battalion to the spawned in to fight against.
 */
public class VandralBattalion implements Comparable<VandralBattalion> {
    // Quick side note: usually these would all be private... Think about why and perhaps ask your tutor :)
    // Hint: if they are public, what does that mean other objects can do and is that something you would want them to be able to do?
    public int muggles;
    public int healers;
    public int lowArchons;
    public int highArchons;

    /**
     * Constructor which sets class values.
     */
    public VandralBattalion(int muggles, int healers, int lowArchons, int highArchons) {
        this.muggles = muggles;
        this.healers = healers;
        this.lowArchons = lowArchons;
        this.highArchons = highArchons;
    }

    /**
     * Provides a strength value for the current battalion
     * @return integer representing the strength value
     */
    public int strength() {
        return (int) (muggles + lowArchons*2 + Math.pow(highArchons, 2))*Math.max(1, healers);
    }

    /**
     * Used for testing, simply checks if two battalions are equal in numbers (not strength).
     * @param otherBattalion battalion to compare to
     * @return true if they contain the same number of every type of Vandral
     */
    @Override
    public boolean equals(Object otherBattalion) {
        // Ensure they are of the same type and if true, cast it!
        if (!(otherBattalion instanceof VandralBattalion)) {
            return false;
        }

        // Cast the type
        VandralBattalion otherBattalionCasted = (VandralBattalion) otherBattalion;

        // Ensure they are equal
        return (muggles == otherBattalionCasted.muggles
                && healers == otherBattalionCasted.healers
                && lowArchons == otherBattalionCasted.lowArchons
                && highArchons == otherBattalionCasted.highArchons);
    }

    /**
     * Used to compare two battalions
     * @param otherBattalion battalion to compare to
     * @return relative strength
     */
    @Override
    public int compareTo(VandralBattalion otherBattalion) {
         return strength() - otherBattalion.strength();
    }

    /**
     * Prints out the battalion
     * @return string containing the information of the battalion
     */
    @Override
    public String toString() {
        return "VandralBattalion{" +
                "muggles=" + muggles +
                ", healers=" + healers +
                ", lowArchons=" + lowArchons +
                ", highArchons=" + highArchons +
                '}';
    }
}
