package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.task.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class AddCommand extends Command {

    enum COMMAND_TYPE {
        TODO,
        DEADLINE,
        EVENT
    };

    private COMMAND_TYPE type;
    private String message;
    private String deadline;
    private String from;
    private String to;

    /**
     * Constructor for the AddCommand for todos.
     *
     * @param message The description for the to-do in the to-do list.
     */
    public AddCommand(String message) {
        this.type = COMMAND_TYPE.TODO;
        this.message = message;
        this.deadline = null;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructor for the AddCommand for deadlines.
     *
     * @param message The description for the deadline in the to-do list.
     * @param deadline The deadline as a String in the format yyyy-mm-ddThh:mm:ss.
     */
    public AddCommand(String message, String deadline) {
        this.type = COMMAND_TYPE.DEADLINE;
        this.message = message;
        this.deadline = deadline;
        this.from = null;
        this.to = null;
    }

    /**
     * Constructor for the AddCommand for events.
     *
     * @param message The description for the event in the to-do list.
     * @param from The starting date for the event as a String in the format yyyy-mm-ddThh:mm:ss.
     * @param to The ending date for the event as a String in the format yyyy-mm-ddThh:mm:ss.
     */
    public AddCommand(String message, String from, String to) {
        this.type = COMMAND_TYPE.EVENT;
        this.message = message;
        this.deadline = null;
        this.from = from;
        this.to = to;
    }

    /**
     * Executes the given AddCommand using the specified TaskList, Ui and Storage.
     *
     * @param tasks The task list to run the command on.
     * @param ui The UI to print any output onto.
     * @param storage The storage to save tasks to.
     * @throws DukeException If task type is invalid; or date Strings cannot be parsed (for deadlines and events).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask;
            switch (this.type) {
            case TODO:
                newTask = new Todo(message);
                break;
            case DEADLINE:
                newTask = new Deadline(message, LocalDateTime.parse(deadline));
                break;
            case EVENT:
                newTask = new Event(message, LocalDateTime.parse(from), LocalDateTime.parse(to));
                break;
            default:
                throw new DukeException("Task type is invalid!");
                // break not needed as exception is thrown
            }
            tasks.add(newTask);
            ui.print("Got it. I've added this task:");
            ui.print("  " + newTask);
            ui.print("Now you have " + tasks.getSize() + " tasks in the list.");
            storage.saveTasks(tasks);
        } catch (DateTimeParseException e) {
            throw new DukeException("Dates should be formatted yyyy-mm-ddThh:mm:ss,\n"
                    + "\t e.g. 2023-09-12T12:06:53");
        }
    }

    /**
     * Gets the command type for the specific AddCommand.
     *
     * @return "Add Todo" if AddCommand adds a to-do; "Add Deadline" if AddCommand adds a deadline;
     *         "Add Event" if AddCommand adds an event; "Add " otherwise.
     */
    @Override
    public String getCommandType() {
        String typeStr;
        switch (this.type) {
        case TODO:
            typeStr = "Todo";
            break;
        case DEADLINE:
            typeStr = "Deadline";
            break;
        case EVENT:
            typeStr = "Event";
            break;
        default:
            typeStr = "";
            break;
        }
        return "Add " + typeStr;
    }
}
