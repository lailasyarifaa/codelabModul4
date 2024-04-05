package modul3.tugas1;

import java.util.Scanner;

public class BrowserNavigator {
    private Stack<String> backwardHistory;
    private Stack<String> forwardHistory;
    private String currentURL;

    public BrowserNavigator() {
        backwardHistory = new Stack<>();
        forwardHistory = new Stack<>();
        currentURL = "";
    }

    // Method untuk mengunjungi URL baru dan menyimpannya dalam riwayat navigasi
    public void visitURL(String url) {
        if (!currentURL.isEmpty()) {
            backwardHistory.push(currentURL); // Simpan URL sebelumnya ke dalam riwayat mundur
            // Bersihkan riwayat maju dengan menghapus setiap elemen satu per satu
            while (!forwardHistory.isEmpty()) {
                forwardHistory.pop();
            }
        }
        currentURL = url;
        System.out.println("Berhasil mengunjungi URL: " + url);
    }

    // Method untuk kembali ke URL sebelumnya dalam riwayat navigasi
    public void back() {
        if (!backwardHistory.isEmpty()) {
            forwardHistory.push(currentURL); // Simpan URL saat ini ke dalam riwayat maju
            String previousURL = backwardHistory.pop();
            System.out.println("Kembali ke URL sebelumnya: " + previousURL);
            currentURL = previousURL;
        } else {
            System.out.println("Tidak ada riwayat navigasi sebelumnya.");
        }
    }

    // Method untuk menuju ke URL berikutnya dalam riwayat navigasi
    public void forward() {
        if (!forwardHistory.isEmpty()) {
            String nextURL = forwardHistory.pop();
            backwardHistory.push(currentURL); // Simpan URL saat ini ke dalam riwayat mundur
            currentURL = nextURL;
            System.out.println("Menuju ke URL berikutnya: " + nextURL);
        } else {
            System.out.println("Tidak ada riwayat navigasi selanjutnya.");
        }
    }

    // Method untuk mendapatkan URL yang sedang diakses pengguna
    public String getCurrentURL() {
        return currentURL;
    }

    public static void main(String[] args) {
        BrowserNavigator browser = new BrowserNavigator();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Visit URL");
            System.out.println("2. Back");
            System.out.println("3. Forward");
            System.out.println("4. Current URL");
            System.out.println("5. Exit");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membuang newline character

            switch (choice) {
                case 1:
                    System.out.print("Masukkan URL baru: ");
                    String newURL = scanner.nextLine();
                    browser.visitURL(newURL);
                    break;
                case 2:
                    browser.back();
                    break;
                case 3:
                    browser.forward();
                    break;
                case 4:
                    System.out.println("URL yang sedang diakses: " + browser.getCurrentURL());
                    break;
                case 5:
                    System.out.println("Terima kasih telah menggunakan browser ini.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

// Struktur data Stack sederhana
class Stack<T> {
    private Node<T> top;

    public Stack() {
        top = null;
    }

    public void push(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty");
        }
        T data = top.data;
        top = top.next;
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }

    private static class Node<T> {
        private T data;
        private Node<T> next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }
}