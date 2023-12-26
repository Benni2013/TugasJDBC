import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaksi extends Barang implements TotalBayar{

    private String namaPelanggan;
    private String nomorHp;
    private String alamat;
    private Integer jumlahBeli;
    private Date waktuSekarang = new Date(); //deklarasi variabel bertipe Date
    private String kasir;

    public Transaksi(String namaPelanggan, String nomorHp, String alamat, String kodeBarang, String namaBarang, Double hargaBarang, Integer jumlahBeli, String kasir) {
        super(kodeBarang, namaBarang, hargaBarang);
        this.namaPelanggan = namaPelanggan;
        this.nomorHp = nomorHp;
        this.alamat = alamat;
        this.jumlahBeli = jumlahBeli;
        this.kasir = kasir;
    }

    public Double hitungTotalBayar() {
        return getHargaBarang() * this.jumlahBeli;
    }

    //method untuk mendapatkan data tanggal
    public String getTanggal() {
        SimpleDateFormat tgl = new SimpleDateFormat("E, dd/MM/yyyy");
        String tanggal = tgl.format(this.waktuSekarang);
        return tanggal;
    }

    //method untuk mendapatkan data jam
    public String getJam() {
        SimpleDateFormat jam = new SimpleDateFormat("HH:mm:ss zzz");
        String waktu = jam.format(this.waktuSekarang);
        return waktu;
    }

    //method untuk menampilkan struk transaksi
    public void tampilkanStruk() {
        System.out.println("\tBenni's Market");
        System.out.println("Tanggal : " + getTanggal());
        System.out.println("Waktu\t: " + getJam());
        System.out.println("=========================");
        System.out.println("DATA PELANGGAN");
        System.out.println("--------------");
        System.out.println("Nama Pelanggan\t: " + namaPelanggan);
        System.out.println("No. HP\t\t: " + nomorHp);
        System.out.println("Alamat\t\t: " + alamat);
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("DATA PEMBELIAN BARANG");
        System.out.println("---------------------");
        System.out.println("Kode Barang\t: " + getKodeBarang());
        System.out.println("Nama Barang\t: " + getNamaBarang());
        System.out.println("Harga Barang\t: " + getHargaBarang());
        System.out.println("Jumlah Beli\t: " + jumlahBeli);
        System.out.println("TOTAL BAYAR\t: " + hitungTotalBayar());
        System.out.println("+++++++++++++++++++++++++");
        System.out.println("Kasir\t: " + kasir + "\n");

    }
     
}
