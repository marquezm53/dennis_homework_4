package exercise3;

//import sun.nio.cs.HistoricallyNamedCharset;

import java.util.LinkedList;
import java.util.List;


public class CalculatorController {

    public IConsoleUI view;
    public ICalculator calculator;
    public IHistory history;

    /**
     * @author: Scott Apalsch
     */
    public CalculatorController() {
        view = new ConsoleUI();
        calculator = new Calculator();
        history = new History();
    }

    /**
     * @author: Scott Apalsch
     */
    public void start() {
        boolean exit = false;
        while (!exit) {
            try {
                String operation = view.requestOperation();
                exit = performOperation(operation);
            } catch (ArithmeticException e) {
                view.printNotification(e.getMessage());
            } catch (Exception e) {
                view.printNotification("I'm sorry, an error has occurred. Please try again.");
            }
        }
    }

    /**
     * @author: Scott Apalsch & Miguel Marquez
     */
    public boolean performOperation(String operation) throws Exception {
        boolean exit = false;
        Computation computation = new Computation();
        switch (operation.toLowerCase().trim()) {
            case "add":
                computation.opType = "add";
                getValues(computation);
                computation.result = calculator.add(computation.values);
                view.printComputation(computation);
                history.add(computation);
                break;

            case "sub":
                computation.opType = "sub";
                getValues(computation);
                computation.result = calculator.subtract(computation.values);
                view.printComputation(computation);
                history.add(computation);
                break;

            case "mul":
                computation.opType = "mul";
                getValues(computation);
                computation.result = calculator.multiply(computation.values);
                view.printComputation(computation);
                history.add(computation);
                break;

            case "div":
                computation.opType = "div";
                getValues(computation);
                computation.result = calculator.divide(computation.values);
                view.printComputation(computation);
                history.add(computation);
                break;

            case "fac":
                view.printNotification("Program will round down value to nearest integer!");
                computation.opType = "fac";
                getValues(computation);
                computation.result = calculator.factorial(computation.values);
                view.printComputation(computation);
                history.add(computation);
                break;

            case "his":
                if (history.getComputations().size() > 0) {
                    view.printHistory(history.getComputations());
                } else {
                    view.printNotification("No history found.");
                }
                break;

            case "delh":
                history.deleteHistory();
                break;

            case "exit":
                view.printNotification("Exiting . . .");
                exit = true;
                break;

            default:
                view.printNotification("Invalid Operation, please try again.");
        }
        return exit;
    }

    /**
     * @author: Scott Apalsch
     */
    public void getValues(Computation computation) {
        boolean valid = false;
        while (!valid) {
            try {
                String userInput = view.requestValues().trim();
                if (userInput.length() == 0) {
                    view.printNotification("I'm sorry, you must enter at least one value.");
                } else {
                    String[] stringValuesToCalculate = userInput.split(",");
                    List<Double> valuesToCalculate = new LinkedList<>();

                    for (int i = 0; i < stringValuesToCalculate.length; i++) {
                        String valueString = stringValuesToCalculate[i].trim();
                        if (valueString.startsWith("$")) {
                            valueString = valueString.substring(1);
                            int index = Integer.parseInt(valueString);
                            Double dblToAdd = history.getComputation(index).result;
                            valuesToCalculate.add(dblToAdd);
                        } else {
                            Double dblToAdd = Double.valueOf(valueString);
                            valuesToCalculate.add(dblToAdd);
                        }
                    }
                    if (valuesToCalculate.size() > 0) {
                        computation.values = valuesToCalculate;
                        valid = true;
                    }
                }
            } catch (IndexOutOfBoundsException e) {
                view.printNotification("History value is out of bounds. Check history to see valid values.");
            } catch (Exception e) {
                view.printNotification("Invalid input. Please try again!");
            }
        }
    }
}