
package bioskop;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Bioskop{
    private String naziv;
    private String adresa;
    private ArrayList<Film> filmovi;

    public Bioskop(String naziv, String adresa) {
        this.naziv = naziv;
        this.adresa = adresa;
        this.filmovi = new ArrayList<>();
    }
    public void dodajFilm(Film film){
        try {
                for (Film film1 : filmovi) {
                    if(film.getNaziv().equals(film1.getNaziv())) throw new PostojiFilmException("\t\tFilm vec postoji!");
                }
                
               this.filmovi.add(film);
            } catch (PostojiFilmException e) {
                System.out.println(e.getMessage());
            }
    
    }
    public void dodajViseFilmova(ArrayList<Film> listaFilm){
        for (Film film : listaFilm) {
            try {
                for (Film film1 : filmovi) {
                    if(film.getNaziv().equals(film1.getNaziv())) throw new PostojiFilmException("\t\tFilm vec postoji!");
                }
                
               this.filmovi.add(film);
            } catch (PostojiFilmException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    
    public  void ispisiListuFilmova() {
        System.out.println("\n\n\t\t        --- Lista filmova: ---    ");
        for (int i = 0; i < filmovi.size(); i++) {
            Film film = filmovi.get(i);
            System.out.println("\t\t"+(i + 1) + ". " + film.toString());
        }
        System.out.print("\t\t--------------------------------------------\n\n\n");
    }
    
    public Film nadjiFilmPoNazivu(String naziv){
        for (Film film : filmovi) {
            if(film.getNaziv().equals(naziv))
                return film;
        }
    return null;
    }
    
    public void izbrisiFilm(String ime) {
        for (int i = 0; i < filmovi.size(); i++) {
            if(filmovi.get(i).getNaziv().equals(ime)){
                filmovi.remove(i);
                i--;
            }
        }
    }

   

    public void izlistajTermine(String naziv) {
        for (Film film : filmovi) {
            if(film.getNaziv().equals(naziv)){
               if(!film.getTermini().isEmpty()){
                    System.out.println("\t\tTermini filma "+ naziv+": ");
               for (int i = 0; i < film.getTermini().size(); i++) {
                   int broj=i+1;
                   System.out.println("\t\t"+broj+". termin: " +film.getTermini().get(i).toString());
                }
                   System.out.println("\n");
                }
               else System.out.println("\t\tOvaj film nema termina!\n\n");
            }
        }
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public ArrayList<Film> getFilmovi() {
        return filmovi;
    }

    public void setFilmovi(ArrayList<Film> filmovi) {
        this.filmovi = filmovi;
    }


    public static void upisiFilmove(ArrayList<Film> filmovi_List, String fajl){
       PrintWriter pw=null;
       JSONArray jsonFilmovi= new JSONArray();
       for (Film film : filmovi_List) {
            JSONObject obj = new JSONObject();
            obj.put("naziv", film.getNaziv());
            obj.put("zanr", film.getZanr());
            obj.put("trajanje", film.getTrajanje());
            obj.put("cena", film.getCena());
            JSONArray JSONTermini=new JSONArray();
            for (Termin t : film.getTermini()){
                JSONObject objTermin = new JSONObject();
                objTermin.put("datum", t.getDatum());
                objTermin.put("vreme", t.getVreme());
                objTermin.put("slobodnaMesta", t.getSlobodnaMesta());
                JSONTermini.add(objTermin);
            }
                
            obj.put("termini", JSONTermini);
            
            jsonFilmovi.add(obj);
        }
       
       try
        {
            pw = new PrintWriter(fajl);
            pw.flush();
            pw.write(jsonFilmovi.toJSONString());
           // System.out.println("zavrsen je upis u json fajl ");
            pw.close();
        }
        catch(IOException ex)
        {
            System.err.println("Exception kod upisa u JSON: " + ex.getLocalizedMessage());
        }
        
        finally
        {
            if (pw != null)
                pw.close();
        }
       
    }
    
    public static ArrayList<Film> procitajFilmove() throws Exception{
        String putanja = "filmovi2.json";
        ArrayList<Film> listaFilm = new ArrayList<>();
        
        JSONArray jsonNiz= (JSONArray) new JSONParser().parse(new FileReader(putanja));
        
        for (Object object : jsonNiz) {
            JSONObject o=(JSONObject) object;
            
            String naziv=o.get("naziv").toString();
            String zanr = o.get("zanr").toString();
            Double trajanje = Double.parseDouble(o.get("trajanje").toString());
            Double cena = Double.parseDouble(o.get("cena").toString());
            

            JSONArray jsonTermini = (JSONArray) o.get("termini");
            ArrayList <Termin> listaTermin = new ArrayList<>();
            for(Object objTermin : jsonTermini){
                JSONObject objT = (JSONObject) objTermin;
                String datum = objT.get("datum").toString();
                String vreme = objT.get("vreme").toString();
                int slobodnaMesta = Integer.parseInt(objT.get("slobodnaMesta").toString());
            
                listaTermin.add(new Termin(datum,vreme,slobodnaMesta));
            }
           listaFilm.add(new Film(naziv,zanr,trajanje,cena,listaTermin));
        }
    return listaFilm;
    }
    
    
    

}
