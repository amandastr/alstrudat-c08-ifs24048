package del.alstrudat;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Program program = new Program();

        int n = Integer.parseInt(scanner.nextLine().trim());

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().trim();
            String[] parts = line.split(" ");
            String command = parts[0];

            if (command.equals("ENQUEUE_IGD")) {
                String nama = parts[1];
                int prioritas = Integer.parseInt(parts[2]);
                program.enqueueIGD(nama, prioritas);
            } else if (command.equals("ENQUEUE_REG")) {
                String nama = parts[1];
                int prioritas = Integer.parseInt(parts[2]);
                program.enqueueReg(nama, prioritas);
            } else if (command.equals("SERVE")) {
                program.serve();
            } else if (command.equals("UNDO")) {
                program.undo();
            } else if (command.equals("PEEK")) {
                program.peek();
            } else if (command.equals("STATS")) {
                program.stats();
            }
        }

        scanner.close();
    }
}