import java.io.Serializable;
import java.rmi.Naming;
import java.rmi.server.UnicastRemoteObject;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Client extends UnicastRemoteObject implements IVirusWars, Serializable {

    String SERVICE_PATH = "rmi://localhost/ServerService";
    private  int playerNum = 0;
    static  char[][] field;
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
        field = table;
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
    @Override
    public  int[][] WaitForTurn()
    {
        System.out.println("It's your turn!");
        int turnCounter = 0;
        int[][] result = new int[3][2];
        Scanner scanner = new Scanner(System.in);
        while(turnCounter < 3)
        {
            String turn = scanner.next();
            if(turn.equals("miss"))
            {
                return  null;
            }
            if(!turn.isEmpty()  && IsLetter(turn.toUpperCase().charAt(0)) && IsNumber(turn.substring(1)))
            {
                result[turnCounter][0] = turn.toUpperCase().charAt(0) - 'A';
                result[turnCounter][1] = Integer.parseInt(turn.substring(1)) - 1;
                if(Is(result[turnCounter][0],result[turnCounter][1]))
                {
                    turnCounter++;
                }
                else
                {
                    System.out.println("You cant put virus in this field");
                }
            }
            else
            {
                System.out.println("Input your turn like a8,J3,f10 etc or \"miss\" to miss the turn \n Remember first positions is a1 and c`0");
            }
        }
        return result;
    }

    private  boolean IsNumber(String number)
    {
        int num = Integer.parseInt(number);
        return num > 0 && num < 11;
    }
    private  boolean IsLetter(char c)
    {
        return c <=  'J' && c >=  'A';
    }
    private  boolean IsValid(int i,int j)
    {
        if(playerNum == 1 && i == 0 && j = 0 && field[0][0] = 152)
        {
            return true;
        }
        return  true;
    }
    private  void PutVirus(int[] turn,int player) {
        char c;
        switch (player) {
            case 0:
                c = 'X';
                break;
            case 1:
                c = 'O';
                break;
            default:
                c = 'N';
                break;
        }
        field[turn[0]][turn[1]] = c;
    }
}
