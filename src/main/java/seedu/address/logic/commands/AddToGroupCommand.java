package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.state.ApplicationState;
import seedu.address.model.Model;
import seedu.address.model.group.Group;
import seedu.address.model.person.Person;

/** Adds a person to a group in the address book.
 *
 */
public class AddToGroupCommand extends Command {

    public static final String COMMAND_WORD = "addToG";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds person to a group identified by the index number used in the displayed "
            + "person and group lists in the address book.\n "
            + "Parameters: PERSON_INDEX (must be a positive integer)\n"
            + "GROUP_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " p/1 g/2";

    public static final String MESSAGE_SUCCESS = "Person(s) added to group : %1$s";
    public static final String MESSAGE_INVALID_GROUP_INDEX = "Please enter a valid group number";
    public static final String MESSAGE_INVALID_PERSON_INDEX = "Please enter all valid person indexes";

    private final Index groupIndex;
    private final Set<Index> personIndexes;

    /**
     * Creates an AddGroupCommand to add the specified {@code Person} objects to the specified {@code Group}.
     * @param groupIndex
     * @param personIndexes
     */
    public AddToGroupCommand(Index groupIndex, Set<Index> personIndexes) {
        requireAllNonNull(groupIndex, personIndexes);
        this.groupIndex = groupIndex;
        this.personIndexes = personIndexes;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Group> lastShownGroupList = model.getFilteredGroupList();
        List<Person> lastShownPersonList = model.getFilteredPersonList();

        if (groupIndex.getZeroBased() >= lastShownGroupList.size()) {
            throw new CommandException(MESSAGE_INVALID_GROUP_INDEX);
        }

        for (int i = 0; i < personIndexes.size(); i++) {
            Object[] personArray = personIndexes.toArray();
            Index curr = (Index) personArray[i];
            if (curr.getZeroBased() >= lastShownPersonList.size()) {
                throw new CommandException(MESSAGE_INVALID_PERSON_INDEX);
            }
        }

        Group groupToChange = lastShownGroupList.get(groupIndex.getZeroBased());
        Set<Person> personSet = personIndexes.stream()
                .map(x -> lastShownPersonList.get(x.getZeroBased()))
                .collect(Collectors.toSet());

        model.addToGroup(groupToChange, personSet);

        return new CommandResult.Builder(String.format(MESSAGE_SUCCESS, groupToChange))
                .setNextAppState(ApplicationState.HOME).build();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddToGroupCommand // instanceof handles nulls
                && groupIndex.equals(((AddToGroupCommand) other).groupIndex));
    }
}
