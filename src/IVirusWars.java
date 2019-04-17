import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IVirusWars extends Remote {
    void Connect(IVirusWars client) throws RemoteException;
    void ShowClientMessage(String message) throws RemoteException;
    void SetClientNum(int num) throws RemoteException;
    int GetClientNum() throws RemoteException;
    void Display(char[][] table) throws RemoteException;
    int[][] WaitForTurn() throws  RemoteException;
}
