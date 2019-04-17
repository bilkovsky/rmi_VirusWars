import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;

public class Client extends UnicastRemoteObject implements IVirusWars, Serializable {

    String SERVICE_PATH = "rmi://localhost/ServerService";
    private  int playerNum = 0;
    public  Client() throws RemoteException
    {
        try{
            IVirusWars server = (IVirusWars)Naming.lookup(SERVICE_PATH);
            server.Connect(this);
        }catch (RemoteException e) {
            System.err.println("RemoteException : "+e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
            System.exit(2);
        }
    }

    public  static  void main(String[] args) {
        try{
            new Client();
        }catch (RemoteException e) {
            System.err.println("RemoteException : "+e.getMessage());
            System.exit(1);
        } catch (Exception e) {
            System.err.println("Exception : " + e.getMessage());
            System.exit(2);
        }
    }
    @Override
    public void Connect(IVirusWars client)
    {

    }
    @Override
    public void ShowClientMessage(String message)
    {
        System.out.println(message);
    }
    @Override
    public  void SetClientNum(int num)
    {
        playerNum = num;
    }
    @Override
    public  int GetClientNum()
    {
        return playerNum;
    }
    @Override public  void Display(char[][] table)
    {
        System.out.println("  1  2 3 4 5  6 7 8 9 10");
        for(int i = 0; i < table.length; i++)
        {
            System.out.print((char)('A' + i));
            System.out.print(' ');
            for (int j = 0; j < table[0].length; j++)
            {
                System.out.print(table[i][j] + " ");
            }
            System.out.println();
        }
    }
}
