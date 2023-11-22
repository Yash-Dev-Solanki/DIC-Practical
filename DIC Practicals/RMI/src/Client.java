import java.rmi.Naming;

public class Client {

    public static void main(String[] args) {
        try {
            RemoteInterface remoteObject = (RemoteInterface) Naming.lookup("rmi://localhost/RemoteObject");

            // Call the remote method
            String message = remoteObject.sayHello();
            System.out.println("Message from server: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
