package seedu.address.model.group;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalGroups.CS2103T;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.GroupBuilder;

public class GroupTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Group group = new GroupBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> group.getPersons()
                .asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void isSameGroup() {
        // same object -> returns true
        assertTrue(CS2103T.isSameGroup(CS2103T));

        // null -> returns false
        assertFalse(CS2103T.isSameGroup(null));

        // same name, group members different -> returns true
        Group editedGroupCS2103T = new GroupBuilder(CS2103T).withMembers().build();
    }
}
