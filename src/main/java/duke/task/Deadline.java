package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDateTime deadline;

    /**
     * Returns a new Deadline task containing the message and the deadline date.
     *
     * @param message The description for the deadline.
     * @param deadline The deadline, as a LocalDateTime.
     */
    public Deadline(String message, LocalDateTime deadline) {
        super(message);
        this.deadline = deadline;
    }

    /**
     * Returns a String containing information within the Deadline task, formatted to be saved.
     *
     * @return The deadline, formatted as a String to be saved in the save file.
     */
    public String toSaveFormatString() {
        return "D | " + this.getStatusNumber() + " | " + this.message + " | " + deadline;
    }

    /**
     * Returns a String representation of the Deadline task, formatted for output in the application.
     *
     * @return The deadline, formatted as a String for output in the application.
     */
    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.message
                + " (by: " + deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm:ss")) + ")";
    }
}
