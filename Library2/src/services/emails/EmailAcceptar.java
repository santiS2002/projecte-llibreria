package services.emails;


import interfaces.EnviadoraEmailsAcceptar;

public class EmailAcceptar extends EnviarEmails implements EnviadoraEmailsAcceptar {
    protected String htmlAcceptar;

    public EmailAcceptar(String destinatari, String assumpte, String motiu, String usuariBiblio) {
        super(destinatari, assumpte, motiu, usuariBiblio);
        //this.htmlAcceptar = "";

    }

    @Override
    public void enviarCorreuAcceptacio() {
        //super.crearEmail(htmlAcceptar); --> mètode crear email amb un html d'acceptació. per exemple: es denega el préstec perque no hi ha en llibre demanat etc.

    }
}
