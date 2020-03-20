import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {
    public static void main(String[] args){
        try {
            Registry registry= LocateRegistry.getRegistry("localhost", 8086);
            RmiTestInterface t=(RmiTestInterface) registry.lookup("test");
            System.out.println("Client:"+t.getTest());
        } catch (RemoteException e) {
            e.printStackTrace();
        }catch (NotBoundException e) {
            e.printStackTrace();
        }
    }
}