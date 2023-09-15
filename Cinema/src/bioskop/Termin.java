package bioskop;
public class Termin {
    private String datum,vreme;
    private int slobodnaMesta;

    public Termin(String datum, String vreme, int slobodnaMesta) {
        this.datum = datum;
        this.vreme = vreme;
        this.slobodnaMesta=slobodnaMesta;
    }

    @Override
    public String toString() {
        return datum + " - " + vreme + ", broj slobodnih mesta: " + slobodnaMesta;
    }
    
    

    public String getDatum() {
        return datum;
    }

    public String getVreme() {
        return vreme;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setVreme(String vreme) {
        this.vreme = vreme;
    }

    public int getSlobodnaMesta() {
        return slobodnaMesta;
    }

    public void setSlobodnaMesta(int slobodnaMesta) {
        this.slobodnaMesta = slobodnaMesta;
    }
    

}

