import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {

    public static void main(String[] args) {
        try {
            RemoteInterface remoteObject = new RemoteImplementation();

            // Create and start a registry on the default port
            LocateRegistry.createRegistry(1099);

            // Bind the remote object's instance to a name
            Naming.rebind("RemoteObject", remoteObject);

            System.out.println("Server is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
