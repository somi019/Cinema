package osoba;
public class Admin extends Osoba{
    private String username,password;

    public Admin(String username, String password, String ime, String prezime, int godine) {
        super(ime, prezime, godine);
        this.username = username;
        this.password = password;
    }
     @Override
    public String toString() {
        return "Admin{" +super.toString() +" username=" + username + ", password=" + password +'}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public int getGodine() {
        return godine;
    }

    public void setGodine(int godine) {
        this.godine = godine;
    }
    

}
