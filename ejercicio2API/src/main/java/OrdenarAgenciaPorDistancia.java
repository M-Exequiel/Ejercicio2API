import java.util.Comparator;

public class OrdenarAgenciaPorDistancia implements Comparator<Agency> {
    public int compare(Agency o1, Agency o2) {
        return o1.getDistance().compareTo(o2.getDistance());
    }
}
