package exercise3;

import java.util.List;




public class Calculator implements ICalculator{

    /**
     * @author: Miguel Marquez
     */
    public double add(List<Double> valuesToCalculate) {
        Double sum = 0.0;

        for (Double val : valuesToCalculate) {
            sum += val;
        }

        return sum;
    }

    /**
     * @author: Miguel Marquez
     */
    public double subtract(List<Double> valuesToCalculate) {
        Double difference = valuesToCalculate.get(0);

        for (int i = 1; i < valuesToCalculate.size(); i++) {
            difference -= valuesToCalculate.get(i);
        }

        return difference;
    }

    /**
     * @author: Miguel Marquez
     */
    public double multiply(List<Double> valuesToCalculate) {
        Double product = valuesToCalculate.get(0);

        for (int i = 1; i < valuesToCalculate.size(); i++) {
            product *= valuesToCalculate.get(i);
        }

        return product;
    }

    /**
     * @author: Miguel Marquez
     */
    public double divide(List<Double> valuesToCalculate) throws ArithmeticException {
        Double quotient = valuesToCalculate.get(0);

        for (int i = 1; i < valuesToCalculate.size(); i++) {
            if (i != 0) {
                if (valuesToCalculate.get(i) == 0) {
                    throw new ArithmeticException("Can not divide by zero.");
                }
            }
            quotient /= valuesToCalculate.get(i);
        }

        return quotient;
    }

    /**
     * @author  Miguel Marquez
     */
    public double factorial(List<Double> valuesToCalculate) {
        if (valuesToCalculate.size() != 1) {
            throw new ArithmeticException("Factorial operation can only use one value.");
        }

        Double value = valuesToCalculate.get(0);

        if (value < 0) {
            throw new ArithmeticException("Factorial operation can only compute positive values.");
        }

        Double cleanedValue = Math.floor(value);

        if (cleanedValue != 0) {
            for (double d = cleanedValue - 1; d > 1.0; d--) {
                cleanedValue *= d;
            }
        } else {
            cleanedValue = 1.0;
        }


        return cleanedValue;

    }







}
