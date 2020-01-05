import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

    // mean earth radius (in KM) as defined by the International Union of Geodesy and Geophysics
    public static final double EARTH_RADIUS = 6371.0087714150598;

    public static ArrayList<Airport> generateAirports() throws IOException {

        URL url = new URL(
            "https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        Scanner scanner = new Scanner(br);
        ArrayList<Airport> airports = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.replace("\\N", "").replace("\"", "")
                .split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");

            // if statement to fix names which contain a comma such as: "Harstad/Narvik Airport, Evenes".
            if (splitLine.length > 14) {
                splitLine = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                for (int i = 0; i < splitLine.length; i++) {
                    splitLine[i] = splitLine[i].replace("\\N", "").replace("\"", "");
                }
            }
            airports.add(new Airport(Integer.parseInt(splitLine[0]), splitLine[1], splitLine[2],
                splitLine[3], splitLine[4], splitLine[5], Double.parseDouble(splitLine[6]),
                Double.parseDouble(splitLine[7]), Integer.parseInt(splitLine[8]), splitLine[12],
                splitLine[13]));
        }
        scanner.close();
        return airports;
    }

    public static Airport selectAirport() throws IOException {
        ArrayList<Airport> airports = generateAirports();
        ArrayList<Airport> possibleAirports = new ArrayList<>();
        String inputtedString = "";

        Scanner input = new Scanner(System.in);
        do {
            System.out.print(
                "Enter the name of the airport e.g. Birmingham International Airport or the 3 Letter IATA code e.g. BHX:\n");
            inputtedString = input.next();
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
            System.out.println("Sorry we could not find any airports matching " + inputtedString + ".");
            return null;
        } else if (possibleAirports.size() == 1) {
            System.out.println("Airport found: " + possibleAirports.get(0).toString());
            return possibleAirports.get(0);
        } else {
            System.out.println("We found multiple airports that matched: " + inputtedString + ". When prompted please enter a valid selection number for the correct airport or if none match please input 999 to terminate the program.");
            for (int i = 0; i < possibleAirports.size(); i++) {
                System.out.println("Input " + i + " for: " + possibleAirports.get(i).toString());
            }

            int airportSelection = Integer.MIN_VALUE;
            do {
                airportSelection = input.nextInt();
            //} while (!(airportSelection >= 0 && airportSelection <= (possibleAirports.size() - 1)) || airportSelection != 999);
            } while (!(airportSelection >= 0 && airportSelection <= possibleAirports.size() - 1 || airportSelection == 999));
            input.close();

            if (airportSelection == 999) {
                System.out.print("Sorry we couldn't find a match!");
                return null;
            } else {
                System.out.println("You selected: " + possibleAirports.get(airportSelection).toString());
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
        System.out.println("\nThe distance between " + startAirport.getName() + " & " + endAirport.getName() + " is " + getDistance(startAirport.getLatitude(),  startAirport.getLongitude(), endAirport.getLatitude(),  endAirport.getLongitude()));
    }
}
