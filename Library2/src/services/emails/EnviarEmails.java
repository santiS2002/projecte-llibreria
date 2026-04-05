package services.emails;


import interfaces.EnviadoraEmails;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class EnviarEmails implements EnviadoraEmails {

    protected String destinatari;
    protected String assumpte;
    protected String motiu;
    protected  String usuariBiblio;
    protected String cosAmbHTMLBenvinguda;
    protected  String cosHTMLalerta;
    public EnviarEmails(String destinatari, String assumpte, String motiu, String usuariBiblio) {
        this.destinatari = destinatari;
        this.assumpte = assumpte;
        this.motiu = motiu;
        this.usuariBiblio = usuariBiblio;
        this.cosAmbHTMLBenvinguda = "<html>" +
                "<body>" +
                "<h2 style='color: #2e6c80;'>Benvingut/da a la Biblioteca SCRUM</h2>" +
                "<p>Hola <b>" + usuariBiblio + "</b>,</p>" +
                "<p>T'has registrat correctament al nostre sistema de gestió de llibres. " +
                "A partir d'ara podràs consultar el catàleg i fer préstecs online.</p>" +
                "<p>Si tens qualsevol dubte, respon a aquest correu.</p>" +
                "<hr>" +
                "<footer style='font-size: 0.8em;'>Aquest és un missatge automàtic de la Biblioteca de l'Institut.</footer>" +
                "</body>" +
                "</html>";


        this.cosHTMLalerta = "<html><body style='font-family: Arial, sans-serif; color: #333;'>" +
                "<div style='background-color: #f8d7da; padding: 20px; border-radius: 5px; border: 1px solid #f5c6cb;'>" +
                "<h2 style='color: #721c24; margin-top: 0;'>Avís de seguretat important</h2>" +
                "<p>Hola <strong>" + usuariBiblio + "</strong>,</p>" +
                "<p>S'han detectat <b>3 intents fallits de sessió</b> seguits al teu compte de la Biblioteca SCRUM.</p>" +
                "<p style='background-color: #ffffff; padding: 10px; border-left: 5px solid #721c24;'>" +
                "<strong>Estat:</strong> Compte bloquejat temporalment (30 segons).<br>" +
                "<strong>Acció recomanada:</strong> Si no has estat tu, et recomanem revisar la teva contrasenya.</p>" +
                "<p>Per seguretat, hem bloquejat nous intents des de la teva ubicació actual durant un breu període de temps.</p>" +
                "<hr><p style='font-size: 0.8em; color: #666;'>Aquest és un missatge automàtic de protecció del sistema SCRUM-Library.</p>" +
                "</div></body></html>";
    }




    public void crearEmail(String html){
        Properties propietat = new Properties();
        Properties propietatAUTH = new Properties();
        try{

            try(
                    InputStream SMTPserverProps = EnviarEmails.class.getClassLoader().getResourceAsStream("SMTP.properties");
                    InputStream inputStreamAUTH = EnviarEmails.class.getClassLoader().getResourceAsStream("auth.properties")

            ){
                System.out.println("carregant les propietats del smtp...");
                propietat.load(SMTPserverProps);
                System.out.println("carregant ls propietats d'autenticació...");
                propietatAUTH.load(inputStreamAUTH);

                final String emailEnviador = propietatAUTH.getProperty("mail.user.account");
                final String emailKey = propietatAUTH.getProperty("mail.app.password");

                Session sessio = Session.getInstance(propietat, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailEnviador, emailKey);
                    }
                });

                try{
                    Message missatgeAenviar = new MimeMessage(sessio);
                    missatgeAenviar.addRecipient(Message.RecipientType.TO, new InternetAddress(this.destinatari));
                    missatgeAenviar.setFrom(new InternetAddress(emailEnviador));
                    missatgeAenviar.setSubject( assumpte + " : " + this.motiu);

                    missatgeAenviar.setContent(html, "text/html; charset=utf-8");
                    System.out.println("Enviant l'email a l'usuari " + usuariBiblio + " que té el correu: " + this.destinatari + " desde la classe Enviar Emails...");
                    Transport.send(missatgeAenviar);
                    System.out.println("correu enviat correctament a: " + this.destinatari);



                } catch (AddressException e){
                    System.out.println("error en ficar l'adreça del recipient! revisar mètode enviar emails en la classe Enviar Emails!");
                }
                catch (MessagingException e){
                    System.out.println("El format del mime message no és correcte! revisar la classe enviar emails.java!");
                }

            } catch (IOException e){
                System.out.println("error en la classe EnviarEmails! " + e);

            }
        } catch (RuntimeException e){
            System.out.println("els inputStream són null! dona error. Revisar els files .properties.");
        }

    }
    @Override
    public void enviarEmailBenvinguda() {
        crearEmail(cosAmbHTMLBenvinguda);
    }

    @Override
    public void enviarCorreuAlerta() {
        crearEmail(cosHTMLalerta);
    }
}
