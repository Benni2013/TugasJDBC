public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private Double hargaBarang;

    public Barang (String kodeBarang, String namaBarang, Double hargaBarang){
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public Double getHargaBarang() {
        return hargaBarang;
    }
}
