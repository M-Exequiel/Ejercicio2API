import java.util.Collection;
import java.util.HashMap;

public class AgencyServiceMapImp implements AgencyService {

    private HashMap<String,Agency> agencyMap;

    public AgencyServiceMapImp() {
        agencyMap = new HashMap<String, Agency>();
    }

    public Collection<Agency> getAgency() {
        return agencyMap.values();
    }

    public void addAgency(Agency agency) {
        agencyMap.put(agency.getId(),agency);
    }
}
