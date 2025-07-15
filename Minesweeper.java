/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg215886;

public class Minesweeper
        
{
    Parser parser = new Parser();
    Minefield game = new Minefield(8,8,10);
    private boolean gameFinished = true;
    public Minesweeper() {
        game.populate(game.getMines());
    }
    
    private void execute(Command c) {
        
        switch(c.getCommand()) {
            case NEW:
                if (gameFinished){
                    int mines = 10;
                    game = new Minefield(c.getRow(), c.getColumn(), mines);
                    game.populate(mines);
                    gameFinished = !gameFinished;
                    System.out.println("New game started, grid is " + c.getRow() +  " by " + c.getColumn() + " and has " + mines + "mines.");
                    System.out.println(game.toString());
                }
            break;
            case STEP:
                if (!gameFinished) {
                    System.out.println(c);
                    game.step(c.getRow(), c.getColumn()); 
                    if(game.mineField[c.getRow()][c.getColumn()].getMined())
                    {
                       System.out.println("You Lose");
                       System.out.println(game.toStringReveal());
                       gameFinished = true;    
                    } else {
                    game.revealNeighbourTiles(c.getRow(), c.getColumn());
                    System.out.println(game.toString());
                    }
                }
            break;
            case MARK: 
                if(!gameFinished){
                    System.out.println(c);
                    game.mark(c.getRow(),c.getColumn());
                    System.out.println(game.toString());
                }
            break;
            case QUIT: 
                System.out.println(c.getMsg());
                gameFinished = true;
                break;
            default:
                System.out.println(c);
                System.out.println(game.toString());
            }
            printPrompt(c.getMsg());
        }
    
    private void commandLine() {
        printPrompt("New Game");
        Command c = parser.getCommand();
        while(c.getCommand() != CommandWord.QUIT) {
            if(game.areAllMinesFound()){
                System.out.println("Winner!");
                gameFinished = true;
                
            }
            execute(c);
            c = parser.getCommand();
        }
    }
    private void printPrompt(String msg) {
        System.out.println(msg);
        System.out.print(">");
    }
public static void main(String args[]) {
    Minesweeper ct = new Minesweeper();
    ct.commandLine();
}
}
