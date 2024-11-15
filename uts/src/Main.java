import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Kamar {
    private String nomorKamar;
    private String tipeKamar;
    private double hargaPerMalam;
    private boolean tersedia;

    public Kamar(String nomorKamar, String tipeKamar, double hargaPerMalam) {
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.hargaPerMalam = hargaPerMalam;
        this.tersedia = true;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public double getHargaPerMalam() {
        return hargaPerMalam;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void setTersedia(boolean tersedia) {
        this.tersedia = tersedia;
    }

    public void tampilkanInfoKamar() {
        System.out.println("Nomor Kamar: " + nomorKamar);
        System.out.println("Tipe Kamar: " + tipeKamar);
        System.out.println("Harga Per Malam: " + hargaPerMalam);
        System.out.println("Status: " + (tersedia ? "Tersedia" : "Dipesan"));
    }
}

class KamarSuite extends Kamar {
    private boolean includesBreakfast;

    public KamarSuite(String nomorKamar, double hargaPerMalam, boolean includesBreakfast) {
        super(nomorKamar, "Suite", hargaPerMalam);
        this.includesBreakfast = includesBreakfast;
    }

    public boolean isIncludesBreakfast() {
        return includesBreakfast;
    }

    public void tampilkanInfoKamar() {
        super.tampilkanInfoKamar();
        System.out.println("Includes Breakfast: " + (includesBreakfast ? "Yes" : "No"));
    }
}

class Tamu {
    private String nama;
    private String nomorIdentitas;
    private String kontak;
    private List<Reservasi> daftarReservasi;

    public Tamu(String nama, String nomorIdentitas, String kontak) {
        this.nama = nama;
        this.nomorIdentitas = nomorIdentitas;
        this.kontak = kontak;
        this.daftarReservasi = new ArrayList<>();
    }

    public String getNama() {
        return nama;
    }

    public String getNomorIdentitas() {
        return nomorIdentitas;
    }

    public String getKontak() {
        return kontak;
    }

    public List<Reservasi> getDaftarReservasi() {
        return daftarReservasi;
    }

    public void tambahReservasi(Reservasi reservasi) {
        this.daftarReservasi.add(reservasi);
    }

    public void tampilkanInfoTamu() {
        System.out.println("Nama: " + nama);
        System.out.println("Nomor Identitas: " + nomorIdentitas);
        System.out.println("Kontak: " + kontak);
        System.out.println("Reservasi Aktif:");
        for (Reservasi reservasi : daftarReservasi) {
            reservasi.tampilkanInfoReservasi();
        }
    }
}

class Reservasi {
    private Tamu tamu;
    private Kamar kamar;
    private String tanggalCheckIn;
    private String tanggalCheckOut;
    private int durasiMenginap;
    private boolean aktif;

    public Reservasi(Tamu tamu, Kamar kamar, String tanggalCheckIn, String tanggalCheckOut, int durasiMenginap) {
        this.tamu = tamu;
        this.kamar = kamar;
        this.tanggalCheckIn = tanggalCheckIn;
        this.tanggalCheckOut = tanggalCheckOut;
        this.durasiMenginap = durasiMenginap;
        this.aktif = true;
    }

    public Tamu getTamu() {
        return tamu;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public String getTanggalCheckIn() {
        return tanggalCheckIn;
    }

    public String getTanggalCheckOut() {
        return tanggalCheckOut;
    }

    public int getDurasiMenginap() {
        return durasiMenginap;
    }

    public boolean isAktif() {
        return aktif;
    }

    public void batalkan() {
        this.aktif = false;
        this.kamar.setTersedia(true);
    }

    public void tampilkanInfoReservasi() {
        System.out.println("Tamu: " + tamu.getNama());
        System.out.println("Kamar: " + kamar.getNomorKamar());
        System.out.println("Check-In: " + tanggalCheckIn);
        System.out.println("Check-Out: " + tanggalCheckOut);
        System.out.println("Durasi Menginap: " + durasiMenginap + " malam");
        System.out.println("Status: " + (aktif ? "Aktif" : "Dibatalkan"));
    }
}

class Hotel {
    private List<Kamar> daftarKamar;
    private List<Tamu> daftarTamu;
    private List<Reservasi> daftarReservasi;

    public Hotel() {
        this.daftarKamar = new ArrayList<>();
        this.daftarTamu = new ArrayList<>();
        this.daftarReservasi = new ArrayList<>();
    }

    public void tambahKamar(Kamar kamar) {
        this.daftarKamar.add(kamar);
    }

    public void tambahTamu(Tamu tamu) {
        this.daftarTamu.add(tamu);
    }

    public void buatReservasi(Tamu tamu, Kamar kamar, String tanggalCheckIn, String tanggalCheckOut, int durasiMenginap) {
        Reservasi reservasi = new Reservasi(tamu, kamar, tanggalCheckIn, tanggalCheckOut, durasiMenginap);
        this.daftarReservasi.add(reservasi);
        tamu.tambahReservasi(reservasi);
        kamar.setTersedia(false);
    }

    public void batalkanReservasi(Reservasi reservasi) {
        reservasi.batalkan();
    }

    public void daftarKamarTersedia() {
        System.out.println("Daftar Kamar Tersedia:");
        for (Kamar kamar : daftarKamar) {
            if (kamar.isTersedia()) {
                kamar.tampilkanInfoKamar();
            }
        }
    }

    // Tambahkan getter untuk daftar tamu dan daftar kamar
    public List<Tamu> getDaftarTamu() {
        return daftarTamu;
    }

    public List<Kamar> getDaftarKamar() {
        return daftarKamar;
    }
}


public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Hotel Management System ===");
            System.out.println("1. Tambah Kamar");
            System.out.println("2. Tambah Tamu");
            System.out.println("3. Buat Reservasi");
            System.out.println("4. Batalkan Reservasi");
            System.out.println("5. Tampilkan Kamar Tersedia");
            System.out.println("6. Tampilkan Info Tamu");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");

            int pilihan = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nomor kamar: ");
                    String nomorKamar = scanner.nextLine();
                    System.out.print("Masukkan tipe kamar (Single/Double/Suite): ");
                    String tipeKamar = scanner.nextLine();
                    System.out.print("Masukkan harga per malam: ");
                    double harga = scanner.nextDouble();
                    scanner.nextLine(); // Membersihkan buffer

                    if (tipeKamar.equalsIgnoreCase("Suite")) {
                        System.out.print("Apakah termasuk sarapan? (true/false): ");
                        boolean includesBreakfast = scanner.nextBoolean();
                        scanner.nextLine(); // Membersihkan buffer
                        hotel.tambahKamar(new KamarSuite(nomorKamar, harga, includesBreakfast));
                    } else {
                        hotel.tambahKamar(new Kamar(nomorKamar, tipeKamar, harga));
                    }
                    System.out.println("Kamar berhasil ditambahkan!");
                    break;

                case 2:
                    System.out.print("Masukkan nama tamu: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan nomor identitas: ");
                    String nomorIdentitas = scanner.nextLine();
                    System.out.print("Masukkan kontak: ");
                    String kontak = scanner.nextLine();

                    Tamu tamu = new Tamu(nama, nomorIdentitas, kontak);
                    hotel.tambahTamu(tamu);
                    System.out.println("Tamu berhasil ditambahkan!");
                    break;

                case 3:
                    System.out.print("Masukkan nama tamu: ");
                    String namaTamu = scanner.nextLine();
                    Tamu tamuReservasi = null;
                    for (Tamu t : hotel.getDaftarTamu()) {
                        if (t.getNama().equalsIgnoreCase(namaTamu)) {
                            tamuReservasi = t;
                            break;
                        }
                    }
                    if (tamuReservasi == null) {
                        System.out.println("Tamu tidak ditemukan!");
                        break;
                    }

                    System.out.print("Masukkan nomor kamar: ");
                    String nomorKamarReservasi = scanner.nextLine();
                    Kamar kamarReservasi = null;
                    for (Kamar k : hotel.getDaftarKamar()) {
                        if (k.getNomorKamar().equalsIgnoreCase(nomorKamarReservasi) && k.isTersedia()) {
                            kamarReservasi = k;
                            break;
                        }
                    }
                    if (kamarReservasi == null) {
                        System.out.println("Kamar tidak ditemukan atau tidak tersedia!");
                        break;
                    }

                    System.out.print("Masukkan tanggal check-in (YYYY-MM-DD): ");
                    String checkIn = scanner.nextLine();
                    System.out.print("Masukkan tanggal check-out (YYYY-MM-DD): ");
                    String checkOut = scanner.nextLine();
                    System.out.print("Masukkan durasi menginap: ");
                    int durasi = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer

                    hotel.buatReservasi(tamuReservasi, kamarReservasi, checkIn, checkOut, durasi);
                    System.out.println("Reservasi berhasil dibuat!");
                    break;

                case 4:
                    System.out.print("Masukkan nama tamu untuk membatalkan reservasi: ");
                    String namaPembatalan = scanner.nextLine();
                    Tamu tamuPembatalan = null;
                    for (Tamu t : hotel.getDaftarTamu()) {
                        if (t.getNama().equalsIgnoreCase(namaPembatalan)) {
                            tamuPembatalan = t;
                            break;
                        }
                    }
                    if (tamuPembatalan == null) {
                        System.out.println("Tamu tidak ditemukan!");
                        break;
                    }

                    System.out.println("Daftar Reservasi Aktif:");
                    int i = 1;
                    for (Reservasi r : tamuPembatalan.getDaftarReservasi()) {
                        if (r.isAktif()) {
                            System.out.println(i + ". " + r.getKamar().getNomorKamar());
                            i++;
                        }
                    }
                    if (i == 1) {
                        System.out.println("Tidak ada reservasi aktif.");
                        break;
                    }

                    System.out.print("Pilih nomor reservasi yang akan dibatalkan: ");
                    int nomorReservasi = scanner.nextInt();
                    scanner.nextLine(); // Membersihkan buffer
                    if (nomorReservasi > 0 && nomorReservasi < i) {
                        Reservasi reservasi = tamuPembatalan.getDaftarReservasi().get(nomorReservasi - 1);
                        hotel.batalkanReservasi(reservasi);
                        System.out.println("Reservasi berhasil dibatalkan!");
                    } else {
                        System.out.println("Nomor reservasi tidak valid!");
                    }
                    break;

                case 5:
                    hotel.daftarKamarTersedia();
                    break;

                case 6:
                    System.out.print("Masukkan nama tamu: ");
                    String namaInfoTamu = scanner.nextLine();
                    Tamu tamuInfo = null;
                    for (Tamu t : hotel.getDaftarTamu()) {
                        if (t.getNama().equalsIgnoreCase(namaInfoTamu)) {
                            tamuInfo = t;
                            break;
                        }
                    }
                    if (tamuInfo == null) {
                        System.out.println("Tamu tidak ditemukan!");
                    } else {
                        tamuInfo.tampilkanInfoTamu();
                    }
                    break;

                case 7:
                    System.out.println("Terima kasih telah menggunakan sistem!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Pilihan tidak valid!");
                    break;
            }
        }
    }
}

