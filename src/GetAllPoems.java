import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/* Collins Self 12/26/23
 * Grabs all the Virgil from this website: https://www.thelatinlibrary.com/verg.html
 * And then converts it to a .txt file WITHOUT THE pesky line numbers
 */
public class GetAllPoems {
    public static void main(String[] args) {
        private static final String USER_AGENT = "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 " +
                "(KHTML, like Gecko) Chrome/120.0.0.0 Mobile Safari/537.36";
        private static final String URL = "https://" + "example.com";

        public static void SendGET() throws IOException {
            java.net.URL url = new URL(URL);
            // A URLConnection() is made, and cast as an HttpsURLConnection
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // NOTE: GET is also the default method
            connection.setRequestProperty("User-Agent", USER_AGENT);
            int resCode = connection.getResponseCode();
            System.out.println("GET Response code : " + resCode);

            if (resCode == HttpsURLConnection.HTTP_OK) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) { // line read is not null
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response);
            } else {
                System.out.println("GET request did not work.");
            }
        }

        public static void main(String[] args) throws IOException {
            SendGET();
        }
    }
    }
}
