import java.util.Comparator;

public class H_NodeComparator implements Comparator<H_Node> {

    public int compare(H_Node a, H_Node b) {

        return a.getFrequency() - b.getFrequency();

    }
}
