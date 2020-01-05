public class Airport {

    // Datasource URL: https://raw.githubusercontent.com/jpatokal/openflights/master/data/airports.dat. (\N represents a null value).
    private int airportID;              // Unique OpenFlights identifier for this airport.
    private String name;                // Name of airport. May or may not contain the City name.
    private String city;                // Main city served by airport. May be spelled differently from Name.
    private String country;             // Country or territory where airport is located.
    private String iAtA;                // 3 Letter IATA code. Null if not assigned/unknown.
    private String iCaO;                // 4-letter ICAO code. Null if not assigned.
    private double latitude;            // Decimal degrees, usually to six significant digits. Negative is South, positive is North.
    private double longitude;           // Decimal degrees, usually to six significant digits. Negative is West, positive is East.
    private int altitude;               // Altitude in feet.
    // private double timezone;         // Hours offset from UTC. Fractional hours are expressed as decimals, eg. India is 5.5.
    // private String dSt;              // Daylight savings time. One of E (Europe), A (US/Canada), S (South America), O (Australia), Z (New Zealand), N (None) or U (Unknown). See also: Help: Time
    // private String databaseTimeZone; // Database time zone. Timezone in "tz" (Olson) format, eg. "America/Los_Angeles".
    private String type;                // Type of the airport. Value "airport" for air terminals, "station" for train stations, "port" for ferry terminals and "unknown" if not known. In airports.csv, only type=airport is included.
    private String source;              // Source of this data. "OurAirports" for data sourced from OurAirports, "Legacy" for old data not matched to OurAirports (mostly DAFIF), "User" for unverified user contributions. In airports.csv, only source=OurAirports is included.
    private String combinedIdentifier;  // A user friendly concatenation of iAtA + name & country variables.

    /**
     * Airport constructor.
     *
     * @param airportID     Unique OpenFlights identifier for this airport.
     * @param name          Name of airport. May or may not contain the City name.
     * @param city          Main city served by airport. May be spelled differently from Name.
     * @param country       Country or territory where airport is located.
     * @param iAtA          3 Letter IATA code. Empty string if not assigned/unknown.
     * @param iCaO          4-letter ICAO code. Empty string if not assigned.
     * @param latitude      Decimal degrees, usually to six significant digits. Negative is South, positive is North.
     * @param longitude     Decimal degrees, usually to six significant digits. Negative is West, positive is East.
     * @param altitude      Altitude in feet.
     * @param type          Type of the airport. Value "airport" for air terminals, "station" for train stations, "port" for ferry terminals and "unknown" if not known.
     * @param source        Source of this data. "OurAirports" for data sourced from OurAirports, "Legacy" for old data not matched to OurAirports (mostly DAFIF), "User" for unverified user contributions.
     */
    public Airport(int airportID, String name, String city, String country, String iAtA,
        String iCaO, double latitude, double longitude, int altitude, String type, String source) {
        this.airportID = airportID;
        this.name = name;
        this.city = city;
        this.country = country;
        this.iAtA = iAtA;
        this.iCaO = iCaO;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
        this.type = type;
        this.source = source;
        this.combinedIdentifier = iAtA + " " + name + " [" + country + ']';
    }

    public int getAirportID() {
        return airportID;
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getiAtA() {
        return iAtA;
    }

    public String getiCaO() {
        return iCaO;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getAltitude() {
        return altitude;
    }

    public String getType() {
        return type;
    }

    public String getSource() {
        return source;
    }

    public String getCombinedIdentifier() {
        return combinedIdentifier;
    }

    /**
     * toString method for Aircraft object.
     *
     * @return Name, City and Country of an airport in string form.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", City: " + city + ", Country: " + country + '.';
    }
}
