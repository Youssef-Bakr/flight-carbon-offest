import java.net.URL;
import javax.print.attribute.standard.MediaSize;
import javax.print.attribute.standard.MediaSize.ISO;

public class Airport {

    // Datasource URL: https://ourairports.com/data/airports.csv. (\N represents a null value).
    // Data Dictionary URL: https://ourairports.com/help/data-dictionary.html#airports
    private int ID;                     // Internal OurAirports integer identifier for the airport.
    private String ident;               // The text identifier used in the OurAirports URL. This will be the ICAO code if available. Otherwise, it will be a local airport code (if no conflict), or if nothing else is available, an internally-generated code starting with the ISO2 country code, followed by a dash and a four-digit number.
    private String type;                // The type of the airport. Allowed values are "closed_airport", "heliport", "large_airport", "medium_airport", "seaplane_base", and "small_airport".
    private String name;                // The official airport name, including "Airport", "Airstrip", etc.
    private double latitude;            // The airport latitude in decimal degrees (positive for north).
    private double longitude;           // The airport longitude in decimal degrees (positive for east).
    private int elevation;              // The airport elevation MSL in feet (not metres).
    private String continent;           // The code for the continent where the airport is (primarily) located. Allowed values are "AF" (Africa), "AN" (Antarctica), "AS" (Asia), "EU" (Europe), "NA" (North America), "OC" (Oceania), or "SA" (South America).
    private String isoCountry;          // The two-character ISO 3166:1-alpha2 code for the country where the airport is (primarily) located. A handful of unofficial, non-ISO codes are also in use, such as "XK" for Kosovo.
    private String isoRegion;           // An alphanumeric code for the high-level administrative subdivision of a country where the airport is primarily located (e.g. province, governorate), prefixed by the ISO2 country code and a hyphen. OurAirports uses ISO 3166:2 codes whenever possible, preferring higher administrative levels, but also includes some custom codes.
    private String municipality;        // The primary municipality that the airport serves (when available). Note that this is not necessarily the municipality where the airport is physically located.
    private String scheduledService;    // "yes" if the airport currently has scheduled airline service; "no" otherwise.
    private String GPSCode;             // The code that an aviation GPS database (such as Jeppesen's or Garmin's) would normally use for the airport. This will always be the ICAO code if one exists. Note that, unlike the ident column, this is not guaranteed to be globally unique.
    private String iAtA;                // The three-letter IATA code for the airport (if it has one).
    private String localCode;           // The local country code for the airport, if different from the gps_code and iata_code fields (used mainly for US airports).
    private URL homeLink;	            // http://www.heathrowairport.com/	URL of the airport's official home page on the web, if one exists.
    private URL wikipediaLink;	        // https://en.wikipedia.org/wiki/Heathrow_Airport	URL of the airport's page on Wikipedia, if one exists.
    private String keywords;	                // Extra keywords/phrases to assist with search, comma-separated. May include former names for the airport, alternate codes, names in other languages, nearby tourist destinations, etc.

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
        this.ident = ident;
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
