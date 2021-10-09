package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.group.Group;

public class AddGCommand extends Command {
    public static final String COMMAND_WORD = "addG";

    public static final String MESSAGE_DUPLICATE_GROUP = "This group already exists in the address book";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a group to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + "\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Favourite Group";

    public static final String MESSAGE_SUCCESS = "New group added: %1$s";

    private final Group toAdd;


    /**
     * Creates an AddGCommand to add the specified {@code Group}
     */
    public AddGCommand(Group group) {
        requireNonNull(group);
        toAdd = group;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasGroup(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_GROUP);
        }

        model.addGroup(toAdd);
        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, toAdd))
                .setNextListType(CommandResult.ListType.GroupList)
                .build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddGCommand // instanceof handles nulls
                && toAdd.equals(((AddGCommand) other).toAdd));
    }
}
