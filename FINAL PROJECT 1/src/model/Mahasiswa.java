package src.model;

public class Mahasiswa {
    private int nim;
    private String namaMhs;
    private String tglLahir;
    private String alamat;
    private char jenisKelamin; 

    public Mahasiswa() {}

    public Mahasiswa(int nim, String namaMhs, String tglLahir, String alamat, char jenisKelamin) {
        this.nim = nim;
        this.namaMhs = namaMhs;
        this.tglLahir = tglLahir;
        this.alamat = alamat;
        this.jenisKelamin = jenisKelamin;
    }

    public int getNim() { return nim; }
    public void setNim(int nim) { this.nim = nim; }
    public String getNamaMhs() { return namaMhs; }
    public void setNamaMhs(String namaMhs) { this.namaMhs = namaMhs; }
    public String getTglLahir() { return tglLahir; }
    public void setTglLahir(String tglLahir) { this.tglLahir = tglLahir; }
    public String getAlamat() { return alamat; }
    public void setAlamat(String alamat) { this.alamat = alamat; }
    public char getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(char jenisKelamin) { this.jenisKelamin = jenisKelamin; }
}
