import com.opencsv.bean.CsvToBeanBuilder;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

;

public class Utils {

    // mean earth radius (in KM) as defined by the International Union of Geodesy and Geophysics
    private static final double EARTH_RADIUS = 6371.0087714150598;
    private static final double AVG_SEAT_NUMBER_GSH = 153.51;
    private static final double AVG_SEAT_NUMBER_GLH = 280.21;
    private static final double PASSENGER_LOAD_FACTOR = 0.82;
    private static final double ONE_MINUS_CARGO_FACTOR_GSH = 0.93;
    private static final double ONE_MINUS_CARGO_FACTOR_GLH = 0.74;
    private static final double ECONOMY_CLASS_CLASS_WEIGHTING_FACTOR_GSH = 0.96;
    private static final double ECONOMY_CLASS_CLASS_WEIGHTING_FACTOR_GLH = 0.80;
    private static final double BUSINESS_CLASS_CLASS_WEIGHTING_FACTOR_GSH = 1.26;
    private static final double BUSINESS_CLASS_CLASS_WEIGHTING_FACTOR_GLH = 1.54;
    private static final double FIRST_CLASS_CLASS_WEIGHTING_FACTOR = 2.40;
    private static final double EMISSION_FACTOR = 3.15;
    private static final double PREPRODUCTION = 0.54;
    private static final int MULTIPLIER = 2;
    private static final double AIRCRAFTFACTOR = 0.00038;
    private static final double AIRCRAFT_AND_AIRPORT_INFRA_EMISSIONS = 11.68;

    /**
     * Utilises OurAirports data to create an ArrayList containing information related to each
     * airport as per the Airport class for all current airports available at this URL:
     * https://ourairports.com/data/airports.csv
     *
     * @return ArrayList<Airport>
     * @throws IOException
     */
    public static List<Airport> generateAirports() throws IOException {

        URL url = new URL("https://ourairports.com/data/airports.csv");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        List<Airport> airports = new CsvToBeanBuilder(br).withType(Airport.class)
            .withOrderedResults(false).build().parse();
        return airports;
    }

    public static double passengerLoadFactor(String originRegion, String destRegion) {

        // If substantially similar route found within ICAO Passenger Load Factors pairs the % is used. If multiple match due to lesser granularity the avg. is used.
        // If no pair found use average of all ICAO Passenger Load Factors https://www.icao.int/environmental-protection/CarbonOffset/Documents/Methodology%20ICAO%20Carbon%20Calculator_v10-2017.pdf
        if ((originRegion + destRegion).equals("AFAF")) {
            return 0.6035;
        } else if ((originRegion + destRegion).equals("AFSA") || (originRegion + destRegion)
            .equals("SAAF")) {
            return 0.6020;
        } else if ((originRegion + destRegion).equals("AFAS") || (originRegion + destRegion)
            .equals("ASAF")) {
            return 0.7290;
        } else if ((originRegion + destRegion).equals("ASAS")) {
            return 0.7605;
        } else if ((originRegion + destRegion).equals("AFNA") || (originRegion + destRegion)
            .equals("NAAF")) {
            return 0.7728;
        } else if ((originRegion + destRegion).equals("AFEU") || (originRegion + destRegion)
            .equals("EUAF")) {
            return 0.7739;
        } else if ((originRegion + destRegion).equals("SASA")) {
            return 0.7740;
        } else if ((originRegion + destRegion).equals("NASA") || (originRegion + destRegion)
            .equals("SANA")) {
            return 0.7966;
        } else if ((originRegion + destRegion).equals("ASNA") || (originRegion + destRegion)
            .equals("NAAS")) {
            return 0.7980;
        } else if ((originRegion + destRegion).equals("ASEU") || (originRegion + destRegion)
            .equals("EUAS")) {
            return 0.8080;
        } else if ((originRegion + destRegion).equals("EUEU")) {
            return 0.8089;
        } else if ((originRegion + destRegion).equals("NANA")) {
            return 0.8178;
        } else if ((originRegion + destRegion).equals("EUNA") || (originRegion + destRegion)
            .equals("NAEU")) {
            return 0.8216;
        } else if ((originRegion + destRegion).equals("EUSA") || (originRegion + destRegion)
            .equals("SAEU")) {
            return 0.8220;
        } else {
            return 0.7672;
        }
    }

    public static Airport selectAirport() throws IOException {
        List<Airport> airports = generateAirports();
        ArrayList<Airport> possibleAirports = new ArrayList<>();
        String inputtedString = "";

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(
                "Enter the name of the airport e.g. Birmingham International Airport or the 3 Letter IATA code e.g. BHX:\n");
            inputtedString = scanner.next();
        } while (!inputtedString.chars().allMatch(Character::isLetter));

        // 3 is the length of an IATA code
        if (inputtedString.length() == 3) {
            for (Airport airport : airports) {
                if (airport.getiAtA().toLowerCase().equals(inputtedString.toLowerCase())) {
                    possibleAirports.add(airport);
                }
            }
        } else {
            for (Airport airport : airports) {
                if (airport.getName().toLowerCase().equals(inputtedString.toLowerCase())) {
                    possibleAirports.add(airport);
                }
            }

            if (possibleAirports.size() == 0) {
                for (Airport airport : airports) {
                    if (airport.getName().toLowerCase().contains(inputtedString.toLowerCase())) {
                        possibleAirports.add(airport);
                    }
                }
            }
        }

        if (possibleAirports.size() == 0) {
            System.out.println(
                "Sorry we could not find any possible matches for " + inputtedString + ".");
            return null;
        } else if (possibleAirports.size() == 1) {
            System.out.println("Airport found! " + possibleAirports.get(0).toString());
            return possibleAirports.get(0);
        } else {
            System.out.println("We found multiple airports that matched: " + inputtedString
                + ". When prompted please enter a valid selection number for the correct airport or if none match input 999 to end the program.");
            Collections.sort(possibleAirports, Comparator.comparing(Airport::getName));
            for (int i = 0; i < possibleAirports.size(); i++) {
                System.out.printf("%-10s %-10s %-10s\n", "Input " + i + " for: ",
                    possibleAirports.get(i).getName(), possibleAirports.get(i).getIsoCountry());
                //System.out.println("Input " + i + " for: " + possibleAirports.get(i).toString());
            }

            int airportSelection;
            do {
                airportSelection = scanner.nextInt();
            } while (!(airportSelection >= 0 && airportSelection <= possibleAirports.size() - 1
                || airportSelection == 999));
            scanner.close();

            if (airportSelection == 999) {
                System.out.print("Sorry we couldn't find a correct match!");
                return null;
            } else {
                System.out
                    .println("You selected: " + possibleAirports.get(airportSelection).toString());
                return possibleAirports.get(airportSelection);
            }
        }
    }

    // Haversine formula calculating
    public static double getDistance(double startLat, double startLon, double endLat,
        double endLon) {
        double dLat = Math.toRadians(endLat - startLat);
        double dLon = Math.toRadians(endLon - startLon);
        startLat = Math.toRadians(startLat);
        endLat = Math.toRadians(endLat);

        double a =
            Math.pow(Math.sin(dLat / 2), 2) + Math.pow(Math.sin(dLon / 2), 2) * Math.cos(startLat)
                * Math.cos(endLat);
        double c = 2 * Math.asin(Math.sqrt(a));

        // GCD correction factor accounting for distance flown in excess of the GCD including; stacking, traffic and weather-driven corrections (https://www.icao.int/environmental-protection/CarbonOffset/Documents/Methodology%20ICAO%20Carbon%20Calculator_v10-2017.pdf)
        if (EARTH_RADIUS * c < 550) {
            return (EARTH_RADIUS * c) + 50;
        } else if (EARTH_RADIUS * c >= 550 && EARTH_RADIUS * c <= 5500) {
            return (EARTH_RADIUS * c) + 100;
        } else {
            return (EARTH_RADIUS * c) + 125;
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("Select your origin airport:");
        Airport startAirport = selectAirport();
        System.out.println("\nSelect your destination airport:");
        Airport endAirport = selectAirport();
        System.out.println(
            "\nThe distance between " + startAirport.getName() + " & " + endAirport.getName()
                + " is " + (int) getDistance(startAirport.getLatitude(),
                startAirport.getLongitude(),
                endAirport.getLatitude(), endAirport.getLongitude()) + "km.");
    }
}
