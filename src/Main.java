import java.util.Scanner;

public class Main
{
    public static int velikostPole = 10;
    public static int pocetZnakuPoSobe = 4;
    public static char hrac1 = 'X';
    public static char hrac2 = 'O';
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        int[][] pole = new int[velikostPole][velikostPole];
        int[] bod;
        int[] vyhra = new int[2];

        for(int Y = 0; Y < velikostPole; Y++)
        {
            for(int X = 0; X < velikostPole; X++)
            {
                pole[Y][X] = 0;
            }
        }

        do //Game loop
        {
                vypsatPole(pole);

                System.out.println("Souřadnice je mimo pole. Zadejte znovu.");
                bod = zadatBod(1); //Zadání bodu hráčem 1
                pole[bod[0] - 1][bod[1] - 1] = 1; //Uložení pozice bodu hráče 1 do pole


                //TODO Dodělat exception pro již zadané pole

                vyhra[0] = checkVyhra(pole, hrac1, bod);
                if(vyhra[0] == 1)
                {
                    vyhra[1] = hrac1;
                }

            if(vyhra[0] == 0)
            {
                vypsatPole(pole);

                try {
                    bod = zadatBod(2); //Zadání bodu hráče 1
                }
                catch (Exception souradniceOutOfBounds)
                {
                    System.out.println("Souřadnice jste zadal chybně. Zadejte znovu.");
                    bod = zadatBod(2);
                }
                pole[bod[0] - 1][bod[1] - 1] = 2; //Uložení pozice bodu hráče 2 do pole

                vyhra[0] = checkVyhra(pole, hrac2, bod);
                if(vyhra[0] == 1)
                {
                    vyhra[1] = hrac2;
                }
            }
            if(vyhra[0] == 0)
            {
                clearScreen();
            }
        }
        while(vyhra[0] == 0);

        System.out.println("Hráč " + vyhra[1] + "vyhrál!");
        
    }

    public static void vypsatPole(int[][] pole)
    {
        System.out.print("   ");
        for(int X = 0; X < velikostPole; X++)
        {
            System.out.print(String.format("%02d", X+1) + " ");
        }
        System.out.println("|");

        for(int Y = 0; Y < velikostPole; Y++) //Osa Y
        {
            System.out.print(String.format("%02d", Y+1) + " ");
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
                    System.out.print("-- ");
                }
            }
            System.out.print("|");
            System.out.println();
        }
        System.out.println(" --------------------------------");
    }
    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int zadatSouradnici(char osa)
    {
        int souradnice;
        System.out.print("Pozice " + osa +": ");

        souradnice = Integer.parseInt(sc.nextLine());
        if(souradnice < 0 || souradnice > velikostPole)
        {
            throw new ArrayIndexOutOfBoundsException("Pozice X je zadána chybně. Zadejte znovu.");
        }
        return souradnice;
    }
    public static int[] zadatBod(int hrac)
    {
        int[] bod = new int[2];

        System.out.println("Hráč " + hrac + ": Zadejte místo na poli: ");

        try
        {
            bod[1] = zadatSouradnici('X');
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.getMessage());
            bod[1] = zadatSouradnici('X');
        }

        try
        {
            bod[0] = zadatSouradnici('Y');
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println(e.getMessage());
            bod[0] = zadatSouradnici('Y');
        }

        return bod;
    }


    /**
     *
     * @param pole Hrací pole
     * @param bod Bod, který zadal hráč
     * @param hrac Hráč
     * @param inkrementaceX O kolik se má posunout kontrola výhry na poli na ose X
     * @param inkrementaceY O kolik se má posunout kontrola výhry na poli na ose Y
     * @return Výhra, nebo ne (1, 0)
     */
    public static int checkVyhraOsa(int[][] pole, int[] bod, int hrac, int inkrementaceX, int inkrementaceY)
    {
        int znakyPoSobe = 0;
        int[] kontrolovanyBod = new int[2];

        kontrolovanyBod[1] = bod[1] - (pocetZnakuPoSobe - 1) * inkrementaceX; //Osa Y
        kontrolovanyBod[0] = bod[0] - (pocetZnakuPoSobe - 1) * inkrementaceY; //Osa X

        for(int i = 0; i < 2 * pocetZnakuPoSobe - 1; i++)
        {
            if(kontrolovanyBod[1] > 0 && kontrolovanyBod[1] <= velikostPole && kontrolovanyBod[0] > 0 && kontrolovanyBod[0] <= velikostPole)
            {
                if(pole[kontrolovanyBod[0]][kontrolovanyBod[1]] == hrac)
                {
                    znakyPoSobe++;
                }
                else
                {
                    znakyPoSobe = 0;
                }
            }

            kontrolovanyBod[1] += inkrementaceX;
            kontrolovanyBod[0] += inkrementaceY;
        }
        if(znakyPoSobe == pocetZnakuPoSobe)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
    public static int checkVyhra(int [][] pole, int hrac, int[] bod)
    {
        if(        checkVyhraOsa(pole, bod, hrac, 1, 0) == 1
                || checkVyhraOsa(pole, bod, hrac, 0, 1) == 1
                || checkVyhraOsa(pole, bod, hrac, 1, 1) == 1
                || checkVyhraOsa(pole, bod, hrac, 1, -1) == 1)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}