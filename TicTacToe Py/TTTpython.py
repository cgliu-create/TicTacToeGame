'''
Created on Dec 31, 2019

@author: cgliu
'''
class TTT:
    def __init__(self):
        self.board = [[0,0,0],
                      [0,0,0],
                      [0,0,0]]
        self.playgame = True
        self.answer = "yes"
    
    def reset(self):
        self.board[0][0] = 0
        self.board[1][0] = 0
        self.board[2][0] = 0
        self.board[0][1] = 0
        self.board[1][1] = 0
        self.board[2][1] = 0
        self.board[0][2] = 0
        self.board[1][2] = 0
        self.board[2][2] = 0
    def printmatrix(self):
        for arr in self.board:
            print(arr)
    def checkwin(self, player):
        # column 
        if self.board[0][0] == player and self.board[1][0] == player and self.board[2][0] == player:
            return True
        if self.board[0][1] == player and self.board[1][1] == player and self.board[2][1] == player:
            return True
        if self.board[0][2] == player and self.board[1][2] == player and self.board[2][2] == player:
            return True
        # row
        if self.board[0][0] == player and self.board[0][1] == player and self.board[0][2] == player:
            return True
        if self.board[1][0] == player and self.board[1][1] == player and self.board[1][2] == player:
            return True
        if self.board[2][0] == player and self.board[2][1] == player and self.board[2][2] == player:
            return True
        # diagonal
        if self.board[0][0] == player and self.board[1][1] == player and self.board[2][2] == player:
            return True;
        if self.board[0][2] == player and self.board[1][1] == player and self.board[2][0] == player:
            return True;
        return False;
    def checkdraw(self):
        if self.board[0][0] != 0 and self.board[1][0] != 0 and self.board[2][0] != 0 and self.board[1][1] != 0 and self.board[2][1] != 0 and self.board[0][2] != 0 and self.board[1][2] != 0 and self.board[2][2] != 0:
            if self.checkwin(self.board, 1) == False and self.checkwin(self.board, 2) == False:
                return True
        return False;
    def checkstuff(self):
        if self.checkwin(1):
            print("Player one won")
            self.printmatrix()
            self.answer = input("Play Again? y/n")
            self.reset()
        if self.checkwin(2):
            print("Player two won")
            self.printmatrix()
            self.answer = input("Play Again? y/n")
            self.reset()
        if self.checkdraw():
            print("It was a draw")
            self.printmatrix()
            self.answer = input("Play Again? y/n")
            self.reset()
        if self.answer == "n" or self.answer == "no":
            self.playgame = False
            self.reset()
    def mark(self, x, y, player):
        self.board[y][x] = player
    
    def makemove(self, player):
        clear = True;
        valid = True;
        num = 1
        x = 0
        y = 0
        while clear:
            valid = True;
            print("It is player " + player + "'s turn");
            self.printmatrix();
            xs = input("enter position x:")
            if x != 0 and x != 1 and x != 2:
                valid = False;
            ys = input("enter position y:")
            if y != 0 and y != 1 and y != 2:
                valid = False;
            if valid:
                x = int(xs)
                y = int(ys)
                if self.board[y][x] == 0:
                    num = 1
                    if player == "two":
                        num = 2;
                    self.mark(x, y, num);
                    clear = False;
def main():
    run = TTT()
    print("Welcome to the Tic Tac Toe Program")
    print("Instructions:")
    print("when marking enter position x and then enter y")
    example = [[ "0,0", "1,0", "2,0" ],
               [ "0,1", "1,1", "2,1" ],
               [ "0,2", "1,2", "2,2" ]]
    
    while run.playgame == True:
        run.makemove("one")
        run.checkstuff()
        run.makemove("two")
        run.checkstuff()
        
if __name__ == "__main__": main()



