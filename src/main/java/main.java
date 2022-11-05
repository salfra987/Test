import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.Buffer;
import java.util.HashMap;
import java.util.Map;

public class main {

  public static void main(String args[]) throws IOException {

    URL url =
        new URL(
            "http://api.weatherapi.com/v1/current.json?key=67ebbdcece194ee0942174811220511&q=Paris&aqi=no");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    for (int i = 0; i < 10; i++) {
      System.out.println(con.getHeaderField(i));
    }

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String line = null;
    while ((line = in.readLine()) != null) {
      System.out.println(line);
    }
  }
}
