/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg215886;

/**
 *
 * @author lhjp20
 */
public class Minefield {

    
    MineTile[][] mineField;
    private int noOfMines;
    private int noOfRows;
    private int noOfColumns;
    private int noActiveMines;
    private int currentMines;
    
    

    public Minefield(int rows, int columns, int mines) {
        
        this.noOfRows = rows;
        this.noOfColumns = columns;
        this.noOfMines = mines;
        mineField = new MineTile[rows][columns];
        
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < columns; c++){
                mineField[r][c] = new MineTile();
            }
        }
    }
    
    public int getMines() {
    return noOfMines; //returns max number of mines
    }
    public int getRows() {
        return noOfRows; // returns number of rows in grid
    }
    
    public int getColumns() {
        return noOfColumns; // returns number of columns in grid
    }
    
    public int getActiveMines(){
        return noActiveMines;
    }
    
    
    
    
    public boolean mineTile(int rowToBeMined, int colToBeMined){
        if(mineField[rowToBeMined][colToBeMined].getMined()){
            return false;
        }
        
        mineField[rowToBeMined][colToBeMined].setMine();
       for (int r = rowToBeMined - 1; r <= rowToBeMined + 1; r++) {
            if (r < 0 || r > noOfRows - 1) {
                continue;
            }
            for (int c = colToBeMined - 1; c <= colToBeMined + 1; c++) {
                if (c < 0 || c > noOfColumns - 1) {
                    continue;
                }
                if (r == rowToBeMined && c == colToBeMined) {
                    continue;
                }
                mineField[r][c].incrMinedNeighbours();
            }
        }
        return true;
    }
    
    public MineTile getTile(int row, int col){
        return mineField[row][col];
    }
    
    public void mark(int row, int col){
        this.mineField[row][col].toggleFlag();
        }
  
    
    
    public boolean step(int row, int col) {
        if (row >= 0 && row < getRows() && col >= 0 && col < getColumns()) {
		if (mineField[row][col].getMined()) {
				return false;
		} else {
		    mineField[row][col].setRevealed(true);
		        if (mineField[row][col].getMinedNeighbours() == 0) {
				revealNeighbourTiles(row, col);
			}
		}

	}
	return true;

	}
    
    public void revealNeighbourTiles(int row, int col) {
        for (int r = row - 1; r <= row + 1; r++) {
	    for (int c = col - 1; c <= col + 1; c++) 
		if (r >= 0 && c >= 0 && r < mineField.length && c < mineField[r].length){
		    if (!(r == row && c == col)){
			if (!mineField[r][c].getRevealed()) {
			    mineField[r][c].setRevealed(true); //sets reveal to true
				if (mineField[r][c].getMinedNeighbours() == 0) {
				revealNeighbourTiles(r, c); //calls upon method again to check tiles around
				}
			    }
		        }
                }
	}
        }
    

	
    //populate method from method on canvas
    public void populate(int Mines){
        while (noActiveMines <this.noOfMines){
            int row = (int) (Math.random() * this.noOfRows);
            int col = (int) (Math.random() * this.noOfColumns);
            if (!mineField[row][col].getMined() && !(row == 0 && col == 0)) {
            mineTile(row, col);
            noActiveMines++;
        }
        }
    }

    @Override
    public String toString() {
        String print="";
        for (int row = 0; row<noOfRows; row++){
            for (int col = 0; col<noOfColumns; col++){
                print = print + mineField[row][col].RevealString();
            }
            print = (print + "\n");
        }
        return print;
    }
    
   
    public String toStringReveal() {
        String print="";
        for (int row = 0; row<noOfRows; row++){
            for (int col = 0; col<noOfColumns; col++){
                print = print + mineField[row][col].RevealAll();
            }
            print = (print + "\n");
        }
        return print;
    }
    
    public boolean areAllMinesFound(){
        for (int r = 0; r < this.noOfRows; r++) {
	    for (int c = 0; c < this.noOfColumns; c++) {
		if ((mineField[r][c].getMined() && !mineField[r][c].checkFlag()) || (!mineField[r][c].getMined() && mineField[r][c].checkFlag())) {
		    return false;
		}
	     }
	}
        return true;
    }
       
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
