import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.util.Scanner;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;

public class TTTgui {
	
	private static int[][] board = { 
			{ 0, 0, 0 },
			{ 0, 0, 0 },
			{ 0, 0, 0 }
			};
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
	
	public void makemove(int[][] board, int x, int y, int player) {
		boolean clear = true;
		boolean valid = true;
		do{
			valid = true;
			// position x
			if(x != 0 && x != 1 && x != 2 )
				valid = false;
			// position y
			if(y != 0 && y != 1 && y != 2 )
				valid = false;
			if(valid) 
			if(board[y][x] == 0) {
				board[y][x] = player;
				clear = false;
			}
		}while(clear == true);
	}
	protected Shell shell;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TTTgui window = new TTTgui();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		do {
			/*
			 * do the game
			 */
			
			if (!display.readAndDispatch())
				display.sleep();
		}while (!shell.isDisposed());
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setSize(313, 373);
		shell.setText("SWT Application");
		shell.setLayout(null);
		
		TTTgui run = new TTTgui();
		
		Label lblTicTacToe = new Label(shell, SWT.NONE);
		lblTicTacToe.setBounds(10, 11, 194, 14);
		lblTicTacToe.setText("Tic Tac Toe");
	
		Button btnNewButtonzz = new Button(shell, SWT.NONE);
		btnNewButtonzz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtonzz.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtonzz.setText("X");
						run.makemove(board, 0, 0, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtonzz.setText("O");
						run.makemove(board, 0, 0, 2);
						lblTicTacToe.setText("Player One");
					}
				}
				if(run.checkwin(board, 1)) 
					lblTicTacToe.setText("Player One Won");
				if(run.checkwin(board, 2)) 
					lblTicTacToe.setText("Player Two Won");
				if(run.checkdraw(board)) {
					lblTicTacToe.setText("It was a draw");
				}
			}
		});
		btnNewButtonzz.setBounds(10, 31, 94, 94);
		btnNewButtonzz.setText("-");
		
		Button btnNewButtonoz = new Button(shell, SWT.NONE);
		btnNewButtonoz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtonoz.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtonoz.setText("X");
						run.makemove(board, 1, 0, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtonoz.setText("O");
						run.makemove(board, 1, 0, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtonoz.setText("-");
		btnNewButtonoz.setBounds(110, 31, 94, 94);
		
		Button btnNewButtontz = new Button(shell, SWT.NONE);
		btnNewButtontz.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtontz.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtontz.setText("X");
						run.makemove(board, 2, 0, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtontz.setText("O");
						run.makemove(board, 2, 0, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtontz.setText("-");
		btnNewButtontz.setBounds(210, 31, 94, 94);
		
		Button btnNewButtonzo = new Button(shell, SWT.NONE);
		btnNewButtonzo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtonzo.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtonzo.setText("X");
						run.makemove(board, 0, 1, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtonzo.setText("O");
						run.makemove(board, 0, 1, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtonzo.setText("-");
		btnNewButtonzo.setBounds(10, 131, 94, 94);
		
		Button btnNewButtonoo = new Button(shell, SWT.NONE);
		btnNewButtonoo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtonoo.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtonoo.setText("X");
						run.makemove(board, 1, 1, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtonoo.setText("O");
						run.makemove(board, 1, 1, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtonoo.setText("-");
		btnNewButtonoo.setBounds(110, 131, 94, 94);
		
		Button btnNewButtonto = new Button(shell, SWT.NONE);
		btnNewButtonto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtonto.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtonto.setText("X");
						run.makemove(board, 2, 1, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtonto.setText("O");
						run.makemove(board, 2, 1, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtonto.setText("-");
		btnNewButtonto.setBounds(210, 131, 94, 94);
		
		Button btnNewButtonzt = new Button(shell, SWT.NONE);
		btnNewButtonzt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtonzt.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtonzt.setText("X");
						run.makemove(board, 0, 2, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtonzt.setText("O");
						run.makemove(board, 0, 2, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtonzt.setText("-");
		btnNewButtonzt.setBounds(10, 231, 94, 94);
		
		Button btnNewButtonot = new Button(shell, SWT.NONE);
		btnNewButtonot.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtonot.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtonot.setText("X");
						run.makemove(board, 1, 2, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtonot.setText("O");
						run.makemove(board, 1, 2, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtonot.setText("-");
		btnNewButtonot.setBounds(110, 231, 94, 94);
		
		Button btnNewButtontt = new Button(shell, SWT.NONE);
		btnNewButtontt.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				String isclear = btnNewButtontt.getText();
				if (isclear.equals("-")) {
					String state = lblTicTacToe.getText();
					if(state.equals("Tic Tac Toe") || state.equals("Player One")) {
						btnNewButtontt.setText("X");
						run.makemove(board, 2, 2, 1);
						lblTicTacToe.setText("Player Two");
					}
					if(state.equals("Player Two")) {
						btnNewButtontt.setText("O");
						run.makemove(board, 2, 2, 2);
						lblTicTacToe.setText("Player One");
					}
					if(run.checkwin(board, 1)) 
						lblTicTacToe.setText("Player One Won");
					if(run.checkwin(board, 2)) 
						lblTicTacToe.setText("Player Two Won");
					if(run.checkdraw(board)) {
						lblTicTacToe.setText("It was a draw");
					}
				}
			}
		});
		btnNewButtontt.setText("-");
		btnNewButtontt.setBounds(210, 231, 94, 94);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				btnNewButtontt.setText("-");
				btnNewButtonot.setText("-");
				btnNewButtonzt.setText("-");
				btnNewButtonto.setText("-");
				btnNewButtonoo.setText("-");
				btnNewButtonzo.setText("-");
				btnNewButtontz.setText("-");
				btnNewButtonoz.setText("-");
				btnNewButtonzz.setText("-");
				lblTicTacToe.setText("Tic Tac Toe");	
				run.reset(board);
			}
		});
		btnNewButton.setBounds(210, 5, 94, 27);
		btnNewButton.setText("Reset");

	}
}
