/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author lhjp20
 */
import pkg215886.Minefield;
public class MinefieldTest {
    
    Minefield mine;
    public MinefieldTest() {
    }
    
    @Test
    public void TestCreation() {
         Minefield mine = new Minefield(10,10,20);
         assertEquals(10, mine.getRows());
         assertEquals(10, mine.getColumns());
         assertEquals(20, mine.getMines());
         System.out.println("TestCreationtest the grid is made - before pop");
         mine.populate(mine.getMines());
         System.out.println(mine.toString());
         
    }
   
    @Test
    public void testPopulate(){
        int activeMines = 0;
        Minefield mine = new Minefield(10,10,10);
        mine.populate(10);
        System.out.println(mine.getActiveMines());
        System.out.println(mine.toStringReveal());
        assertEquals(10, mine.getActiveMines());
        
        
    }
    
    @Test
    public void testToString(){
        Minefield testmine = new Minefield(10,10,10);
        testmine.mineTile(5,5);
        String premadeString = ("0000000000\n" +
"0000000000\n" +
"0000000000\n" +
"0000000000\n" +
"0000111000\n" +
"00001*1000\n" +
"0000111000\n" +
"0000000000\n" +
"0000000000\n" +
"0000000000\n" );
        System.out.println("test");
        System.out.println(testmine.toStringReveal());
        assertTrue(premadeString.equals(testmine.toStringReveal()));
        assertEquals(premadeString, testmine.toStringReveal());
    }
    
    
    @Test
    public void testingMark(){
        Minefield m = new Minefield(10,10,10);
        m.mark(5,5);
        m.revealNeighbourTiles(5, 5);
         String premadeString = ("0000000000\n" +
"0000000000\n" +
"0000000000\n" +
"0000000000\n" +
"0000000000\n" +
"00000x0000\n" +
"0000000000\n" +
"0000000000\n" +
"0000000000\n" +
"0000000000\n" );
        System.out.println("Mark test");
        System.out.println(m.toString());
        
        assertTrue(premadeString.equals(m.toString()));
        
    }
    
    @Test 
    public void testStep(){
        Minefield m = new Minefield(3,3,1);
        m.mineTile(0, 1);
        m.step(2, 2);
        String test = ("+++\n" +
"111\n" +
"000\n");
        System.out.println(m.toString());
        System.out.println(m.toStringReveal());
        assertEquals(m.toString(), test);
    }
    @Test
    public void lossTest(){
        Minefield m = new Minefield(10,10,10);
        m.mineTile(5, 5);
        assertFalse(m.step(5, 5));
    }
    
    @Test
    public void allMinesRevealedTestWin(){
        Minefield m = new Minefield(10,10,10);
        m.mineTile(5, 5);
        m.mark(5, 5);
        assertTrue(m.areAllMinesFound());
    }
    
    @Test
    public void allMinesRevealedTestNotWin(){
        Minefield m = new Minefield(10,10,10);
        m.mineTile(5, 5);
        m.mark(6, 6);
        assertFalse(m.areAllMinesFound());
    }
}