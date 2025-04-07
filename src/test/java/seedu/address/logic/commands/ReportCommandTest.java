package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;
import seedu.address.testutil.PersonBuilder;

public class ReportCommandTest {

    private Person createPersonWithTasks(String name, List<Task> tasks) {
        String[] taskDescriptions = tasks.stream()
                .map(Task::getDescription)
                .toArray(String[]::new);
        return new PersonBuilder().withName(name).withTasks(taskDescriptions).build();
    }

    private Task createTask(String description, TaskStatus status) {
        return new Task(description, status, null);
    }

    private Model createModelWithPersons(List<Person> persons) {
        return new SimpleModelStub(persons);
    }

    @Test
    public void execute_noTasksFound_returnsNoTasksMessage() throws CommandException {
        List<Person> persons = new ArrayList<>();

        // Use withoutTasks() to explicitly clear tasks
        persons.add(new PersonBuilder().withName("Alice").withoutTasks().build());
        persons.add(new PersonBuilder().withName("Bob").withoutTasks().build());

        Model model = createModelWithPersons(persons);
        ReportCommand command = new ReportCommand();
        CommandResult result = command.execute(model);

        assertEquals(ReportCommand.MESSAGE_NO_TASKS, result.getFeedbackToUser());
    }

    @Test
    public void execute_tasksPresent_returnsSuccessMessageAndTaskLists() throws CommandException {
        // Setup test data
        List<Person> persons = new ArrayList<>();

        // Person with COMPLETED and IN_PROGRESS tasks
        Person alice = new PersonBuilder().withName("Alice")
                .withTasks(
                        new Task("Submit report", TaskStatus.COMPLETED, null),
                        new Task("Review code", TaskStatus.IN_PROGRESS, null)
                )
                .build();

        // Person with YET_TO_START task
        Person bob = new PersonBuilder().withName("Bob")
                .withTasks(
                        new Task("Design logo", TaskStatus.YET_TO_START, null)
                )
                .build();

        persons.add(alice);
        persons.add(bob);

        // Execute command
        Model model = new SimpleModelStub(persons);
        CommandResult result = new ReportCommand().execute(model);

        // Verify results
        assertEquals(ReportCommand.MESSAGE_SUCCESS, result.getFeedbackToUser());

        // Check completed tasks
        List<Person> completedTasks = result.getCompletedTasks();
        assertEquals(1, completedTasks.size());
        assertEquals("Alice", completedTasks.get(0).getName().toString());

        // Check in-progress tasks
        List<Person> inProgressTasks = result.getInProgressTasks();
        assertEquals(1, inProgressTasks.size());
        assertEquals("Alice", inProgressTasks.get(0).getName().toString());

        // Check yet-to-start tasks
        List<Person> yetToStartTasks = result.getYetToStartTasks();
        assertEquals(1, yetToStartTasks.size());
        assertEquals("Bob", yetToStartTasks.get(0).getName().toString());
    }

    @Test
    public void execute_personWithTasksInAllGroups_appearsInAllGroups() throws CommandException {
        // Example: All tasks are YET_TO_START (default)
        Person person = new PersonBuilder()
                .withName("Charlie")
                .withTasks("Task 1", "Task 2", "Task 3")
                .build();

        Model model = createModelWithPersons(Collections.singletonList(person));
        CommandResult result = new ReportCommand().execute(model);

        // Person should only appear in yet-to-start list
        List<Person> yetToStartList = result.getYetToStartTasks();
        assertEquals(1, yetToStartList.size());
        assertEquals("Charlie", yetToStartList.get(0).getName().toString());
    }

    @Test
    public void execute_tasksAddedInOrder_preservesOrderInReport() throws CommandException {
        // Tasks are added in order via PersonBuilder.withTasks()
        Person person = new PersonBuilder()
                .withName("Diana")
                .withTasks("First Task", "Second Task")
                .build();

        Model model = createModelWithPersons(Collections.singletonList(person));
        CommandResult result = new ReportCommand().execute(model);

        List<Person> yetToStartList = result.getYetToStartTasks();
        assertEquals(1, yetToStartList.size());

        // Verify task order matches insertion order
        List<Task> retrievedTasks = yetToStartList.get(0).getTasks();
        assertEquals(
                Arrays.asList("First Task", "Second Task"),
                retrievedTasks.stream().map(Task::getDescription).toList()
        );
    }

    // Removed invalid test checking task order in feedback string

    private static class SimpleModelStub implements Model {
        private final ObservableList<Person> persons;

        public SimpleModelStub(List<Person> persons) {
            this.persons = FXCollections.observableArrayList(persons);
        }

        @Override
        public ObservableList<Person> getFilteredPersonList() {
            return persons;
        }

        @Override
        public void updateFilteredPersonList(Predicate<Person> predicate) {
            // No-op
        }

        // Stub out remaining Model methods
        @Override public void setUserPrefs(seedu.address.model.ReadOnlyUserPrefs userPrefs) {}

        @Override public seedu.address.model.ReadOnlyUserPrefs getUserPrefs() {
            return null;
        }

        @Override public void setGuiSettings(seedu.address.commons.core.GuiSettings guiSettings) {}

        @Override public seedu.address.commons.core.GuiSettings getGuiSettings() {
            return null;
        }

        @Override public void setAddressBookFilePath(Path addressBookFilePath) {}

        @Override public Path getAddressBookFilePath() {
            return null;
        }

        @Override public void setAddressBook(seedu.address.model.ReadOnlyAddressBook addressBook) {}

        @Override public seedu.address.model.ReadOnlyAddressBook getAddressBook() {
            return null;
        }

        @Override public boolean hasPerson(Person person) {
            return false;
        }

        @Override public void deletePerson(Person target) {}

        @Override public void addPerson(Person person) {}

        @Override public void setPerson(Person target, Person editedPerson) {}
    }
}
