import java.util.Arrays;
import java.util.Scanner;
public class TTTgame {
	private static int[][] board = {
	{ 0, 0, 0 },
	{ 0, 0, 0 },
	{ 0, 0, 0 }
	};
	private static boolean playgame = true;
	public void reset(int[][] board) {
		board[0][0] = 0;
		board[1][0] = 0;
		board[2][0] = 0;
		board[0][1] = 0;
		board[1][1] = 0;
		board[2][1] = 0;
		board[0][2] = 0;
		board[1][2] = 0;
		board[2][2] = 0;
		
	}
	public void printmatrix(int[][] board) {
		for (int i = 0; i < board.length; i++) {
			System.out.println(Arrays.toString(board[i]));
		}
	}
	public boolean checkwin(int[][] board, int player)  {
	// column 
		if (board[0][0] == player && board[1][0] == player && board[2][0] == player)
			return true;
		if (board[0][1] == player && board[1][1] == player && board[2][1] == player)
			return true;
		if (board[0][2] == player && board[1][2] == player && board[2][2] == player)
			return true;
	// row
		if (board[0][0] == player && board[0][1] == player && board[0][2] == player)
			return true;
		if (board[1][0] == player && board[1][1] == player && board[1][2] == player)
			return true;
		if (board[2][0] == player && board[2][1] == player && board[2][2] == player)
			return true;
	//diagonal
		if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
			return true;
		if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
			return true;
		return false;
	}
	public boolean checkdraw(int[][] board)  {
		if (	board[0][0] != 0 &&
				board[1][0] != 0 &&
				board[2][0] != 0 &&
				board[1][1] != 0 &&
				board[2][1] != 0 &&
				board[0][2] != 0 &&
				board[1][2] != 0 &&
				board[2][2] != 0) {
			if(this.checkwin(board, 1) == false && this.checkwin(board, 2) == false)
				return true;
		}
		return false;
	}
	public void checkstuff(Scanner key) {
		if(this.checkwin(board, 1)) {
			System.out.println("Player one won");
			this.printmatrix(board);
			System.out.println("Play Again? y/n");
			String answer = key.next();
			if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
				playgame = false;
			this.reset(board);
		}
		if(this.checkwin(board, 2)) {
			System.out.println("Player two won");
			this.printmatrix(board);
			System.out.println("Play Again? y/n");
			String answer = key.next();
			if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
				playgame = false;
			this.reset(board);
		}
		if(this.checkdraw(board)) {
			System.out.println("It was a draw");
			this.printmatrix(board);
			System.out.println("Play Again? y/n");
			String answer = key.next();
			if(answer.equalsIgnoreCase("n") || answer.equalsIgnoreCase("no"))
				playgame = false;
			this.reset(board);
		}
	}
	public void mark(int[][] board, int x, int y, int player) {
		// player = 1 or 2
		board[y][x] = player;
	}
	public void makemove(int[][] board, Scanner key, String player) {
		boolean clear = false;
		boolean valid = true;
		int onex, oney;
		do{
			valid = true;
			System.out.println("It is player " + player + "'s turn");
			this.printmatrix(board);
			System.out.println("enter position x:");
			onex = key.nextInt();
			if(onex != 0 && onex != 1 && onex != 2 )
				valid = false;
			System.out.println("enter position y:");
			oney = key.nextInt();
			if(oney != 0 && oney != 1 && oney != 2 )
				valid = false;
			if(valid) 
			if(board[oney][onex] == 0) {
				int num = 1;
				if(player.equals("two"))
					num = 2;
				this.mark(board, onex, oney, num);
				clear = true;
			}
		}while(clear == false);
	}
	public static void main(String [] args) {
		TTTgame run = new  TTTgame();
		Scanner kb = new Scanner(System.in);
		System.out.println("Welcome to the Tic Tac Toe Program");
		System.out.println("Instructions:");
		System.out.println("when marking enter position x and then enter y");
		String[][] grid = {
				{ "0,0", "1,0", "2,0" },
				{ "0,1", "1,1", "2,1" },
				{ "0,2", "1,2", "2,2" }
				};
		for (int i = 0; i < grid.length; i++) {
			System.out.println(Arrays.toString(grid[i]));
		}
		do {
			
			run.makemove(board, kb, "one");
			run.checkstuff(kb);
			run.makemove(board, kb, "two");
			run.checkstuff(kb);
			
		}while(playgame == true);
	}
}
