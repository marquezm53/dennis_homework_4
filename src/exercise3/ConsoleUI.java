package exercise3;

import java.util.List;
import java.util.Scanner;




public class ConsoleUI implements IConsoleUI{

    /**
     * @author: Miguel Marquez
     */
    public String requestOperation() {
        System.out.println("Select an operation: add (Addition), sub (Subtraction), mul (Multiply), div (Divide), fac (Factorial), his (Display History), delh (Delete History, exit.");

        Scanner in = new Scanner(System.in);

        String userSelection = in.nextLine();

        return userSelection;

    }

    /**
     * @author: Miguel Marquez
     */
    public String requestValues() {

        System.out.println("Enter comma seperated list of values (\"$x\" will retrieve the value of a previous result, where x is the number of results back.)");

        Scanner in = new Scanner(System.in);

        String userSelection = in.nextLine();

        return userSelection;

    }

    /**
     * @author: Miguel Marquez
     */
    public void printNotification(String notificationMessage) {
        System.out.println(notificationMessage);
    }

    /**
    * @author: Miguel Marquez
    */
    public void printComputation(Computation computation) {
        System.out.println(computation.toString());
    }

    /**
     * @author: Scott Apalsch
     */
    public void printHistory(List<Computation> history) {
        if (history.size() > 0) {
            System.out.println("Index : Operation : Values : Result");
            for (int i = 1; i <= history.size(); i++) {
                System.out.println(i + " : " + history.get(i - 1).toString());
            }
        } else {
            printNotification("No history to print.");
        }
    }

}
