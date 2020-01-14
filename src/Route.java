import com.opencsv.bean.CsvBindByPosition;

public class Route {

    @CsvBindByPosition(position = 0, required = true)
    private String iAtA;                    // Airline  2-letter (IATA) or 3-letter (ICAO) code of the airline.

    @CsvBindByPosition(position = 1, required = true)
    private int airlineID;                  // Airline ID   Unique OpenFlights identifier for airline (see Airline).

    @CsvBindByPosition(position = 2, required = true)
    private String sourceAirportIaTa;       // Source airport   3-letter (IATA) or 4-letter (ICAO) code of the source airport.

    @CsvBindByPosition(position = 3, required = true)
    private int sourceAirportID;            // Source airport ID    Unique OpenFlights identifier for source airport (see Airport)

    @CsvBindByPosition(position = 4, required = true)
    private String destinationAirportIaTa;  // Destination airport  3-letter (IATA) or 4-letter (ICAO) code of the destination airport.

    @CsvBindByPosition(position = 5, required = true)
    private int destinationAirportID;       // Destination airport  ID	Unique OpenFlights identifier for destination airport (see Airport)

    @CsvBindByPosition(position = 6, required = true)
    private String codeshare;               // Codeshare    "Y" if this flight is a codeshare (that is, not operated by Airline, but another carrier), empty otherwise.

    @CsvBindByPosition(position = 7, required = true)
    private int stops;                      // Stops    Number of stops on this flight ("0" for direct)

    @CsvBindByPosition(position = 8, required = true)
    private String equipment;               // Equipment    3-letter codes for plane type(s) generally used on this flight, separated by spaces

    public Route() {
    }

    public String getiAtA() {
        return iAtA;
    }

    public int getAirlineID() {
        return airlineID;
    }

    public String getSourceAirportIaTa() {
        return sourceAirportIaTa;
    }

    public int getSourceAirportID() {
        return sourceAirportID;
    }

    public String getDestinationAirportIaTa() {
        return destinationAirportIaTa;
    }

    public int getDestinationAirportID() {
        return destinationAirportID;
    }

    public String getCodeshare() {
        return codeshare;
    }

    public int getStops() {
        return stops;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setiAtA(String iAtA) {
        this.iAtA = iAtA;
    }

    public void setAirlineID(int airlineID) {
        this.airlineID = airlineID;
    }

    public void setSourceAirportIaTa(String sourceAirportIaTa) {
        this.sourceAirportIaTa = sourceAirportIaTa;
    }

    public void setSourceAirportID(int sourceAirportID) {
        this.sourceAirportID = sourceAirportID;
    }

    public void setDestinationAirportIaTa(String destinationAirportIaTa) {
        this.destinationAirportIaTa = destinationAirportIaTa;
    }

    public void setDestinationAirportID(int destinationAirportID) {
        this.destinationAirportID = destinationAirportID;
    }

    public void setCodeshare(String codeshare) {
        this.codeshare = codeshare;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }
}
