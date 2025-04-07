package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_EMPTY_TASK_DESC;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.logging.Logger;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.TaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.Task;

/**
 * Parses input and creates a new {@code TaskCommand}.
 */
public class TaskCommandParser implements Parser<TaskCommand> {
    private static final Logger logger = Logger.getLogger(TaskCommandParser.class.getName());

    @Override
    public TaskCommand parse(String args) throws ParseException {
        // Manually tokenize args, ignoring the ArgumentMultimap
        String trimmedArgs = args.trim();
        String[] split = trimmedArgs.split(" ", 2);

        // Ensure there are at least two parts: index and task description
        if (split.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }

        // Parse the index part
        Index index;
        try {
            index = ParserUtil.parseIndex(split[0]);
            logger.info("Parsed index: " + index.getOneBased());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE), pe);
        }

        // Extract task description after the first space
        String taskString = split[1].trim();

        // Manually handle multiple "task/" occurrences, treating only the first as a delimiter
        int taskPrefixIndex = taskString.indexOf("task/");
        if (taskPrefixIndex != -1) {
            taskString = taskString.substring(taskPrefixIndex + "task/".length()).trim();
        }

        // If the task description is empty after trimming, throw an error
        if (taskString.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_TASK_DESC);
        }

        // Log and parse the task string (lowercase for consistency)
        logger.info("Task string: " + taskString);
        Task task = ParserUtil.parseTask(taskString.toLowerCase());

        // Return the new TaskCommand
        return new TaskCommand(index, task);
    }
}
