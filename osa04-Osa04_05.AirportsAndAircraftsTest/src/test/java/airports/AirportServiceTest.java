package airports;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class AirportServiceTest {

    @Autowired
    private AirportRepository airportRepository;

    @Autowired
    private AirportService airportService;

    @Test
    public void canCreateAirport(){
        String identifier = "MAD";
        String name = "Madrid";
        this.airportService.create(identifier, name);
        Airport airport = this.airportRepository.findAll().get(0);
        assertEquals(airport.getName(), name);
        assertEquals(airport.getIdentifier(), identifier);
    }

    @Test
    public void canReturnAllAirports(){
//        Create a few airports
        this.airportService.create("JFK", "John Kennedy NYC");
        this.airportService.create("AMS", "Amsterdam");
        this.airportService.create("LAX", "Los Angeles");
        List<Airport> airports = this.airportService.list();
        assertEquals(3, airports.size());
    }

    @Test
    public void cannotCreateAirportIfAlreadyExists(){
//        Add one airport
        this.airportService.create("JFK", "John Kennedy NYC");
        assertEquals(1, airportRepository.count());
//        Add again with different name
        this.airportService.create("JFK", "JFK NYC");
        assertEquals(1, airportRepository.count());
//        Add again with different identifier
        this.airportService.create("EWR", "John Kennedy NYC");
        assertEquals(1, airportRepository.count());

    }

}
