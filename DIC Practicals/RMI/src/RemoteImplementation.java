import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteImplementation extends UnicastRemoteObject implements RemoteInterface {

    public RemoteImplementation() throws RemoteException {
        // Constructor
    }

    // Implement the method from the interface
    public String sayHello() throws RemoteException {
        return "Hello, this is the server speaking!";
    }
}
