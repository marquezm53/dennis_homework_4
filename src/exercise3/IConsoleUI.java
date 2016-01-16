package exercise3;

import java.util.List;
import java.util.Scanner;




public interface IConsoleUI {
    public String requestOperation();

    public String requestValues();

    public void printNotification(String notificationMessage);

    public void printComputation(Computation computation);

    void printHistory(List<Computation> computations);
}