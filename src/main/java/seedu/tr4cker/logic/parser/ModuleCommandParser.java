package seedu.tr4cker.logic.parser;

import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.AddCommand;
import seedu.tr4cker.logic.commands.ModuleCommand;
import seedu.tr4cker.logic.commands.PlannerCommand;
import seedu.tr4cker.logic.parser.exceptions.ParseException;
import seedu.tr4cker.model.module.Module;
import seedu.tr4cker.model.module.ModuleCode;

import java.util.stream.Stream;

import static seedu.tr4cker.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.tr4cker.logic.parser.CliSyntax.*;

/**
 * Parses input arguments and creates a new ModuleCommand object.
 */
public class ModuleCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ModuleCommand
     * and returns an ModuleCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    public ModuleCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_MODULE_NAME, PREFIX_MODULE_CODE, PREFIX_MODULE_DELETE);

        // user wants to go to Module tab
        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME, PREFIX_MODULE_CODE, PREFIX_MODULE_DELETE)) {
            String string = argMultimap.getPreamble();
            if (!string.isEmpty()) {
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        ModuleCommand.MESSAGE_SWITCH_TAB_USAGE));
            }
            return new ModuleCommand();
        }

        // user wants to add a new Module
        if (arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME, PREFIX_MODULE_CODE) &&
                !arePrefixesPresent(argMultimap, PREFIX_MODULE_DELETE) &&
                argMultimap.getPreamble().isEmpty()) {
            String moduleName = argMultimap.getValue(PREFIX_MODULE_NAME).get();
            String moduleCode = argMultimap.getValue(PREFIX_MODULE_CODE).get();
            Module module = ParserUtil.parseModule(moduleName, moduleCode);
            return new ModuleCommand(module);
        }

        // user wants to delete an existing Module
        if (!arePrefixesPresent(argMultimap, PREFIX_MODULE_NAME, PREFIX_MODULE_CODE) &&
                arePrefixesPresent(argMultimap, PREFIX_MODULE_DELETE) &&
                argMultimap.getPreamble().isEmpty()) {
            Index index = ParserUtil.parseIndex(argMultimap.getValue(PREFIX_MODULE_DELETE).get());
            return new ModuleCommand(index);
        }

        throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }

    /**
     * Returns true if any of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
