package seedu.tr4cker.logic.parser;

/**
 * Contains Command Line Interface (CLI) syntax definitions common to multiple commands
 */
public class CliSyntax {

    /* Prefix definitions */
    public static final Prefix PREFIX_NAME = new Prefix("n/");
    public static final Prefix PREFIX_DEADLINE = new Prefix("dt/");
    public static final Prefix PREFIX_TAG = new Prefix("t/");
    public static final Prefix PREFIX_TASK_DESCRIPTION = new Prefix("des/");
    public static final Prefix PREFIX_DELETE_TAG = new Prefix("del/");
    public static final Prefix PREFIX_NEW_TAG = new Prefix("new/");

}
