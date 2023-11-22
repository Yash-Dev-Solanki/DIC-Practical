import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteInterface extends Remote {
    // Declare the method to be invoked remotely
    int add(int a, int b) throws RemoteException;
}
