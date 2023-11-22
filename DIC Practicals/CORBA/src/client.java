import Example.*;

public class client {
    public static void main(String[] args) {
        org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args, null);

        org.omg.CORBA.Object obj = orb.string_to_object("corbaname::your_cpp_server_address/ExampleInterface");
        ExampleInterface example = ExampleInterfaceHelper.narrow(obj);

        String response = example.hello();
        System.out.println("Response from C++ server: " + response);
    }
}
