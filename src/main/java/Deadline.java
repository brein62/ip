public class Deadline extends Task {

    protected String deadline;


    Deadline(String message, String deadline) {
        super(message);
        this.deadline = deadline;
    }

    public String toString() {
        return "[D]" + this.getStatusIcon() + " " + this.message + " (by: " + deadline + ")";
    }
}
