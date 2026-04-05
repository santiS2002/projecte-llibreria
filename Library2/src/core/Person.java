package core;

public abstract class Person {
    private String mail;
    private String name;
    private String dni;
    private String password;

    public Person(String mail, String name, String dni, String password) {
        this.mail = mail;
        this.name = name;
        this.dni = dni;
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Person{" +
                "mail='" + mail + '\'' +
                ", name='" + name + '\'' +
                ", dni='" + dni + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
