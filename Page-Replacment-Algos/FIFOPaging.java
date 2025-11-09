import java.util.*;

class FIFOPaging {
    int frames;
    int[] memory;
    int pointer = 0;
    int faults = 0;

    FIFOPaging(int frames) {
        this.frames = frames;
        this.memory = new int[frames];
        Arrays.fill(memory, -1);
    }

    void run(int[] ref) {
        System.out.println("\nFIFO Paging Simulation:");
        for (int page : ref) {
            if (!inMemory(page)) {
                memory[pointer] = page;       
                pointer = (pointer + 1) % frames; 
                faults++;
                System.out.println("Page " + page + " -> Fault, Frames: " + Arrays.toString(memory));
            } else {
                System.out.println("Page " + page + " -> Hit, Frames: " + Arrays.toString(memory));
            }
        }
        System.out.println("Total Faults = " + faults);
    }

    boolean inMemory(int page) {
        for (int p : memory) if (p == page) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of frames: ");
        int f = sc.nextInt();

        System.out.print("Enter length of reference string: ");
        int n = sc.nextInt();

        int[] ref = new int[n];
        System.out.println("Enter reference string:");
        for (int i = 0; i < n; i++) ref[i] = sc.nextInt();

        FIFOPaging pg = new FIFOPaging(f);
        pg.run(ref);

        sc.close();
    }
}

