import java.rmi.Naming;

public class Client {

    public static void main(String[] args) {
        try {
            RemoteInterface remoteObject = (RemoteInterface) Naming.lookup("rmi://localhost/RemoteObject");

            // Call the remote method
            int result = remoteObject.add(4, 5);
            System.out.println("Result from server: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
