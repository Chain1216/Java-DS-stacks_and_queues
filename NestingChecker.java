/*
 * I attest that the code in this file is entirely my own except for the starter
 * code provided with the assignment and the following exceptions:
 * <Enter all external resources and collaborations here.>
 *
 * Note external code may reduce your score but appropriate citation is required
 * to avoid academic integrity violations. Please see the Course Syllabus as
 * well as the university code of academic integrity:
 *    https://catalog.upenn.edu/pennbook/code-of-academic-integrity/
 *
 * Signed,
 * Author: YOUR NAME HERE
 * Penn email: <YOUR-EMAIL-HERE@seas.upenn.edu>
 * Date: YYYY-MM-DD
 */

import java.util.Queue;
import java.util.Stack;

public class NestingChecker {
    /*
     * TODO Implement this method! It must return a non-null NestingReport and should not throw an exception.
     */
    public static NestingReport checkNesting(Queue<? extends Nestable> elements) {
        Stack<Nestable> stack = new Stack<>();

        return new NestingReport(NestingReport.Status.VALID, null, stack);
    }

    /*
     * Instructor-provided code to assist your development and testing.  Do not alter these methods.
     */
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 8683452581122896189L;

    /*
     * A function to interactively try your nesting checker. "jshell" is an easy way to use this.
     * From a command line in the directory with your java files, run:
     *    jshell Nestable.java NestingReport.java NestableCharacter.java NestingChecker.java
     * On the jshell command line, run:
     *    NestingChecker.interact();
     */
    /*
     * A function to interactively try your nesting checker. "jshell" is an easy way to use this.
     * From a command line in the directory with your java files, run:
     *    jshell Nestable.java NestingReport.java NestableCharacter.java NestingChecker.java
     * On the jshell command line, run:
     *    NestingChecker.interact();
     */
    public static void interact() {
        var in = new java.util.Scanner(System.in);
        System.out.println("Nesting reports will be printed for each line of text you enter." +
                "\nEnter a line with only \"exit\" to stop (Ctrl-D will not work)");

        String line;
        while (true) {
            System.out.print("check> ");
            System.out.flush();

            if (!in.hasNextLine())
                break;
            line = in.nextLine();

            if (line.equals(""))
                continue;
            else if (line.equals("exit"))
                break;

            var characters = NestableCharacter.getNestableCharactersFromString(line);
            NestingReport result = checkNesting(characters);
            System.out.println(result + "\n");
        }

        System.out.println("Thank you for using the interactive nesting checker. Have a nice day.");
        in.close();
    }
}
