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



    URL url = new URL(
            "http://api.weatherapi.com/v1/future.json?key=67ebbdcece194ee0942174811220511&q=27514&dt=2023-05-15");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    String line = null;
    line = in.readLine();

    String x = line.substring(line.indexOf("name")+7, line.indexOf("region")-3);
    String y = line.substring(line.indexOf("totalprecip_in")+16, line.indexOf("avgvis_km")-2);

    in.close();

    System.out.println(x + y);

    String recipient = "samsonseth449@gmail.com";
    String sender = "rainminder1@outlook.com";
    String host = "smtp-mail.outlook.com";

    Properties properties = System.getProperties();
    properties.put("mail.smtp.user", sender);
    properties.put("mail.smtp.host", host);
    properties.put("mail.smtp.port", "587");
    properties.put("mail.smtp.starttls.enable", "true");
    properties.put("mail.smtp.auth", "true");

    Session session = Session.getInstance(properties, new javax.mail.Authenticator(){
      protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication("rainminder1@outlook.com", "RAINrain");
      }
    });
    try {
      MimeMessage message = new MimeMessage(session);
      message.setFrom(new InternetAddress(sender));
      message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
      message.setSubject("This is the Subject");
      message.setText("Testing the email");

      Transport.send(message);
      System.out.println("Message sent successfully");
    } catch (MessagingException ex){
      ex.printStackTrace();
    }
  }
}
