import java.util.*;

class Process {
    int pid;   // process ID
    int at;    // arrival time
    int bt;    // burst time
    int pr;    // priority
    int ct;    // completion time
    int tat;   // turnaround time
    int wt;    // waiting time
    boolean done; // to check if process is completed

    Process(int pid, int at, int bt, int pr) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.pr = pr;
        this.done = false;
    }
}

public class PriorityNonPreemptive {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        Process[] p = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time, Burst Time, Priority for Process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            int pr = sc.nextInt();
            p[i] = new Process(i + 1, at, bt, pr);
        }

        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        int time = 0, completed = 0;

        while (completed < n) {
            int idx = -1;
            int minPr = Integer.MAX_VALUE;

            for (int i = 0; i < n; i++) {
                if (!p[i].done && p[i].at <= time && p[i].pr < minPr) {
                    minPr = p[i].pr;
                    idx = i;
                }
            }

            if (idx == -1) {
                time++; 
            } else {
                time += p[idx].bt;
                p[idx].ct = time;
                p[idx].tat = p[idx].ct - p[idx].at;
                p[idx].wt = p[idx].tat - p[idx].bt;
                p[idx].done = true;
                completed++;
            }
        }

        System.out.println("\nPriority Scheduling (Non-Preemptive):");
        System.out.println("PID\tAT\tBT\tPR\tCT\tTAT\tWT");
        double totalTAT = 0, totalWT = 0;

        for (Process proc : p) {
            System.out.println(proc.pid + "\t" + proc.at + "\t" + proc.bt + "\t" + proc.pr + "\t" + proc.ct + "\t" + proc.tat + "\t" + proc.wt);
            totalTAT += proc.tat;
            totalWT += proc.wt;
        }

        System.out.println("\nAverage Turnaround Time = " + (totalTAT / n));
        System.out.println("Average Waiting Time = " + (totalWT / n));

        sc.close();
    }
}
