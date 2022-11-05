import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

  public static void Main() throws IOException {
   /* URL url = new URL("http://api.weatherapi.com/v1");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");*/

      URL url = new URL("http://api.weatherapi.com/v1/current.json?key=67ebbdcece194ee0942174811220511&q=London&aqi=no");
      HttpURLConnection con = (HttpURLConnection) url.openConnection();
      con.setRequestMethod("GET");
        }
}
