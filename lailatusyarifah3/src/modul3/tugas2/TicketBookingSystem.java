package modul3.tugas2;

import java.util.Scanner;

public class TicketBookingSystem {
    private Queue<TicketOrder> orders;
    private int orderNumber;

    public TicketBookingSystem() {
        orders = new Queue<>();
        orderNumber = 1;
    }

    // Method untuk menambahkan pemesanan tiket ke dalam antrian
    public void addOrder(TicketOrder order) {
        order.setOrderNumber(orderNumber++);
        orders.enqueue(order);
        System.out.println("Pemesanan berhasil ditambahkan. Nomor pemesanan: " + order.getOrderNumber());
    }

    // Method untuk menampilkan semua pemesanan dalam antrian
    public void displayOrders() {
        System.out.println("Daftar Pemesanan Tiket:");
        Queue<TicketOrder> tempQueue = new Queue<>();
        while (!orders.isEmpty()) {
            TicketOrder order = orders.dequeue();
            System.out.println(order);
            tempQueue.enqueue(order);
        }
        orders = tempQueue;
    }

    // Method untuk menghapus pemesanan tertentu dari antrian berdasarkan nomor pemesanan
    public void cancelOrder(int orderNumber) {
        boolean found = false;
        Queue<TicketOrder> tempQueue = new Queue<>();
        while (!orders.isEmpty()) {
            TicketOrder order = orders.dequeue();
            if (order.getOrderNumber() == orderNumber) {
                found = true;
                System.out.println("Pemesanan dengan nomor " + orderNumber + " berhasil dibatalkan.");
            } else {
                tempQueue.enqueue(order);
            }
        }
        if (!found) {
            System.out.println("Pemesanan dengan nomor " + orderNumber + " tidak ditemukan.");
        }
        orders = tempQueue;
    }

    public static void main(String[] args) {
        TicketBookingSystem bookingSystem = new TicketBookingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu:");
            System.out.println("1. Tambah Pemesanan");
            System.out.println("2. Tampilkan Pemesanan");
            System.out.println("3. Batalkan Pemesanan");
            System.out.println("4. Keluar");
            System.out.print("Pilih menu: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Membuang newline character

            switch (choice) {
                case 1:
                    System.out.print("Masukkan nama pemesan: ");
                    String name = scanner.nextLine();
                    System.out.print("Masukkan jumlah tiket: ");
                    int quantity = scanner.nextInt();
                    TicketOrder newOrder = new TicketOrder(name, quantity);
                    bookingSystem.addOrder(newOrder);
                    break;
                case 2:
                    bookingSystem.displayOrders();
                    break;
                case 3:
                    System.out.print("Masukkan nomor pemesanan yang ingin dibatalkan: ");
                    int cancelOrderNumber = scanner.nextInt();
                    bookingSystem.cancelOrder(cancelOrderNumber);
                    break;
                case 4:
                    System.out.println("Terima kasih telah menggunakan sistem pemesanan tiket.");
                    System.exit(0);
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        }
    }
}

// Struktur data Queue sederhana
class Queue<T> {
    private Node<T> front;
    private Node<T> rear;

    public Queue() {
        front = null;
        rear = null;
    }

    public void enqueue(T data) {
        Node<T> newNode = new Node<>(data);
        if (isEmpty()) {
            front = newNode;
        } else {
            rear.next = newNode;
        }
        rear = newNode;
    }

    public T dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }
        T data = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
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

// Kelas untuk representasi pemesanan tiket
class TicketOrder {
    private static int nextOrderNumber = 1;
    private int orderNumber;
    private String name;
    private int quantity;

    public TicketOrder(String name, int quantity) {
        this.orderNumber = nextOrderNumber++;
        this.name = name;
        this.quantity = quantity;
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public String toString() {
        return "Nomor Pemesanan: " + orderNumber + ", Nama Pemesan: " + name + ", Jumlah Tiket: " + quantity;
    }
}