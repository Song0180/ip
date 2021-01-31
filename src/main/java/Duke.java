import java.util.Scanner;


public class Duke {
    /**
     * A divider (horizontal line).
     */
    public static final String DIVIDER_LINE_ONLY = "__________________________________________";
    /**
     * A divider (horizontal line) with line break at the end.
     */
    public static final String DIVIDER = "__________________________________________\n";

    /**
     * Prints greetings when the program starts.
     */
    public static void printGreetings() {
        System.out.println(
                DIVIDER
                        + " Welcome.\n"
                        + " I am your assistant Friday. ✧ʕ̢̣̣̣̣̩̩̩̩·͡˔·ོɁ̡̣̣̣̣̩̩̩̩✧ \n"
                        + " Just FYI, I am developed by Song Yu.\n"
                        + " May I know what I can help you?\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Prints greetings when the program ends.
     */
    public static void printExitGreetings() {
        System.out.println(
                DIVIDER
                        + " Thank you for getting in touch.\n"
                        + " See you next time. \n"
                        + "✧( ु•⌄• )◞ᴴᴬᵛᴱ ᴬ ᴳᴼᴼᴰ ᵀᴵᴹᴱ\n"
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
                + " e.g. 'deadline read book /by Sunday' will"
                + " add \"read book\" to your task list with deadline \"Sunday\"\n"
                + " 'event' : Add a new event task\n\t"
                + " e.g. 'event read book /at Mon 2-4pm' will"
                + " add \"read book\" to your task list with period  \"Mon 2-4pm\"\n";

        System.out.println(
                DIVIDER_LINE_ONLY + DIVIDER
                        + helpMessage
                        + DIVIDER_LINE_ONLY + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Echos user's input string.
     *
     * @param userInput A string input by the user.
     */
    public static void echoInput(String userInput) {
        System.out.println(
                DIVIDER
                        + userInput + "\n"
                        + DIVIDER_LINE_ONLY
        );
    }

    /**
     * Gets command from user input.
     *
     * @param userInput user's input.
     * @return command.
     */
    private static String getCommand(String userInput) {
        String[] inputWords = userInput.split(" ");
        return inputWords[0];
    }

    /**
     * Gets user input and execute corresponding command until the loop is exit.
     *
     * @param sc       Java Scanner to get user input.
     * @param taskList the list of tasks.
     */
    private static void loopOperation(Scanner sc, TaskList taskList) {
        String userInput;
        while (true) {
            userInput = sc.nextLine();
            String userCommand = getCommand(userInput);
            switch (userCommand) {
            case "help":
                printHelp();
                break;
            case "exit":
            case "bye":
                printExitGreetings();
                return;
            case "list":
                taskList.printCurrentList();
                break;
            case "todo":
                try {
                    String[] parseResult = Todo.parseTaskContent(userInput);
                    taskList.addTodoTask(parseResult[0]);
                } catch (Exception e) {
                    System.out.println(DIVIDER + e.getMessage() + DIVIDER_LINE_ONLY);
                }
                break;
            case "deadline":
                try {
                    if (Deadline.isCommandValid(userInput)) {
                        String[] parseResult = Deadline.parseTaskContent(userInput);
                        String taskContent = parseResult[0];
                        String taskDeadline = parseResult[1];
                        taskList.addDeadlineTask(taskContent, taskDeadline);
                    } else {
                        throw new Exception("Invalid deadline command. Check 'help'.\n");
                    }
                } catch (Exception e) {
                    System.out.println(DIVIDER + e.getMessage() + DIVIDER_LINE_ONLY);
                }
                break;
            case "event":
                try {
                    if (Event.isCommandValid(userInput)) {
                        String[] parseResult = Event.parseTaskContent(userInput);
                        String taskContent = parseResult[0];
                        String taskPeriod = parseResult[1];
                        taskList.addEventTask(taskContent, taskPeriod);
                    } else {
                        throw new Exception("Invalid event command. Check 'help'.\n");
                    }
                } catch (Exception e) {
                    System.out.println(DIVIDER + e.getMessage() + DIVIDER_LINE_ONLY);
                }
                break;
            case "done":
                try {
                    String taskIndexString = userInput.split(" ")[1];
                    int itemIndex = Integer.parseInt(taskIndexString);
                    if (!taskList.isIndexInRange(itemIndex - 1)) {
                        System.out.println(DIVIDER
                                + "The task index input is out of range!\n"
                                + DIVIDER
                                + "Try again:"
                        );
                        continue;
                    }
                    taskList.updateTaskStatus(itemIndex - 1, true);
                } catch (Exception e) {
                    System.out.println(DIVIDER
                            + "Invalid input!\n"
                            + "The item number should be a valid integer!\n"
                            + DIVIDER
                            + "Try again:"
                    );
                    continue;
                }
                break;
            default:
                printNotCommand();
                break;
            }
        }
    }

    public static void main(String[] args) {
        printGreetings();
        Scanner sc = new Scanner(System.in);
        TaskList taskList = new TaskList();
        loopOperation(sc, taskList);
    }
}
