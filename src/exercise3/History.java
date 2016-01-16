package exercise3;

import java.util.LinkedList;
import java.util.List;

/**
 * @author: Scott Apalsch
 */
public class History implements IHistory{

    private List<Computation> history;

    public History() {
        history = new LinkedList<>();
    }

    public void add(Computation computation) {
        history.add(0, computation);
    }

    public List<Computation> getComputations() {
        return history;
    }

    public Computation getComputation(int index) {
        return history.get(index-1);
    }

    public void deleteHistory() {
        history.clear();
    }
}
