package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC_GROUP1;
import static seedu.address.logic.commands.CommandTestUtil.INDEX_DESC_PERSON1;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddToGroupCommand;

public class AddToGroupCommandParserTest {
    private AddToGroupCommandParser parser = new AddToGroupCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Set<Index> personIndexesSet = new HashSet<>();
        personIndexesSet.add(INDEX_FIRST);
        assertParseSuccess(
                parser,
                INDEX_DESC_PERSON1 + INDEX_DESC_GROUP1,
                new AddToGroupCommand(INDEX_FIRST, personIndexesSet)
        );
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(
                parser,
                "hello",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddToGroupCommand.MESSAGE_USAGE)
        );
    }
}
