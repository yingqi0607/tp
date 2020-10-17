package seedu.tr4cker.logic.commands;

import seedu.tr4cker.model.Model;

public class PlannerCommand extends Command {

    public static final String COMMAND_WORD = "planner";

    public static final String MESSAGE_SUCCESS = "Switched to Planner tab!";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult(MESSAGE_SUCCESS, false, false, true);
    }

}
