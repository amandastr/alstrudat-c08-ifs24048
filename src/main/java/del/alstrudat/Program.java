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
        // TODO: implement
    }

    public void enqueueReg(String nama, int prioritas) {
        // TODO: implement
    }

    public void serve() {
        // TODO: implement
    }

    public void undo() {
        // TODO: implement
    }

    public void peek() {
        // TODO: implement
    }

    public void stats() {
        // TODO: implement
    }
}