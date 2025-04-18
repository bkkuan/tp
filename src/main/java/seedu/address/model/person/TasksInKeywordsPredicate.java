package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.task.Task;

/**
 * Tests that a {@code Person}'s {@code Tag} are any of the keywords given.
 */
public class TasksInKeywordsPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TasksInKeywordsPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        System.out.println(person.getTasks());
        return keywords.stream()
                .anyMatch(keyword -> StringUtil
                    .containsWordIgnoreCase(this.concatenateTags(person.getTasks()), keyword));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TasksInKeywordsPredicate)) {
            return false;
        }

        TasksInKeywordsPredicate otherNameContainsKeywordsPredicate = (TasksInKeywordsPredicate) other;
        return keywords.equals(otherNameContainsKeywordsPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }

    private String concatenateTags(List<Task> tasks) {
        return tasks.stream()
                .map(Task::getDescription)
                .reduce("", (acc, task) -> acc + task + " ");
    }
}

