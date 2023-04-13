import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date flightsBefore = new Date(System.currentTimeMillis());
        flightsBefore.setHours(flightsBefore.getHours() + 2);
        List<Flight> flights = airport.getTerminals().stream()
                .flatMap(Terminal -> Terminal.getFlights().stream())
                .filter(Flight->Flight.getType().equals(com.skillbox.airport.Flight.Type.DEPARTURE))
                .filter(Flight->Flight.getDate().before(flightsBefore)).collect(Collectors.toList());
        return flights;
    }

}