/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameoflif;

import java.util.Scanner;

/**
 *
 * @author 348983139
 */
public class GameOfLifeEngine {
    /*Pseudo code for engine is here
    Create two 2D array: one for lastTurn and one for current turn.
    1)The one for lastTurn is the one that is being compared; it is not changing all the time
    but only at the end of the turn.
    The one for thisTurn is the one that is being changed based on the lastTurn.
    2)Creates a method for keeping track whether the cells are dead or alive to 
    help deciding the conditions the cells need to meet to survive and checking the number of neighbors.
    3)Creates a method to count the number of neighbors a cell has. This is based on
    the number of occupied or alive cells surrounding the current cell.
    4)Creates a method to decide if the cell survives to the next turn.
    The cells that are alive have a set of conditions to meet to determine whether they live
    while the cells that are dead have one condition to meet to determine whether they reborn
    5)Creates a method to update the all the values of thisTurn to lastTurn
    This is because lastTurn's values only changes at the end of the turn, so at the
    start of the new turn, it needs to be in sync with the values from thisTurn, which changes
    and helps to compare.    
    */
    //Set the size of the board
    static boolean[][] thisTurn = new boolean[10][10];
    static boolean[][] lastTurn = new boolean[10][10];

    /**
     * This method counts the number of neighbor cells that are alive
     *
     * @param row The row number of the cell
     * @param col The column number of the cell
     * @return Tells how many neighbor cells are occupied
     */
    public static int checkNeighbors(int row, int col) {
        int neighbor = 0;
        //Starts at top left side of the cell and goes to the top right side 
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                //Catch out of bounds error when reading negative indexes, which are the corners, top and bottom row
                //And the left and right side column
                try {
                    if (isOccupied(row + i, col + j) == true) {
                        neighbor++;
                    }

                } catch (ArrayIndexOutOfBoundsException e) {

                }

            }

        }
        //Subtract one because the counter adds the cell itself
        if (lastTurn[row][col] == true) {
            neighbor--;
        }

        return neighbor;
    }

    /**
     * Tells if the cell is occupied
     *
     * @param row Row number of the cell
     * @param col Column number of the cell
     * @return true if it is occupied, false if empty
     */
    public static boolean isOccupied(int row, int col) {
        if (lastTurn[row][col] == true) {
            return true;
        }
        return false;
    }

    /**
     * This method will determine which cells survive, dies or reborn
     *
     * @param row reads the row number of the cell
     * @param col reads the column number of the cell
     */
    public static void survives(int row, int col) {
        //Calls the checkNeighbors method to get the number of neighbors
        //the cell has
        int neighbors = checkNeighbors(row, col);
        //If the cell that is being checked is alive
        //Check the following conditions and decides to make it unoccupied
        if (isOccupied(row, col) == true) {
            if (neighbors == 2 || neighbors == 3) {
                thisTurn[row][col] = true;
            } else if (neighbors >= 4) {
                thisTurn[row][col] = false;
            } else if (neighbors == 1 || neighbors == 0) {
                thisTurn[row][col] = false;
            }
        } 
        //If the cell being checked is dead
        //Check the following condition and decides to make it reborn
        else if (isOccupied(row, col) == false) {
            if (neighbors == 3) {
                thisTurn[row][col] = true;
            }

        }

    }

    /**
     * This method will update the grid from last turn to become the grid from
     * this turn Since values of lastTurn is not being changed while comparing
     * as it is being used to compare so the values of thisTurn is being changed
     * based on the values of last turn
     *
     * @param row the row number of the cell that is checked
     * @param col the column number of the cell that is checked
     */
    public static void updateBoard(int row, int col) {
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                lastTurn[i][j] = thisTurn[i][j];
            }
        }
    }
}
