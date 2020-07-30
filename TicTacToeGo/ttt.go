package main

import (
    "bufio"
    "fmt"
    "os"
)

type Game struct{
  playing bool
  board [3][3]int
  players map[int]string
}
func NewGame() *Game{
  var empty, ids = [3][3]int{
		{0, 0, 0},
		{0, 0, 0},
		{0, 0, 0},
  }, map[int]string{
    0:"_",
    1:"X",
    2:"O",
	}
  game := Game{playing:true, board:empty, players:ids }
  return &game
}
func (g Game) Display(){
  for i := 0; i < len(g.board); i++ {
    row := g.board[i]
    markedrow := make([]string, 3)
    for i, v := range row {
      markedrow[i] = g.players[v]
    }
    fmt.Println(markedrow)
  }
}
func (g *Game) Reset(){
  for y, section := range g.board {
    for x := range section {
      g.board[y][x] = 0
    }
  }
}
func (g *Game) Mark(x, y, player int) {
  g.board[y][x] = player
}
func (g *Game) MakeMove(player int) {
  var (
    untilMoveMade, validMove, clearMove bool
    x, y int
  )
  for !untilMoveMade {
   fmt.Println("It is player", player, "'s turn")
    fmt.Println("Enter X Position")
    fmt.Scan(&x)
    fmt.Println("Enter Y Position")
    fmt.Scan(&y)
    if x>=0 && x <= 2 && y>=0 && y <= 2 { validMove = true }
    if validMove && g.board[y][x] == 0 { clearMove = true }
    if clearMove {
      g.Mark(x, y, player)
      g.Display()
      untilMoveMade = true
    }
  }
}
func (g Game) CheckWin(player int) bool {
  pp := player
  // col 
  if g.board[0][0] == pp && g.board[1][0] == pp && g.board[2][0] == pp { return true; }
  if g.board[0][1] == pp && g.board[1][1] == pp && g.board[2][1] == pp { return true; }
  if g.board[0][2] == pp && g.board[1][2] == pp && g.board[2][2] == pp { return true; }
  // row
  if g.board[0][0] == pp && g.board[0][1] == pp && g.board[0][2] == pp { return true; }
  if g.board[1][0] == pp && g.board[1][1] == pp && g.board[1][2] == pp { return true; }
  if g.board[2][0] == pp && g.board[2][1] == pp && g.board[2][2] == pp { return true; }
  //diag
  if g.board[0][0] == pp && g.board[1][1] == pp && g.board[2][2] == pp { return true; }
  if g.board[0][2] == pp && g.board[1][1] == pp && g.board[2][0] == pp { return true; }
  return false;
}
func (g Game) CheckFull() bool {
  for y, section := range g.board {
    for x := range section {
      if g.board[y][x] == 0 { return false }
    }
  }
  return true
}
func (g Game) CheckDraw() bool {
  var full, nowin_1, nowin_2 bool
  full = g.CheckFull()
  nowin_1 = !g.CheckWin(1)
  nowin_2 = !g.CheckWin(2)
  return full && nowin_1 && nowin_2
}
func (g *Game) PlayAgain() {
  fmt.Println("Play again? y/n")
  scanner := bufio.NewScanner(os.Stdin)
  scanner.Scan()
  response := scanner.Text()
  if response == "no" || response == "n" {
    g.playing = false
  }
  g.Reset()
}
func (g *Game) CheckGame() {
  var (
    outcome string
    game_over bool
  )
  if g.CheckWin(1) { outcome="Player1 won"; game_over=true}
  if g.CheckWin(2) { outcome="Player2 won";  game_over=true}
  if g.CheckDraw() { outcome="It was a draw"; game_over=true}
  if (game_over) {
    fmt.Println(outcome)
    g.PlayAgain()
  }
}

func main(){
  fmt.Println("Welcome to the Tic Tac Toe Program");
  fmt.Println("--- Instructions ---")
  fmt.Println("when marking enter position x and then enter y")
  example := [][]string{
    { "0,0", "1,0", "2,0" },
    { "0,1", "1,1", "2,1" },
    { "0,2", "1,2", "2,2" },
  }
  for i := 0; i < len(example); i++ {
    fmt.Println((example)[i])
  }
  fmt.Println("--- Game ---")
  run := NewGame()
  for {
    if run.playing {
      run.MakeMove(1)
      run.CheckGame()
    } else {
      fmt.Println("Thanks for playing")
      os.Exit(0)
    }
    if run.playing {
      run.MakeMove(2)
      run.CheckGame()
    } else {
      fmt.Println("Thanks for playing")
      os.Exit(0)
    }
  }
}

