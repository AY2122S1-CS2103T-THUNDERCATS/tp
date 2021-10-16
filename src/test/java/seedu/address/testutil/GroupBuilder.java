package seedu.address.testutil;

import java.util.Arrays;

import seedu.address.model.group.Group;
import seedu.address.model.names.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * A utility class to help with building Group objects.
 */
public class GroupBuilder {

    public static final String DEFAULT_GROUP_NAME = "CS2103T";

    private Name name;
    private UniquePersonList persons;

    /**
     * Creates a {@code GroupBuilder} with the default details.
     */
    public GroupBuilder() {
        name = new Name(DEFAULT_GROUP_NAME);
        persons = new UniquePersonList();
    }

    /**
     * Initializes the GroupBuilder with the data of {@code groupToCopy}.
     */
    public GroupBuilder(Group groupToCopy) {
        name = groupToCopy.getName();
        persons = groupToCopy.getPersons();
    }

    /**
     * Sets the {@code Name} of the {@code Group} that we are building.
     */
    public GroupBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code UniquePersonList} of the {@code Group} to contain specified {@code Person}.
     * @return
     */
    public GroupBuilder withMembers(Person... personList) {
        UniquePersonList editedList = new UniquePersonList();
        Arrays.stream(personList).forEach(person -> editedList.add(person));
        persons.setPersons(editedList);
        return this;
    }

    public Group build() {
        return new Group(name);
    }

}
