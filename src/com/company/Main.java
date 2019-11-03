/*=================================================================
Main Class
Peter Ma
Oct 27 2019
Java JDK 11 LTS
=================================================================
Problem Definition – Write a program that can navigate the mouse
    through a maze
    and find the shortest path through the maze
Input – The starting location (User Input) and the maze (file reading)
Output – The map of how to traverse the maze
Process – Runs the mouse class which finds the shortest path the
    mouse will traverse
=================================================================
List of Identifiers - will be listed in each file.
=================================================================
*/
package com.company;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
public class Main {
    /**main method
     * This is where the main program is executed. All logical process
     * brought together
     * and executed according to the definition of the problem.
     *
     * User input - where mouse starts (rows and col)
     * Output - the solution path with a map for each.
     *
     * List of local variables
     * @param - maze <type Maze>
     * @param - mouse <type Mouse>
     * @param - flag <type boolean>
     *
     * @param - startTime <type long>
     * @param - endTime <type long>
     * @param - solution <type arraylist>
     * @param - exit <type arraylist>
     * @param - t <type int>
     * @param - r <type int>
     * @param - none
     * @return  none
     */
    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Mouse mouse = new Mouse();
        Maze maze = new Maze();
        boolean flag = true;
        System.out.println("WELCOME TO THE GAME!!!");
        System.out.println("Here is your board");
        maze.printMap();
        System.out.println("Where do you wan the mouse to start?");
        while(flag)
        try{
            System.out.println("Type the ROW value");
            int x = Integer.parseInt(br.readLine());
            System.out.println("Type the COLUMN value");
            int y = Integer.parseInt(br.readLine());
            Place start = new Place(x,y);
            if(!maze.isWall(x,y)
                    && !maze.isCheese(x,y)
                    && !maze.isExit(x,y)
                    && maze.isValidLocation(x,y)){
                maze.setStart(start);
                flag = false;
            }
        }catch(Exception e) {
            System.out.println("INVALID POSITION");
            continue;
        }
        long startTime = System.currentTimeMillis();
        List<Place> solution = mouse.solutionCheese(maze);
        char[][] map = maze.getMap();
        int t, r;
        for(int i=0;i<solution.size();i++){
            t= solution.get(i).getX();
            r = solution.get(i).getY();
            map[t][r] = ' ';
        }
        System.out.println("This is the path to the Cheese, follow the empty spaces");
        maze.printMap();
        maze.resetMap();
        maze.resetExplore();
        long endTime = System.currentTimeMillis();
        System.out.println("Execution time " + (double)(endTime - startTime)/1000 + " seconds");
        startTime = System.currentTimeMillis();
        List <Place> exit = mouse.solutionExit(maze);
        System.out.println();
        System.out.println("This is the exit path, follow the empty spaces");
        for(int i=0;i<exit.size();i++){
            t= exit.get(i).getX();
            r = exit.get(i).getY();
            map[t][r] = ' ';
        }
        maze.printMap();
        maze.resetMap();
        endTime = System.currentTimeMillis();
        System.out.println("Execution time " + (double)(endTime - startTime)/1000 + " seconds");
    }// end of main method
}// end of main class
