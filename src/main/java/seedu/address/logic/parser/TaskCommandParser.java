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
        String trimmedArgs = args.trim();
        String[] split = trimmedArgs.split(" ", 2);

        if (split.length < 2) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE));
        }

        Index index;
        try {
            index = ParserUtil.parseIndex(split[0]);
            logger.info("Parsed index: " + index.getOneBased());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, TaskCommand.MESSAGE_USAGE), pe);
        }

        String taskString = split[1].trim();

        int taskPrefixIndex = taskString.indexOf("task/");
        if (taskPrefixIndex != -1) {
            taskString = taskString.substring(taskPrefixIndex + "task/".length()).trim();
        }

        if (taskString.isEmpty()) {
            throw new ParseException(MESSAGE_EMPTY_TASK_DESC);
        }

        logger.info("Task string: " + taskString);
        Task task = ParserUtil.parseTask(taskString.toLowerCase());

        return new TaskCommand(index, task);
    }
}
