/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg215886;

/**
 *
 * @author Louis
 */
public class MineTile {
    private boolean isMined = false;
    private int minedNeighbours = 0;
    private boolean isRevealed = false;
    private boolean isMarked = false;
    
    public boolean getMined(){
        return isMined;
    }
    
    public void setMine(){
        this.isMined = true;
    }
 
    public int getMinedNeighbours(){
        return this.minedNeighbours;
    }
    
    public void incrMinedNeighbours(){
        this.minedNeighbours++;
    }
    
    public void setRevealed(boolean reveal){
        this.isRevealed = reveal;
    }
    
    public boolean getRevealed(){
        return this.isRevealed;
    }
    
    public void toggleFlag(){
        this.isMarked = !this.isMarked;
    }
    
    public boolean checkFlag(){
        return this.isMarked;
    }
  
    public String RevealString(){
        if (this.isMarked) {
            return "x";
        }
        else if (!this.isRevealed){
            return "+";
        }
        else {
            return String.valueOf(this.minedNeighbours);
    }
}
    public String RevealAll() {
     if (this.isMined){
         return "*";
     }   
     else{
         return String.valueOf(this.minedNeighbours);
     }
    }
}
