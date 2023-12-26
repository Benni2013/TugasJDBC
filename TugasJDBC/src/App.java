import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class App {    
    public static void main(String[] args) {
        //membuat scanner
        Scanner scanStr = new Scanner(System.in);
        Scanner scanInt = new Scanner(System.in);
        Scanner scanDou = new Scanner(System.in);
        String userName;
        String pass;
        String kodeCaptcha = Captcha.generateCaptcha();
        String captcah;

        //membuat Log In
        System.out.println("\nSELAMAT DATANG DI BENNI'S MARKET");
        System.out.println("================================");
        System.out.println("Log in");

        System.out.print("Username : ");
        userName = scanStr.nextLine();

        System.out.print("Password : ");
        pass = scanStr.nextLine();

        if (userName.equals("benni2013") && pass.equals("login123")) {
            System.out.println("\tLOG IN BERHASIL\n");

            //membuat captcha
            System.out.println("Kode Captcha\t: " + kodeCaptcha);
            Boolean statusCaptcha = false;
            while (!statusCaptcha) {
                System.out.print("Entry Captcha\t: ");
                captcah = scanStr.nextLine();

                if (captcah.equalsIgnoreCase(kodeCaptcha)) {
                    statusCaptcha = true;
                    System.out.println("--------------------------------");
                }
            }
                        
        } else {
            System.out.println("\tLOG IN GAGAL\nUSERNAME ATAU PASSWORD SALAH\n"); 
            System.exit(0); 
        }

        //INPUT DATA
        System.out.print("\nInput Nama Kasir: ");
        String kasir = scanStr.nextLine();
        System.out.println("--------------------------");

        Connection conn = null;
        Statement stmt = null;

        try {
            //membuat koneksi dan statement
            System.out.print("Status Koneksi: ");
            conn = Koneksi.buatKoneksi();
            stmt = conn.createStatement();

            //variabel pilihan
            Integer choice;

            do {
                System.out.println("\nMenu:");
                System.out.println("1. Melakukan Transaksi");
                System.out.println("2. Menampilkan Data Transaksi");
                System.out.println("3. Mengedit Data Transaksi");
                System.out.println("4. Menghapus Data Transaksi");
                System.out.println("0. Keluar dari Program");
    
                System.out.print("Pilih menu (0-4): ");
                choice = scanInt.nextInt();
    
                switch (choice) {
                    case 1:
                        CRUD.createTransaction(stmt, scanStr, scanInt, scanDou, kasir);
                        break;
                    case 2:
                        CRUD.displayTransactions(stmt);
                        break;
                    case 3:
                        CRUD.editTransaction(stmt, scanStr, scanInt, scanDou, kasir);
                        break;
                    case 4:
                        CRUD.deleteTransaction(stmt, scanInt);
                        break;
                    case 0:
                        System.out.println("Keluar dari Program. Terima kasih!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
                }
            } while (choice != 0);

            scanInt.close();
            scanStr.close();
            scanDou.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    System.out.println("Connection closed.");
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

    }
    
}
