package exercise3;

//import exercise3.*;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Before;
import org.junit.After;;
import org.junit.Rule;
import org.junit.Test;
import org.junit.Assert;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import java.util.List;




public class CalculatorTest {
    

   /********************************************************************************
  * Test Suite for History User Story 5, 6, 7
  * @author: Miguel Marquez
  **********************************************************************************/
   public Computation createTestComputation(double variance) {
       Computation testComputation = new Computation();
       testComputation.opType = "add";
       List<Double> testValues = new LinkedList<>();
       Double valueToAdd = .75 + variance;
       Double valueToAdd2 = .25 + variance;
       testValues.add(valueToAdd);
       testValues.add(valueToAdd2);

       testComputation.result = valueToAdd + valueToAdd2;

       testComputation.values = testValues;

       return testComputation;
   }

   @Test
   public void testHistory_AddSomething() {
       History history = new History();
       Computation expectedComputation = createTestComputation(0.0);
       history.add(expectedComputation);

       List<Computation> historyComputations =  history.getComputations();

       Assert.assertEquals(expectedComputation, historyComputations.get(0));
   }

   @Test
   public void testHistory_AddNothing() {
       History history = new History();
       history.add(null);
       Object expectedComputation = null;
       List<Computation> historyComputations =  history.getComputations();

       Assert.assertEquals(expectedComputation, historyComputations.get(0));
   }

   @Test
   public void testHistory_GetHistoryItem() {
       History history = new History();

       Computation expectedComputation1 = createTestComputation(0.0);
       Computation expectedComputation2 = createTestComputation(1.0);
       Computation expectedComputation3 = createTestComputation(2.0);

       history.add(expectedComputation1);
       history.add(expectedComputation2);
       history.add(expectedComputation3);

       Assert.assertEquals(expectedComputation1, history.getComputation(3));
       Assert.assertEquals(expectedComputation2, history.getComputation(2));
       Assert.assertEquals(expectedComputation3, history.getComputation(1));
   }

   @Test
   public void testHistory_HistorySizeValid() {
       History history = new History();

       for (double i = 0; i < 10; i++) {
           history.add(createTestComputation(i));
       }

       Assert.assertEquals(10, history.getComputations().size());
   }

   @Test
   public void testHistory_DeleteHistory() {
       History history = new History();

       for (double i = 0; i < 10; i++) {
           history.add(createTestComputation(i));
       }

       history.deleteHistory();
       Assert.assertEquals(0, history.getComputations().size());
   }

   @Test
   public void testConsoleUI_PrintHistoryEmpty() {
       ConsoleUI consoleUI = new ConsoleUI();

       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       PrintStream printStream = new PrintStream(byteArrayOutputStream);

       PrintStream systemOut = System.out;
       System.setOut(printStream);



       consoleUI.printHistory(new ArrayList<Computation>());

       System.out.flush();
       System.setOut(systemOut);

       String expected = "No history to print." + System.lineSeparator();

       Assert.assertEquals(expected, byteArrayOutputStream.toString());
   }

   /*****************************************************************
   * Test Suite for History
   * End
   ******************************************************************/




   /*****************************************************************
    * Test suite for user story 1 add.
    * Start
    * @author: Scott Apalsch
    ******************************************************************/

   public Calculator calculator;
   public ConsoleUI view;
   public CalculatorController controller;

   @Before
   public void setUp() throws Exception {
       calculator = new Calculator();
       view = new ConsoleUI();
       controller = new CalculatorController();
   }

   @Test
   public void testAdd1() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.1);
       double result = calculator.add(values);
       Assert.assertEquals(1.1, result, 0.01);
   }

   @Test
   public void testAdd2() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.2);
       values.add(2.1);
       double result = calculator.add(values);
       Assert.assertEquals(3.3, result, 0.01);
   }

   @Test
   public void testAdd3() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.2);
       values.add(2.1);
       values.add(3.0);
       double result = calculator.add(values);
       Assert.assertEquals(6.3, result, 0.01);
   }

   @Test
   public void testAdd4() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(-1.2);
       values.add(-2.1);
       double result = calculator.add(values);
       Assert.assertEquals(-3.3, result, 0.01);
   }

   @Test
   public void testAdd5() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(2.1);
       values.add(-1.2);
       double result = calculator.add(values);
       Assert.assertEquals(0.9, result, 0.01);
   }

   @Test
   public void testControllerAdd() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       final List<Double> values = new ArrayList<Double>();
       values.add(1.0);
       values.add(2.0);
       final Computation computation = new Computation();
       computation.opType = "add";
       computation.values = values;
       computation.result = 3.0;
       context.checking(new Expectations() {
           {
               oneOf(controller.view).requestValues();
               will(returnValue("1, 2"));
               oneOf(controller.calculator).add(values);
               will(returnValue(3.0));
               oneOf(controller.view).printComputation(with(equal(computation)));
               oneOf(controller.history).add(with(equal(computation)));
           }});
       String operation = "Add";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }
   /*****************************************************************
    * Test suite for user story 1 add
    * End
    ******************************************************************/

   /*****************************************************************
    * Test suite for user story 2 subtract
    * Start
    * @author: Scott Apalsch
    ******************************************************************/
   @Test
   public void testSub1() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.1);
       double result = calculator.subtract(values);
       Assert.assertEquals(1.1, result, 0.01);
   }

   @Test
   public void testSub2() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(2.1);
       values.add(1.2);
       double result = calculator.subtract(values);
       Assert.assertEquals(0.9, result, 0.01);
   }

   @Test
   public void testSub3() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.2);
       values.add(2.1);
       values.add(3.0);
       double result = calculator.subtract(values);
       Assert.assertEquals(-3.9, result, 0.01);
   }

   @Test
   public void testSub4() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(-1.2);
       values.add(-2.1);
       double result = calculator.subtract(values);
       Assert.assertEquals(0.9, result, 0.01);
   }

   @Test
   public void testSub5() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(2.1);
       values.add(-1.2);
       double result = calculator.subtract(values);
       Assert.assertEquals(3.3, result, 0.01);
   }

   @Test
   public void testControllerSub() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       final List<Double> values = new ArrayList<Double>();
       values.add(3.0);
       values.add(1.0);
       final Computation computation = new Computation();
       computation.opType = "sub";
       computation.values = values;
       computation.result = 2.0;
       context.checking(new Expectations() {
           {
               oneOf(controller.view).requestValues();
               will(returnValue("3, 1"));
               oneOf(controller.calculator).subtract(values);
               will(returnValue(2.0));
               oneOf(controller.view).printComputation(with(equal(computation)));
               oneOf(controller.history).add(with(equal(computation)));
           }});
       String operation = "Sub";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }
   /*****************************************************************
    * Test suite for user story 2 subtract
    * End
    ******************************************************************/

   /*****************************************************************
    * Test suite for user story 3 multiply
    * Start
    * @author: Scott Apalsch
    ******************************************************************/

   @Test
   public void testMul1() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.1);
       double result = calculator.multiply(values);
       Assert.assertEquals(1.1, result, 0.01);
   }

   @Test
   public void testMul2() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(2.1);
       double result = calculator.multiply(values);
       Assert.assertEquals(2.1, result, 0.01);
   }

   @Test
   public void testMul3() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(2.0);
       values.add(3.0);
       double result = calculator.multiply(values);
       Assert.assertEquals(6.0, result, 0.01);
   }

   @Test
   public void testMul4() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(-1.0);
       values.add(-2.1);
       double result = calculator.multiply(values);
       Assert.assertEquals(2.1, result, 0.01);
   }

   @Test
   public void testMul5() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(2.1);
       values.add(-1.0);
       double result = calculator.multiply(values);
       Assert.assertEquals(-2.1, result, 0.01);
   }
   
   @Test
   public void testControllerMul() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       final List<Double> values = new ArrayList<Double>();
       values.add(3.0);
       values.add(2.0);
       final Computation computation = new Computation();
       computation.opType = "mul";
       computation.values = values;
       computation.result = 6.0;
       context.checking(new Expectations() {
           {
               oneOf(controller.view).requestValues();
               will(returnValue("3, 2"));
               oneOf(controller.calculator).multiply(values);
               will(returnValue(6.0));
               oneOf(controller.view).printComputation(with(equal(computation)));
               oneOf(controller.history).add(with(equal(computation)));
           }});
       String operation = "Mul";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   /*****************************************************************
    * Test suite for user story 3 multiply
    * End
    ******************************************************************/



   /*****************************************************************
    * Test suite for printing history to view user story 5
    * Start
    * @author: Miguel Marquez
    ******************************************************************/

   public List<Computation> createListOfComputations (double numberOfComputations) {
       List<Computation> computations = new LinkedList<>();

       for (double i = 0.0; i < numberOfComputations; i++) {
           computations.add(createTestComputation(i));
       }

       return computations;
   }

   @Test
   public void testConsoleUI_PrintComputation() {
       ConsoleUI consoleUI = new ConsoleUI();

       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       PrintStream printStream = new PrintStream(byteArrayOutputStream);

       PrintStream systemOut = System.out;
       System.setOut(printStream);

       consoleUI.printComputation(createTestComputation(0.0));

       System.out.flush();
       System.setOut(systemOut);

       String expected = "add : 0.75, 0.25 : 1.0" + System.lineSeparator();

       Assert.assertEquals(expected, byteArrayOutputStream.toString());
   }

   @Test
   public void testConsoleUI_PrintHistory() {
       ConsoleUI consoleUI = new ConsoleUI();

       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       PrintStream printStream = new PrintStream(byteArrayOutputStream);

       PrintStream systemOut = System.out;
       System.setOut(printStream);

       consoleUI.printHistory(createListOfComputations(3.0));

       System.out.flush();
       System.setOut(systemOut);

       String expected = "Index : Operation : Values : Result" + System.getProperty("line.separator") +
               "1 : add : 0.75, 0.25 : 1.0"+System.getProperty("line.separator") +
               "2 : add : 1.75, 1.25 : 3.0"+System.getProperty("line.separator") +
               "3 : add : 2.75, 2.25 : 5.0"+System.getProperty("line.separator");

       Assert.assertEquals(expected, byteArrayOutputStream.toString());
   }
   /*****************************************************************
    * Test suite for printing history to view user story 5
    * End
    ******************************************************************/


   /*****************************************************************
    * Test suite for division functionality (User Story 4)
    * Start
    * @author: Scott Apalsch
    ******************************************************************/
   @Test
   public void testDiv1() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.1);
       double result = calculator.divide(values);
       Assert.assertEquals(1.1, result, 0.01);
   }

   @Test
   public void testDiv2() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(2.0);
       double result = calculator.divide(values);
       Assert.assertEquals(0.5, result, 0.01);
   }

   @Test
   public void testDiv3() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(2.0);
       values.add(4.0);
       double result = calculator.divide(values);
       Assert.assertEquals(0.125, result, 0.01);
   }

   @Test
   public void testDiv4() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(-2.0);
       values.add(-2.0);
       double result = calculator.divide(values);
       Assert.assertEquals(1.0, result, 0.01);
   }

   @Test
   public void testDiv5() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(-2.0);
       double result = calculator.divide(values);
       Assert.assertEquals(-0.5, result, 0.01);
   }

   @Rule
   public ExpectedException thrown = ExpectedException.none();

   @Test
   public void testDiv6() throws Exception {
       thrown.expect(ArithmeticException.class);
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(0.0);
       calculator.divide(values);
   }

   @Test
   public void testDiv7() throws Exception {
       thrown.expect(ArithmeticException.class);
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(0.0);
       values.add(2.0);
       calculator.divide(values);
   }

   @Test
   public void testControllerDiv() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       final List<Double> values = new ArrayList<Double>();
       values.add(1.0);
       values.add(2.0);
       final Computation computation = new Computation();
       computation.opType = "div";
       computation.values = values;
       computation.result = 0.5;
       context.checking(new Expectations() {
           {
               oneOf(controller.view).requestValues();
               will(returnValue("1, 2"));
               oneOf(controller.calculator).divide(values);
               will(returnValue(0.5));
               oneOf(controller.view).printComputation(with(equal(computation)));
               oneOf(controller.history).add(with(equal(computation)));
           }});
       String operation = "Div";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   /*****************************************************************
    * Test suite for division functionality (User Story 4)
    * End
    ******************************************************************/


   /*****************************************************************
    * Test suite for calculating the factorial functionality (User Story 8)
    * Start
    * @author: Scott Apalsch
    ******************************************************************/
   @Test
   public void testFac1() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(5.0);
       double result = calculator.factorial(values);
       Assert.assertEquals(120.0, result, 0.01);
   }

   @Test
   public void testFac2() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(5.2);
       double result = calculator.factorial(values);
       Assert.assertEquals(120.0, result, 0.01);
   }

   @Test
   public void testFac3() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(5.8);
       double result = calculator.factorial(values);
       Assert.assertEquals(120.0, result, 0.01);
   }

   @Test
   public void testFac4() throws Exception {
       List<Double> values = new ArrayList<>();
       values.add(0.0);
       double result = calculator.factorial(values);
       Assert.assertEquals(1.0, result, 0.01);
   }

   @Test
   public void testFac5() throws Exception {
       thrown.expect(ArithmeticException.class);
       List<Double> values = new ArrayList<>();
       values.add(1.0);
       values.add(2.0);
       calculator.factorial(values);
   }

   @Test
   public void testFac6() throws Exception {
       thrown.expect(ArithmeticException.class);
       List<Double> values = new ArrayList<>();
       values.add(-5.0);
       calculator.factorial(values);
   }

   @Test
   public void testControllerFac() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       final List<Double> values = new ArrayList<Double>();
       values.add(3.0);
       final Computation computation = new Computation();
       computation.opType = "fac";
       computation.values = values;
       computation.result = 6.0;
       context.checking(new Expectations() {
           {
               allowing(controller.view).printNotification(with(any(String.class)));
               oneOf(controller.view).requestValues();
               will(returnValue("3"));
               oneOf(controller.calculator).factorial(values);
               will(returnValue(6.0));
               oneOf(controller.view).printComputation(with(equal(computation)));
               oneOf(controller.history).add(with(equal(computation)));
           }});
       String operation = "Fac";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }
   /*****************************************************************
    * Test suite for calculating the factorial functionality (User Story 8)
    * End
    ******************************************************************/


   /*****************************************************************
    * Test suite for verifying console UI funcitonality.
    * Applicable to all user stories.
    * Start
    * @author: Scott Apalsch
    ******************************************************************/
   public void setSystemIn(String systemInString) {
       try {
           String preparedString = systemInString + System.lineSeparator();
           System.setIn(new ByteArrayInputStream(preparedString.getBytes("UTF8")));
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

   @Test
   public void testConsoleUI_RequestOperation() {
       ConsoleUI consoleUI = new ConsoleUI();

       String expected = "sum";

       setSystemIn(expected);

       String operationActual = consoleUI.requestOperation();

       Assert.assertEquals(expected, operationActual);
   }

   @Test
   public void testConsoleUI_RequestOperationEmpty() {
       ConsoleUI consoleUI = new ConsoleUI();

       String expected = "";

       setSystemIn(expected);

       String operationActual = consoleUI.requestOperation();

       Assert.assertEquals(expected, operationActual);
   }

   @Test
   public void testConsoleUI_RequestValues() {
       ConsoleUI consoleUI = new ConsoleUI();

       String expected = "1, 2, 3, 4";

       setSystemIn(expected);

       String operationActual = consoleUI.requestValues();

       Assert.assertEquals(expected, operationActual);

   }

   @Test
   public void testConsoleUI_RequestValuesEmpty() {
       ConsoleUI consoleUI = new ConsoleUI();

       String expected = "";

       setSystemIn(expected);

       String operationActual = consoleUI.requestValues();

       Assert.assertEquals(expected, operationActual);
   }

   @Test
   public void testConsoleUI_PrintNotification() {
       ConsoleUI consoleUI = new ConsoleUI();

       String systemOutToPrint = "Alert, this is a notification!";

       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       PrintStream printStream = new PrintStream(byteArrayOutputStream);

       PrintStream systemOut = System.out;
       System.setOut(printStream);

       consoleUI.printNotification(systemOutToPrint);

       System.out.flush();
       System.setOut(systemOut);

       String expected = "Alert, this is a notification!" + System.lineSeparator();

       Assert.assertEquals(expected, byteArrayOutputStream.toString());
   }
   /*****************************************************************
    * Test suite for verifying console UI funcitonality.
    * Applicable to all user stories.
    * End
    ******************************************************************/


   /*****************************************************************
    * Test suite to verify CalculatorController non-arithmetic logic
    * Start
    * @author: Miguel Marquez
    ******************************************************************/
   @Test
   public void testControllerExit() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       context.checking(new Expectations() {
           {
               oneOf(controller.view).printNotification(with(any(String.class)));
           }});
       String operation = "exit";
       Assert.assertEquals(true, controller.performOperation(operation));
       context.assertIsSatisfied();
   }

   @Test
   public void testControllerHisValid() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       final List<Computation> computations = new ArrayList<>();
       computations.add(new Computation());
       context.checking(new Expectations() {
           {
               exactly(2).of(controller.history).getComputations();
               will(returnValue(computations));
               oneOf(controller.view).printHistory(with(equal(computations)));
           }});
       String operation = "his";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   @Test
   public void testControllerHisEmpty() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       context.checking(new Expectations() {
           {
               oneOf(controller.history).getComputations();
               will(returnValue(new ArrayList<Double>()));
               oneOf(controller.view).printNotification("No history found.");
           }});
       String operation = "his";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   @Test
   public void testControllerDelh() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       context.checking(new Expectations() {
           {
               oneOf(controller.history).deleteHistory();
           }});
       String operation = "delh";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   @Test
   public void testControllerAddFromHist() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       final List<Double> values = new ArrayList<Double>();
       values.add(3.0);
       values.add(2.0);
       final Computation computation1 = new Computation();
       computation1.opType = "add";
       computation1.values = new ArrayList<>();
       computation1.result = 3.0;
       final Computation computation2 = new Computation();
       computation2.opType = "add";
       computation2.values = values;
       computation2.result = 5.0;
       context.checking(new Expectations() {
           {
               oneOf(controller.view).requestValues();
               will(returnValue("$1, 2"));
               oneOf(controller.history).getComputation(1);
               will(returnValue(computation1));
               oneOf(controller.calculator).add(values);
               will(returnValue(5.0));
               oneOf(controller.view).printComputation(with(equal(computation2)));
               oneOf(controller.history).add(with(equal(computation2)));
           }});
       String operation = "Add";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   @Test
   public void testControllerNoValues() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       context.checking(new Expectations() {
           {
               oneOf(controller.view).requestValues();
               will(returnValue(""));
               oneOf(controller.view).printNotification(with(equal("I'm sorry, you must enter at least one value.")));
               oneOf(controller.view).requestValues();
               will(returnValue("1"));
               oneOf(controller.calculator).add(with(any(List.class)));
               will(returnValue(3.0));
               oneOf(controller.view).printComputation(with(any(Computation.class)));
               oneOf(controller.history).add(with(any(Computation.class)));
           }});
       String operation = "Add";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   @Test
   public void testControllerNoOp() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       context.checking(new Expectations() {
           {
               oneOf(controller.view).printNotification(with(equal("Invalid Operation, please try again.")));
           }});
       String operation = "";
       controller.performOperation(operation);
       context.assertIsSatisfied();
   }

   @Test
   public void testControllerStartExit() throws Exception {
       Mockery context = new Mockery();
       controller.calculator = context.mock(ICalculator.class);
       controller.history = context.mock(IHistory.class);
       controller.view = context.mock(IConsoleUI.class);
       context.checking(new Expectations() {
           {
               oneOf(controller.view).requestOperation();
               will(returnValue("exit"));
               oneOf(controller.view).printNotification(with(any(String.class)));
           }});
       controller.start();
       context.assertIsSatisfied();
   }
   /*****************************************************************
    * Test suite to verify CalculatorController non-arithmetic logic
    * End
    ******************************************************************/
}
