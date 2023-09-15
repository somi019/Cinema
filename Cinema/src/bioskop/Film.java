
package bioskop;

import java.util.ArrayList;
import osoba.Korisnik;

public class Film implements RezervacijaKarte{
    private String naziv,zanr;
    private double trajanje;
    private double cena;
    private ArrayList<Termin> termini;

    public Film(String naziv, String zanr, double trajanje, double cena, ArrayList<Termin> termini) {
        this.naziv = naziv;
        this.zanr = zanr;
        this.trajanje = trajanje;
        this.cena = cena;
        this.termini = termini;
    }

    
    
    
    public Film(String naziv,String zanr, double trajanje,double cena) {
        this.naziv = naziv;
        this.zanr=zanr;
        this.trajanje=trajanje;
        this.cena=cena;
        this.termini = new ArrayList<>();
    }

    
     @Override
    public void rezervisiKartu(int brojTermina, int brojKarata, Korisnik korisnik) {
        double stanje = korisnik.getStanjeNaRacunu();
        int slobodnaMesta=termini.get(brojTermina-1).getSlobodnaMesta();
        String sat_str=termini.get(brojTermina-1).getVreme().substring(0, 2);
        int sat= Integer.parseInt(sat_str);
        if(slobodnaMesta >= brojKarata ){
            if(stanje >= (   brojKarata*this.cena )){
            slobodnaMesta-=brojKarata;
            termini.get(brojTermina-1).setSlobodnaMesta(slobodnaMesta);
            korisnik.setStanjeNaRacunu(stanje-brojKarata*this.cena);
            System.out.println("\t\tUspesno ste rezervisali termin!");
            
            }else{
                System.out.println("\t\t#####################################################");
                System.out.println("\t\tNemate dovoljno novca na racunu!");
                System.out.println("\t\t#####################################################");
            }
        }else{
            System.out.println("\t\t#####################################################");
            System.out.println("\t\tNema dovoljno slobodnih mesta!");
            System.out.println("\t\t#####################################################");
        }
        
    }
    
   
    
    @Override
    public String toString() {
        return naziv + " (" + zanr + ")," + Math.round(trajanje) + " min  - "+cena + " RSD";
    }
    

    public String getNaziv() {
        return naziv;
    }

    public ArrayList<Termin> getTermini() {
        return termini;
    }

    public void dodajTermin(Termin termin) {
       try{
        for (Termin t : termini) {
            if (t.getDatum().equals(termin.getDatum()) &&
                    t.getVreme().equals(termin.getVreme()) ) {
                throw new PostojiTerminException("\t\tTermin vec postoji!");
            }
        }
        termini.add(termin);
       }
    catch (PostojiTerminException e){
            System.out.println(e.getMessage());
        }
}
     public void dodajViseTermina(ArrayList<Termin> termin_List) {
        
      for (Termin termin : termin_List) {
       try{
        for (Termin t : termini) {
            if (t.getDatum().equals(termin.getDatum()) &&
                    t.getVreme().equals(termin.getVreme()) ) {
                throw new PostojiTerminException("\t\tTermin vec postoji!");
            }
        }
        termini.add(termin);
       }
        catch (PostojiTerminException e){
            System.out.println(e.getMessage());
        }
       
       }
}

    public void obrisiTermin(String datum, String vreme) {
            boolean obrisan=false;
                for (int i = 0; i < termini.size(); i++) {
                    if (termini.get(i).getDatum().equals(datum) &&
                    termini.get(i).getVreme().equals(vreme) )
                    {
                        obrisan=true;
                        termini.remove(i);
                        i--;
                    }
                }
               if(obrisan){
                   System.out.println("\t\tTermin uspesno obrisan!");
               }else System.out.println("\t\tNe postoji dati termin za film "+ naziv);
    }

    public String getZanr() {
        return zanr;
    }

    public void setZanr(String zanr) {
        this.zanr = zanr;
    }

    public double getTrajanje() {
        return trajanje;
    }

    public void setTrajanje(double trajanje) {
        this.trajanje = trajanje;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }

   
    
    
    
}
