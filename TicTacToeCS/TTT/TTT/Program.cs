using System;

namespace TTT
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Welcome to the Tic Tac Toe Program");
            Console.WriteLine("Instructions:");
            Console.WriteLine("when marking enter position x and then enter y");
            String[,] grid = {
                { "0,0", "1,0", "2,0" },
                { "0,1", "1,1", "2,1" },
                { "0,2", "1,2", "2,2" }
                };
            int rows = grid.GetLength(0);
            int cols= grid.GetLength(1);
            for (int r = 0; r < rows; r++)
            {
                for (int c = 0; c < cols; c++)
                {
                    Console.Write("[" + grid[r, c] + "]");
                }
                Console.WriteLine();
            }
            Game game = new Game();
            game.PrintData();
            bool play = true;
            do
            {
                game.MakeMove(1);
                game.CheckGame(ref play);
                game.MakeMove(2);
                game.CheckGame(ref play);

            } while (play);
        }
    }
}
