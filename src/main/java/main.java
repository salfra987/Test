import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class main {

  public static void main(String args[]) throws IOException {

    URL url =
        new URL(
            "http://api.weatherapi.com/v1/current.json?key=67ebbdcece194ee0942174811220511&q=Paris&aqi=no");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    System.out.println(con.getResponseMessage());
  }
}
