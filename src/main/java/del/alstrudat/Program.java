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
            // Prioritas lebih kecil (1) didahulukan daripada yang besar (5)
            if (this.prioritas != other.prioritas) {
                return this.prioritas - other.prioritas;
            }
            // Jika prioritas sama, gunakan urutan kedatangan (FIFO)
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
    private double totalSumPrioritas = 0;

    public void enqueueIGD(String nama, int prioritas) {
        antrianIGD.add(new Pasien(nama, prioritas, counter++, "IGD"));
    }

    public void enqueueReg(String nama, int prioritas) {
        antrianReg.add(new Pasien(nama, prioritas, counter++, "REG"));
    }

    public void serve() {
        Pasien diproses = null;

        if (!antrianIGD.isEmpty()) {
            diproses = antrianIGD.poll();
            System.out.println("[IGD] Dilayani: " + diproses.nama + " (Prioritas: " + diproses.prioritas + ")");
            igdDilayani++;
        } else if (!antrianReg.isEmpty()) {
            diproses = antrianReg.poll();
            System.out.println("[REG] Dilayani: " + diproses.nama + " (Prioritas: " + diproses.prioritas + ")");
            regDilayani++;
        } else {
            System.out.println("Semua antrian kosong");
            return;
        }

        // Update Statistik (Sesuai aturan: UNDO tidak mengurangi statistik)
        totalDilayani++;
        totalSumPrioritas += diproses.prioritas;
        riwayat.push(diproses);
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
        if (!antrianIGD.isEmpty()) {
            Pasien p = antrianIGD.peek();
            System.out.println("Berikutnya [IGD]: " + p.nama + " (Prioritas: " + p.prioritas + ")");
        } else if (!antrianReg.isEmpty()) {
            Pasien p = antrianReg.peek();
            System.out.println("Berikutnya [REG]: " + p.nama + " (Prioritas: " + p.prioritas + ")");
        } else {
            System.out.println("Semua antrian kosong");
        }
    }

    public void stats() {
        double rataRata = (totalDilayani == 0) ? 0.00 : totalSumPrioritas / totalDilayani;
        
        System.out.println("Total dilayani: " + totalDilayani);
        System.out.printf("Rata-rata prioritas: %.2f\n", rataRata);
        System.out.println("Pasien IGD dilayani: " + igdDilayani);
        System.out.println("Pasien Reguler dilayani: " + regDilayani);
    }
}
