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
 * Author: Zhaoqin Wu
 * Penn email: zhaoqinw@seas.upenn.edu>
 * Date: 2022-06-07
 */

import java.util.Queue;
import java.util.Stack;

public class NestingChecker {
    /*
     * TODO Implement this method! It must return a non-null NestingReport and should not throw an exception.
     */
    public static NestingReport checkNesting(Queue<? extends Nestable> elements) {
        Stack<Nestable> stack = new Stack<>();
        
        //If the input queue is null, return a NestingReport with status set to NULL_INPUT, badItem
        //set to null, and stackState set to the empty Stack.
        if (elements == null) {
        	return new NestingReport(NestingReport.Status.NULL_INPUT, null, stack);
        }
        
        
        while(!elements.isEmpty()) {
        	
        	if(elements.element().getEffect() == Nestable.NestEffect.OPEN) {        // If the dequeued element¡¯s nesting effect is opening, we push it to a Stack.
        		stack.add(elements.poll()); 		
        	}
        	
        	else if(elements.element().getEffect() == Nestable.NestEffect.CLOSE) {  // If the dequeued element¡¯s nesting effect is closing, we pop the Stack only if the top of the Stack	                                                                 
        		if(elements.element().matches(stack.peek())) {                      // matches the dequeued element;          
        			elements.poll();
        			stack.pop();
        		}
        		return new NestingReport(NestingReport.Status.INVALID_CLOSE, elements.element(), stack); // When an element b in the Queue is an invalid closing element, return
        	}
        	
        	else if(elements.element().getEffect() == Nestable.NestEffect.NEUTRAL) { // ignore when the element's nesting effect is neutral
        		continue;
        	}
        	
        	else if(elements.element() == null) {
        		return new NestingReport(NestingReport.Status.NOT_TERMINATED, null, stack); // When an element b in the Queue is an invalid closing element, return
        	}	
        }
        
        if (!stack.empty()) {                                                      //When the Queue represents an invalid nesting because it¡¯s empty but there are still elements
        	return new NestingReport(NestingReport.Status.NULL_ITEM, null, stack); //remaining in the Stack that were never closed, return
        }
        
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
