import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Utils {

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
        return airports;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(generateAirports().get(0).toString());
    }
}
