package modul4.tugas2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class DataPemilih {
    private HashMap<String, String> users;
    private HashMap<String, ArrayList<String>> userDetails;
    private HashMap<String, Integer> candidates;

    public DataPemilih() {
        users = new HashMap<>();
        userDetails = new HashMap<>();
        candidates = new HashMap<>();
    }

    public void register(String email, String password, String nama, String nik) {
        if (!email.endsWith("@gmail.com") || users.containsKey(email) || userDetails.containsValue(nik)) {
            System.out.println("Gagal Mendaftar");
            return;
        }

        ArrayList<String> userInfo = new ArrayList<>();
        userInfo.add(nama);
        userInfo.add(nik);

        users.put(email, password);
        userDetails.put(email, userInfo);
        System.out.println("Berhasil Mendaftar");
    }

    public void login(String email, String password) {
        if (!users.containsKey(email) || !users.get(email).equals(password) || !email.endsWith("@gmail.com")) {
            System.out.println("Gagal Login");
            return;
        }

        ArrayList<String> userInfo = userDetails.get(email);
        System.out.println("Login Berhasil");
        System.out.println("Nama: " + userInfo.get(0));
        System.out.println("NIK: " + userInfo.get(1));
    }

    public void voting(String email, String candidate) {
        if (!candidates.containsKey(candidate)) {
            System.out.println("Kandidat tidak valid.");
            return;
        }

        if (candidates.get(candidate) != null) {
            System.out.println("Anda sudah memilih kandidat lain.");
            return;
        }

        candidates.put(candidate, 1);
        System.out.println("Terima kasih, suara Anda telah direkam.");
    }

    public void logout() {
        System.out.println("Logout Berhasil");
    }

    public void tambahKandidat(String namaKandidat) {
        candidates.put(namaKandidat, null);
    }

    public void tampilkanHasilVoting() {
        System.out.println("\nHasil Voting:");
        for (String kandidat : candidates.keySet()) {
            int suara = candidates.get(kandidat) != null ? candidates.get(kandidat) : 0;
            System.out.println("- " + kandidat + ": " + suara + " suara");
        }
    }

    public static void main(String[] args) {
        DataPemilih sistemVoting = new DataPemilih();
        sistemVoting.tambahKandidat("Kandidat A");
        sistemVoting.tambahKandidat("Kandidat B");
        sistemVoting.tambahKandidat("Kandidat C");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nSelamat datang di Sistem Voting Online");
            System.out.println("Pilih menu :");
            System.out.println("1. Login");
            System.out.println("2. Daftar");
            System.out.println("3. Hasil Vote");
            System.out.print("Pilihan anda : ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline
            switch (choice) {
                case 1:
                    System.out.println("\nMasukkan email dan password untuk login:");
                    System.out.print("Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Password: ");
                    String password = scanner.nextLine();
                    sistemVoting.login(email, password);
                    if (sistemVoting.users.containsKey(email)) {
                        String candidate;
                        do {
                            System.out.println("\nPilih kandidat yang ingin Anda dukung:");
                            for (String kandidat : sistemVoting.candidates.keySet()) {
                                System.out.println("- " + kandidat);
                            }
                            System.out.print("Masukkan nama kandidat (atau ketik 'selesai' untuk keluar): ");
                            candidate = scanner.nextLine();
                            if (!candidate.equalsIgnoreCase("selesai")) {
                                sistemVoting.voting(email, candidate);
                            }
                        } while (!candidate.equalsIgnoreCase("selesai"));
                    }
                    break;
                case 2:
                    System.out.println("\nDaftar akun baru:");
                    System.out.print("Email: ");
                    String newEmail = scanner.nextLine();
                    System.out.print("Password: ");
                    String newPassword = scanner.nextLine();
                    System.out.print("Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("NIK: ");
                    String nik = scanner.nextLine();
                    sistemVoting.register(newEmail, newPassword, nama, nik);
                    break;
                case 3:
                    sistemVoting.tampilkanHasilVoting();
                    break;
                default:
                    System.out.println("Pilihan tidak valid");
            }
        } while (choice != 0);
    }
}