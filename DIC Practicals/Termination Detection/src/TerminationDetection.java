import java.util.ArrayList;
import java.util.List;

class Process {
    private int processId;
    private boolean active;
    private List<Process> neighbors;

    public Process(int processId) {
        this.processId = processId;
        this.active = true;
        this.neighbors = new ArrayList<>();
    }

    public void addNeighbor(Process neighbor) {
        neighbors.add(neighbor);
    }

    public void receiveToken(boolean token) {
        // Huang's termination detection logic
        if (token && active) {
            active = false;
            for (Process neighbor : neighbors) {
                neighbor.receiveToken(true);
            }
        }
    }

    public boolean isActive() {
        return active;
    }
}

public class TerminationDetection {
    public static void main(String[] args) {
        // Create processes
        Process p0 = new Process(0);
        Process p1 = new Process(1);
        Process p2 = new Process(2);

        // Establish connections (you might have a more sophisticated way in a real implementation)
        p0.addNeighbor(p1);
        p1.addNeighbor(p0);
        p1.addNeighbor(p2);
        p2.addNeighbor(p1);

        // Simulate termination and pass the token
        p0.receiveToken(true);

        // Check for termination
        System.out.println("Process 0 active: " + p0.isActive());
        System.out.println("Process 1 active: " + p1.isActive());
        System.out.println("Process 2 active: " + p2.isActive());
    }
}
