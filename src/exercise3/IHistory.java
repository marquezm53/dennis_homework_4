package exercise3;

import java.util.List;




/**
 * @author: Scott Apalsch
 */
public interface IHistory {
    public void add(Computation computation);

    public List<Computation> getComputations();

    public Computation getComputation(int index);

    public void deleteHistory();
}
