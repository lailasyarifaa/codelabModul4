package modul4.tugas1;

import java.util.HashMap;
import java.util.Scanner;

public class SistemVotingOnline {
    private HashMap<String, Integer> candidates;

    public SistemVotingOnline() {
        candidates = new HashMap<>();
    }

    public void tambahKandidat(String namaKandidat) {
        candidates.put(namaKandidat, 0);
    }

    public void vote(String namaKandidat) {
        if (candidates.containsKey(namaKandidat)) {
            int jumlahSuara = candidates.get(namaKandidat);
            candidates.put(namaKandidat, jumlahSuara + 1);
            System.out.println("Terima kasih, suara Anda telah direkam.");
        } else {
            System.out.println("Kandidat tidak valid.");
        }
    }

    public void tampilkanHasilVoting() {
        System.out.println("\nHasil Voting:");
        for (String kandidat : candidates.keySet()) {
            System.out.println("- " + kandidat + ": " + candidates.get(kandidat) + " suara");
        }
    }

    public static void main(String[] args) {
        SistemVotingOnline sistemVoting = new SistemVotingOnline();
        sistemVoting.tambahKandidat("Kandidat A");
        sistemVoting.tambahKandidat("Kandidat B");
        sistemVoting.tambahKandidat("Kandidat C");

        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Selamat datang di Sistem Voting Online");
        do {
            System.out.println("\nPilih kandidat yang ingin Anda dukung:");
            for (String kandidat : sistemVoting.candidates.keySet()) {
                System.out.println("- " + kandidat);
            }
            System.out.print("Masukkan nama kandidat (atau ketik 'selesai' untuk keluar): ");
            input = scanner.nextLine();
            if (!input.equalsIgnoreCase("selesai")) {
                sistemVoting.vote(input);
            }
        } while (!input.equalsIgnoreCase("selesai"));

        sistemVoting.tampilkanHasilVoting();
    }
}