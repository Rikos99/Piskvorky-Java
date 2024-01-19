import java.util.Scanner;

public class Main
{
    public static int velikostPole = 10;
    public static char hrac1 = 'X';
    public static char hrac2 = 'O';
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        int[][] pole = new int[velikostPole][velikostPole];
        int[] bod = new int[2];

        for(int Y = 0; Y < velikostPole; Y++)
        {
            for(int X = 0; X < velikostPole; X++)
            {
                pole[Y][X] = 0;
            }
        }

        do
        {
            vypsatPole(pole);

            bod = zadatBod(1); //Zadání bodu hráče 1

            pole[bod[1] - 1][bod[0] - 1] = 1; //Uložení pozice bodu hráče 1 do pole

            vypsatPole(pole);

            bod = zadatBod(2); //Zadání bodu hráče 2

            pole[bod[1] - 1][bod[0] - 1] = 2; //Uložení pozice bodu hráče 2 do pole

            clearScreen();


            //TODO Algoritmus na check výhry
        }
        while(true); //TODO podmínka pro výhru

        //TODO Winning screen

    }

    public static void vypsatPole(int[][] pole)
    {
        System.out.println("---------------------------------");
        for(int Y = 0; Y < velikostPole; Y++) //Osa Y
        {
            System.out.print("| ");
            for(int X = 0; X < velikostPole; X++) //Osa X
            {
                if(pole[Y][X] == 1) //Hráč 1
                {
                    System.out.print(hrac1 + "  ");
                }
                else if(pole[Y][X] == 2) //Hráč 2
                {
                    System.out.print(hrac2 + "  ");
                }
                else //Volné pole
                {
                    System.out.print("-  ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println("---------------------------------");
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
    public static int[] zadatBod(int hrac)
    {
        int[] bod = new int[2];

        System.out.println("Hráč " + hrac + ": Zadejte místo na poli: ");

        System.out.print("Pozice X: ");
        do {
            bod[0] = Integer.parseInt(sc.nextLine());
            if(bod[0] < 0 || bod[0] > velikostPole)
            {
                System.out.println("Pozice X je zadána chybně. Zadejte znovu.");
            }
        }
        while(bod[0] < 0 || bod[0] > velikostPole);


        System.out.print("Pozice Y: ");
        do {
            bod[1] = Integer.parseInt(sc.nextLine());
            if(bod[1] < 0 || bod[1] > velikostPole)
            {
                System.out.println("Pozice Y je zadána chybně. Zadejte znovu.");
            }
        }
        while(bod[1] < 0 || bod[1] > velikostPole);

        return bod;
    }
}