package src.model;

public class Dosen {
    private int nip;
    private String namaDosen;

    // Constructor, getters, and setters
    public Dosen() {}
    public Dosen(int nip, String namaDosen) {
        this.nip = nip;
        this.namaDosen = namaDosen;
    }

    public int getNip() { return nip; }
    public void setNip(int nip) { this.nip = nip; }
    public String getNamaDosen() { return namaDosen; }
    public void setNamaDosen(String namaDosen) { this.namaDosen = namaDosen; }
}
