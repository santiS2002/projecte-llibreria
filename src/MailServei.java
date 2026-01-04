import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import io.github.cdimascio.dotenv.Dotenv;



import java.io.IOException;
public class MailServei {

    public static void enviarCorreuBenvinguda(String correuDestinatari, String nomUsuari) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");

        Email from = new Email("scrumbibliotecaservices@gmail.com");
        Email to = new Email(correuDestinatari);
        String subject = "Benvingut/da a la Biblioteca SCRUM";
        String cosHtml = "<html>" +
                "<body>" +
                "<h2 style='color: #2e6c80;'>Benvingut/da a la Biblioteca SCRUM</h2>" +
                "<p>Hola <b>" + nomUsuari + "</b>,</p>" +
                "<p>T'has registrat correctament al nostre sistema de gestió de llibres. " +
                "A partir d'ara podràs consultar el catàleg i fer préstecs online.</p>" +
                "<p>Si tens qualsevol dubte, respon a aquest correu.</p>" +
                "<hr>" +
                "<footer style='font-size: 0.8em;'>Aquest és un missatge automàtic de la Biblioteca de l'Institut.</footer>" +
                "</body>" +
                "</html>";

        Content content = new Content("text/html", cosHtml);


        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() == 202) {
                System.out.println(" Correu enviat correctament a " + correuDestinatari);
            } else {
                System.out.println(" Error enviant: " + response.getBody());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    public static void ferDiagnosticConfiguracio() {
        Dotenv dotenv = Dotenv.load();
        String sgKey = dotenv.get("API_KEY");

        System.out.println("\n--- DIAGNÒSTIC SENDGRID ---");
        if (sgKey == null || sgKey.isEmpty()) {
            System.err.println(" ERROR: No s'ha trobat API_KEY al .env");
        } else {
            System.out.println(" API_KEY trobada.");
        }
        System.out.println("---------------------------\n");
    }


    public static void enviarCorreuAlerta(String correuDestinatari, String nomUsuari) {
        Dotenv dotenv = Dotenv.load();
        String apiKey = dotenv.get("API_KEY");

        Email from = new Email("scrumbibliotecaservices@gmail.com");
        Email to = new Email(correuDestinatari);
        String subject = "ALERTA DE SEGURETAT: Intents de login fallits";

        String cosHtml = "<html><body style='font-family: Arial, sans-serif; color: #333;'>" +
                "<div style='background-color: #f8d7da; padding: 20px; border-radius: 5px; border: 1px solid #f5c6cb;'>" +
                "<h2 style='color: #721c24; margin-top: 0;'>Avís de seguretat important</h2>" +
                "<p>Hola <strong>" + nomUsuari + "</strong>,</p>" +
                "<p>S'han detectat <b>3 intents fallits de sessió</b> seguits al teu compte de la Biblioteca SCRUM.</p>" +
                "<p style='background-color: #ffffff; padding: 10px; border-left: 5px solid #721c24;'>" +
                "<strong>Estat:</strong> Compte bloquejat temporalment (30 segons).<br>" +
                "<strong>Acció recomanada:</strong> Si no has estat tu, et recomanem revisar la teva contrasenya.</p>" +
                "<p>Per seguretat, hem bloquejat nous intents des de la teva ubicació actual durant un breu període de temps.</p>" +
                "<hr><p style='font-size: 0.8em; color: #666;'>Aquest és un missatge automàtic de protecció del sistema SCRUM-Library.</p>" +
                "</div></body></html>";

        Content content = new Content("text/html", cosHtml);

        Mail mail = new Mail(from, subject, to, content);

        SendGrid sg = new SendGrid(apiKey);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            Response response = sg.api(request);

            if (response.getStatusCode() == 202) {
                System.out.println(" Correu enviat correctament a " + correuDestinatari);
            } else {
                System.out.println(" Error enviant: " + response.getBody());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }




}
