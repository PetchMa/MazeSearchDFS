/*=================================================================
Mouse Class
Peter Ma
Oct 27 2019
Java JDK 11 LTS
=================================================================
Problem Definition – Write a program that can navigate the mouse through a maze
    and find the shortest path through the maze
Input – The starting location (User Input) and the maze (file reading)
Output – The map of how to traverse the maze
Process – Runs a DFS and search every path from starting node and numbers the steps taken from the start
    node.
=================================================================
List of Identifiers - will be listed in each file.
=================================================================
*/
package com.company;

import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

public class Mouse {
    private int startX;
    private int startY;
    /**Mouse method
     * This is the constructor method that initializes the mouse
     * object
     *
     * List of local variables
     * maze - object maze is created within the class <type Maze>
     * start - object for the initial position of mouse <type Place>
     * end - object that holds the end point of the mouse <type Place>
     *
     * @param - none
     * @return  none
     */
    public Mouse() throws IOException {
        Maze maze = new Maze();
        Place start = new Place(0, 0);
        start = maze.getStart();
        Place end = new Place(0, 0);
        end = maze.getExit();
        this.startX = start.getX();
        this.startY = start.getY();
    }// end of Mouse() method
    /**solutionCheese method
     * Functional method where the recursive function is executed. This finds the path to the cheese
     * and saves the path into an array list. It does so by first exploring all pathes
     * (brainCheese method) and backtracks the path (printCorrectPath method).
     * Then the proper path is returned as a list.
     *
     * List of local variables
     * path - object maze is created within the class <type ArrayList>
     *
     * @param - Maze <type Maze>
     * @return  path <type arraylist>
     */
    public List<Place> solutionCheese(Maze maze) {
        brainCheese(maze, maze.getStart().getX(), maze.getStart().getY(), 0);
        List<Place> path = new ArrayList<>();
        printCorrectPath(maze, maze.getStart().getX(), maze.getStart().getY(),0, path );
        return path;
    }
    /**solutionExit method
     * Functional method where the recursive function is executed. This finds the path to the Exist
     * from the cheese and saves the path into an array list. It does so by first exploring all pathes
     * (brainExit method) and backtracks the path (printCorrectPathExit method).
     * Then the proper path is returned as a list.
     *
     * List of local variables
     * ExitPath - object maze is created within the class <type ArrayList>
     *
     * @param - Maze <type Maze>
     * @return  ExitPath <type arraylist>
     */
    public List<Place> solutionExit(Maze maze) {
        brainExit(maze, maze.getCheese().getX(), maze.getCheese().getY(), 0);
        List<Place> ExitPath = new ArrayList<>();
        printCorrectPathExit(maze, maze.getCheese().getX(), maze.getCheese().getY(),0, ExitPath );
        return ExitPath;
    }
    /**brainCheese method
     * Procedural method where the recursive logic helps the mouse traverse all possible pathes
     * within the maze starting from . It does so by checking every direction for a path recursively. It also labels
     * each position with an integer
     *
     * List of local variables
     * none
     *
     * @param - Maze <type Maze>
     * @param - row <type int>
     * @param - col <type int>
     * @param - num <type int>
     * @return  none
     */
    private void brainCheese(Maze maze, int row, int col, int num) {
        if (maze.isValidLocation(row, col)) {
            if (maze.getRoute(row, col) == 0 ||
                    maze.getRoute(row, col) > num) {
                num += 1;
                maze.setRoute(row, col, num);
                brainCheese(maze, row + 1, col, num);
                brainCheese(maze, row - 1, col, num);
                brainCheese(maze, row, col + 1, num);
                brainCheese(maze, row, col - 1, num);
            }
        }
    }// end of brainCheese method
    /**brainExit method
     * Procedural method where the recursive logic helps the mouse traverse all possible path
     * within the maze. It does so by checking every direction for a path recursively. It also labels
     * each position with an integer
     *
     * List of local variables
     * none
     *
     * @param - Maze <type Maze>
     * @param - row <type int>
     * @param - col <type int>
     * @param - num <type int>
     * @return  none
     */
    private void brainExit(Maze maze, int row, int col, int num) {
        if (maze.isValidLocation(row, col)) {
            if (maze.getExitRoute(row, col) == 0 ||
                    maze.getExitRoute(row, col) > num) {
                num += 1;
                maze.setExitRoute(row, col, num);
                brainExit(maze, row + 1, col, num);
                brainExit(maze, row - 1, col, num);
                brainExit(maze, row, col + 1, num);
                brainExit(maze, row, col - 1, num);
            }
        }
    }// end of brainExit method
    /**printCorrectPath method
     * functional method that recursively traverses the optimal path indicated by the label path.
     * It takes steps towards the cheese by looking at the labeled maze created by brainCheese Method
     *
     * List of local variables
     * none
     *
     * @param - Maze <type Maze>
     * @param - row <type int>
     * @param - col <type int>
     * @param - num <type int>
     * @param - path <type ArrayList>
     * @return  none
     */
    private boolean printCorrectPath(Maze maze, int row, int col, int num, List<Place> path){
        if (!maze.isValidLocation(row, col)
                || maze.isWall(row, col)
                || maze.isExplored(row, col)||
                maze.getRoute(row, col)<num) {
            return false;
        }
        path.add(new Place(row, col));
        maze.setVisited(row, col, true);
        if (maze.isCheese(row, col)) {
            return true;
        }
        if (printCorrectPath(maze, row + 1, col,num+1, path)) {
            return true;
        }
        if (printCorrectPath(maze, row - 1, col,num+1, path)) {
            return true;
        }
        if (printCorrectPath(maze, row, col + 1,num+1, path)) {
            return true;
        }
        if (printCorrectPath(maze, row, col - 1,num+1, path)) {
            return true;
        }
        path.remove(path.size() - 1);
        return false;
    }// end of printCorrectPath method
    /**printCorrectPathExit method
     * functional method that recursively traverses the optimal path indicated by the label path.
     * It starts at the cheese and takes steps towards the exit by looking at the labeled maze
     * created by brainExit Method
     *
     * List of local variables
     * none
     *
     * @param - Maze <type Maze>
     * @param - row <type int>
     * @param - col <type int>
     * @param - num <type int>
     * @param - ExitPath <type ArrayList>
     * @return  none
     */
    private boolean printCorrectPathExit(Maze maze, int row, int col, int num, List<Place> ExitPath){
        if (!maze.isValidLocation(row, col)
                || maze.isWall(row, col)
                || maze.isExplored(row, col)||
                maze.getExitRoute(row, col)<num) {
            return false;
        }
        ExitPath.add(new Place(row, col));
        maze.setVisited(row, col, true);
        if (maze.isExit(row, col)) {
            return true;
        }
        if (printCorrectPathExit(maze, row + 1, col,num+1, ExitPath)) {
            return true;
        }
        if (printCorrectPathExit(maze, row - 1, col,num+1, ExitPath)) {
            return true;
        }
        if (printCorrectPathExit(maze, row, col + 1,num+1, ExitPath)) {
            return true;
        }
        if (printCorrectPathExit(maze, row, col - 1,num+1, ExitPath)) {
            return true;
        }
        ExitPath.remove(ExitPath.size() - 1);
        return false;
    }//end of printCorrectPathExit
}// end of Mouse class.

