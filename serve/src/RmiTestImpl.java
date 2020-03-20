import java.io.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/*接口的实现：*/
public class RmiTestImpl implements RmiTestInterface{
    public RmiTestImpl() throws RemoteException {

    }
    public static void readToBuffer(StringBuffer buffer, String filePath) throws IOException {
        InputStream is = new FileInputStream(filePath);
        String line; // 用来保存每行读取的内容
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        line = reader.readLine(); // 读取第一行
        while (line != null) { // 如果 line 为空说明读完了
            buffer.append(line); // 将读到的内容添加到 buffer 中
            buffer.append("\n"); // 添加换行符
            line = reader.readLine(); // 读取下一行
        }
        reader.close();
        is.close();
    }
    @Override
    public String getTest() throws IOException {
        StringBuffer sb = new StringBuffer();
        RmiTestImpl.readToBuffer(sb, "C:\\Users\\38138\\Desktop\\rmi-demo\\serve\\src\\flag.txt");
        //sb.toString();
        return  sb.toString();
    }
    public static void main(String[] args) throws RemoteException {
        RmiTestImpl t=new RmiTestImpl();
        RmiTestInterface tt=(RmiTestInterface) UnicastRemoteObject.exportObject(t, 0);
        Registry registry= LocateRegistry.createRegistry(8086);
        registry.rebind("test", tt);
        System.out.println("server is start");
    }

}