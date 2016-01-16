package exercise3;

import java.util.List;




public class Computation {

    public String opType;
    public List<Double> values;
    public double result;

	/**
	 * @author: Scott Apalsch
	 */
    public String toString() {
        String toPrint  = opType + " : ";
        for (int i = 0; i < values.size(); i++) {
            if (i == 0) {
                toPrint += values.get(i).toString();
            } else {
                toPrint += ", " + values.get(i).toString();
            }
        }
        toPrint += " : " + result;
        return toPrint;
    }

	/**
	 * @author: Scott Apalsch
	 */
    public boolean equals(Object o){
        boolean equal = false;
        try {
            if (o.getClass() == Computation.class) {
                Computation c = (Computation) o;
                if ((opType == c.opType) && (values.equals(c.values)) && (result == c.result)) {
                    equal = true;
                }
            }
        }catch (Exception e){}
        return equal;
    }

}
