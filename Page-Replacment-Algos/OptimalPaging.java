import java.util.*;

class OptimalPaging {
    int frames;
    int[] memory;
    int faults = 0;

    OptimalPaging(int frames) {
        this.frames = frames;
        this.memory = new int[frames];
        Arrays.fill(memory, -1); 
    }

    void run(int[] ref) {
        System.out.println("\nOptimal Paging Simulation:");
        for (int i = 0; i < ref.length; i++) {
            int page = ref[i];
            if (inMemory(page)) {
                System.out.println("Page " + page + " -> Hit,   Frames: " + Arrays.toString(memory));
            } else {
                faults++;
                replacePage(page, ref, i + 1); 
                System.out.println("Page " + page + " -> Fault, Frames: " + Arrays.toString(memory));
            }
        }
        System.out.println("Total Faults = " + faults);
    }

    boolean inMemory(int page) {
        for (int p : memory) if (p == page) return true;
        return false;
    }

    void replacePage(int page, int[] ref, int nextIndex) {
        
        for (int i = 0; i < frames; i++) {
            if (memory[i] == -1) {
                memory[i] = page;
                return;
            }
        }

        int farthestIndex = -1, pos = -1;
        for (int i = 0; i < frames; i++) {
            int j;
            for (j = nextIndex; j < ref.length; j++) {
                if (memory[i] == ref[j]) break; 
            }
            if (j == ref.length) { 
                pos = i;
                break;
            } else if (j > farthestIndex) { 
                farthestIndex = j;
                pos = i;
            }
        }
        memory[pos] = page;
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

        OptimalPaging pg = new OptimalPaging(f);
        pg.run(ref);

        sc.close();
    }
}
