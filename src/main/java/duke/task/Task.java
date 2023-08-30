package duke.task;

public abstract class Task {
    /** The message or description of the task. */
    protected String message;

    /** Whether the task is done or not. */
    protected boolean isDone;

    /**
     * Creates a new Task object with the specified message.
     *
     * @param message The description of the task.
     */
    public Task(String message) {
        this.message = message;
        this.isDone = false;
    }

    /**
     * Returns the status icon of the task, dependent on whether the task is done or not.
     *
     * @return [X] if task is done; [ ] if task is not done.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the status number of the task as a String, dependent on whether the task is done or not.
     *
     * @return 1 if task is done; 0 if task is not done.
     */
    public String getStatusNumber() {
        return isDone ? "1" : "0";
    }

    /**
     * Marks the given task as done.
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the given task as undone.
     */
    public void unmarkAsDone() {
        this.isDone = false;
    }

    public abstract String toSaveFormatString();
    public abstract String toString();
}