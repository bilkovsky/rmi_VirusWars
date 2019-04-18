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
                if(IsValid(result[turnCounter][0],result[turnCounter][1]))
                {
                    PutVirus(result[turnCounter],playerNum);
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
        if(playerNum == 1 && i == 0 && j == 0 && field[0][0] == 152)
        {
            return true;
        }
        if(playerNum == 2 && i == 9 && j == 9 && field[9][9] == 152)
        {
            return true;
        }
        if(playerNum == 1 && field[i][j] == 'X' && field[i][j] == '+')
        {
            return false;
        }
        if(playerNum == 2 && field[i][j] == 'O' && field[i][j] == 92)
        {
            return false;
        }
        return  CheckNeigh(i,j);
    }
    private boolean CheckNeigh(int i,int j)
    {
        if(playerNum == 1)
        {
            int left = j - 1;
            int right = j + 1;
            int bot = i - 1;
            int top = i +1;
            if(left == -1)
            {
                left++;
            }
            if(right == 10)
            {
                right--;
            }
            if(top == -1)
            {
                top++;
            }
            if(bot == 10)
            {
                bot--;
            }
            for(;left <= right; left++)
            {
                for(;top <= bot ; top++)
                {
                    if(left != i && top != j)
                    {
                        if (field[left][top] == 'X' || field[left][top] == '+')
                        {
                            return true;
                        }
                    }

                }
            }
            return  false;
        }
        else {
            int left = j - 1;
            int right = j + 1;
            int bot = i - 1;
            int top = i +1;
            if(left == -1)
            {
                left++;
            }
            if(right == 10)
            {
                right--;
            }
            if(top == -1)
            {
                top++;
            }
            if(bot == 10)
            {
                bot--;
            }
            for(;left <= right; left++)
            {
                for(;top <= bot ; top++)
                {
                    if(left != i && top != j)
                    {
                        if (field[left][top] == 'O' || field[left][top] == 92)
                        {
                            return true;
                        }
                    }

                }
            }
            return  false;
        }

    }
    private  void PutVirus(int[] turn,int player) {
        switch (player) {
            case 0:
                if (field[turn[0]][turn[1]] == 152)
                {
                    field[turn[0]][turn[1]] = 'X';
                }
                else
                {
                    field[turn[0]][turn[1]] = '+';
                }
                break;
            case 1:
                if (field[turn[0]][turn[1]] == 152)
                {
                    field[turn[0]][turn[1]] = 'O';
                }
                else
                {
                    field[turn[0]][turn[1]] = 92;
                }
                break;
                default:
                    break;
        }
    }
}
