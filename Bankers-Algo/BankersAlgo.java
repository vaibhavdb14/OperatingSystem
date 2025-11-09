import java.util.Scanner;

public class BankersAlgo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        System.out.print("Enter number of resources: ");
        int m = sc.nextInt();

        int[][] max = new int[n][m];
        int[][] allocation = new int[n][m];
        int[][] need = new int[n][m];
        int[][] available = new int[2][m]; 

        System.out.println("Enter MAX matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                max[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter ALLOCATION matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                allocation[i][j] = sc.nextInt();
            }
        }

        System.out.println("Enter AVAILABLE resources (row 1):");
        for (int j = 0; j < m; j++) {
            available[1][j] = sc.nextInt(); 
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                need[i][j] = max[i][j] - allocation[i][j];
            }
        }

        boolean[] finish = new boolean[n];
        int[] safeSequence = new int[n];
        int count = 0;

        while (count < n) {
            boolean found = false;
            for (int i = 0; i < n; i++) {
                if (!finish[i]) {
                    int j;
                    for (j = 0; j < m; j++) {
                        if (need[i][j] > available[1][j]) { 
                            break;
                        }
                    }

                    if (j == m) { 
                        for (int k = 0; k < m; k++) {
                            available[1][k] += allocation[i][k]; 
                        }

                        safeSequence[count++] = i;
                        finish[i] = true;
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("\nSystem is in UNSAFE state.");
                sc.close();
                return;
            }
        }

        System.out.println("\nSystem is in SAFE state.");
        System.out.print("Safe Sequence: ");
        for (int i = 0; i < n; i++) {
            System.out.print("P" + safeSequence[i]);
            if (i != n - 1) System.out.print(" -> ");
        }

        sc.close();
    }
}
