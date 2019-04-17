import java.util.Comparator;

public class OrdenarAgenciaPorAddress implements Comparator<Agency> {

    public int compare(Agency o1, Agency o2) {
        return o1.getAddress().getAddress_line().compareTo(o2.getAddress().getAddress_line());
    }
}
