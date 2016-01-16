**** Project Description ****

This package contains the source code to a Calculator written in Java, it also contains a file that runs tests against the source code to verify that it's functioning properly. 


*** Authors ****

Apalsch, Scott
Marquez, Miguel (Author of this document) 


*** Java Version ****
Java 1.7

*** Library Dependencies ****

--------------------------
| Name          | Version|
|------------------------|
| JUnit         | 4.11   |
| hamcrest-core | 1.3    |
| jMock         | 2.6.0  |
--------------------------

*** Build Tool & Instructions ***

Project can be built and tested with Maven:
1) Install Maven & make sure Maven is in the PATH.
2) Go the folder that this is inside of inside a terminal/command line tool.
3) Execute "mvn compile" to compile the code or execute "mvn test" to test the code.

Note: 
- The class files that are built with maven are outputted in: path/to/folder/containingpom/target/classes/exercise3/*.class
    - If you would like to blow away the files that are built by maven you can execute "maven clean", and that will clean up your folder.
    
- The success of the tests can be determined after executing "maven test". The test results can be seen in a line of the output. Any tests that fail will also be printed out after executing "maven tests". The output after executing tests with "maven test" should look like this:

. . .
. . .
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running exercise3.CalculatorTest
Select an operation: add (Addition), sub (Subtraction), mul (Multiply), div (Divide), fac (Factorial), his (Display History), delh (Delete History, exit.
Enter comma seperated list of values ("$x" will retrieve the value of a previous result, where x is the number of results back.)
Enter comma seperated list of values ("$x" will retrieve the value of a previous result, where x is the number of results back.)
Select an operation: add (Addition), sub (Subtraction), mul (Multiply), div (Divide), fac (Factorial), his (Display History), delh (Delete History, exit.
Tests run: 54, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.109 sec

Results :

Tests run: 54, Failures: 0, Errors: 0, Skipped: 0
. . .
. . .

