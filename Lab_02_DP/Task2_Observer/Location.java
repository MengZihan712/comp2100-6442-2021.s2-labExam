package Task2_Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * A location.
 * Tracks users who have been to the location.
 * If a user becomes infected, informs all users within 4 ticks that the location has been compromised.
 * <p>
 * Please note that you may edit this class as much as you like (create helper methods if you want!) So long as you genuinely pass the tests.
 */
public class Location implements Subject {
    public int locationId;
    List<AttendanceLog> attendanceLogs;

    /**
     * Constructor which sets class values.
     */
    public Location(int locationId) {
        this.locationId = locationId;
        this.attendanceLogs = new ArrayList<>();
    }

    /**
     * Increments the amount of time (ticks) that have passed.
     *
     * @param inc the amount of time (ticks) that have passed.
     */
    public void incTicks(int inc) {
        // TODO: write this method so that it increments 'tick' for each attendance log
        // Hint: look into and read the class AttendanceLog
        for (AttendanceLog atLog : attendanceLogs) {
            atLog.incTick(inc);
        }

    }

    /**
     * Simply adds the user to the list of attendees.
     *
     * @param observer user to be added.
     */
    public void attach(Observer observer) {
        attendanceLogs.add(new AttendanceLog(observer));
    }

    /**
     * Removes the attendance log which contains the provided observer.
     *
     * @param observer to remove
     */
    public void detach(Observer observer) {
        // TODO: write this method so that it removes the user from the list of attendees (removes the attendee)
        if (observer != null) {
            for (AttendanceLog atLog : attendanceLogs) {
                if (atLog.getObserver().equals( observer)) {
                    this.attendanceLogs.remove(atLog);
                }
            }
        }

    }

    /**
     * Informs all observers within 4 ticks of that there has been an outbreak.
     */
    public void notifyObservers() {
        /*
        TODO: write this method so that it notifies all observers within 4 ticks (inclusive)
        Please note that the tick value '0' represents the current time period.
        Hint: all observers already have an 'update()' method.
         */
        for (AttendanceLog atLog : attendanceLogs) {
            if (atLog.getTick() <= 4) {
                atLog.getObserver().update();
            }
        }

    }

    /**
     * Prints out the user details
     *
     * @return string containing the information of the user
     */
    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", attendanceLogs=" + attendanceLogs +
                '}';
    }
}
