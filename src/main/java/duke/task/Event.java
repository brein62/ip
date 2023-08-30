package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Returns a new Event task containing the message and the from and to dates.
     *
     * @param message The description for the Event.
     * @param from The starting date of the Event.
     * @param to The ending date of the Event.
     */
    public Event(String message, LocalDateTime from, LocalDateTime to) {
        super(message);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns a String containing information within the Event task, formatted to be saved.
     *
     * @return The event, formatted as a String to be saved in the save file.
     */
    public String toSaveFormatString() {
        return "E | " + this.getStatusNumber() + " | " + this.message + " | " + from + " | " + to;
    }

    /**
     * Returns a String representation of the Event task, formatted for output in the application.
     *
     * @return The event, formatted as a String for output in the application.
     */
    public String toString() {
        return "[E]" + this.getStatusIcon() + " " + this.message
                + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) + ")";
    }
}
