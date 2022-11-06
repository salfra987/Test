import java.io.*;
import java.net.MalformedURLException;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;
import javax.mail.Session;
import javax.mail.Transport;
import java.net.HttpURLConnection;
import java.net.URL;

public class RainUpdater {
  public String location = "27514";
  public String date = "2023-05-15";
  public String recipient = null;
  public BufferedReader in;
  public URL url;
  public double checker = 0.5;

  public double initialY;

  public RainUpdater(String location, String date, String recipient, String checker) throws IOException {
      if (location != null){
          this.location = location;
      }
      if (date != null){
          this.date = date;
      }
      if (recipient == null) {
          this.recipient = "samsonseth449@gmail.com";
      } else{
          this.recipient = recipient;
      }
      if (checker != null) {
          this.checker = Double.parseDouble(checker);
      }


    url =
        new URL(
            "http://api.weatherapi.com/v1/future.json?key=67ebbdcece194ee0942174811220511&q="
                + this.location
                + "&dt="
                + this.date);
    HttpURLConnection con = (HttpURLConnection) url.openConnection();

    in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String line = null;
    line = in.readLine();

    String x = line.substring(line.indexOf("name") + 7, line.indexOf("region") - 3);
    String y = line.substring(line.indexOf("totalprecip_in") + 16, line.indexOf("avgvis_km") - 2);

    in.close();

    initialY = Double.parseDouble(y);

    String sender = "rainminder1@outlook.com";
    String host = "smtp-mail.outlook.com";

    Properties properties = System.getProperties();
    properties.put("mail.smtp.user", sender);
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");

    Session session =
        Session.getInstance(
            properties,
            new javax.mail.Authenticator() {
              protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("rainminder1@outlook.com", "RAINrain");
              }
            });
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(sender));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.recipient));
      message.setSubject("Welcome to RainMinder");
      message.setText(
          "The expected amount of rain in "
              + x
              + " on "
              + this.date
              + " is "
              + y
              + " inches. You will receive updates if there are any significant changes to the expected amount of rain.");

      Transport.send(message);
      System.out.println("Message sent successfully");
    } catch (MessagingException ex) {
      ex.printStackTrace();
    }
  }

  public void check_again() throws IOException {
      HttpURLConnection con = (HttpURLConnection) url.openConnection();

      in = new BufferedReader(new InputStreamReader(con.getInputStream()));
      String line = null;
      line = in.readLine();

      String x = line.substring(line.indexOf("name") + 7, line.indexOf("region") - 3);
      String y = line.substring(line.indexOf("totalprecip_in") + 16, line.indexOf("avgvis_km") - 2);

      in.close();

      double y2 = Double.parseDouble(y);

      if (y2 - initialY > checker || initialY - y2 < checker){
          initialY = y2;

          String sender = "rainminder1@outlook.com";
          String host = "smtp-mail.outlook.com";

          Properties properties = System.getProperties();
          properties.put("mail.smtp.user", sender);
          properties.put("mail.smtp.host", host);
          properties.put("mail.smtp.port", "587");
          properties.put("mail.smtp.starttls.enable", "true");
          properties.put("mail.smtp.auth", "true");

          Session session =
                  Session.getInstance(
                          properties,
                          new javax.mail.Authenticator() {
                              protected PasswordAuthentication getPasswordAuthentication() {
                                  return new PasswordAuthentication("rainminder1@outlook.com", "RAINrain");
                              }
                          });

          try {
              MimeMessage message = new MimeMessage(session);
              message.setFrom(new InternetAddress(sender));
              message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
              message.setSubject("EXPECTED RAINFALL HAS CHANGED!!!!!!!!!");
              message.setText(
                      "The expected amount of rain in "
                              + x
                              + " on "
                              + date
                              + " has changed since we last updated you. The new expected amount is "
                              + y
                              + " inches.");

              Transport.send(message);
              System.out.println("Message sent successfully");
          } catch (MessagingException ex) {
              ex.printStackTrace();
          }
      }

  }
}