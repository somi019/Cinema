package bioskop;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import osoba.Korisnik;
import osoba.Osoba;

public abstract class Prozor {
    public static void ispisKorisnik(){
    
        System.out.println("\t\t|---------------------------|");
        System.out.println("\t\t|1. Prikaz filmova          |");
        System.out.println("\t\t|---------------------------|");
        System.out.println("\t\t|2. Rezervisi kartu         |");
        System.out.println("\t\t|---------------------------|");
        System.out.println("\t\t|3. Stanje na racunu        |");
        System.out.println("\t\t|---------------------------|");
        System.out.println("\t\t|4. Odjava                  |");
        System.out.println("\t\t|---------------------------|");
        System.out.println("\t\t|5. Izlaz                   |");
        System.out.println("\t\t|---------------------------|");
        System.out.print("\t\t Izaberite opciju:");
        
    }
   public static void ispisAdmin(){
   
       System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|     Dobrodosao, admine!   |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|1. Prikazi filmove         |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|2. Dodaj film              |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|3. Obrisi film             |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|4. Prikazi termine         |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|5. Dodaj termin            |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|6. Obrisi termin           |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|7. Izlistaj korisnike      |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|8. Dodaj korisnika         |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|9. Obrisi korisnika        |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|10. Odjava                 |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.println("\t\t|11. Izlaz                  |");
                            System.out.println("\t\t|---------------------------|");
                            System.out.print("\t\t Izaberite opciju:");
   
   }
   
   public static void pisiGresku(String greska){
       System.out.println("\t\t#####################################################");
       System.out.println("\t\t"+greska);
       System.out.println("\t\t#####################################################");
   
   
   }
   
   public static void RezervacijaKarte(Bioskop bioskopVozdovac, Korisnik korisnik){
                                    Scanner scanner = new Scanner(System.in);
                                    System.out.print("\t\tUnesite naziv filma: ");
                                    String naziv=scanner.nextLine();
                                    boolean postojiFilm=false;
                                    for (Film film : bioskopVozdovac.getFilmovi()) {
                                        if(film.getNaziv().equals(naziv)) postojiFilm=true;
                                    }
                                    if(postojiFilm){
                                   bioskopVozdovac.izlistajTermine(naziv);
                                    Film film=bioskopVozdovac.nadjiFilmPoNazivu(naziv);
                                    System.out.print("\n\t\tUnesite broj ispred termina koji zelite: ");
                                    String unos = scanner.nextLine();
                                    int brojTermina=0;
                                    boolean preskok=false;
                                    try {
                                         brojTermina = Integer.parseInt(unos);
                                         if(brojTermina>film.getTermini().size() || brojTermina<=0){
                                        preskok=true;
                                        Prozor.pisiGresku("Broj termina je pogresan!");
                                        }
                                    } catch (NumberFormatException e) {
                                        Prozor.pisiGresku("Neispravan broj termina!");
                                        preskok=true;
                                    }
                                    
                                    if(!preskok ){

                                    System.out.print("\n\t\tUnesite broj karata koje zelite da kupite: ");
                                    unos = scanner.nextLine();
                                        
                                    int brojKarata=0;
                                    try {
                                         brojKarata = Integer.parseInt(unos);
                                    } catch (NumberFormatException e) {
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tNeispravan broj karata!");
                                        System.out.println("\t\t#####################################################");
                                    }
                                    if(brojTermina > 0 && brojTermina<=film.getTermini().size() && brojKarata > 0 ){
                                            film.rezervisiKartu(brojTermina, brojKarata, korisnik);
                                    }
                                    else{
                                            System.out.println("\t\t#####################################################");
                                            System.out.println("\t\tBroj termina koji ste uneli ne postoji!");
                                            System.out.println("\t\t#####################################################");
                                        }
                                    }
                                    } else {
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tFilm koji ste uneli nemamo u repertoaru!");
                                        System.out.println("\t\t#####################################################");
                                    }
   
   }
   
   public static void dodavanjeFilma(Bioskop bioskopVozdovac){
                                    Scanner scanner= new Scanner(System.in);
                                    System.out.print("\t\t Unesi naziv filma: ");
                                    String ime= scanner.nextLine();
                                    System.out.print("\t\t Unesi zanr filma: ");
                                    String zanr= scanner.nextLine();
                                    System.out.print("\t\t Unesi trajanje filma: ");
                                    String unos = scanner.nextLine();
                                    double trajanje=0;
                                    boolean preskok=false;
                                    try {
                                         trajanje = Double.parseDouble(unos);
                                         if(trajanje <=0) throw new NeispravnoTrajanjeException();
                                    } catch (NumberFormatException e) {
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tNeispravno trajanje filma!");
                                        System.out.println("\t\t#####################################################");
                                            preskok=true;
                                    } catch (NeispravnoTrajanjeException ex) {
            Prozor.pisiGresku("Neispravno trajanje filma!");
            preskok=true;
        }
                                    if(!preskok){
                                        try{
                                            System.out.print("\t\t Unesi cenu filma: ");
                                            String cena_str = scanner.nextLine();
                                            double cena=Double.parseDouble(cena_str);
                                            if(cena<=0) throw new NeispravnaCenaException();
                                            bioskopVozdovac.dodajFilm(new Film(ime,zanr,trajanje,cena));
                                        }catch (NumberFormatException e) {
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tNeispravna cena filma!");
                                        System.out.println("\t\t#####################################################");
                                        } catch (NeispravnaCenaException ex) {
                                            Prozor.pisiGresku("Neispravna cena filma!");
                                        }
                                    }
   
   
   }
   
   public static void brisanjeFilma(Bioskop bioskopVozdovac){
                                    Scanner scanner= new Scanner(System.in);
                                    System.out.print("\t\t Unesi naziv filma: ");
                                    String ime = scanner.nextLine();
                                    if(bioskopVozdovac.nadjiFilmPoNazivu(ime)!= null){
                                    bioskopVozdovac.izbrisiFilm(ime);
                                    System.out.println("\t\tUspesno ste obrisali film: "+ ime);
                                    }
                                    else{
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tFilm koji ste uneli nemamo u repertoaru!");
                                        System.out.println("\t\t#####################################################");
                                    }

   
   
   }
   public static void prikazTermina(Bioskop bioskopVozdovac){
                                    Scanner scanner = new Scanner(System.in);
                                    System.out.print("\t\tUnesite naziv filma: ");
                                    String naziv=scanner.nextLine();
                                    boolean postojiFilm=false;
                                    for (Film film : bioskopVozdovac.getFilmovi()) {
                                        if(film.getNaziv().equals(naziv)) postojiFilm=true;
                                    }
                                    if(postojiFilm){
                                        bioskopVozdovac.izlistajTermine(naziv);
                                    }else{
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tFilm koji ste uneli nemamo u repertoaru!");
                                        System.out.println("\t\t#####################################################");
                                    }
   
   }
   public static void dodavanjeTermina(Bioskop bioskopVozdovac){
                                    Scanner scanner = new Scanner(System.in);
                                    System.out.print("\t\t Unesi naziv filma: ");
                                    String ime = scanner.nextLine();
                                    Film film=bioskopVozdovac.nadjiFilmPoNazivu(ime);
                                    if(film!=null){
                                        // public Termin(String datum, String vreme, int slobodnaMesta)
                                      try{
                                        System.out.print("\t\t Unesi datum termina(u formatu dd.mm.yyyy. ): ");
                                        String datum= scanner.nextLine();
                                        if(!proveriValidnostDatuma(datum)) throw new NeispravanDatumException();
                                        System.out.print("\t\t Unesi vreme termina(u formatu hh:mm )");
                                        String vreme= scanner.nextLine();
                                        if(!proveriValidnostVremena(vreme)) throw new NeispravnoVremeException();
                                            System.out.print("\t\t Unesi slobodna mesta za termin: ");
                                            String slobodnaMesta_str = scanner.nextLine();
                                            int slobodnaMesta=Integer.parseInt(slobodnaMesta_str);
                                            if(slobodnaMesta>0)
                                            film.dodajTermin(new Termin(datum,vreme,slobodnaMesta));
                                            else {
                                                System.out.println("\t\t#####################################################");
                                                System.out.println("\t\tBroj slobodnih mesta mora biti veci od 0!");
                                                System.out.println("\t\t#####################################################");
                                            }
                                        }
                                      catch(NeispravnoVremeException e){
                                          Prozor.pisiGresku("Neispravno vreme!");
                                      }
                                      catch(NeispravanDatumException e){
                                            Prozor.pisiGresku("Neispravan datum!");
                                      }
                                      catch (NumberFormatException e) {
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tNeispravan broj za slobodna mesta!");
                                        System.out.println("\t\t#####################################################");
                                        }
                                    }
                                    else{
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tFilm koji ste uneli nemamo u repertoaru!");
                                        System.out.println("\t\t#####################################################");
                                    }
   
   }

    public static void brisanjeTermina(Bioskop bioskopVozdovac) {
                                    Scanner scanner = new Scanner(System.in);
                                    System.out.print("\t\t Unesi naziv filma: ");
                                    String ime = scanner.nextLine();
                                    Film film=bioskopVozdovac.nadjiFilmPoNazivu(ime);
                                    if(film!=null){
                                        // public Termin(String datum, String vreme, int slobodnaMesta)
                                        System.out.print("\t\t Unesi datum termina: ");
                                        String datum= scanner.nextLine();
                                        System.out.print("\t\t Unesi vreme termina: ");
                                        String vreme= scanner.nextLine();
                                        film.obrisiTermin(datum,vreme);
                                    }
                                    else{
                                        System.out.println("\t\t#####################################################");
                                        System.out.println("\t\tFilm koji ste uneli nemamo u repertoaru!");
                                        System.out.println("\t\t#####################################################");
                                    }
    }
/**/
 

    private static boolean proveriValidnostDatuma(String datum) {
        boolean provera=false;
            
            if(datum.length()==11){
                String[] nizDatum=datum.split("\\.");
                //System.out.println("Duzina nizDatum: " + nizDatum.length);
                if(nizDatum.length==3){
                    if(nizDatum[0].length()==2 && nizDatum[1].length()==2 && nizDatum[2].length()==4){
                       for (String string : nizDatum) {
                            for (int i = 0; i < string.length(); i++) {
                               if(!Character.isDigit(string.charAt(i))) return false;
                           }
                        }
                       String dan = nizDatum[0];
                       String mesec = nizDatum[1];
                       
                       int dan_int = (dan.charAt(0) - '0')*10 + (dan.charAt(1) - '0');
                       int mesec_int = (mesec.charAt(0) - '0')*10 + (mesec.charAt(1) - '0');
                      switch (mesec_int) {
                            case 1:
                                if(dan_int>=1 && dan_int<=31) provera = true;
                                break;
                            case 2:
                                if(dan_int>=1 && dan_int<=29) provera = true;
                                break;
                            case 3:
                                if(dan_int>=1 && dan_int<=31) provera = true;
                                break;
                            case 4:
                                if(dan_int>=1 && dan_int<=30) provera = true;
                                break;
                            case 5:
                                if(dan_int>=1 && dan_int<=31) provera = true;
                                break;
                            case 6:
                                if(dan_int>=1 && dan_int<=30) provera = true;
                                break;
                            case 7:
                                if(dan_int>=1 && dan_int<=31) provera = true;
                                break;
                            case 8:
                                if(dan_int>=1 && dan_int<=31) provera = true;
                                break;
                            case 9:
                                if(dan_int>=1 && dan_int<=30) provera = true;
                                break;
                            case 10:
                                if(dan_int>=1 && dan_int<=31) provera = true;
                                break;
                            case 11:
                                if(dan_int>=1 && dan_int<=30) provera = true;
                                break;
                            case 12:
                                if(dan_int>=1 && dan_int<=31) provera = true;
                                break;
                            default:
                                return false;
                        }
                    }
                }
            }
        
        return provera;
    }

    private static boolean proveriValidnostVremena(String vreme) {
        boolean provera = false;
            if(vreme.length()==5){
                String []nizVreme=vreme.split(":");
                if(nizVreme.length==2){
                    for (String string : nizVreme) {
                            for (int i = 0; i < string.length(); i++) {
                               if(!Character.isDigit(string.charAt(i))) return false;
                           }
                        }
                       String sat = nizVreme[0];
                       String minut = nizVreme[1];
                       
                       int sat_int = (sat.charAt(0) - '0')*10 + (sat.charAt(1) - '0');
                       int minut_int = (minut.charAt(0) - '0')*10 + (minut.charAt(1) - '0');
                       
                       if(sat_int>=0 && sat_int<=23 && minut_int>=0 && minut_int<=59)
                           provera = true;
                }
            }
        return provera;
    }
    
    
       public static void dodavanjeKorisnika(ArrayList<Osoba> listaOsoba1) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\t\t Unesi ime korisnika: ");
 //public Korisnik(String ime, String prezime, int godine, String username, String password,double stanjeNaRacunu)
        String ime = scanner.nextLine();
        System.out.print("\t\t Unesi prezime korisnika: ");
        String prezime = scanner.nextLine();
        System.out.print("\t\t Unesi godine korisnika: ");
        int godine=0;
        try{
                                            String godine_str = scanner.nextLine();
                                            godine=Integer.parseInt(godine_str);
                                            if(godine<=0){
                                                System.out.println("\t\t#####################################################");
                                                System.out.println("\t\tBroj godina mora biti veci od 0!");
                                                System.out.println("\t\t#####################################################");
                                            }
        
        System.out.print("\t\t Unesi username korisnika: ");
        String username = scanner.nextLine();
        for (Osoba osoba : listaOsoba1) {
            if(osoba instanceof Korisnik){
            Korisnik k = (Korisnik) osoba;
              if(username.equals(k.getUsername())){
                  throw new PostojiKorisnikException();
                  
              } 
            }   
            }
        
        
        System.out.print("\t\t Unesi password korisnika: ");
        String password = scanner.nextLine();
        
            System.out.print("\t\t Unesi stanje na racunu: ");
        double stanjeNaRacunu;
            String stanje_str=scanner.nextLine();
            stanjeNaRacunu = Double.parseDouble(stanje_str);
            if(stanjeNaRacunu<0){
                                                System.out.println("\t\t#####################################################");
                                                System.out.println("\t\tStanje mora biti vece ili jednako 0!");
                                                System.out.println("\t\t#####################################################");
                                            }
            else if(godine>0){
                
                listaOsoba1.add(new Korisnik(ime,prezime,godine,username,password,stanjeNaRacunu));
                System.out.println("\t\tKorisnik " + ime + " uspesno dodat.");
                
                
            }
            
            
            
        } catch (NumberFormatException e) {
            System.out.println("\t\t#####################################################");
            System.out.println("\t\tNeispravan unos!");
            System.out.println("\t\t#####################################################");
        } catch (PostojiKorisnikException ex) {
                Prozor.pisiGresku("Korisnik vec postoji");
        }
        
        
    }

    

    public static void brisanjeKorisnika(ArrayList<Osoba> listaOsoba) {
        Scanner scanner=new Scanner(System.in);
        System.out.print("\t\t Unesi username korisnika: ");
        String username = scanner.nextLine();
        System.out.print("\t\t Unesi password korisnika: ");
        String password = scanner.nextLine();
        int ind=0;
        for (int i = 0; i < listaOsoba.size(); i++) {
            if(listaOsoba.get(i) instanceof Korisnik){
                Korisnik korisnik = (Korisnik) listaOsoba.get(i);
                if(korisnik.getUsername().equals(username) && korisnik.getPassword().equals(password)){ 
                    listaOsoba.remove(korisnik);
                    i--;
                    Korisnik.setBrojKorisnika(Korisnik.getBrojKorisnika()-1);
                    System.out.println("\t\tKorisnik uspesno obrisan!");
                    ind=1;
                }
            }
        }
        
        if(ind==0){
            Prozor.pisiGresku("Ne postoji dati korisnik!");
        }
        
    }

    public static void pisiKorisnike(ArrayList<Osoba> listaOsoba) {
        for (Osoba osoba : listaOsoba) {
            if(osoba instanceof Korisnik){
                Korisnik k=(Korisnik) osoba;
                System.out.println(k.toString());
            }
        }
    }
}
