package airports;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AirportService {

    @Autowired
    private AirportRepository airportRepository;

    public List<Airport> list() {
        return airportRepository.findAll();
    }

    public void create(String identifier, String name) {
        List<Airport> airports = list();
        for (Airport a: airports){
            if (a.getIdentifier().equals(identifier) || a.getName().equals(name)){
                return;
            }
        }

        Airport a = new Airport();
        a.setIdentifier(identifier);
        a.setName(name);

        airportRepository.save(a);
    }
}
