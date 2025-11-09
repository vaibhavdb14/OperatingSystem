import java.util.*;

class LRUPaging {
    int frames;
    int[] memory;
    int[] lastUsed;  // to track last access time
    int time = 0;    // global counter
    int faults = 0;

    LRUPaging(int frames) {
        this.frames = frames;
        this.memory = new int[frames];
        this.lastUsed = new int[frames];
        Arrays.fill(memory, -1); // empty slots
        Arrays.fill(lastUsed, -1);
    }

    void run(int[] ref) {
        System.out.println("\nLRU Paging Simulation:");
        for (int page : ref) {
            time++;
            if (inMemory(page)) {
                updateTime(page);
                System.out.println("Page " + page + " -> Hit,   Frames: " + Arrays.toString(memory));
            } else {
                faults++;
                replacePage(page);
                System.out.println("Page " + page + " -> Fault, Frames: " + Arrays.toString(memory));
            }
        }
        System.out.println("Total Faults = " + faults);
    }

    boolean inMemory(int page) {
        for (int i = 0; i < frames; i++) {
            if (memory[i] == page) return true;
        }
        return false;
    }

    void updateTime(int page) {
        for (int i = 0; i < frames; i++) {
            if (memory[i] == page) {
                lastUsed[i] = time;
                break;
            }
        }
    }

    void replacePage(int page) {
        
        for (int i = 0; i < frames; i++) {
            if (memory[i] == -1) {
                memory[i] = page;
                lastUsed[i] = time;
                return;
            }
        }
        
        int lruIndex = 0;
        int minTime = lastUsed[0];
        for (int i = 1; i < frames; i++) {
            if (lastUsed[i] < minTime) {
                minTime = lastUsed[i];
                lruIndex = i;
            }
        }
        memory[lruIndex] = page;
        lastUsed[lruIndex] = time;
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

        LRUPaging pg = new LRUPaging(f);
        pg.run(ref);

        sc.close();
    }
}
