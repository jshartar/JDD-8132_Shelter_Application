package persistence;
import java.util.LinkedList;
import java.util.List;

public class CommandManager {
    @SuppressWarnings("MismatchedQueryAndUpdateOfCollection")
    private final List<AbstractCommand> history = new LinkedList<>();

    /**
     * This maintains a list of commands that have been undone
     * Front of list is oldest command, tail is most recent
     */
    private final List<AbstractCommand> redoList = new LinkedList<> ();

    /**
     * Execute a command and add to history if the command returned true
     * The history is emptied if the command returns false
     *
     * @param command the command to execute
     */
    public void executeCommand(final AbstractCommand command) {
        if (command.execute()) {
            history.add(command);
        } else {
            history.clear();
        }
    }


}
