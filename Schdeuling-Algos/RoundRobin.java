import java.util.*;

class Process {
    int pid;   // process ID
    int at;    // arrival time
    int bt;    // burst time
    int rt;    // remaining time
    int ct;    // completion time
    int tat;   // turnaround time
    int wt;    // waiting time

    // Constructor
    Process(int pid, int at, int bt) {
        this.pid = pid;
        this.at = at;
        this.bt = bt;
        this.rt = bt; // initially remaining time = burst time
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of processes: ");
        int n = sc.nextInt();
        Process[] p = new Process[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter Arrival Time and Burst Time for Process " + (i + 1) + ": ");
            int at = sc.nextInt();
            int bt = sc.nextInt();
            p[i] = new Process(i + 1, at, bt);
        }

        System.out.print("Enter Time Quantum: ");
        int tq = sc.nextInt();

        // Sort by arrival time
        Arrays.sort(p, Comparator.comparingInt(a -> a.at));

        Queue<Process> q = new LinkedList<>();
        int time = 0, completed = 0, index = 0;

        while (completed < n) {
            // Add all processes that have arrived till current time
            while (index < n && p[index].at <= time) {
                q.add(p[index]);
                index++;
            }

            if (q.isEmpty()) {
                time++; // CPU idle time
                continue;
            }

            Process curr = q.poll();

            int execTime = Math.min(tq, curr.rt);
            curr.rt -= execTime;
            time += execTime;

            // Add newly arrived processes during execution
            while (index < n && p[index].at <= time) {
                q.add(p[index]);
                index++;
            }

            if (curr.rt > 0) {
                q.add(curr); // Put back into queue if still not completed
            } else {
                curr.ct = time;
                curr.tat = curr.ct - curr.at;
                curr.wt = curr.tat - curr.bt;
                completed++;
            }
        }

        System.out.println("\nRound Robin Scheduling:");
        System.out.println("PID\tAT\tBT\tCT\tTAT\tWT");

        double totalTAT = 0, totalWT = 0;
        for (Process proc : p) {
            System.out.println(proc.pid + "\t" + proc.at + "\t" + proc.bt + "\t" + proc.ct + "\t" + proc.tat + "\t" + proc.wt);
            totalTAT += proc.tat;
            totalWT += proc.wt;
        }

        System.out.println("\nAverage Turnaround Time = " + (totalTAT / n));
        System.out.println("Average Waiting Time = " + (totalWT / n));

        sc.close();
    }
}
