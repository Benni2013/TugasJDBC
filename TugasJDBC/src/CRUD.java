import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.Scanner;

public class CRUD extends Koneksi{

    public static void displayTransactions(Statement stmt) throws SQLException {
        // Execute select query
        String sql = "SELECT * FROM transaksi";
        ResultSet rs = stmt.executeQuery(sql);

        // Display results
        System.out.println("\nData Transaksi:");
        while (rs.next()) {
            Integer id = rs.getInt("id_transaksi");
            String tglTransaksi = rs.getString("tanggal_transaksi");
            String jamTransaksi = rs.getString("jam_transaksi");
            String namaPelanggan = rs.getString("nama_pelanggan");
            String noHp = rs.getString("no_hp");
            String alamat = rs.getString("alamat");
            String kodeBarang = rs.getString("kode_barang");
            String namaBarang = rs.getString("nama_barang");
            Double harga = rs.getDouble("harga_barang");
            Integer jumlah = rs.getInt("jumlah");
            Double total = rs.getDouble("total");
            String kasir = rs.getString("kasir");
            String tglLastEdit = rs.getString("tgl_last_edit");
            String jamLastEdit = rs.getString("jam_last_edit");

            System.out.println("ID: " + id + ", Tanggal Transaksi: " + tglTransaksi + ", Jam Transaksi: " + 
                                jamTransaksi + ", Nama Pelanggan: " + namaPelanggan + ", No. HP: " + noHp + 
                                ", Alamat Pelanggan: " + alamat + ", Kode Barang: " + kodeBarang + ", Nama Barang: " + 
                                namaBarang + ", Harga Barang: " + harga + ", Jumlah Beli: " + jumlah + ", Total Harga: " + 
                                total + ", Nama Kasir: " + kasir + ", Tanggal Terakhir Edit: " + tglLastEdit + 
                                ", Jam Terakhir Edit: " + jamLastEdit + "\n");
        }
        System.out.println("\n");
    }

    public static void createTransaction(Statement stmt, Scanner scanStr, Scanner scanInt, Scanner scanDou, String kasir) throws SQLException {
        
        System.out.println("--------------------------");
        System.out.println("MELAKUKAN TRANSAKSI");

        // Input customer information
        System.out.print("Input Nama Pelanggan: ");
        String namaPelanggan = scanStr.nextLine();

        System.out.print("Input No. HP: ");
        String nomorHp = scanStr.nextLine();

        System.out.print("Input Alamat: ");
        String alamat = scanStr.nextLine();

        // Input product information
        System.out.print("Input Kode Barang: ");
        String kodeBarang = scanStr.nextLine();

        System.out.print("Input Nama Barang: ");
        String namaBarang = scanStr.nextLine();

        // Input harga barang + exception handling jika harga barang tidak valid
        Boolean inputHargaValid = true;
        Double hargaBarang = 0.0;
        while (inputHargaValid) {
            try {
                System.out.print("Input Harga Barang: ");
                hargaBarang = scanDou.nextDouble();
                if (hargaBarang < 0) {
                    throw new ArithmeticException("Harga barang tidak boleh minus.");
                }
                inputHargaValid = false;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input harga barang tidak valid. Silakan coba lagi.");
                scanDou.nextDouble();
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Input jumlah beli + exception handling jika jumlah beli tidak valid
        boolean inputJumlahValid = true;
        int jumlahBeli = 0;
        while (inputJumlahValid) {
            try {
                System.out.print("Input Jumlah Beli: ");
                jumlahBeli = scanInt.nextInt();
                if (jumlahBeli <= 0) {
                    throw new ArithmeticException("Jumlah barang harus lebih dari 0.");
                }
                inputJumlahValid = false;
            } catch (InputMismatchException e) {
                System.out.println("Error: Input jumlah barang tidak valid. Silakan coba lagi.");
                scanInt.nextInt();
            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        Transaksi transaksi = new Transaksi(namaPelanggan, nomorHp, alamat, kodeBarang, namaBarang, hargaBarang, jumlahBeli, kasir);

        String tanggal = transaksi.getTanggal();
        String jam = transaksi.getJam();
        Double total = transaksi.hitungTotalBayar();

        // Execute insert query
        String insertSql = "INSERT INTO transaksi  VALUES (NULL" + ", '" + tanggal + "', '" + jam + "', '" + namaPelanggan + "', '" + 
                            nomorHp + "', '" + alamat + "', '" + kodeBarang + "', '" + namaBarang + "', " + hargaBarang + ", " +
                            jumlahBeli + ", " + total + ", '" + kasir + "', '" + tanggal + "', '" + jam + "');";
        stmt.executeUpdate(insertSql);

        System.out.println("\nTransaksi berhasil ditambahkan!\n\nCetak Struk Belanja:\n");
        transaksi.tampilkanStruk();
    }

    public static void editTransaction(Statement stmt, Scanner scanStr, Scanner scanInt, Scanner scanDou, String kasir) throws SQLException {
        System.out.print("Masukkan ID data transaksi yang akan diedit: ");
        int idToEdit = scanInt.nextInt();

        // Check if the data with the given ID exists
        ResultSet rs = stmt.executeQuery("SELECT * FROM transaksi WHERE id_transaksi = " + idToEdit);
        if (rs.next()) {
            System.out.println("Masukkan data baru: ");

            // Input customer information
            System.out.print("Input Nama Pelanggan: ");
            String newNamaPelanggan = scanStr.nextLine();

            System.out.print("Input No. HP: ");
            String newNomorHp = scanStr.nextLine();

            System.out.print("Input Alamat: ");
            String newAlamat = scanStr.nextLine();

            // Input product information
            System.out.print("Input Kode Barang: ");
            String newKodeBarang = scanStr.nextLine();

            System.out.print("Input Nama Barang: ");
            String newNamaBarang = scanStr.nextLine();

            // Input harga barang + exception handling jika harga barang tidak valid
            Boolean inputNewHargaValid = true;
            Double newHargaBarang = 0.0;
            while (inputNewHargaValid) {
                try {
                    System.out.print("Input Harga Barang: ");
                    newHargaBarang = scanDou.nextDouble();
                    if (newHargaBarang < 0) {
                        throw new ArithmeticException("Harga barang tidak boleh minus.");
                    }
                    inputNewHargaValid = false;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Input harga barang tidak valid. Silakan coba lagi.");
                    scanDou.nextDouble();
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            // Input jumlah beli + exception handling jika jumlah beli tidak valid
            boolean inputNewJumlahValid = true;
            int newJumlahBeli = 0;
            while (inputNewJumlahValid) {
                try {
                    System.out.print("Input Jumlah Beli: ");
                    newJumlahBeli = scanInt.nextInt();
                    if (newJumlahBeli <= 0) {
                        throw new ArithmeticException("Jumlah barang harus lebih dari 0.");
                    }
                    inputNewJumlahValid = false;
                } catch (InputMismatchException e) {
                    System.out.println("Error: Input jumlah barang tidak valid. Silakan coba lagi.");
                    scanInt.nextInt();
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }

            Transaksi transaksi = new Transaksi(newNamaPelanggan, newNomorHp, newAlamat, newKodeBarang, newNamaBarang, newHargaBarang, newJumlahBeli, kasir);

            String newTanggal = transaksi.getTanggal();
            String newJam = transaksi.getJam();
            Double newTotal = transaksi.hitungTotalBayar();

            // Execute insert query
            String updateSql =  "UPDATE transaksi SET " +
                                      "nama_pelanggan = '" + newNamaPelanggan + "', " +
                                      "no_hp = '" + newNomorHp + "', " +
                                      "alamat = '" + newAlamat + "', " +
                                      "kode_barang = '" + newKodeBarang + "', " +
                                      "nama_barang = '" + newNamaBarang + "', " +
                                      "harga_barang = " + newHargaBarang + ", " +
                                      "jumlah = " + newJumlahBeli + ", " +
                                      "total = " + newTotal + ", " +
                                      "kasir = '" + kasir + "', " +
                                      "tgl_last_edit = '" + newTanggal + "', " +
                                      "jam_last_edit = '" + newJam + "' " +
                                "WHERE id_transaksi = "+ idToEdit + ";";

            stmt.executeUpdate(updateSql);

            System.out.println("Data berhasil diperbarui.\n");
        } else {
            System.out.println("Data dengan ID " + idToEdit + " tidak ditemukan.\n");
        }
    }

    public static void deleteTransaction(Statement stmt, Scanner scanInt) throws SQLException {
        System.out.print("Masukkan ID data yang akan dihapus: ");
        int idToDelete = scanInt.nextInt();

        // Check if the data with the given ID exists
        ResultSet rs = stmt.executeQuery("SELECT * FROM transaksi WHERE id_transaksi = " + idToDelete);
        if (rs.next()) {
            // Execute delete query
            String deleteSql = "DELETE FROM transaksi WHERE id_transaksi = " + idToDelete;
            stmt.executeUpdate(deleteSql);

            System.out.println("Data berhasil dihapus.\n");
        } else {
            System.out.println("Data dengan ID " + idToDelete + " tidak ditemukan.\n");
        }
    }
}
