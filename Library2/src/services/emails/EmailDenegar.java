package services.emails;

public class EmailDenegar extends EnviarEmails implements interfaces.EnviadoraEmailsDenegar {
     protected String htmlDenegar;

    public EmailDenegar(String destinatari, String assumpte, String motiu, String usuariBiblio) {
        super(destinatari, assumpte, motiu, usuariBiblio);

        //this.htmlDenegar = "";
    }

    @Override
    public void enviarCorreuDenegacio() {
        //super.crearEmail(htmlDenegar); --> mètode crear email amb un html de denegació. per exemple: es denega el préstec perque no hi ha en llibre demanat etc.
    }

}
