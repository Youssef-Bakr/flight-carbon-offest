import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Airport {

    // Datasource URL: https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat

    private int airportID;              // Unique OpenFlights identifier for this airport.
    private String name;                // Name of airport. May or may not contain the City name.
    private String city;                // Main city served by airport. May be spelled differently from Name.
    private String country;             // Country or territory where airport is located. See countries.dat to cross-reference to ISO 3166-1 codes.
    private String iAtA;                // 3 Letter IATA code. Null if not assigned/unknown.
    private String iCaO;                // 4-letter ICAO code. Null if not assigned.
    private double latitude;            // Decimal degrees, usually to six significant digits. Negative is South, positive is North.
    private double longitude;           // Decimal degrees, usually to six significant digits. Negative is West, positive is East.
    private int altitude;               // Altitude in feet.
    private double timezone;            // Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5.
    private String dSt;                 // Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U (Unknown). See also: Help: Time
    private String databaseTimeZone;    // Database time zone. Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".
    private String type;                // Type of the airport. Value "airport" for air terminals, "station" for train stations, "port" for ferry terminals and "unknown" if not known. In airports.csv, only type=airport is included.
    private String source;              // Source of this data. "OurAirports" for data sourced from OurAirports, "Legacy" for old data not matched to OurAirports (mostly DAFIF), "User" for unverified user contributions. In airports.csv, only source=OurAirports is included.

    public Airport(int airportID, String name, String city, String country, String iAtA,
        String iCaO, double latitude, double longitude, int altitude, double timezone,
        String dSt, String databaseTimeZone, String type, String source) {
        this.airportID = airportID;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iAtA = iAtA;
        this.iCaO = iCaO;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.timezone = timezone;
        this.dSt = dSt;
        this.databaseTimeZone = databaseTimeZone;
        this.type = type;
        this.source = source;
    }

    public static ArrayList<Airport> generateAirports() throws IOException {

        URL url = new URL(
            "https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat");
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));
        Scanner scanner = new Scanner(br);
        ArrayList<Airport> airports = new ArrayList<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] splitLine = line.replace("\"", "").split(","); // TODO: fix split where , i legitimate e.g. airportID 641
            airports.add(new Airport(Integer.parseInt(splitLine[0]), splitLine[1], splitLine[2],
                splitLine[3], splitLine[4], splitLine[5], Double.parseDouble(splitLine[6]),
                Double.parseDouble(splitLine[7]), Integer.parseInt(splitLine[8]),
                Double.parseDouble(splitLine[9]), splitLine[10], splitLine[11], splitLine[12],
                splitLine[13]));
        }
        return airports;
    }

    public static void main(String[] args) throws IOException {
        generateAirports();
    }
}
