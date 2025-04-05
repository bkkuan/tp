package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_NO_TASK_FOR_MEM;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DUE_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_INDEX;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskStatus;

/**
 * Set the due date for a task.
 */
public class SetDueDateCommand extends Command {

    public static final String COMMAND_WORD = "setduedate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Set a due date for the task under a member.\n"
            + "Parameters: PERSON INDEX (must be a positive integer)\n"
            + PREFIX_TASK_INDEX + "TASK INDEX (must be a positive integer)\n"
            + PREFIX_DUE_DATE + "DUE DATE (yyyy-mm-dd HH:mm)" + "\n"
            + "Example: " + COMMAND_WORD + " 1 " + PREFIX_TASK_INDEX + " 1 "
            + PREFIX_DUE_DATE + "2025/01/01 23:59";

    public static final String MESSAGE_SUCCESS_SET_DUE_DATE = "Task due date updated to %1$s! Person: %2$s";

    private final LocalDateTime dueDate;
    private final Index taskIndex;
    private final Index personIndex;

    /**
     * Creates a SetDueDateCommand to set a due date for the specified task.
     *
     * @param dueDate The new due date for the particular task.
     * @param taskIndex The index of the particular task.
     * @param personIndex The index of the person to whom this task belongs.
     */
    public SetDueDateCommand(LocalDateTime dueDate, Index taskIndex, Index personIndex) {
        this.dueDate = dueDate;
        this.taskIndex = taskIndex;
        this.personIndex = personIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (personIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(personIndex.getZeroBased());
        List<Task> updatedTasks = new ArrayList<>(personToEdit.getTasks());

        // Check if task index inputted is valid.
        checkTaskIndex(updatedTasks, personToEdit);

        // Update the due date for the specified task.
        Task taskToUpdate = updatedTasks.get(taskIndex.getZeroBased());
        checkDueDateValidity(taskToUpdate);
        taskToUpdate.setDueDate(dueDate);

        // Create a new Person with the updated tasks.
        Person editedPerson = editPerson(personToEdit, updatedTasks);

        model.setPerson(personToEdit, editedPerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_SUCCESS_SET_DUE_DATE,
                formatDueDate(), Messages.format(editedPerson)));
    }

    private Person editPerson(Person personToEdit, List<Task> updatedTasks) {
        return new Person(
                personToEdit.getName(),
                personToEdit.getPhone(),
                personToEdit.getEmail(),
                personToEdit.getTelegram(),
                personToEdit.getPosition(),
                personToEdit.getAddress(),
                personToEdit.getTags(),
                personToEdit.getSkills(),
                personToEdit.getOthers(),
                personToEdit.getTaskStatus(),
                updatedTasks
        );
    }

    private String formatDueDate() {
        DateTimeFormatter displayFormatter = DateTimeFormatter.ofPattern("d MMM yyyy, h:mm a");
        String formattedDueDate = dueDate.format(displayFormatter);

        return formattedDueDate;
    }

    private void checkTaskIndex(List<Task> oldTaskList, Person person) throws CommandException {
        if (oldTaskList.size() == 0) {
            throw new CommandException(String.format(MESSAGE_NO_TASK_FOR_MEM, person.getName()));
        } else if (taskIndex.getZeroBased() >= oldTaskList.size()) {
            throw new CommandException(String.format(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX,
                    taskIndex.getOneBased()));
        }
    }

    private void checkDueDateValidity(Task taskToUpdate) throws CommandException {
        if (taskToUpdate.getDueDate() != null && taskToUpdate.getDueDate().equals(dueDate)) {
            throw new CommandException(String.format("Your due date is already: %s", formatDueDate()));
        } else if (dueDate.isBefore(LocalDateTime.now()) && taskToUpdate.getStatus() != TaskStatus.COMPLETED) {
            throw new CommandException("Due date is in the past!");
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SetDueDateCommand)) {
            return false;
        }

        SetDueDateCommand otherCommand = (SetDueDateCommand) other;
        return taskIndex.equals(otherCommand.taskIndex)
                && personIndex.equals(otherCommand.personIndex)
                && dueDate.equals(otherCommand.dueDate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("personIndex", personIndex)
                .add("taskIndex", taskIndex)
                .add("dueDate", dueDate)
                .toString();
    }
}
