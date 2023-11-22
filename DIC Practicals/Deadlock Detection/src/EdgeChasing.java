import java.util.ArrayList;
import java.util.List;

public class EdgeChasing {
    private int numProcesses;
    private int numResources;
    private int[][] allocationMatrix;
    private int[][] requestMatrix;
    private boolean[] markedProcesses;

    public EdgeChasing(int numProcesses, int numResources) {
        this.numProcesses = numProcesses;
        this.numResources = numResources;
        this.allocationMatrix = new int[numProcesses][numResources];
        this.requestMatrix = new int[numProcesses][numResources];
        this.markedProcesses = new boolean[numProcesses];
    }

    public void setAllocationMatrix(int[][] allocation) {
        this.allocationMatrix = allocation;
    }

    public void setRequestMatrix(int[][] request) {
        this.requestMatrix = request;
    }

    public boolean isDeadlockPresent() {
        List<Integer> unsafeProcesses = new ArrayList<>();
        for (int process = 0; process < numProcesses; process++) {
            if (!markedProcesses[process]) {
                boolean isSafe = isSafe(process);
                if (!isSafe) {
                    unsafeProcesses.add(process);
                }
            }
        }
        if (!unsafeProcesses.isEmpty()) {
            System.out.println("Deadlock detected! Potentially unsafe processes: " + unsafeProcesses);
            return true;
        } else {
            System.out.println("No deadlock detected.");
            return false;
        }
    }
    private boolean isSafe(int process) {
        boolean safe = false;
        for (int resource = 0; resource < numResources; resource++) {
            if (requestMatrix[process][resource] > 0) {
                // Attempt to satisfy the request temporarily
                int[] tempAvailable = calculateTempAvailable(resource);
                if (canAllocate(process, tempAvailable)) {
                    markedProcesses[process] = true;
                    safe = true;
                    break;
                }
            }
        }
        return safe;
    }

    private int[] calculateTempAvailable(int resource) {
        int[] tempAvailable = new int[numResources];
        // Calculate available resources if the request was granted temporarily
        for (int i = 0; i < numResources; i++) {
            tempAvailable[i] = allocationMatrix[i][resource] + requestMatrix[i][resource];
        }
        return tempAvailable;
    }

    private boolean canAllocate(int process, int[] tempAvailable) {
        for (int i = 0; i < numResources; i++) {
            if (requestMatrix[process][i] > tempAvailable[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int numProcesses = 5;
        int numResources = 3;

        int[][] allocation = {
                {0, 1, 0},
                {2, 0, 0},
                {3, 0, 2},
                {2, 1, 1},
                {0, 0, 2}
        };

        int[][] request = {
                {0, 0, 0},
                {2, 0, 2},
                {0, 0, 0},
                {1, 0, 0},
                {0, 0, 2}
        };

        EdgeChasing detector = new EdgeChasing(numProcesses, numResources);
        detector.setAllocationMatrix(allocation);
        detector.setRequestMatrix(request);
        detector.isDeadlockPresent();
    }
}
