import com.opencsv.bean.CsvBindByName;
import java.io.Serializable;

public class Airport implements Serializable {

    // Datasource URL: https://ourairports.com/data/airports.csv.
    // Data Dictionary URL: https://ourairports.com/help/data-dictionary.html#airports
    @CsvBindByName(column = "id", required = true)
    private int ID;                     // Internal OurAirports integer identifier for the airport.

    @CsvBindByName
    private String ident;               // The text identifier used in the OurAirports URL. This will be the ICAO code if available. Otherwise, it will be a local airport code (if no conflict), or if nothing else is available, an internally-generated code starting with the ISO2 country code, followed by a dash and a four-digit number.

    @CsvBindByName
    private String type;                // The type of the airport. Allowed values are "closed_airport", "heliport", "large_airport", "medium_airport", "seaplane_base", and "small_airport".

    @CsvBindByName(required = true)
    private String name;                // The official airport name, including "Airport", "Airstrip", etc.

    @CsvBindByName(column = "latitude_deg", required = true)
    private double latitude;            // The airport latitude in decimal degrees (positive for north).

    @CsvBindByName(column = "longitude_deg", required = true)
    private double longitude;           // The airport longitude in decimal degrees (positive for east).

    @CsvBindByName(column = "elevation_ft")
    private int elevation;              // The airport elevation MSL in feet (not metres).

    @CsvBindByName
    private String continent;           // The code for the continent where the airport is (primarily) located. Allowed values are "AF" (Africa), "AN" (Antarctica), "AS" (Asia), "EU" (Europe), "NA" (North America), "OC" (Oceania), or "SA" (South America).

    @CsvBindByName(column = "iso_country")
    private String isoCountry;          // The two-character ISO 3166:1-alpha2 code for the country where the airport is (primarily) located. A handful of unofficial, non-ISO codes are also in use, such as "XK" for Kosovo.

    @CsvBindByName(column = "iso_region")
    private String isoRegion;           // An alphanumeric code for the high-level administrative subdivision of a country where the airport is primarily located (e.g. province, governorate), prefixed by the ISO2 country code and a hyphen. OurAirports uses ISO 3166:2 codes whenever possible, preferring higher administrative levels, but also includes some custom codes.

    @CsvBindByName
    private String municipality;        // The primary municipality that the airport serves (when available). Note that this is not necessarily the municipality where the airport is physically located.

    @CsvBindByName(column = "scheduled_service")
    private String scheduledService;    // "yes" if the airport currently has scheduled airline service; "no" otherwise.

    @CsvBindByName(column = "gps_code")
    private String GPSCode;             // The code that an aviation GPS database (such as Jeppesen's or Garmin's) would normally use for the airport. This will always be the ICAO code if one exists. Note that, unlike the ident column, this is not guaranteed to be globally unique.

    @CsvBindByName(column = "iata_code")
    private String iAtA;                // The three-letter IATA code for the airport (if it has one).

    @CsvBindByName(column = "local_code")
    private String localCode;           // The local country code for the airport, if different from the gps_code and iata_code fields (used mainly for US airports).

    @CsvBindByName(column = "home_link")
    private String homeLink;            // http://www.heathrowairport.com/	URL of the airport's official home page on the web, if one exists.

    @CsvBindByName(column = "wikipedia_link")
    private String wikipediaLink;       // https://en.wikipedia.org/wiki/Heathrow_Airport	URL of the airport's page on Wikipedia, if one exists.

    @CsvBindByName
    private String keywords;            // Extra keywords/phrases to assist with search, comma-separated. May include former names for the airport, alternate codes, names in other languages, nearby tourist destinations, etc.

    public Airport() {
    }

    public int getID() {
        return ID;
    }

    public String getIdent() {
        return ident;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public int getElevation() {
        return elevation;
    }

    public String getContinent() {
        return continent;
    }

    public String getIsoCountry() {
        return isoCountry;
    }

    public String getIsoRegion() {
        return isoRegion;
    }

    public String getMunicipality() {
        return municipality;
    }

    public String getScheduledService() {
        return scheduledService;
    }

    public String getGPSCode() {
        return GPSCode;
    }

    public String getiAtA() {
        return iAtA;
    }

    public String getLocalCode() {
        return localCode;
    }

    public String getHomeLink() {
        return homeLink;
    }

    public String getWikipediaLink() {
        return wikipediaLink;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setIdent(String ident) {
        this.ident = ident;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public void setIsoCountry(String isoCountry) {
        this.isoCountry = isoCountry;
    }

    public void setIsoRegion(String isoRegion) {
        this.isoRegion = isoRegion;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public void setScheduledService(String scheduledService) {
        this.scheduledService = scheduledService;
    }

    public void setGPSCode(String GPSCode) {
        this.GPSCode = GPSCode;
    }

    public void setiAtA(String iAtA) {
        this.iAtA = iAtA;
    }

    public void setLocalCode(String localCode) {
        this.localCode = localCode;
    }

    public void setHomeLink(String homeLink) {
        this.homeLink = homeLink;
    }

    public void setWikipediaLink(String wikipediaLink) {
        this.wikipediaLink = wikipediaLink;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    /**
     * toString method for Aircraft object.
     *
     * @return Name and Country of an airport in string form.
     */
    @Override
    public String toString() {
        return "Name: " + name + ", Country: " + isoCountry + '.';
    }
}
