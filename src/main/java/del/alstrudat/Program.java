package del.alstrudat;

import java.util.Stack;
import java.util.PriorityQueue;

public class Program {

    static class Pasien implements Comparable<Pasien> {
        String nama;
        int prioritas;
        int urutan;
        String asalAntrian;

        public Pasien(String nama, int prioritas, int urutan, String asalAntrian) {
            this.nama = nama;
            this.prioritas = prioritas;
            this.urutan = urutan;
            this.asalAntrian = asalAntrian;
        }

        @Override
        public int compareTo(Pasien other) {
            if (this.prioritas != other.prioritas) {
                return this.prioritas - other.prioritas;
            }
            return this.urutan - other.urutan;
        }
    }

    private PriorityQueue<Pasien> antrianIGD = new PriorityQueue<>();
    private PriorityQueue<Pasien> antrianReg = new PriorityQueue<>();
    private Stack<Pasien> riwayat = new Stack<>();
    private int counter = 0;
    private int totalDilayani = 0;
    private int igdDilayani = 0;
    private int regDilayani = 0;
    private double totalPrioritas = 0;

    public void enqueueIGD(String nama, int prioritas) {
        counter++;
        antrianIGD.add(new Pasien(nama, prioritas, counter, "IGD"));
    }

    public void enqueueReg(String nama, int prioritas) {
        counter++;
        antrianReg.add(new Pasien(nama, prioritas, counter, "REG"));
    }

    public void serve() {
        if (antrianIGD.isEmpty() && antrianReg.isEmpty()) {
            System.out.println("Semua antrian kosong");
            return;
        }

        Pasien p;
        if (!antrianIGD.isEmpty()) {
            p = antrianIGD.poll();
            igdDilayani++;
            System.out.println("[IGD] Dilayani: " + p.nama + " (Prioritas: " + p.prioritas + ")");
        } else {
            p = antrianReg.poll();
            regDilayani++;
            System.out.println("[REG] Dilayani: " + p.nama + " (Prioritas: " + p.prioritas + ")");
        }

        totalDilayani++;
        totalPrioritas += p.prioritas;
        riwayat.push(p);
    }

    public void undo() {
        if (riwayat.isEmpty()) {
            System.out.println("Tidak ada riwayat");
            return;
        }

        Pasien p = riwayat.pop();
        if (p.asalAntrian.equals("IGD")) {
            antrianIGD.add(p);
            System.out.println("UNDO: " + p.nama + " dikembalikan ke antrian IGD");
        } else {
            antrianReg.add(p);
            System.out.println("UNDO: " + p.nama + " dikembalikan ke antrian Reguler");
        }
    }

    public void peek() {
        if (antrianIGD.isEmpty() && antrianReg.isEmpty()) {
            System.out.println("Semua antrian kosong");
            return;
        }

        if (!antrianIGD.isEmpty()) {
            Pasien p = antrianIGD.peek();
            System.out.println("Berikutnya [IGD]: " + p.nama + " (Prioritas: " + p.prioritas + ")");
        } else {
            Pasien p = antrianReg.peek();
            System.out.println("Berikutnya [REG]: " + p.nama + " (Prioritas: " + p.prioritas + ")");
        }
    }

    public void stats() {
        System.out.println("Total dilayani: " + totalDilayani);
        if (totalDilayani == 0) {
            System.out.println("Rata-rata prioritas: 0.00");
        } else {
            System.out.printf("Rata-rata prioritas: %.2f%n", totalPrioritas / totalDilayani);
        }
        System.out.println("Pasien IGD dilayani: " + igdDilayani);
        System.out.println("Pasien Reguler dilayani: " + regDilayani);
    }
}