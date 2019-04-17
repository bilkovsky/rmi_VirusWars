import java.util.Scanner;

public class Main {

    private static char field[][];
    private static void StartGame()
    {
        field = new char[10][10];
        for(int i = 0; i < field.length; i++)
        {
            for (int j = 0; j < field[0].length; j++)
            {
                field[i][j] = '';
            }
        }
        System.out.println("Welcome to the virus wars game");
    }
    public static void main(String[] args)
    {
        StartGame();
        Display();
        String play = "";
        while (!play.equals("end"))
        {
            Scanner scan = new Scanner(System.in);
            play = scan.next();
            MakeTurn(ParsePosition((play)));
            Display();
        }
    }
    private static void MakeTurn(int[] play)
    {   if(play == null)
        {
            return;
        }
        field[play[0]][play[1]] = 1;
    }
    private static int[] ParsePosition(String play)
    {
        int result[] = new int[2];
        if(!play.isEmpty()  && IsLetter(play.charAt(0)) && IsNumber(play.substring(1)))
        {
            System.out.println("Correct input");
            result[0] = play.toLowerCase().charAt(0) - 'a';
            result[1] = Integer.parseInt(play.toLowerCase().substring(1)) - 1;
            System.out.println(result[0]);
            System.out.println(result[1]);
            return result;
        }
        System.out.println("Incorrect input");
        return null;
    }
    private static  boolean IsLetter(char c)
    {
        if(c == 'a' || c == 'b' || c == 'c' || c == 'd'  || c == 'e'  || c == 'f'  || c == 'g' || c == 'h' || c == 'i' || c == 'j')
        {
            return true;
        }
        return  false;
    }
    private static  boolean IsNumber(String c)
    {
        if(c.equals("1") || c.equals("2") || c.equals("3") || c.equals("4")  || c.equals("5")  || c.equals("6")  || c.equals("7") || c.equals("8") || c.equals("9") || c.equals("10"))
        {
            return true;
        }
        return  false;
    }
    private static void Display()
    {
        System.out.println("  1  2 3 4 5  6 7 8 9 10");
        for(int i = 0; i < field.length; i++)
        {
            System.out.print((char)('A' + i));
            System.out.print(' ');
            for (int j = 0; j < field[0].length; j++)
            {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }
 }
