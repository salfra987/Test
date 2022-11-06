import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;
import java.net.HttpURLConnection;
import java.net.URL;

public class main {

  public static void main(String args[]) throws IOException {

    RainUpdater me = new RainUpdater(null, null, null, "0.5");
    me.check_again();
  }
}
