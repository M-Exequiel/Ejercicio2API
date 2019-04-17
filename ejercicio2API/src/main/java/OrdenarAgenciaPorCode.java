import java.util.Comparator;

public class OrdenarAgenciaPorCode implements Comparator<Agency> {
    public int compare(Agency o1, Agency o2) {
        return o1.getAgency_code().compareTo(o2.getAgency_code());
    }
}
