import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.TreeMap;

class Produk {
    private String nama;
    private int stok;
    private double harga;
    private String tanggalDitambahkan;

    public Produk(String nama, int stok, double harga) {
        this.nama = nama;
        this.stok = stok;
        this.harga = harga;

        // Format dan simpan tanggal/waktu saat produk ditambahkan
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.tanggalDitambahkan = LocalDateTime.now().format(formatter);
    }

    public String getNama() {
        return nama;
    }

    public int getStok() {
        return stok;
    }

    public double getHarga() {
        return harga;
    }

    public String getTanggalDitambahkan() {
        return tanggalDitambahkan;
    }

    @Override
    public String toString() {
        return "Nama: " + nama + ", Stok: " + stok + ", Harga: " + harga + ", Ditambahkan: " + tanggalDitambahkan;
    }
}

public class Main {
    public static void tambahProduk(TreeMap<String, Produk> produkMap, Scanner scanner) {
        try {
            System.out.print("Masukkan nama produk: ");
            String nama = scanner.nextLine();
            System.out.print("Masukkan stok: ");
            int stok = Integer.parseInt(scanner.nextLine()); // Parsing angka dengan exception handling
            System.out.print("Masukkan harga: ");
            double harga = Double.parseDouble(scanner.nextLine()); // Parsing angka dengan exception handling

            Produk produk = new Produk(nama, stok, harga);
            produkMap.put(nama, produk);
            System.out.println("Produk " + nama + " berhasil ditambahkan.");
        } catch (NumberFormatException e) {
            System.out.println("Input tidak valid! Stok dan harga harus berupa angka.");
        } catch (Exception e) {
            System.out.println("Terjadi kesalahan: " + e.getMessage());
        }
    }

    public static void hapusProduk(TreeMap<String, Produk> produkMap, Scanner scanner) {
        System.out.print("Masukkan nama produk yang ingin dihapus: ");
        String nama = scanner.nextLine();

        if (produkMap.remove(nama) != null) {
            System.out.println("Produk " + nama + " berhasil dihapus.");
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    public static void cariProduk(TreeMap<String, Produk> produkMap, Scanner scanner) {
        System.out.print("Masukkan nama produk yang ingin dicari: ");
        String nama = scanner.nextLine();

        Produk produk = produkMap.get(nama);
        if (produk != null) {
            System.out.println("Detail Produk: " + produk);
        } else {
            System.out.println("Produk tidak ditemukan.");
        }
    }

    public static void tampilkanSemuaProduk(TreeMap<String, Produk> produkMap) {
        if (produkMap.isEmpty()) {
            System.out.println("Tidak ada produk yang tersimpan.");
        } else {
            System.out.println("Daftar Produk:");
            for (Produk produk : produkMap.values()) {
                System.out.println(produk);
            }
        }
    }

    public static void main(String[] args) {
        TreeMap<String, Produk> produkMap = new TreeMap<>();
        Scanner scanner = new Scanner(System.in);
        int pilihan;

        do {
            try {
                System.out.println("\n=== Menu Produk ===");
                System.out.println("1. Tambah Produk");
                System.out.println("2. Hapus Produk");
                System.out.println("3. Cari Produk");
                System.out.println("4. Tampilkan Semua Produk");
                System.out.println("5. Keluar");
                System.out.print("Masukkan pilihan Anda: ");
                pilihan = Integer.parseInt(scanner.nextLine()); // Parsing angka dengan exception handling

                switch (pilihan) {
                    case 1 -> tambahProduk(produkMap, scanner);
                    case 2 -> hapusProduk(produkMap, scanner);
                    case 3 -> cariProduk(produkMap, scanner);
                    case 4 -> tampilkanSemuaProduk(produkMap);
                    case 5 -> System.out.println("Keluar dari program.");
                    default -> System.out.println("Pilihan tidak valid! Masukkan angka antara 1-5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid! Masukkan angka untuk memilih menu.");
                pilihan = 0; // Reset pilihan untuk menghindari keluar dari loop
            } catch (Exception e) {
                System.out.println("Terjadi kesalahan: " + e.getMessage());
                pilihan = 0;
            }
        } while (pilihan != 5);

        scanner.close();
    }
}
