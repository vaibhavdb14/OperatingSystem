import java.util.*;

public class BestFit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input memory blocks
        System.out.print("Enter number of memory blocks: ");
        int m = sc.nextInt();
        int[] blockSize = new int[m];
        System.out.println("Enter sizes of memory blocks:");
        for (int i = 0; i < m; i++) {
            blockSize[i] = sc.nextInt();
        }

        // Input processes
        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        int[] processSize = new int[n];
        System.out.println("Enter sizes of processes:");
        for (int i = 0; i < n; i++) {
            processSize[i] = sc.nextInt();
        }

        // Allocation array (stores block number for each process)
        int[] allocation = new int[n];
        Arrays.fill(allocation, -1);

        // Best Fit Allocation
        for (int i = 0; i < n; i++) {
            int bestIdx = -1;
            for (int j = 0; j < m; j++) {
                if (blockSize[j] >= processSize[i]) {
                    if (bestIdx == -1 || blockSize[j] < blockSize[bestIdx]) {
                        bestIdx = j;
                    }
                }
            }

            if (bestIdx != -1) {
                allocation[i] = bestIdx + 1; // block number (1-based)
                blockSize[bestIdx] -= processSize[i]; // reduce block size
            }
        }

        // Print Allocation Table
        System.out.println("\nProcess No.\tProcess Size\tBlock Allocated");
        for (int i = 0; i < n; i++) {
            if (allocation[i] != -1)
                System.out.println((i + 1) + "\t\t" + processSize[i] + "\t\t" + allocation[i]);
            else
                System.out.println((i + 1) + "\t\t" + processSize[i] + "\t\tNot Allocated");
        }

        sc.close();
    }
}
