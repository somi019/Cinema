package projekatoop;

import bioskop.Bioskop;
import bioskop.Film;
import bioskop.Prozor;
import bioskop.Termin;
import java.util.ArrayList;
import java.util.Scanner;
import osoba.Admin;
import osoba.Korisnik;
import osoba.Osoba;

public class Main{
    public static void main(String[] args) throws Exception{

        Osoba k1=new Korisnik("Sava","Glavonic",20,"zex6","savacar123",5500);
        Osoba k2=new Korisnik("Dule","Savic",20,"due123","duecar123",100);
        Osoba a1=new Admin("milos","luka123","Luka","Glisic",25);

        ArrayList<Osoba> listaOsoba = new ArrayList<>();
        listaOsoba.add(a1);
        listaOsoba.add(k1);
        listaOsoba.add(k2);
        
        Scanner scanner = new Scanner(System.in);
        boolean provera = false;
      
        ArrayList<Film> listaFilmova=Bioskop.procitajFilmove();
 
        Bioskop bioskopVozdovac = new Bioskop("Bioskop Vozdovac","Vojvode Stepe 120");
        bioskopVozdovac.dodajViseFilmova(listaFilmova);


        while (!provera) {
            System.out.println("\n\t\tDobrodosli, brale!\n");
            System.out.println("\t\t\t\t\t\t\t\t\t\t\tBroj korisnika na planeti: "+ Korisnik.getBrojKorisnika()+"\n\n");
            
            System.out.print("\t\tKorisnicko ime: ");
            String username = scanner.nextLine();

            System.out.print("\t\tLozinka: ");
            String password = scanner.nextLine();
            
            boolean odjava=false;
            Osoba osoba=null;
            for (Osoba osoba1 : listaOsoba) {
                if(osoba1 instanceof Korisnik){
                    Korisnik korisnik = (Korisnik) osoba1;
                    if (korisnik.getUsername().equals(username) && korisnik.getPassword().equals(password)) osoba=osoba1;
                }
                else if(osoba1 instanceof Admin){
                    Admin admin = (Admin) osoba1;
                    if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) osoba = osoba1;
                }
            
            }
            
            
                if (osoba instanceof Korisnik) {
                    Korisnik korisnik = (Korisnik) osoba;
                    if (korisnik.getUsername().equals(username) && korisnik.getPassword().equals(password)) {
                        provera = true;
                        boolean izlaz=false;
                        while(!izlaz){
                          try{
                              Prozor.ispisKorisnik();
                            int izbor = scanner.nextInt();
                            switch (izbor) {
                                case 1:
                                    bioskopVozdovac.ispisiListuFilmova();
                                    break;
                                case 2:
                                    Prozor.RezervacijaKarte(bioskopVozdovac, korisnik);
                                    break;
                                case 3:
                                    korisnik.ispis();
                                    break;
                                case 4:
                                    izlaz=true;
                                    provera=false;
                                    odjava=true;
                                    scanner.nextLine();
                                    break;
                                case 5: 
                                    izlaz=true;
                                    break;
                                default:
                                    Prozor.pisiGresku("Niste izabrali nijednu od ponudjenih opcija!");
                            }
                        }
            catch (Exception e){
                Prozor.pisiGresku("Niste uneli broj kao opciju!");
                scanner.nextLine();
            }
                        
                          
           }
       
        }
                }
                else if (osoba instanceof Admin) {
                    Admin admin = (Admin) osoba;
                    if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
                        provera = true;
                        boolean izlaz=false;
                        while(!izlaz){
                         try{
                             Prozor.ispisAdmin();
                            int izbor = scanner.nextInt();
                            switch (izbor) {
                                case 1:
                                    bioskopVozdovac.ispisiListuFilmova();
                                    break;
                                case 2:
                                    Prozor.dodavanjeFilma(bioskopVozdovac);
                                    break;
                                case 3:
                                    Prozor.brisanjeFilma(bioskopVozdovac);
                                    break;
                                case 4:
                                    Prozor.prikazTermina(bioskopVozdovac);
                                    break;
                                case 5:
                                    Prozor.dodavanjeTermina(bioskopVozdovac);
                                    break;
                                case 6:
                                    Prozor.brisanjeTermina(bioskopVozdovac);
                                    break;
                                case 7:
                                    Prozor.pisiKorisnike(listaOsoba);
                                    break;
                                case 8:
                                    Prozor.dodavanjeKorisnika(listaOsoba);
                                    break;
                                case 9:
                                    Prozor.brisanjeKorisnika(listaOsoba);
                                    break;
                                 case 10:
                                    izlaz=true;
                                    provera=false;
                                    odjava=true;
                                    scanner.nextLine();
                                    break;
                                case 11: 
                                    izlaz=true;
                                    break;
                                
                                default:
                                    Prozor.pisiGresku("Niste izabrali nijednu od ponudjenih opcija!");
                                    
                            }
                           }
                           catch (Exception e){
                                Prozor.pisiGresku("Niste uneli broj kao opciju!");
                                scanner.nextLine();
                                }
                        }
                    }
                }
            
            if (!provera && !odjava) {
                Prozor.pisiGresku("Pogresno korisnicko ime ili lozinka, pokusajte ponovo.");
            }
        }
        
        
        
        Bioskop.upisiFilmove(bioskopVozdovac.getFilmovi(), "filmovi2.json");

    }
   
}