package duke.ui;

import duke.task.Task;
import duke.task.TaskType;

import java.time.LocalDate;

/**
 * This class contains methods that print prompt information of the user interface.
 */
public class UI {

    /**
     * A divider (horizontal line) with line break at the end.
     */
    public static final String DIVIDER = "_____________________________________________________________\n";
    /**
     * A divider (horizontal line).
     */
    public static final String DIVIDER_LINE_ONLY = "_____________________________________________________________";

    /**
     * Prints greetings when the program starts.
     */
    public static void printGreetings() {
        System.out.println(
                DIVIDER
                        + " Welcome.\n"
                        + " I am your assistant Friday :)\n"
                        + " Just FYI, I am developed by Song Yu.\n"
                        + " May I know what I can help you?\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints greetings when the program ends.
     */
    public static void printExitGreetings() {
        System.out.print(
                DIVIDER
                        + " Thank you for getting in touch.\n"
                        + " See you next time.\n"
                        + " Have a nice day :)\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints the error message when user's input does not match any command.
     */
    public static void printNotCommand() {
        System.out.println(
                DIVIDER
                        + " Sorry, your command is not recognized\n"
                        + " See 'help'. \n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints the help message.
     */
    public static void printHelp() {
        String helpMessage = " 'help'     : Display tips on using this application\n"
                + " 'exit'     : Exit the application\n"
                + " 'bye'      : Exit the application\n"
                + " 'list'     : List all type of tasks you added to your task list\n"
                + " 'done'     : Mark a task as done\n\t"
                + " e.g. 'done 2' will mark the second task as done\n"
                + " 'todo'     : Add a new todo task\n\t"
                + " e.g. 'todo read book' will add \"read book\" to your task list\n"
                + " 'deadline' : Add a new deadline task\n\t"
                + " e.g. 'deadline read book /by YYYY-DD-MM' will"
                + " add \"read book\" to your task list with deadline task by date in format YYYY-DD-MM\n"
                + " 'event'    : Add a new event task\n\t"
                + " e.g. 'event read book /at YYYY-DD-MM' will"
                + " add \"read book\" to your task list with an event task at date in format YYYY-DD-MM\n"
                + " 'delete'   : Delete a task using the index\n\t"
                + " e.g. 'delete 1' will"
                + " delete the task with index of 1\n"
                + " 'find'     : Search tasks using strings\n\t"
                + " e.g. 'find eat' will"
                + " display a list of tasks that contains the string 'eat'\n";

        System.out.println(
                DIVIDER_LINE_ONLY + DIVIDER_LINE_ONLY + DIVIDER
                        + helpMessage
                        + DIVIDER_LINE_ONLY + DIVIDER_LINE_ONLY + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints a warning that the input is not a valid integer.
     */
    public static void printInvalidIntegerWarning() {
        System.out.println(DIVIDER
                + "Invalid input!\n"
                + "The item number should be a valid integer!\n"
                + DIVIDER
                + "Try again:"
        );
    }

    /**
     * Prints the error message of a given exception object.
     *
     * @param e the exception object
     */
    public static void printErrorMessage(Exception e) {
        System.out.println(DIVIDER + e.getMessage() + "\n" + DIVIDER_LINE_ONLY);
    }

    /**
     * Prints the error message to notify the date input cannot be parsed.
     */
    public static void printParseDateError() {
        System.out.println(DIVIDER
                + "The time format you input is incorrect or out of range!\n"
                + "The format is in YYYY-MM-DD. Please try again.\n"
                + DIVIDER_LINE_ONLY);
    }

    /**
     * Returns the time limit of a task in format WEEKDAY, DAY MONTH YEAR.
     *
     * @param task a task object.
     * @return a string of formatted time limit of a task.
     */
    public static String getTimeLimitFormatted(Task task) {
        String timeLimitFormatted = "";
        if (task.getTaskType().equals(TaskType.TODO)) {
            return timeLimitFormatted;
        }
        LocalDate timeLimitInDate = LocalDate.parse(task.getTimeLimitString());
        timeLimitFormatted = timeLimitInDate.getDayOfWeek().toString() + ", "
                + timeLimitInDate.getDayOfMonth() + " "
                + timeLimitInDate.getMonth() + " "
                + timeLimitInDate.getYear();

        if (task.getTaskType().equals(TaskType.EVENT)) {
            timeLimitFormatted = "(at: " + timeLimitFormatted + ")";
        } else if (task.getTaskType().equals(TaskType.DEADLINE)) {
            timeLimitFormatted = "(by: " + timeLimitFormatted + ")";
        }
        return timeLimitFormatted;
    }

    /**
     * Converts the enum type to string.
     *
     * @param type the type of the task.
     * @return the type of the task in string.
     */
    public static String convertTaskType(TaskType type) {
        switch (type) {
        case TODO:
            return "T";
        case EVENT:
            return "E";
        case DEADLINE:
            return "D";
        default:
            return "Unknown task type";
        }
    }
}
