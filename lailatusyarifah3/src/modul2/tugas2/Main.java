package modul2.tugas2;

import java.util.Scanner;

class Contact {
    String name;
    String phoneNumber;
    Contact next;

    public Contact(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.next = null;
    }
}

class ContactManager {
    private Contact head;

    public void addContact(String name, String phoneNumber) {
        Contact newContact = new Contact(name, phoneNumber);
        if (head == null) {
            head = newContact;
        } else {
            Contact current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newContact;
        }
        System.out.println("Kontak berhasil ditambahkan!");
    }

    public void displayContacts() {
        if (head == null) {
            System.out.println("Daftar kontak kosong.");
        } else {
            Contact current = head;
            int index = 1;
            System.out.println("Daftar Kontak:");
            while (current != null) {
                System.out.println(index + ". " + current.name + " - " + current.phoneNumber);
                current = current.next;
                index++;
            }
        }
    }

    public void searchContact(String name) {
        if (head == null) {
            System.out.println("Kontak tidak ditemukan.");
        } else {
            Contact current = head;
            boolean found = false;
            while (current != null) {
                if (current.name.equalsIgnoreCase(name)) {
                    found = true;
                    break;
                }
                current = current.next;
            }
            if (found) {
                System.out.println("Kontak ditemukan!");
                System.out.println("Nama: " + current.name);
                System.out.println("Nomor Telepon: " + current.phoneNumber);
            } else {
                System.out.println("Kontak tidak ditemukan.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContactManager contactManager = new ContactManager();

        while (true) {
            System.out.println("\nSelamat datang di Manajemen Kontak!");
            System.out.println("1. Tambah Kontak");
            System.out.println("2. Tampilkan Kontak");
            System.out.println("3. Cari Kontak");
            System.out.println("4. Keluar");

            System.out.print("Pilih menu (1/2/3/4): ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama kontak: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan nomor telepon: ");
                    String phoneNumber = scanner.nextLine();
                    contactManager.addContact(name, phoneNumber);
                    break;
                case 2:
                    contactManager.displayContacts();
                    break;
                case 3:
                    System.out.print("Masukkan nama kontak yang ingin dicari: ");
                    String searchName = scanner.nextLine();
                    contactManager.searchContact(searchName);
                    break;
                case 4:
                    System.out.println("Terima kasih!");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid. Silakan pilih menu yang sesuai.");
            }
        }
    }
}
