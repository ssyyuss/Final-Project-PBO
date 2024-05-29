package src.model;

public class Perkuliahan {
    private int nim;
    private String kodeMK;
    private int nip;
    private String nilai;

    // Constructor, getters, and setters
    public Perkuliahan() {}

    public Perkuliahan(int nim, String kodeMK, int nip, String nilai) {
        this.nim = nim;
        this.kodeMK = kodeMK;
        this.nip = nip;
        this.nilai = nilai;
    }

    public int getNim() { 
        return nim; 
    }

    public void setNim(int nim) { 
        this.nim = nim; 
    }

    public String getKodeMK() { 
        return kodeMK; 
    }

    public void setKodeMK(String kodeMK) { 
        this.kodeMK = kodeMK; 
    }

    public int getNip() { 
        return nip; 
    }

    public void setNip(int nip) { 
        this.nip = nip; 
    }

    public String getNilai() { 
        return nilai; 
    }

    public void setNilai(String nilai) { 
        this.nilai = nilai; 
    }
}
