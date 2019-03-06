import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class Notificator {

    static String bot = System.getenv("TG_NOTI_BOT");
    static long chat_id = Long.valueOf(System.getenv("TG_ADMIN_ID"));

    public static void sendToAdmin(String text) {
        try {
            sendPost(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void sendPost(String text) throws Exception {

        String url = "https://api.telegram.org/bot" + bot + "/sendMessage";
        URL obj = new URL(url);
        HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "chat_id=:CHAT&text=:TEXT".replace(":CHAT", String.valueOf(chat_id)).replace(":TEXT", text);


        try {
            // Send post request
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
        } catch (Exception e) {
            System.out.println("smth went wrong while sending notification");
        }


        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }
}
