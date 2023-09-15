package osoba;

import java.util.Objects;

public class Korisnik extends Osoba {
    private String username,password;
    private double stanjeNaRacunu;
    private static int brojKorisnika=0;
    
    public Korisnik(String ime, String prezime, int godine, String username, String password,double stanjeNaRacunu) {
        super(ime, prezime, godine);
        this.username=username;
        this.password=password;
        if(stanjeNaRacunu>0)
        this.stanjeNaRacunu=stanjeNaRacunu;
        else this.stanjeNaRacunu=0;
        brojKorisnika++;
    }
    
    public void ispis(){
        System.out.println("\t\t==============================");
        System.out.println("\t\t     Ime     : " + this.getIme()  );
        System.out.println("\t\t     Prezime : " + this.getPrezime() );
        System.out.println("\t\t     Godine  : " + this.getGodine());
        System.out.println("\t\t     Stanje  : " + this.getStanjeNaRacunu() + " RSD");
        System.out.println("\t\t==============================");
    
    }
    
    public static int getBrojKorisnika(){
        return brojKorisnika;
    }

    public static void setBrojKorisnika(int brojKorisnika) {
        Korisnik.brojKorisnika = brojKorisnika;
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

    public double getStanjeNaRacunu() {
        return stanjeNaRacunu;
    }

    public void setStanjeNaRacunu(double stanjeNaRacunu) {
        this.stanjeNaRacunu = stanjeNaRacunu;
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
       
    @Override
    public String toString() {
        return "\t\t===========================\n\t\t"+
                super.toString() +
                "\n\t\tusername: " + username +
                "\n\t\tpassword: " + password +
                "\n\t\tstanjeNaRacunu: " + stanjeNaRacunu+
                "\n\t\t===========================\n";
    }
    
}
