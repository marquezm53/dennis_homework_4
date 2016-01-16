package exercise3;

import java.util.List;




public interface ICalculator {

    /**
     * @author: Miguel Marquez
     */
    double add(List<Double> valuesToCalculate);

    /**
     * @author: Miguel Marquez
     */
    public double subtract(List<Double> valuesToCalculate);

    /**
     * @author: Miguel Marquez
     */
    public double multiply(List<Double> valuesToCalculate);

    /**
     * @author: Miguel Marquez
     */
    public double divide(List<Double> valuesToCalculate) throws ArithmeticException;

    /**
     * @author  Miguel Marquez
     */
    public double factorial(List<Double> valuesToCalculate);
}
