package src.model;

public class MataKuliah {
    private String kodeMK;
    private String namaMK;
    private int sks;

    // Constructor, getters, and setters
    public MataKuliah() {}
    public MataKuliah(String kodeMK, String namaMK, int sks) {
        this.kodeMK = kodeMK;
        this.namaMK = namaMK;
        this.sks = sks;
    }

    public String getKodeMK() { return kodeMK; }
    public void setKodeMK(String kodeMK) { this.kodeMK = kodeMK; }
    public String getNamaMK() { return namaMK; }
    public void setNamaMK(String namaMK) { this.namaMK = namaMK; }
    public int getSks() { return sks; }
    public void setSks(int sks) { this.sks = sks; }
}
