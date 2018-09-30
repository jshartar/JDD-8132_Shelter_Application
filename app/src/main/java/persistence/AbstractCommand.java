package persistence;

@SuppressWarnings("SameReturnValue")
abstract class AbstractCommand {
    public final static CommandManager manager = new CommandManager();

    public abstract boolean execute();

    public abstract boolean undo();
}
