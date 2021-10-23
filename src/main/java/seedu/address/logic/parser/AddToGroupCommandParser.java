package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON;

import java.util.Set;
import java.util.stream.Stream;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToGroupCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class AddToGroupCommandParser implements Parser<AddToGroupCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddGroupCommand
     * and returns an AddGroupCommand object for execution.
     *
     * @throws ParseException if  the user does not conform to the expected format
     */
    @Override
    public AddToGroupCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_GROUP, PREFIX_PERSON);

        if (!arePrefixesPresent(argMultimap, PREFIX_GROUP)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToGroupCommand.MESSAGE_USAGE));
        }

        Index groupIndex = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_GROUP).get());
        Set<Index> personIndexes = ParserUtil.parseIndexes(argMultimap.getAllValues(PREFIX_PERSON));

        return new AddToGroupCommand(groupIndex, personIndexes);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
