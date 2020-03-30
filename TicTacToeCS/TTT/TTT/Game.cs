using System;

namespace TTT
{
    public class Game
    {
        public int[,] Gamedata { get; set; } = new int[3, 3];
        public void PrintData()
        {
            for(int r=0; r< Gamedata.GetLength(0); r++)
            {
                for (int c = 0; c < Gamedata.GetLength(1); c++)
                {
                    Console.Write("[" + Gamedata[r, c] + "]");
                }
                Console.WriteLine();
            }
        }
        public void Reset()
        {
            for (int r = 0; r < Gamedata.GetLength(0); r++)
            {
                for (int c = 0; c < Gamedata.GetLength(1); c++)
                {
                    Gamedata[r, c] = 0;
                }
            }
        }
        public void Mark(int x, int y, int player)
        {
            Gamedata[y, x] = player;
        }
        public void MakeMove(int player)
        {
            bool moveMade = false;
            bool validMove = false;
            bool clearMove = false;
            int x, y;
            do
            {
                Console.WriteLine("It is player" + player + "'s turn");
                Console.WriteLine("Enter X Position:");
                x = int.Parse(Console.ReadLine());
                Console.WriteLine("Enter Y Position:");
                y = int.Parse(Console.ReadLine());
                if (x>=0 && x <= 2 && y>=0 && y <= 2) { validMove = true; }
                if (validMove)
                {
                    if (Gamedata[y, x] == 0) { clearMove = true; }
                }
                if (clearMove)
                {
                    Mark(x,y,player);
                    PrintData();
                    moveMade = true;
                }

            } while (moveMade == false);
            
        }
        public bool CheckWin(int player)
        {
        // column 
            if (Gamedata[0, 0] == player && Gamedata[1, 0] == player && Gamedata[2, 0] == player) { return true; }
            if (Gamedata[0, 1] == player && Gamedata[1, 1] == player && Gamedata[2, 1] == player) { return true; }
            if (Gamedata[0, 2] == player && Gamedata[1, 2] == player && Gamedata[2, 2] == player) { return true; }
        // row
            if (Gamedata[0, 0] == player && Gamedata[0, 1] == player && Gamedata[0, 2] == player) { return true; }
            if (Gamedata[1, 0] == player && Gamedata[1, 1] == player && Gamedata[1, 2] == player) { return true; }
            if (Gamedata[2, 0] == player && Gamedata[2, 1] == player && Gamedata[2, 2] == player) { return true; }
        //diagonal
            if (Gamedata[0, 0] == player && Gamedata[1, 1] == player && Gamedata[2, 2] == player) { return true; }
            if (Gamedata[0, 2] == player && Gamedata[1, 1] == player && Gamedata[2, 0] == player) { return true; }
            return false;
        }
        public bool CheckFull()
        {
            foreach(int i in Gamedata)
            {
                if (i == 0) { return false; }
            }
            return true;
        }
        public bool CheckDraw()
        {
            bool full = CheckFull();
            bool noWin1 = !(CheckWin(1));
            bool noWin2 = !(CheckWin(2));
            if (full && noWin1 && noWin2) { return true; }
            return false;
        }
        public void PlayAgain(ref bool playgame)
        {
            Console.WriteLine("Play Again? y/n");
            String answer = Console.ReadLine();
            if (answer == "n" || answer == "no")
            {
                playgame = false;
                Environment.Exit(0);
            }
            Reset();
        }
        public void CheckGame(ref bool playgame)
        {
            String outcome = "";
            bool gameOver = false;
            if (CheckWin(1)) { outcome = "Player1 won"; gameOver = true; }
            if (CheckWin(2)) { outcome = "Player2 won"; gameOver = true; }
            if (CheckDraw()) { outcome = "It was a draw"; gameOver = true; }
            if (gameOver)
            {
                Console.WriteLine(outcome);
                PlayAgain(ref playgame);
            }
        }
    }
}
