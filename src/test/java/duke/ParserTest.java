package duke;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import duke.command.Command;

public class ParserTest {
    @Test
    public void parse_otherMessage_dukeExceptionThrown() {
        try {
            Parser.parse("nonsense");
            fail();
        } catch (DukeException e) {
            assertEquals("I'm sorry, but I don't know what that means :-(", e.getMessage());
        }
    }

    @Test
    public void parse_badEventParams_dukeExceptionThrown() {
        try {
            Parser.parse("event /from Jan 2023 /to FSJsf");
            fail();
        } catch (DukeException e) {
            assertEquals("An event requires exactly 2 from/to dates.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyEventParams_dukeExceptionThrown() {
        try {
            Parser.parse("event ");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of an event cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_badDeadlineParams_dukeExceptionThrown() {
        try {
            Parser.parse("deadline /by asdk 2023");
            fail();
        } catch (DukeException e) {
            assertEquals("A deadline requires exactly 1 due date.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeadlineParams_dukeExceptionThrown() {
        try {
            Parser.parse("deadline ");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a deadline cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyTodoParams_dukeExceptionThrown() {
        try {
            Parser.parse("todo ");
            fail();
        } catch (DukeException e) {
            assertEquals("The description of a todo cannot be empty.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyMarkParams_dukeExceptionThrown() {
        try {
            Parser.parse("mark ");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify the index of the task to mark.", e.getMessage());
        }
    }

    @Test
    public void parse_badMarkParams_dukeExceptionThrown() {
        try {
            Parser.parse("mark test");
            fail();
        } catch (DukeException e) {
            assertEquals("The index of the task to mark is not a valid integer.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyUnmarkParams_dukeExceptionThrown() {
        try {
            Parser.parse("unmark ");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify the index of the task to unmark.", e.getMessage());
        }
    }

    @Test
    public void parse_badUnmarkParams_dukeExceptionThrown() {
        try {
            Parser.parse("unmark test");
            fail();
        } catch (DukeException e) {
            assertEquals("The index of the task to unmark is not a valid integer.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyDeleteParams_dukeExceptionThrown() {
        try {
            Parser.parse("delete ");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify the index of the task to delete.", e.getMessage());
        }
    }

    @Test
    public void parse_badDeleteParams_dukeExceptionThrown() {
        try {
            Parser.parse("delete test");
            fail();
        } catch (DukeException e) {
            assertEquals("The index of the task to delete is not a valid integer.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyFindParams_dukeExceptionThrown() {
        try {
            Parser.parse("find ");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify the keyword to find the tasks.", e.getMessage());
        }
    }

    @Test
    public void parse_emptyUpdateParams_dukeExceptionThrown() {
        try {
            Parser.parse("update ");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify which task and what to update.", e.getMessage());
        }
    }

    @Test
    public void parse_badUpdateParams_dukeExceptionThrown() {
        try {
            Parser.parse("update nothing");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify what to update in the task.", e.getMessage());
        }

        try {
            Parser.parse("update 23");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify what to update in the task.", e.getMessage());
        }

        try {
            Parser.parse("update 2 ms message");
            fail();
        } catch (DukeException e) {
            assertEquals("Update type is invalid!", e.getMessage());
        }
    }

    @Test
    public void parse_emptyCloneParams_dukeExceptionThrown() {
        try {
            Parser.parse("clone ");
            fail();
        } catch (DukeException e) {
            assertEquals("You need to specify the index of the task to clone.", e.getMessage());
        }
    }

    @Test
    public void parse_badCloneParams_dukeExceptionThrown() {
        try {
            Parser.parse("clone test");
            fail();
        } catch (DukeException e) {
            assertEquals("The index of the task to clone is not a valid integer.", e.getMessage());
        }
    }

    @Test
    public void parse_exitCommand_success() throws DukeException {
        Command c = Parser.parse("bye");
        assertTrue(c.isExit());
        assertEquals(c.getCommandType(), "Exit");
    }

    @Test
    public void parse_listCommand_success() throws DukeException {
        Command c = Parser.parse("list");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "List");
    }
    @Test
    public void parse_markCommand_success() throws DukeException {
        Command c = Parser.parse("mark 4");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "Mark");
    }
    @Test
    public void parse_unmarkCommand_success() throws DukeException {
        Command c = Parser.parse("unmark 4");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "Unmark");
    }
    @Test
    public void parse_deleteCommand_success() throws DukeException {
        Command c = Parser.parse("delete 6");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "Delete");
    }

    @Test
    public void parse_todoCommand_success() throws DukeException {
        Command c = Parser.parse("todo something");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "Add Todo");
    }

    @Test
    public void parse_deadlineCommand_success() throws DukeException {
        Command c = Parser.parse("deadline sth /by 2023-11-11T12:30:00");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "Add Deadline");
    }

    @Test
    public void parse_eventCommand_success() throws DukeException {
        Command c = Parser.parse("event some event /from 2023-11-11T12:30:00 /to 2023-11-12T12:00:00");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "Add Event");
    }

    @Test
    public void parse_updateDescription_success() throws DukeException {
        Command[] commands = { Parser.parse("update 3 message something"),
                Parser.parse("update 3 msg something smells"),
                Parser.parse("update 3 description whatever df 123") };

        for (Command c : commands) {
            assertFalse(c.isExit());
            assertEquals(c.getCommandType(), "Update DESCRIPTION");
        }
    }

    @Test
    public void parse_updateDate1_success() throws DukeException {
        Command[] commands = { Parser.parse("update 3 /from 2023-11-12T12:00:00"),
                Parser.parse("update 3 date1 2023-11-12T12:00:00"),
                Parser.parse("update 3 /by 2023-11-12T12:00:00"),
                Parser.parse("update 3 by 2023-11-12T12:00:00"),
                Parser.parse("update 3 from 2023-11-12T12:00:00"),
                Parser.parse("update 3 /deadline 2023-11-12T12:00:00"),
                Parser.parse("update 3 deadline 2023-11-12T12:00:00") };

        for (Command c : commands) {
            assertFalse(c.isExit());
            assertEquals(c.getCommandType(), "Update DATE1");
        }
    }

    @Test
    public void parse_updateDate2_success() throws DukeException {
        Command[] commands = { Parser.parse("update 3 /to 2023-11-12T12:00:00"),
                Parser.parse("update 3 date2 2023-11-12T12:00:00"),
                Parser.parse("update 3 to 2023-11-12T12:00:00") };

        for (Command c : commands) {
            assertFalse(c.isExit());
            assertEquals(c.getCommandType(), "Update DATE2");
        }
    }

    @Test
    public void parse_cloneCommand_success() throws DukeException {
        Command c = Parser.parse("clone 3");
        assertFalse(c.isExit());
        assertEquals(c.getCommandType(), "Clone");
    }
}
